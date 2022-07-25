package edu.dsm.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Cookie工具类
 */
public class CookieUtil {
    /**
     * The constant username.
     */
    public static String username;
    /**
     * The constant hisname.
     */
    public static String hisname;

    /**
     * Get cookie user name string.
     *
     * @param request the request
     * @return the string
     */
    public static String getCookieUserName(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userName")){
                    username = cookie.getValue();
                }
            }
        }
        return username;
    }

    /**
     * Get cookie his name string.
     *
     * @param request the request
     * @return the string
     */
    public static String getCookieHisName(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("hisName")){
                    hisname = cookie.getValue();
                }
            }
        }
        return hisname;
    }
}
