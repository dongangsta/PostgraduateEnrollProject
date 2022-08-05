package edu.dsm.util;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

@Slf4j
public class EasyPoiUtil {

    /**
     * 构建 sheet
     *
     * @param sheetName sheet 名字
     * @param clazz     clazz
     * @param list      列表
     * @return {@link Map<String, Object>}
     */
    public static Map<String, Object> buildSheet(String sheetName, Class<?> clazz, List<?> list) {

        ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.XSSF);
        exportParams.setSheetName(sheetName);
        // 导出样式
        exportParams.setStyle(EasyPoiExcelStyleUtil.class);

        Map<String, Object> map = new HashMap<>(4);
        // title的参数为ExportParams类型
        map.put("title", exportParams);
        // 模版导出对应得实体类型
        map.put("entity", clazz);
        // sheet中要填充得数据
        map.put("data", list);

        return map;

    }

    /**
     * 下载 excel
     *
     * 前端如果用xlsx格式接收表格 后台用XSSFWorkbook workbook = new XSSFWorkbook();创建工作薄
     * response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
     * response.addHeader("Content-Disposition", "attachment;filename=fileName" + ".xlsx");
     *
     * 前端如果用用xls格式接收表格 后台 用HSSFWorkbook workbook = new HSSFWorkbook();创建工作薄
     * response.setContentType("application/vnd.ms-excel");
     * response.addHeader("Content-Disposition", "attachment;filename=fileName"+".xls");
     *
     * @param fileName 文件名称
     * @param response 响应
     * @param workbook 工作簿
     */
    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Expose-Headers",
                    "Content-Disposition");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            log.info("文件下载失败", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 功能描述：根据接收的Excel文件来导入Excel,并封装成实体类
     *
     * @param file       文件
     * @param sheetNum   第几个 sheet
     * @param titleRows  title 行数
     * @param headerRows 标题 行数
     * @param pojoClass  pojo类
     * @return {@link List<T>}
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer sheetNum, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (Objects.isNull(file)) {
            return Collections.emptyList();
        }
        ImportParams params = new ImportParams();
        params.setStartSheetIndex(sheetNum);
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass,
                    params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("excel文件不能为空");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    /**
     * 功能描述：根据接收的 Excel 文件来导入 Excel,并封装成 Map
     *
     * @param file       文件
     * @param sheetNum   第几个 sheet
     * @param titleRows  title 行数
     * @param headerRows 标题 行数
     * @return {@link List<T>}
     */
    public static <T> List<T> parseExcelMap(MultipartFile file, Integer sheetNum, Integer titleRows, Integer headerRows) {
        if (Objects.isNull(file)) {
            return Collections.emptyList();
        }
        ImportParams params = new ImportParams();
        params.setStartSheetIndex(sheetNum);
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        params.setNeedCheckOrder(true);

        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), Map.class,
                    params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("excel文件不能为空");
        } catch (Exception e) {
            log.info("excel 文件读取失败", e);
            throw new RuntimeException("文件读取失败");
        }
        return list;
    }

    /**
     * 得到 Workbook 对象
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkBook(MultipartFile file) throws IOException {
        //这样写  excel 能兼容03和07
        InputStream is = file.getInputStream();
        Workbook hssfWorkbook = null;
        try {
            hssfWorkbook = new HSSFWorkbook(is);
        } catch (Exception ex) {
            is = file.getInputStream();
            hssfWorkbook = new XSSFWorkbook(is);
        }
        return hssfWorkbook;
    }


    /**
     * 检查excel
     *
     * @param file       文件
     * @param sheetNum   第几个 sheet
     * @param titleRows  title 所占行数
     * @param headerRows head 所占行数
     * @param clazz      clazz
     * @return {@link ExcelImportResult<T>}
     */
    public static <T> ExcelImportResult<T> checkExcel(MultipartFile file, Integer sheetNum, Integer titleRows, Integer headerRows, Class<T> clazz) {
        if (Objects.isNull(file)) {
            return new ExcelImportResult<>();
        }

        ImportParams params = new ImportParams();
        params.setStartSheetIndex(sheetNum);
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        // 开启校验
        params.setNeedVerify(true);

        ExcelImportResult<T> result = null;
        try {
            result = ExcelImportUtil.importExcelMore(
                    file.getInputStream(), clazz, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("excel文件不能为空");
        } catch (Exception e) {
            log.info("excel 文件读取失败", e);
            throw new RuntimeException("文件读取失败");
        }
        return result;
    }

}
