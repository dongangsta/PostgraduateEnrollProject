package edu.dsm.test;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

public class JsonUtilTest {
    public static void main(String[] args) {
        String jsonStr = "[\"value1\", \"value2\", \"value3\"]";
        JSONArray array = JSONUtil.parseArray(jsonStr);
        System.out.println(array);
    }
}
