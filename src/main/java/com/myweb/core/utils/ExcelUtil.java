package com.myweb.core.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 */
public class ExcelUtil {


    public static void export(String[] titles, String[] filedNames , List data, OutputStream out) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {

        if (titles.length != filedNames.length || titles.length == 0) {
            return;
        }

        //创建一个workbook，对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个sheet，对应excel中的sheet
        HSSFSheet hssfSheet = workbook.createSheet("sheet1");
        //在sheet中添加表头
        HSSFRow hssfRow = hssfSheet.createRow(0);
        //字体设置
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 15);
        font.setFontName("黑体");
        font.setBold(true);
        //创建单元格，并设置表头居中
        HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
        hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
        hssfCellStyle.setFont(font);


        HSSFCell hssfCell = null;
        for (int i = 0; i < titles.length; i++) {
            hssfCell = hssfRow.createCell(i);
            hssfCell.setCellValue(titles[i]);
            //居中
            hssfCell.setCellStyle(hssfCellStyle);
        }

        //写入实体数据
        if (!CollectionUtils.isEmpty(data)) {
            for (int i = 0; i < data.size(); i++) {
                hssfRow = hssfSheet.createRow(i + 1);
                Object obj = data.get(i);
                for (int j = 0; j < filedNames.length; j++) {
                    Object filedValue = getFiledValueByName(filedNames[j],obj);
                    if (filedValue instanceof Date) {
                        filedValue = DateUtil.formatDate((Date) filedValue,"yyyy-MM-dd");
                    }
                    hssfRow.createCell(j).setCellValue(filedValue.toString());
                }
            }
        }
        for (int i = 0; i < titles.length; i++) {
            hssfSheet.autoSizeColumn(i);
        }
        workbook.write(out);
        out.flush();
        out.close();
    }

    private static Object getFiledValueByName(String filedName,Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String firstLetter = filedName.substring(0,1).toUpperCase();
        String getter = "get" +firstLetter +filedName.substring(1);
        Method method = o.getClass().getMethod(getter,new Class[]{});
        Object value = method.invoke(o,new Object[]{});
        return value;
    }

}
