package util;

import cn.rfatx.api.Setting;
import cn.rfatx.app.util.PathUtil;
import com.google.common.base.Strings;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
*
 * 生成应用的下载文件
 *
*/
public class DownExcelUtil {
    private static DownExcelUtil downExcel=null;
    private final Logger log=LoggerFactory.getLogger(DownExcelUtil.class);
    private DownExcelUtil(){
    }
    public static DownExcelUtil getInstall(){
        if(null==downExcel){
            synchronized(DownExcelUtil.class){
                if(downExcel==null){
                    downExcel=new DownExcelUtil();
                }
            }
        }
        return downExcel;
    }
    /**
     * @Description: (下载excel 模板文件)
     */
    public void downloadExcel(String tip,HttpServletResponse response,LinkedList<String> titleList){
        try{
            String targetDirectory= PathUtil.getRootPath(), path="";
            if(!Strings.isNullOrEmpty(tip)){
                if(tip.equals("1")||tip.equals("6")){

                }else if(tip.equals("2")){
                    path=targetDirectory+ Setting.downloadExcel+"/"+"bilityInf.xls";
                }else if(tip.equals("3")){
                    path=targetDirectory+Setting.downloadExcel+"/"+"companyInfo.xls";
                }else if(tip.equals("4")){
                    path=targetDirectory+Setting.downloadExcel+"/"+"companyPhoneInfo.xls";
                }else if(tip.equals("5")){
                    path=targetDirectory+Setting.downloadExcel+"/"+"userDetail.xls";
                }else if(tip.equals("7")){
                    path=targetDirectory+Setting.downloadExcel+"/"+"crmSignAttAndAdd.xls";
                }else if("8".equals(tip)){
                    path=targetDirectory+Setting.downloadExcel+"/"+"crmAttDeptUser.xls";
                }else if("9".equals(tip)){
                    path=targetDirectory+Setting.downloadExcel+"/"+"orgUser.xlsx";
                }else if("10".equals(tip)){
                    path=targetDirectory+Setting.downloadExcel+"/"+"groupMember.xls";
                }else if("11".equals(tip)){
                    path=targetDirectory+Setting.downloadExcel+"/"+"equipmentList.xls";
                }else if("12".equals(tip)){
                    path=targetDirectory+Setting.downloadExcel+"/"+"cardList.xls";
                }
            }
            File file=new File(path);// path是指欲下载的文件的路径。
            InputStream fis=new BufferedInputStream(new FileInputStream(path));// 以流的形式下载文件。
            byte[] buffer=new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition","attachment;filename="+new String(file.getName().getBytes(),"ISO-8859-1"));
            response.addHeader("Content-Length",""+file.length());
            OutputStream toClient=new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            //downLoadFile(response,path);
        }catch(Exception e){
            log.error("下载excel模板文件，错误信息 {}",e);
        }
    }
    /**
     * 具体下载文件
     *
     * @param response 响应的请求
     * @param filePath 文件路径
     */
    private void downLoadFile(HttpServletResponse response,String filePath) throws Exception{
        try{
            response.reset();
            response.setContentType("application/octet-stream");
            String fileName=new File(filePath).getName();
            fileName=response.encodeURL(new String(fileName.getBytes(),"ISO8859_1"));//转码
            response.setHeader("Content-Disposition","inline; filename=\""+fileName+"\"");
            ServletOutputStream out=response.getOutputStream();
            InputStream inStream=new FileInputStream(filePath);
            //循环取出流中的数据
            byte[] b=new byte[1024];
            int len;
            while((len=inStream.read(b))>0){
                out.write(b,0,len);
            }
            response.setStatus(response.SC_OK);
            response.flushBuffer();
            out.close();
            inStream.close();
        }catch(Exception e){
        }
    }

    /**
     * 通过响应输出流实现文件下载
     *
     * @param response     响应的请求
     * @param fileLocal    文件的绝对路径 请用/斜杠表示路径
     * @param downloadName 自定义的文件名 ( 不要后缀),如果此值为空则使用时间日期做为默认的文件名
     * @param deleFile     下载完成后是否删除文件（true: 删除 , false：不删除）
     */
    public void downLoadFile(HttpServletResponse response,String fileLocal,String downloadName,boolean deleFile){
        InputStream in=null;
        OutputStream out=null;
        try{
            if(!"".equals(downloadName)){
                downloadName=downloadName+fileLocal.substring(fileLocal.lastIndexOf("."));
            }else{
                downloadName=fileLocal.substring(fileLocal.lastIndexOf("/")+1);
            }
            response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode(downloadName,"UTF-8"));
            in=new FileInputStream(fileLocal);
            int len=0;
            byte buffer[]=new byte[1024];
            out=response.getOutputStream();
            while((len=in.read(buffer))>0){
                out.write(buffer,0,len);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(in!=null){
                try{
                    //
                    in.close();
                    if(deleFile){
                        Thread.sleep(1000l);
                        File file=new File(fileLocal);
                        file.delete();
                    }
                }catch(Exception e){
                }
            }
        }
    }
    /**
     * excel 2003版本的导出方法 支持多个sheet导出 导出的文件后缀为.xls
     *
     * @param dataMap       要导出的数据
     * @param excelFilePath excel文件的存放位置
     * @param fileName      excel文件名字
     *
     * @return
     */
    public String exportXlsExcel(Map<String,List<String[]>> dataMap,String excelFilePath,String fileName){
        FileOutputStream fout=null;
        String fileLocal="";
        try{
            File file=new File(excelFilePath);
            if(!file.exists()){
                file.mkdirs();
            }
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb=new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet=null;
            List<String[]> dataList=null;
            HSSFCellStyle style=wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            Set<String> keyTitle=dataMap.keySet();
            for(String title : keyTitle){
                sheet=wb.createSheet(title);
                dataList=dataMap.get(title);
                for(int i=0;null!=dataList&&i<dataList.size();i++){
                    // 生成第一行
                    HSSFRow row=sheet.createRow(i);
                    String[] arr=dataList.get(i);
                    for(int j=0;null!=arr&&j<arr.length;j++){
                        // 给这一行的第一列赋值
                        HSSFCell cell=row.createCell(j);
                        cell.setCellValue(arr[j]);
                        cell.setCellStyle(style);
                        if(i==0){
                            HSSFCellStyle tempStyle=style;
                            tempStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                            tempStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
                            cell.setCellStyle(tempStyle);
                        }
                    }
                }
            }
            // 第六步，将文件存到指定位置
            fileLocal=excelFilePath+"/"+fileName+".xls";
            fout=new FileOutputStream(fileLocal);
            wb.write(fout);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                fout.close();
            }catch(IOException e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return fileLocal;
    }
}