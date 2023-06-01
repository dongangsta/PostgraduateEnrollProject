package edu.dsm.test.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName: New Description: Author: dong Date: 2023/6/1 10:36 History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class AsianOddsConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入投注选项的数量： ");
        int n = scanner.nextInt();

        double[][] odds = new double[n][2];
        for (int i = 0; i < n; i++) {
            System.out.print("请输入第" + (i+1) + "个选项的胜率和负率，用空格分隔：");
            odds[i][0] = scanner.nextDouble();
            odds[i][1] = scanner.nextDouble();
        }

        // 动态规划算法计算最优的n串一路径
        int m = (int) Math.pow(2, n);
        double[][] dp = new double[n][m];
        for (int i = 0; i < n; i++) {
            dp[i][1 << i] = odds[i][0];
            dp[i][1 << i | m / 2] = odds[i][1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (Integer.bitCount(j) == i + 1) {
                    double max_odds = 0.0;
                    for (int k = 0; k < n; k++) {
                        if ((j & (1 << k)) != 0) {
                            if ((j ^ (1 << k)) == 0) {
                                max_odds = dp[k][j];
                            } else {
                                double curr_odds = dp[k][j];
                                for (int p = j & ~(1 << k); p > 0; p = (p - 1) & (j & ~(1 << k))) {
                                    double left_odds = dp[k][p];
                                    double right_odds = dp[k][j ^ p];
                                    curr_odds = Math.max(curr_odds, left_odds * right_odds);
                                }
                                max_odds = Math.max(max_odds, curr_odds);
                            }
                        }
                    }
                    for (int k = 0; k < n; k++) {
                        if ((j & (1 << k)) == 0) {
                            int next = (1 << k) | j;
                            dp[k][next] = Math.max(dp[k][next], max_odds * odds[k][0]);
                            dp[k][next | m / 2] = Math.max(dp[k][next | m / 2], max_odds * odds[k][1]);
                        }
                    }
                }
            }
        }

        // 输出最优的n串一路径及其对应的概率
        List<Integer> choices = new ArrayList<>();
        double max_odds = Double.MIN_VALUE;
        for (int j = 1; j < m; j++) {
            double path_odds = 1.0;
            for (int i = 0; i < n; i++) {
                choices.add((j & (1 << i)) == 0 ? 1 : 0);
                path_odds *= ((j & (1 << i)) == 0 ? odds[i][0] : odds[i][1]);
            }
            if (path_odds > max_odds && dp[n-1][j] == path_odds) {
                System.out.println("最优的n串一路径：" + choices + "，概率为：" + path_odds);
                max_odds = path_odds;
            }
            choices.clear();
        }
    }

}
