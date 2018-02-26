
package cn.rfatx.app.util.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.joda.time.DateTime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Excel处理
 */
public class ExcelUtil {
    private static volatile ExcelUtil instance = null;

    private ExcelUtil() {
    }

    public static ExcelUtil getInstance() {
            synchronized(ExcelUtil.class) {
                if(instance == null) {
                    instance = new ExcelUtil();
                }
                return instance;
            }

    }

    /**
     * 支持Xlsx格式的Excel
     * @param dataMap 数据键值对
     * @param excelFilePath 文件路径
     * @return 本地文件
     */
    public String exportXlsxExcel(Map<String, List<String[]>> dataMap, String excelFilePath) {
        String fileDir = excelFilePath + "/uf" + "/excel";
        String fileLocal = fileDir + "/" + DateTime.now().toString("yyyyMMddHHmmss") + ".xlsx";
        FileOutputStream os = null;

        try {
            File e = new File(fileDir);
            if(!e.exists()) {
                e.mkdir();
            }

            os = new FileOutputStream(fileLocal);
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = null;
            List dataList = null;
            Set keyTitle = dataMap.keySet();
            Iterator i$ = keyTitle.iterator();

            while(i$.hasNext()) {
                String title = (String)i$.next();
                sheet = wb.createSheet(title);
                dataList = (List)dataMap.get(title);

                for(int i = 0; null != dataList && i < dataList.size(); ++i) {
                    XSSFRow row = sheet.createRow(i);
                    String[] arr = (String[])dataList.get(i);

                    for(int j = 0; null != arr && j < arr.length; ++j) {
                        XSSFCell cell = row.createCell(j);
                        cell.setCellValue(arr[j]);
                        if(i == 0) {
                            XSSFCellStyle style = wb.createCellStyle();
                            style.setAlignment((short)2);
                            style.setFillBackgroundColor((short)16);
                            cell.setCellStyle(style);
                        }
                    }
                }
            }

            wb.write(os);
        } catch (Exception var27) {
            var27.printStackTrace();
        } finally {
            try {
                if(os != null) {
                    os.close();
                }
            } catch (IOException var26) {
                var26.printStackTrace();
            }

        }

        return fileLocal;
    }

    /**
     * 支持Xls格式的Excel
     * @param dataMap 数据键值对
     * @param excelFilePath 文件路径
     * @return 本地文件
     */
    public String exportXlsExcel(Map<String, List<String[]>> dataMap, String excelFilePath) {
        FileOutputStream fout = null;
        String fileLocal = "";
        String fileDir = excelFilePath + "/uf" + "/excel";

        try {
            File e = new File(fileDir);
            if(!e.exists()) {
                e.mkdirs();
            }

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = null;
            List dataList = null;
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment((short)2);
            Set keyTitle = dataMap.keySet();
            Iterator i$ = keyTitle.iterator();

            while(i$.hasNext()) {
                String title = (String)i$.next();
                sheet = wb.createSheet(title);
                dataList = (List)dataMap.get(title);

                for(int i = 0; null != dataList && i < dataList.size(); ++i) {
                    HSSFRow row = sheet.createRow(i);
                    String[] arr = (String[])dataList.get(i);

                    for(int j = 0; null != arr && j < arr.length; ++j) {
                        HSSFCell cell = row.createCell(j);
                        cell.setCellValue(arr[j]);
                        cell.setCellStyle(style);
                        if(i == 0) {
                            style.setFillForegroundColor((short)22);
                            style.setFillBackgroundColor((short)22);
                            cell.setCellStyle(style);
                        }
                    }
                }
            }

            fileLocal = fileDir + "/" + DateTime.now().toString("yyyyMMddHHmmss") + ".xls";
            fout = new FileOutputStream(fileLocal);
            wb.write(fout);
        } catch (Exception var28) {
            var28.printStackTrace();
        } finally {
            try {
                if(fout != null){
                    fout.close();
                }
            } catch (IOException var27) {
                var27.printStackTrace();
            }

        }

        return fileLocal;
    }
}