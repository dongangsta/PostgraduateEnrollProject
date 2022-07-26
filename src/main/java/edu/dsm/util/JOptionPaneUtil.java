package edu.dsm.util;

import javax.swing.*;

/**
 * JOptionPane工具类
 */
public class JOptionPaneUtil {
    /**
     * 弹出窗口
     *
     * @param title   弹窗标题
     * @param context 弹窗内容
     */
    public static void Popup(String title,String context){
        System.setProperty("java.awt.headless", "false");
        JOptionPane.showMessageDialog(null,context,title,JOptionPane.PLAIN_MESSAGE);
    }
}
