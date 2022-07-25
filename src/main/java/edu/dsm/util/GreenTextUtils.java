package edu.dsm.util;

import com.baidu.aip.contentcensor.AipContentCensor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * 百度文本审查工具类
 */
@Component
public class GreenTextUtils {

    /**
     * The constant APP_ID.
     */
//设置APPID/AK/SK
    public static final String APP_ID = "25204862";
    /**
     * The constant API_KEY.
     */
    public static final String API_KEY = "0kBVaIUitIDZ1RYP2Z4XSife";
    /**
     * The constant SECRET_KEY.
     */
    public static final String SECRET_KEY = "zVrVPxZi7gpCDk0LmW2WikpPK1vGTRXB";

    /**
     * The Aip content censor.
     */
    AipContentCensor aipContentCensor = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);

    /**
     * 文本审核
     *
     * @param text 文本的网络路径
     * @return String string
     */
    public String greenText(String text) {

        JSONObject response = aipContentCensor.textCensorUserDefined(text);
        return response.get("conclusion").toString();
    }

}
