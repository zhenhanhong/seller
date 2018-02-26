package cn.rfatx.app.util.freemark;



import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;


/**
 * 基于模板生成文本输出的通用工具
 */
public final class FreeMarker
{
  private static volatile FreeMarker freeMarker;

  public static FreeMarker getInstance()
  {
    synchronized (FreeMarker.class) {
      if (freeMarker == null) {
        freeMarker = new FreeMarker();
      }
      return freeMarker;
    }
  }


  /**
   * 获取模板详情
   * @param templateName 模板名字
   * @param map 集合参数
   * @param path 路径
   * @return 模板信息
   */
  public String getTemplateInfo(String templateName, Map<String, String> map, String path)
  {
    String xmlInfo = "";
    try {
      Configuration cfg = new Configuration();
      cfg.setDirectoryForTemplateLoading(new File(path));

      Template template = cfg.getTemplate(templateName);

      StringWriter out = new StringWriter();
      template.process(map, out);

      xmlInfo = out.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return xmlInfo;
  }


  /**
   * 保存到文件
   * @param templateName 文件名字
   * @param map 参数集合
   * @param path 路劲
   * @param writeFile 写文件
   * @return 保存成功失败
   */
  public boolean saveToFile(String templateName, Map<String, String> map, String path, String writeFile)
  {
    boolean flag = false;
    FileOutputStream fos = null;
    try {
      Configuration cfg = new Configuration();
      cfg.setDirectoryForTemplateLoading(new File(path));

      Template template = cfg.getTemplate(templateName, "utf-8");

      StringWriter out = new StringWriter();
      template.process(map, out);
      File file = new File(writeFile);
      file.setExecutable(true, false);
      file.setReadable(true, false);
      file.setWritable(true, false);
      fos = new FileOutputStream(file);
      OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
      try {
        osw.write(out.toString());
        osw.flush();
        osw.close();
        flag = true;
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return flag;
  }
}

