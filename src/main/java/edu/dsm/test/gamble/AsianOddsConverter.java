package edu.dsm.test.gamble;

import edu.dsm.service.SoccerService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * ClassName: New Description: Author: dong Date: 2023/6/1 10:36 History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@SpringBootTest()
@RunWith(SpringRunner.class)
public class AsianOddsConverter {

    @Resource
    private SoccerService soccerService;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();  // 比赛场数
        double[] up = new double[n];  // 赢选择概率
        double[] down = new double[n];  // 输选择概率

        for (int i = 0; i < n; i++) {
            up[i] = scanner.nextDouble();
            down[i] = scanner.nextDouble();
        }

        double[] probabilities = new double[(int) Math.pow(2, n)];  // 存储每种组合的概率
        String[] choices = new String[(int) Math.pow(2, n)];  // 存储每种组合的选择情况，如"UUDD"

        for (int i = 0; i < Math.pow(2, n); i++) {
            String choice = Integer.toBinaryString(i);  // 转为二进制字符串
            while (choice.length() < n) {
                choice = "0" + choice;
            }
            choices[i] = choice;

            double probability = 1.0;
            for (int j = 0; j < n; j++) {
                if (choice.charAt(j) == '0') {
                    probability *= up[j];
                } else {
                    probability *= down[j];
                }
            }
            probabilities[i] = probability;
        }

        // 按照概率大小进行排序
        for (int i = 0; i < Math.pow(2, n) - 1; i++) {
            for (int j = 0; j < Math.pow(2, n) - 1 - i; j++) {
                if (probabilities[j] < probabilities[j + 1]) {
                    double tempProbability = probabilities[j];
                    String tempChoice = choices[j];

                    probabilities[j] = probabilities[j + 1];
                    choices[j] = choices[j + 1];

                    probabilities[j + 1] = tempProbability;
                    choices[j + 1] = tempChoice;
                }
            }
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        // 输出每种组合方式及概率
        for (int i = 0; i < Math.pow(2, n) && i < 50; i++) {
            System.out.println(i+1 +". " + convertResult(choices[i]) + " " + nf.format(probabilities[i]));
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
