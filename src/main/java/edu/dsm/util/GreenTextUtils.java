package edu.dsm.util;

import com.baidu.aip.contentcensor.AipContentCensor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class GreenTextUtils {

    //设置APPID/AK/SK
    public static final String APP_ID = "25204862";
    public static final String API_KEY = "0kBVaIUitIDZ1RYP2Z4XSife";
    public static final String SECRET_KEY = "zVrVPxZi7gpCDk0LmW2WikpPK1vGTRXB";

    AipContentCensor aipContentCensor = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);

    /**
     * 文本审核
     *
     * @param text 文本的网络路径
     * @return String
     */
    public String greenText(String text) {

        JSONObject response = aipContentCensor.textCensorUserDefined(text);
        return response.get("conclusion").toString();
    }

}
