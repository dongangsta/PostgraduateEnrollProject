package edu.dsm.util;

import edu.dsm.entity.College;
import edu.dsm.entity.CollegeForRecommend;
import edu.dsm.entity.MyLike;
import edu.dsm.entity.User;
import edu.dsm.service.CollegeService;
import edu.dsm.service.MyLikeService;
import edu.dsm.service.SchoolService;
import edu.dsm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class Recommend {
    /**
     * 在给定username的情况下，计算其他用户和它的距离并排序
     */
    private static CollegeService collegeService;
    private static UserService userService;
    private static MyLikeService myLikeService;
    private static SchoolService schoolService;
    @Autowired
    public void setCollegeService (CollegeService collegeService){
        Recommend.collegeService = collegeService;
    }
    @Autowired
    public void setUserService (UserService userService){
        Recommend.userService = userService;
    }
    @Autowired
    public void setMyLikeService (MyLikeService myLikeService){
        Recommend.myLikeService = myLikeService;
    }
    @Autowired
    public void setSchoolService (SchoolService schoolService){
        Recommend.schoolService = schoolService;
    }
    private TreeMap<Double, String> computeNearestNeighbor(String username) {
        List<User> users = userService.getAll();
        TreeMap<Double, String> distances = new TreeMap<>();
        User u1 = new User();
        for (User user:users) {
            if (username.equals(user.getUserName())) {
                u1 = user;
            }
        }
        for (int i = 0; i < users.size(); i++) {
            User u2 = users.get(i);
            if (!u2.getUserName().equals(username)) {
                List<College> collegeList1 =new ArrayList<>();
                List<MyLike> myLikeList1 = myLikeService.selectMyLike(u1.getUserId());
                for (MyLike myLike:myLikeList1){
                    College college = collegeService.selectById(myLike.getCollegeId());
                    collegeList1.add(college);
                }
                List<College> collegeList2 =new ArrayList<>();
                List<MyLike> myLikeList2 = myLikeService.selectMyLike(u2.getUserId());
                for (MyLike myLike:myLikeList2){
                    College college = collegeService.selectById(myLike.getCollegeId());
                    collegeList2.add(college);
                }
                double distance = pearson_dis(collegeList1, collegeList2,u1,u2);
                distances.put(distance, u2.getUserName());
            }
        }
        System.out.println("该用户与其他用户的皮尔森相关系数 -> " + distances);
        return distances;
    }


//    /**
//     * 计算2个用户间的pearson距离
//     * 选择公式四进行计算
//     * @param rating1
//     * @param rating2
//     * @return
//     */
    private double pearson_dis(List<College> collegeList1, List<College> collegeList2,User u1,User u2) {
        int n = 0;
        for (College college:collegeList1){
            if (collegeList2.contains(college)) n++;
        }
        List<CollegeForRecommend> collegeForRecommendList1 = new ArrayList<>();
        List<CollegeForRecommend> collegeForRecommendList2 = new ArrayList<>();
        for (int i = 0; i < n ;i++){
            College college = collegeList1.get(i);
            collegeForRecommendList1.add(new CollegeForRecommend(u1.getPredict(),college.getCollegeName()));
        }
        for (int i = 0; i < n ;i++){
            College college = collegeList1.get(i);
            collegeForRecommendList2.add(new CollegeForRecommend(u2.getPredict(),college.getCollegeName()));
        }
        List<Integer> collegeList1ScoreCollect = collegeForRecommendList1.stream().map(A -> A.getMyScore()).collect(Collectors.toList());
        List<Integer> collegeList2ScoreCollect = collegeForRecommendList2.stream().map(A -> A.getMyScore()).collect(Collectors.toList());

        double Ex= collegeList1ScoreCollect.stream().mapToDouble(x->x).sum();
        double Ey= collegeList2ScoreCollect.stream().mapToDouble(y->y).sum();
        double Ex2=collegeList1ScoreCollect.stream().mapToDouble(x->Math.pow(x,2)).sum();
        double Ey2=collegeList2ScoreCollect.stream().mapToDouble(y->Math.pow(y,2)).sum();
        double Exy= IntStream.range(0,n).mapToDouble(i->collegeList1ScoreCollect.get(i)*collegeList2ScoreCollect.get(i)).sum();
        double numerator=Exy-Ex*Ey/n;
        double denominator=Math.sqrt((Ex2-Math.pow(Ex,2)/n)*(Ey2-Math.pow(Ey,2)/n));
        if (denominator==0) return 0.0;
        return numerator/denominator;
    }

    public List<College> recommend(String username) {
        //找到最近邻
        List<User> users = userService.getAll();
        TreeMap<Double, String> distances = computeNearestNeighbor(username);
        String nearest = distances.firstEntry().getValue();
        System.out.println("最近邻 -> " + nearest);

        //找到最近邻标记了，但是我没标记的院校，计算推荐
        User neighborRatings = new User();
        for (User user:users) {
            if (nearest.equals(user.getUserName())) {
                neighborRatings = user;
            }
        }
        System.out.println("最近邻 -> " + neighborRatings);

        User userRatings = new User();
        for (User user:users) {
            if (username.equals(user.getUserName())) {
                userRatings = user;
            }
        }
        System.out.println("我 -> " + userRatings);

        List<College> myCollegeList =new ArrayList<>();
        List<MyLike> myLikeList = myLikeService.selectMyLike(userRatings.getUserId());
        for (MyLike myLike:myLikeList){
            College college = collegeService.selectById(myLike.getCollegeId());
            myCollegeList.add(college);
        }

        List<College> hisCollegeList =new ArrayList<>();
        List<MyLike> hisLikeList = myLikeService.selectMyLike(neighborRatings.getUserId());
        for (MyLike myLike:hisLikeList){
            College college = collegeService.selectById(myLike.getCollegeId());
            hisCollegeList.add(college);
        }

        //根据自己和邻居的电影计算推荐的院校
        List<College> recommendationColleges = new ArrayList<>();
        for (College college : hisCollegeList) {
            if (!myCollegeList.contains(college)) {
                recommendationColleges.add(college);
            }
        }
        return recommendationColleges;
    }

}


