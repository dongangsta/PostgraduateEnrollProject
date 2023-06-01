package edu.dsm.test.function;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: New Description: Author: dong Date: 2023/6/1 10:36 History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class AsianOddsConverter2 {
    public static void main(String[] args) {
        double[][] odds = {{0.4, 0.6}, {0.2, 0.8}, {0.5, 0.6}, {0.2, 0.8}, {0.2, 0.8}};
        int n = odds.length;

        // 动态规划算法计算最优的五串一路径
        double[][] dp5_1 = new double[n][32];
        for (int i = 0; i < n; i++) {
            dp5_1[i][1 << i] = odds[i][0];
            dp5_1[i][1 << i | 16] = odds[i][1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= 31; j++) {
                if ((j & (1 << i)) == 0) {
                    int next = (1 << i) | j;
                    dp5_1[i][next] = Math.max(dp5_1[i - 1][j], dp5_1[i - 1][j | (1 << i)] * odds[i][0]);
                    dp5_1[i][next | 16] = Math.max(dp5_1[i - 1][j], dp5_1[i - 1][j | (1 << i)] * odds[i][1]);
                }
            }
        }

        // 输出最优的五串一路径及其对应的概率
        List<Integer> choices5_1 = new ArrayList<>();
        double maxOdds5_1 = 0.0;
        int maxIndex5_1 = 0;
        for (int j = 1; j <= 31; j++) {
            if (dp5_1[n - 1][j] > maxOdds5_1) {
                maxOdds5_1 = dp5_1[n - 1][j];
                maxIndex5_1 = j;
            }
        }
        int k = maxIndex5_1;
        double path_odds5_1 = 1.0;
        for (int i = n - 1; i >= 0; i--) {
            choices5_1.add((k & (1 << i)) == 0 ? 1 : 0);
            path_odds5_1 *= ((k & (1 << i)) == 0 ? odds[i][0] : odds[i][1]);
        }
        System.out.println("最优的五串一路径：" + choices5_1 + "，概率为：" + path_odds5_1);

        // 动态规划算法计算最优的五过四关容错路径
        double[][] dp5_4 = new double[n][32];
        for (int i = 0; i < n; i++) {
            dp5_4[i][1 << i] = odds[i][0];
            dp5_4[i][1 << i | 16] = odds[i][1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= 31; j++) {
                if ((j & (1 << i)) == 0) {
                    int next = (1 << i) | j;
                    dp5_4[i][next] = Math.max(dp5_4[i - 1][j], dp5_4[i - 1][j | (1 << i)] * odds[i][0]);
                    dp5_4[i][next | 16] = Math.max(dp5_4[i - 1][j], dp5_4[i - 1][j | (1 << i)] * odds[i][1]);
                } else {
                    dp5_4[i][j] = dp5_4[i - 1][j];
                }
            }
        }

        // 输出最优的五过四关容错路径及其对应的概率
        List<Integer> choices5_4 = new ArrayList<>();
        double maxOdds5_4 = 0.0;
        int maxIndex5_4 = 0;
        for (int j = 1; j <= 31; j++) {
            if (dp5_4[n - 1][j] > maxOdds5_4) {
                maxOdds5_4 = dp5_4[n - 1][j];
                maxIndex5_4 = j;
            }
        }
        k = maxIndex5_4;
        double path_odds5_4 = 1.0;
        for (int i = n - 1; i >= 0; i--) {
            choices5_4.add((k & (1 << i)) == 0 ? 1 : 0);
            path_odds5_4 *= ((k & (1 << i)) == 0 ? odds[i][0] : odds[i][1]);
        }
        System.out.println("最优的五过四关容错路径：" + choices5_4 + "，概率为：" + path_odds5_4);

        // 动态规划算法计算最优的五过三关容错路径
        double[][] dp5_3 = new double[n][16];
        for (int i = 0; i < n; i++) {
            dp5_3[i][1 << i] = odds[i][0];
            dp5_3[i][1 << i | 8] = odds[i][1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= 15; j++) {
                if ((j & (1 << i)) == 0) {
                    int next = (1 << i) | j;
                    dp5_3[i][next] = Math.max(dp5_3[i - 1][j], dp5_3[i - 1][j | (1 << i)] * odds[i][0]);
                    dp5_3[i][next | 8] = Math.max(dp5_3[i - 1][j], dp5_3[i - 1][j | (1 << i)] * odds[i][1]);
                } else {
                    dp5_3[i][j] = dp5_3[i - 1][j];
                }
            }
        }

        // 输出最优的五过三关容错路径及其对应的概率
        List<Integer> choices5_3 = new ArrayList<>();
        double maxOdds5_3 = 0.0;
        int maxIndex5_3 = 0;
        for (int j = 1; j <= 15; j++) {
            if (dp5_3[n - 1][j] > maxOdds5_3) {
                maxOdds5_3 = dp5_3[n - 1][j];
                maxIndex5_3 = j;
            }
        }
        k = maxIndex5_3;
        double path_odds5_3 = 1.0;
        for (int i = n - 1; i >= 0; i--) {
            choices5_3.add((k & (1 << i)) == 0 ? 1 : 0);
            path_odds5_3 *= ((k & (1 << i)) == 0 ? odds[i][0] : odds[i][1]);
        }
        System.out.println("最优的五过三关容错路径：" + choices5_3 + "，概率为：" + path_odds5_3);
    }



}
