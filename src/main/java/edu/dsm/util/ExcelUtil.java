package edu.dsm.util;

import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Objects;

public class ExcelUtil {
    private ExcelUtil() {
    }

    /**
     * excel下载
     *
     * @param fileName 下载时的文件名称
     * @param response  resp
     * @param workbook excel数据
     */
    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            if (Objects.isNull(workbook)){return;}

            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException("下载excel失败");
        }
    }
}
