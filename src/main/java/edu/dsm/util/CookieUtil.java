package edu.dsm.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
    public static String username;
    public static String hisname;
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
