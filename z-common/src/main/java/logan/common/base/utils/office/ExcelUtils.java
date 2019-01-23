package logan.common.base.utils.office;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public abstract class ExcelUtils {
    static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
    private static final String pattern = "yyyy-MM-dd HH:mm:ss";

    @Deprecated
    public static void writeXls2003(File destXlsFile, List<String[]> rowDataList) {
        FileOutputStream output = null;
        HSSFWorkbook workbook = null;
        try {
            if (!destXlsFile.exists()) {
                FileUtils.touch(destXlsFile);
            }
            output = new FileOutputStream(destXlsFile);
            workbook = new HSSFWorkbook();

            HSSFSheet sheet = workbook.createSheet();
            sheet.createFreezePane(0, 1);
            workbook.setSheetName(0, FilenameUtils.getBaseName(destXlsFile.getName()));

            int rowIdx = 0;
            for (String[] rows : rowDataList) {
                Row row = sheet.createRow(rowIdx++);
                int cellIdx = 0;
                for (String cellData : rows) {
                    createStringCell(row, cellIdx++, cellData);
                }
            }
            workbook.write(output);
        } catch (Exception e) {
            throw new RuntimeException("生成excel文件时出错!", e);
        } finally {
            if (null != workbook) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            IOUtils.closeQuietly(output);
        }
    }

    /**
     * @param destXlsFile
     * @param rowDataList
     * @Desc: 生产xls2007 可防止内存溢出
     * @Author: qiufeng 2016年12月7日 上午10:12:07
     */
    public static void writeXls2007(File destXlsFile, List<String[]> rowDataList) {
        FileOutputStream output = null;

        try {
            if (!destXlsFile.exists()) {
                FileUtils.touch(destXlsFile);
            }
            output = new FileOutputStream(destXlsFile);
        } catch (Exception e) {
            logger.error("创建xls文件异常：", e);
            e.printStackTrace();
        }
        writeXls2007(output, rowDataList);
    }

    public static void writeXls2007(OutputStream outputStream, List<String[]> rowDataList) {
        SXSSFWorkbook workbook = null;
        try {
            // keep 100 rows in memory,
            // exceeding rows will be
            // flushed to disk
            workbook = new SXSSFWorkbook(100);
            SXSSFSheet sheet = workbook.createSheet();
            sheet.createFreezePane(0, 1);
            // workbook.setSheetName(0,
            // FilenameUtils.getBaseName(destXlsFile.getName()));
            int rowIdx = 0;
            for (String[] rows : rowDataList) {
                Row row = sheet.createRow(rowIdx++);
                int cellIdx = 0;
                for (String cellData : rows) {
                    createStringCell(row, cellIdx++, cellData);
                }
            }
            workbook.write(outputStream);
        } catch (Exception e) {
            throw new RuntimeException("生成excel文件时出错!", e);
        } finally {
            if (null != workbook) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            IOUtils.closeQuietly(outputStream);
        }
    }

    public static void readXls(File file, ExcelDataHandler dataHandler) {
        InputStream inputStream = null;
        long st = System.currentTimeMillis();
        String type = "";
        try {
            Workbook workbook;
            try {
                inputStream = new FileInputStream(file);
                workbook = new HSSFWorkbook(inputStream);
                type = "2003";
            } catch (Exception e) {
                IOUtils.closeQuietly(inputStream);
                inputStream = new FileInputStream(file);
                workbook = new XSSFWorkbook(inputStream);
                type = "2007";
            }
            readXls(workbook, dataHandler);
            long ed = System.currentTimeMillis();
            logger.info("解析Excel{}文件..用时={}ms, [{}]", new Object[]{type, Long.valueOf(ed - st), file});
        } catch (Exception e) {
            long ed = System.currentTimeMillis();
            logger.error("解析Excel{}文件出错! 用时={}ms, [{}]", new Object[]{type, Long.valueOf(ed - st), file, e});
            throw new RuntimeException("解析Excel" + type + "文件出错! " + file, e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    public static void readXls(File file, int columnNumber, ExcelDataHandler dataHandler) {
        InputStream inputStream = null;
        long st = System.currentTimeMillis();
        String type = "";
        try {
            Workbook workbook;
            try {
                inputStream = new FileInputStream(file);
                workbook = new HSSFWorkbook(inputStream);
                type = "2003";
            } catch (Exception e) {
                IOUtils.closeQuietly(inputStream);
                inputStream = new FileInputStream(file);
                workbook = new XSSFWorkbook(inputStream);
                type = "2007";
            }
            readXls(workbook, dataHandler);
            long ed = System.currentTimeMillis();
            logger.info("解析Excel{}文件..用时={}ms, [{}]", new Object[]{type, Long.valueOf(ed - st), file});
        } catch (Exception e) {
            long ed = System.currentTimeMillis();
            logger.error("解析Excel{}文件出错! 用时={}ms, [{}]", new Object[]{type, Long.valueOf(ed - st), file, e});
            throw new RuntimeException("解析Excel" + type + "文件出错! " + file, e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    private static void readXls(Workbook workbook, ExcelDataHandler dataHandler) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());

        List<String> cellDataList = new ArrayList<String>();
        int maxSheets = workbook.getNumberOfSheets();
        int rowNum;
        Iterator<Row> itRow;
        for (int sheetIndex = 0; sheetIndex < maxSheets; sheetIndex++) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            rowNum = 0;
            for (itRow = sheet.iterator(); itRow.hasNext(); ) {
                Row row = (Row) itRow.next();
                cellDataList.clear();
                for (Iterator<Cell> itCell = row.iterator(); itCell.hasNext(); ) {
                    Cell cell = (Cell) itCell.next();
                    String data = getStringValue(cell);
                    cellDataList.add(data);
                }
                dataHandler.handle(sheetIndex, rowNum++, (String[]) cellDataList.toArray(new String[0]));
            }
        }
    }

    private static final FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss" );

    private static String getStringValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        try {
            int cellType = cell.getCellType();
            switch (cellType) {
                case 0:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        return fdf.format(date);
                    }
                    return cell.getNumericCellValue() + "";
                case 1:
                    return cell.getStringCellValue();
                case 2:
                    return cell.getNumericCellValue() + "";
                case 3:
                    return "";
                case 4:
                    return cell.getBooleanCellValue() + "";
                case 5:
                    return cell.getErrorCellValue() + "";
            }
            return cell.toString();
        } catch (Exception e) {
            throw new RuntimeException("无法解析单元格输数据! CellType=" + cell.getCellType(), e);
        }
    }

    private static Cell createStringCell(Row row, int index, Object value) {
        Cell cell = row.createCell(index);
        cell.setCellType(1);
        cell.setCellValue(String.valueOf(value));
        return cell;
    }

    public static abstract interface ExcelDataHandler {
        public abstract void handle(int paramInt1, int paramInt2, String[] paramArrayOfString);
    }
}
