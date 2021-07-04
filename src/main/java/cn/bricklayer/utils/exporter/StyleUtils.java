package cn.bricklayer.utils.exporter;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StyleUtils {
    public static XSSFCellStyle getDeFaultTitleStyle(XSSFWorkbook xssfWorkbook) {


        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();

        //设置颜色
        cellStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);


        XSSFFont font = xssfWorkbook.createFont();
        font.setBold(true);
        font.setFontName("宋体");
        font.setFontHeight(14d);


        cellStyle.setFont(font);

        return cellStyle;

    }


    public static XSSFCellStyle getDeFaultContentStyle(XSSFWorkbook xssfWorkbook) {


        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();


        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);


        XSSFFont font = xssfWorkbook.createFont();
        font.setFontName("宋体");
        font.setFontHeight(11d);


        cellStyle.setFont(font);

        return cellStyle;

    }
}
