package edu.dsm.test.gamble;

import edu.dsm.entity.po.Soccer;
import edu.dsm.service.SoccerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: FiveMatchesTest Description: Author: dong Date: 2023/6/1 14:21 History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
//@SpringBootTest()
//@RunWith(SpringRunner.class)
public class FiveMatchesTest {

    private final String SOCCER = "足球";

    @Resource
    private SoccerService soccerService;
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
                    String host = element.select(".odds_item:first-child .item_left").text().trim();
                    String guest = element.select(".odds_item:last-child").text().replaceAll("(^|(?<=\\s))[\\d.]+(?=\\s|\\[|$)", "").trim();
                    String league = element.getElementsByClass("league").get(0).text();
                    String matchTime = element.getElementsByClass("match_time").get(0).attr("title");
                    LocalDateTime time = LocalDateTime.parse(matchTime.substring(5), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    Elements oupeiElements = element.getElementsByClass("oupei").get(0).select("span[class^='op-']");
                    List<String> oupeiList = new ArrayList<>();
                    for (Element oupeiElement : oupeiElements) {
                        oupeiList.add(oupeiElement.text());
                    }
                    // 定义日期时间格式
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    System.out.println(gameType + " " + league + " " + host + " " + guest + " "+ time.format(formatter) + " " + oupeiList);
                    if (gameType.equals(SOCCER)) {
                        boolean nanFlag = false;
                        double[] doubleArray = new double[oupeiList.size()];
                        for (int i = 0; i < oupeiList.size(); i++) {
                            if (!oupeiList.get(i).equals("-")){
                                doubleArray[i] = Double.parseDouble(oupeiList.get(i));}else {
                                nanFlag = true;
                            }
                        }
                        double[] pro = oddsToProbabilities(doubleArray);
                        System.out.println("换算欧洲概率 " + Arrays.toString(pro));
                        double[] aPro = getAsianHandicapOdds(pro[0], pro[1], pro[2], doubleArray[0] <= doubleArray[2]);
                        // 过关概率换算
                        System.out.println("换算过关概率 " +aPro[0] + " " + aPro[1]);
                        if (!nanFlag) {
                            LocalDateTime now = LocalDateTime.now();
                            LocalDateTime todayTenAM = LocalDateTime.of(now.toLocalDate(), LocalTime.of(10, 0));
                            LocalDateTime tomorrowTenAM = todayTenAM.plusDays(1);

                            if (time.isAfter(todayTenAM) && time.isBefore(tomorrowTenAM)) {
                                Soccer soccer = new Soccer(gameType, time, league, host, guest,
                                        BigDecimal.valueOf(doubleArray[0]), BigDecimal.valueOf(doubleArray[1]),
                                        BigDecimal.valueOf(doubleArray[2]), BigDecimal.valueOf(aPro[0]), BigDecimal.valueOf(aPro[1]));
                                soccerService.addSoccer(soccer);
                            }
                        }
                    }
                }
            }
        }
        catch (IOException e){
            System.out.println("IO读写异常");
        }
    }

    public static String convertResult(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                sb.append("负");
            } else if (str.charAt(i) == '0') {
                sb.append("胜");
            }
        }
        return sb.toString();
    }
}
