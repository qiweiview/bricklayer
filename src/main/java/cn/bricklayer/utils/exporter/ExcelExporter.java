package cn.bricklayer.utils.exporter;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * ExcelExporter excelExporter = new ExcelExporter();
 * excelExporter.setStartCellIndex(1);
 * excelExporter.addSheet(lists, TestDataList.class);
 * excelExporter.addSheet(lists, TestDataList.class,"sheet 2");
 * excelExporter.addMergedRegion(0,0,0,10,"0","合并单元格");
 * byte[] bytes = excelExporter.list2Excel();
 * FileUtils.writeByteArrayToFile(new File("/xxxxx/test.xlsx"), bytes);
 */
public class ExcelExporter implements ObjectRelease {

    private int startRowIndex = 0;
    private int startCellIndex = 0;
    private static int FIX_WIDTH = 4000;
    private String EMPTY_VALUE = "empty";
    private List<SheetDescription> list = new ArrayList<>();
    private int defaultSheetIndex = 0;
    private volatile byte[] exportData;


    @Override
    public void releaseResource() {
        list = null;
    }


    /**
     * @param dataList data list
     * @param t        description class
     */
    public void addSheet(List dataList, Class t) {
        String name = "" + defaultSheetIndex++;
        addSheet(dataList, t, name);
    }

    public void addSheet(List dataList, Class t, String sheetName) {
        SheetDescription sheetDescription = new SheetDescription();
        sheetDescription.setSheetName(sheetName);
        sheetDescription.setDataList(dataList);
        sheetDescription.settClass(t);

        sheetDescription.parseClass();

        list.add(sheetDescription);
    }

    public byte[] list2Excel() {
        if (exportData == null) {
            synchronized (this) {
                if (exportData == null) {
                    XSSFWorkbook xssfWorkbook = new XSSFWorkbook();

                    //create style
                    XSSFCellStyle deFaultTitleStyle = StyleUtils.getDeFaultTitleStyle(xssfWorkbook);
                    XSSFCellStyle deFaultContentStyle = StyleUtils.getDeFaultContentStyle(xssfWorkbook);


                    //create sheet
                    list.forEach(x -> {
                        XSSFSheet sheet = xssfWorkbook.createSheet(x.getSheetName());


                        //step1: set the first title row
                        int rowIndex = startRowIndex;
                        List<SheetDescription.InnerColumnDescription> columnDescriptions = x.getColumnDescriptions();

                        XSSFRow row = sheet.createRow(rowIndex);

                        int titleCellIndex = startCellIndex;
                        int i = 0;
                        for (SheetDescription.InnerColumnDescription innerColumnDescription : columnDescriptions) {
                            XSSFCell cell = row.createCell(titleCellIndex++);
                            cell.setCellStyle(deFaultTitleStyle);
                            cell.setCellValue(innerColumnDescription.getTopicName());
                            sheet.setColumnWidth(i++, FIX_WIDTH);
                        }

                        //step2: set follow row
                        List dataList = x.getDataList();


                        //for each row
                        for (Object o : dataList) {
                            List<Object> list = object2ValueList(x, o);
                            XSSFRow row1 = sheet.createRow(++rowIndex);
                            int valueCellIndex = startCellIndex;
                            //for each cell
                            for (Object value : list) {
                                XSSFCell cell = row1.createCell(valueCellIndex++);
                                cell.setCellStyle(deFaultContentStyle);
                                cell.setCellValue(value == null ? EMPTY_VALUE : value.toString());
                            }
                        }


                        //create merge
                        List<SheetDescription.MergeDescription> mergeDescriptions = x.getMergeDescriptions();
                        for (SheetDescription.MergeDescription mergeDescription : mergeDescriptions) {

                            //title
                            XSSFRow row2 = sheet.getRow(0);
                            XSSFCell cell1 = row2.getCell(mergeDescription.getStartCell());
                            if (cell1 == null) {
                                cell1 = row2.createCell(mergeDescription.getStartCell());
                            }
                            cell1.setCellValue(mergeDescription.getMergeTitle());


                            //merge content
                            CellRangeAddress region = mergeDescription.createMerge();
                            sheet.addMergedRegion(region);
                            XSSFRow row1 = sheet.getRow(mergeDescription.getStartRow());
                            XSSFCell cell = row1.getCell(mergeDescription.getStartCell());
                            if (cell == null) {
                                cell = row1.createCell(mergeDescription.getStartCell());
                            }
                            cell.setCellValue(mergeDescription.getMergeContent());
                        }


                    });


                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        xssfWorkbook.write(byteArrayOutputStream);
                        exportData = byteArrayOutputStream.toByteArray();
                    } catch (IOException e) {
                        throw new NullPointerException("write to byte array fail,cause " + e);
                    }
                }
            }
        }
        releaseResource();
        return exportData;
    }


    /**
     * 对象输出值列表
     *
     * @param o
     * @return
     */
    public List<Object> object2ValueList(SheetDescription sheetDescription, Object o) {
        List<SheetDescription.InnerColumnDescription> columnDescriptions = sheetDescription.getColumnDescriptions();

        List<Object> list = new ArrayList<>();
        Class<?> aClass = o.getClass();

        columnDescriptions.forEach(x -> {
            try {
                Field declaredField = aClass.getDeclaredField(x.getValueName());
                declaredField.setAccessible(true);
                Object value = declaredField.get(o);
                list.add(value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("get value fail,cause " + e);
            } catch (NoSuchFieldException e) {
                list.add("-");
            }
        });
        return list;

    }

    /* ===============getter setter=============== */

    public int getStartRowIndex() {
        return startRowIndex;
    }

    public void setStartRowIndex(int startRowIndex) {
        this.startRowIndex = startRowIndex;
    }

    public int getStartCellIndex() {
        return startCellIndex;
    }

    public void setStartCellIndex(int startCellIndex) {
        this.startCellIndex = startCellIndex;
    }

    public String getEMPTY_VALUE() {
        return EMPTY_VALUE;
    }

    public void setEMPTY_VALUE(String EMPTY_VALUE) {
        this.EMPTY_VALUE = EMPTY_VALUE;
    }

    /**
     * 添加合并的单元格
     *
     * @param startRow     起使行
     * @param endRow       结束行
     * @param startCell    起使列
     * @param endCell      结束列
     * @param sheetName    sheet名
     * @param mergeContent 合并填充内容
     * @param mergeTitle   列标题
     */
    public void addMergedRegion(int startRow, int endRow, int startCell, int endCell, String sheetName, String mergeContent, String mergeTitle) {

        for (SheetDescription sheetDescription : list) {
            if (sheetName.equals(sheetDescription.getSheetName())) {
                List<SheetDescription.MergeDescription> mergeDescriptions = sheetDescription.getMergeDescriptions();
                mergeDescriptions.add(new SheetDescription.MergeDescription(startRow, endRow, startCell, endCell, mergeContent, mergeTitle));
                return;
            }
        }
        throw new RuntimeException("can not found the sheet");
    }


}
