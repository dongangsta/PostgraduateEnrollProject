package edu.dsm.test.function;

import java.util.Arrays;

/**
 * ClassName: FiveMatchesTest Description: Author: dong Date: 2023/6/1 14:21 History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class FiveMatchesTest {
    public static void main(String[] args) {
        double[][] fiveMatches = new double[10][3];
        fiveMatches[0] = new double[] { 2.52, 3.3, 2.63 };
        fiveMatches[1] = new double[] { 1.83, 3.47, 4.08 };
        fiveMatches[2] = new double[] { 1.52, 4.25, 5.41 };
        fiveMatches[3] = new double[] { 1.91, 3.33, 3.79 };
        fiveMatches[4] = new double[] { 3.46, 3.3, 2.02};
        fiveMatches[5] = new double[] { 1.8, 3.47, 4.17 };
        fiveMatches[6] = new double[] { 4.04, 3.31, 1.89 };
        fiveMatches[7] = new double[] { 4, 3.38, 1.88 };
        fiveMatches[8] = new double[] { 1.91, 3.46, 3.78 };
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
}
