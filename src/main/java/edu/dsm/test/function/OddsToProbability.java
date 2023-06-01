package edu.dsm.test.function;

import java.util.Arrays;
import java.util.Scanner;

public class OddsToProbability {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入用逗号分开的赔率：");
        String input = scanner.nextLine();
        String[] oddsStrs = input.split(",");
        double[] odds = new double[oddsStrs.length];
        for (int i = 0; i < oddsStrs.length; i++) {
            odds[i] = Double.parseDouble(oddsStrs[i]);
        }

        double[] probabilities = oddsToProbabilities(odds);
        double[] aProbabilities = getAsianHandicapOdds(probabilities[0],probabilities[1], probabilities[2],true);
        System.out.println("胜平负概率："+Arrays.toString(probabilities));
        System.out.println("胜负过关概率："+Arrays.toString(aProbabilities));
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

