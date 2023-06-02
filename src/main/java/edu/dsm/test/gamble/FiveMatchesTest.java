package edu.dsm.test.gamble;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: FiveMatchesTest Description: Author: dong Date: 2023/6/1 14:21 History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class FiveMatchesTest {
    public static void main(String[] args) {
        double[][] fiveMatches = new double[10][3];
        fiveMatches[0] = new double[] { 1.8, 3.75, 4 };
        fiveMatches[1] = new double[] { 2.15, 3.3, 3 };
        fiveMatches[2] = new double[] { 3.8, 3.7, 1.85 };
//        fiveMatches[3] = new double[] { 1.76, 3.64, 4.12 };
//        fiveMatches[4] = new double[] { 1.51, 4.2, 5.32 };
//        fiveMatches[5] = new double[] { 1.5, 3.96, 5.97 };
//        fiveMatches[6] = new double[] { 4.04, 3.31, 1.89 };
//        fiveMatches[7] = new double[] { 4, 3.38, 1.88 };
//        fiveMatches[8] = new double[] { 1.91, 3.46, 3.78 };
//        fiveMatches[9] = new double[] { , 3.58, 1.73 };
        for (double[] d:fiveMatches) {
            double[] pro = oddsToProbabilities(d);
//            System.out.println(Arrays.toString(pro));
            double[] aPro = getAsianHandicapOdds(pro[0],pro[1], pro[2], d[0] <= d[2]);
            // 过关概率换算
            System.out.println(aPro[0] + " " + aPro[1]);
        }
    }


    /**
     * 将赔率转换为对应胜、平、负的概率
     *
     * @param odds 赔率数组
     * @return 对应的胜、平、负的概率数组
     */
    public static double[] oddsToProbabilities(double[] odds) {
        double sumInverseOdds = Arrays.stream(odds).map(o -> 1 / o).sum();

        double[] probabilities = new double[odds.length];
        for (int i = 0; i < odds.length; i++) {
            probabilities[i] = 1 / (odds[i] * sumInverseOdds);
        }

        return probabilities;
    }

    /**
     * 计算主队让半球和主队受半球两种情况下的胜负概率
     * @param winOdds 胜的概率
     * @param drawOdds 平的概率
     * @param loseOdds 负的概率
     * @param isHome 是否为主队让半球，true 表示主队让半球，false 表示主队受半球
     * @return 主队让半球或受半球的胜负概率
     */
    public static  double[] getAsianHandicapOdds(double winOdds, double drawOdds, double loseOdds, boolean isHome) {
        // 计算亚洲盘口下的胜负概率
        double aWinOdds = isHome ? winOdds : winOdds+drawOdds;
        double aLoseOdds = isHome ? drawOdds + loseOdds : loseOdds;
        // 计算主队让或受半球的胜负概率
        // 返回主队让或受半球的胜负概率
        return new double[] {aWinOdds,aLoseOdds};
    }

    @Test
    public void ouPeiHuoQu(){
        try {
            String path = "https://trade.500.com/bjdcsf/";
            Document document = Jsoup.connect(path).timeout(50000).get();
            Elements tables = document.select("table.bet_table_oneline");
            for (Element table : tables) {
                Elements textInElements = table.getElementsByTag("tr");
                for (Element element : textInElements) {
                    String gameType = element.getElementsByClass("event").get(0).text();
                    String league = element.getElementsByClass("league").get(0).text();
                    String title = element.getElementsByClass("match_time").get(0).attr("title");
                    LocalDateTime time = LocalDateTime.parse(title.substring(5), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    Elements oupeiElements = element.getElementsByClass("oupei").get(0).select("span[class^='op-']");
                    List<String> oupeiList = new ArrayList<>();
                    for (Element oupeiElement : oupeiElements) {
                        oupeiList.add(oupeiElement.text());
                    }
                    // 定义日期时间格式
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    System.out.println(gameType + " " + league + " " + time.format(formatter) + " " + oupeiList);
                    if (gameType.equals("足球")) {
                        double[] doubleArray = new double[oupeiList.size()];
                        for (int i = 0; i < oupeiList.size(); i++) {
                            doubleArray[i] = Double.parseDouble(oupeiList.get(i));
                        }
                        double[] pro = oddsToProbabilities(doubleArray);
                        System.out.println("换算欧洲概率" + Arrays.toString(pro));
                        double[] aPro = getAsianHandicapOdds(pro[0], pro[1], pro[2], doubleArray[0] <= doubleArray[2]);
                        // 过关概率换算
                        System.out.println(aPro[0] + " " + aPro[1]);
                    }
                }
            }
        }
        catch (IOException e){
            System.out.println("IO读写异常");
        }
    }
}
