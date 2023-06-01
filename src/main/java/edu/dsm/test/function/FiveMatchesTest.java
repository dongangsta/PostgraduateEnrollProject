package edu.dsm.test.function;

import java.util.Arrays;

/**
 * ClassName: FiveMatchesTest Description: Author: dong Date: 2023/6/1 14:21 History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class FiveMatchesTest {
    public static void main(String[] args) {
        double[][] fiveMatches = new double[9][3];
        fiveMatches[0] = new double[] { 3.5, 3.7, 1.95 };
        fiveMatches[1] = new double[] { 1.67, 3.4, 4.5 };
        fiveMatches[2] = new double[] { 3.1, 3.25, 2.1 };
        fiveMatches[3] = new double[] { 1.73, 3.4, 4.2 };
        fiveMatches[4] = new double[] { 1.5, 3.75, 6 };
        fiveMatches[5] = new double[] { 1.57, 3.75, 5 };
//        fiveMatches[6] = new double[] { 2, 3.25, 3.9 };
//        fiveMatches[7] = new double[] { 1.6, 4.2, 5 };
//        fiveMatches[8] = new double[] { 1.8, 3.75, 4 };
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
}
