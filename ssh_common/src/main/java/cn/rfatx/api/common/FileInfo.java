 package cn.rfatx.api.common;
 
 import java.util.Date;

 /**
  * 操作文件和文件夹在文件系统中的结构的数据类
  */
 public class FileInfo {
   private String name;
   private String url;
   private Boolean isDirectory;
   private Long size;
   private Date lastModified;
   
   public String getName() {
     return this.name;
   }
   
   public void setName(String name) { this.name = name; }
   
   public String getUrl() {
     return this.url;
   }
   
   public void setUrl(String url) { this.url = url; }
   
   public Boolean getIsDirectory() {
     return this.isDirectory;
   }
   
   public void setIsDirectory(Boolean isDirectory) { this.isDirectory = isDirectory; }
   
   public Long getSize() {
     return this.size;
   }
   
   public void setSize(Long size) { this.size = size; }
   
   public Date getLastModified() {
     return this.lastModified;
   }
   
   public void setLastModified(Date lastModified) { this.lastModified = lastModified; }
   
   public static enum FileType {
     image,  flash,  media,  file;
     
     private FileType() {} }
   public static enum OrderType { name,  size,  type;
     
     private OrderType() {}
   }
 }
