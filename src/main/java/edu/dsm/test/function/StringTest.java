package edu.dsm.test.function;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringTest {
    @Test
    public void stringTest() {
        String historyTrans = "123554,1234566,123556,284996";
        List<String> historyTransList = new ArrayList<>();
        if (!StringUtils.isBlank(historyTrans)) {
            historyTransList = Arrays.stream(historyTrans.split(",")).collect(Collectors.toList());
        }
        if (!ObjectUtils.isEmpty(historyTransList)){
            String str = "123554";
            if (historyTransList.contains(str)) {
                // 禁止历史结算单发起工作流付款提示
                System.out.println(str + "该数据为系统切换时禁止线上发起的历史结算单，不允许再次发起付款");
            }
        }
    }
}
