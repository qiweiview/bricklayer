package cn.anicert.utils.exporter;

import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SheetDescription implements ObjectRelease {
    private String sheetName;

    private Class tClass;

    private List dataList;

    private List<InnerColumnDescription> columnDescriptions;


    private List<MergeDescription> mergeDescriptions = new ArrayList<>();

    private static final Map<Class, InnerReflectCache> map = new ConcurrentHashMap<>();

    @Override
    public void releaseResource() {
        tClass = null;
        dataList = null;
        mergeDescriptions = null;
    }


    /**
     * need cache
     */
    public void parseClass() {
        InnerReflectCache innerReflectCache = map.get(tClass);
        if (innerReflectCache == null) {
            synchronized (SheetDescription.class) {
                if (innerReflectCache == null) {
                    innerReflectCache = new InnerReflectCache();
                    Field[] declaredFields = tClass.getDeclaredFields();
                    List<InnerColumnDescription> collect = Stream.of(declaredFields).map(x -> {
                        ColumnDescription annotation = x.getAnnotation(ColumnDescription.class);
                        if (annotation == null) {
                            return null;
                        } else {
                            InnerColumnDescription innerColumnDescription = new InnerColumnDescription();
                            innerColumnDescription.setOrder(annotation.order());
                            innerColumnDescription.setValueName(x.getName());
                            innerColumnDescription.setTopicName(annotation.columnName());
                            return innerColumnDescription;

                        }
                    }).filter(x -> x != null).sorted(Comparator.comparing(a -> a.order)).collect(Collectors.toList());


                    innerReflectCache.setColumnDescriptions(collect);
                    innerReflectCache.settClass(tClass);
                    map.put(tClass, innerReflectCache);
                }
            }
        }
        columnDescriptions = innerReflectCache.getColumnDescriptions();


    }

    /* ==================================== 分割线 ====================================*/

    public class InnerColumnDescription {
        private String topicName;
        private String valueName;
        private Integer order;

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getValueName() {
            return valueName;
        }

        public void setValueName(String valueName) {
            this.valueName = valueName;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }
    }



    /* ==================================== 分割线 ====================================*/

    public static class MergeDescription {


        private int startRow;

        private int endRow;

        private int startCell;

        private int endCell;

        private String mergeTitle;

        private String mergeContent;


        public MergeDescription(int startRow, int endRow, int startCell, int endCell, String mergeContent, String mergeTitle) {
            this.startRow = startRow;
            this.endRow = endRow;
            this.startCell = startCell;
            this.endCell = endCell;
            this.mergeContent = mergeContent;
            this.mergeTitle = mergeTitle;
        }

        public CellRangeAddress createMerge() {
            CellRangeAddress cellRangeAddress = new CellRangeAddress(startRow, endRow, startCell, endCell);
            return cellRangeAddress;
        }

        public String getMergeTitle() {
            return mergeTitle;
        }

        public void setMergeTitle(String mergeTitle) {
            this.mergeTitle = mergeTitle;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getStartCell() {
            return startCell;
        }

        public void setStartCell(int startCell) {
            this.startCell = startCell;
        }

        public int getEndCell() {
            return endCell;
        }

        public void setEndCell(int endCell) {
            this.endCell = endCell;
        }

        public String getMergeContent() {
            return mergeContent;
        }

        public void setMergeContent(String mergeContent) {
            this.mergeContent = mergeContent;
        }


    }

    /* ==================================== 分割线 ====================================*/

    public class InnerReflectCache {
        private Class tClass;

        private List<InnerColumnDescription> columnDescriptions;

        public Class gettClass() {
            return tClass;
        }

        public void settClass(Class tClass) {
            this.tClass = tClass;
        }

        public List<InnerColumnDescription> getColumnDescriptions() {
            return columnDescriptions;
        }

        public void setColumnDescriptions(List<InnerColumnDescription> columnDescriptions) {
            this.columnDescriptions = columnDescriptions;
        }
    }
    /* ==================================== 分割线 ====================================*/

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Class gettClass() {
        return tClass;
    }

    public void settClass(Class tClass) {
        this.tClass = tClass;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public List<InnerColumnDescription> getColumnDescriptions() {
        return columnDescriptions;
    }

    public void setColumnDescriptions(List<InnerColumnDescription> columnDescriptions) {
        this.columnDescriptions = columnDescriptions;
    }

    public List<MergeDescription> getMergeDescriptions() {
        return mergeDescriptions;
    }

    public void setMergeDescriptions(List<MergeDescription> mergeDescriptions) {
        this.mergeDescriptions = mergeDescriptions;
    }

    public static Map<Class, InnerReflectCache> getMap() {
        return map;
    }
}
