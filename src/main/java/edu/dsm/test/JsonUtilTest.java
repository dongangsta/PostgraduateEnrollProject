package edu.dsm.test;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

/**
 * 测试JsonUtil from hutool
 */
public class JsonUtilTest {
    /**
     * 测试功能入口
     *
     * @param args the input arguments
     */
    public static <List> void main(String[] args) {
        String jsonStr = "[\"value1\", \"value2\", \"value3\"]";
        JSONArray array = JSONUtil.parseArray(jsonStr);
        System.out.println(array);
        String jsonTime = "1660123752780";
        // String con = JSON.parse(JSONObject.toJSONStringWithDateFormat(要json的值,"yyyy-MM-dd HH:mm:ss")).toString();

    }
}
