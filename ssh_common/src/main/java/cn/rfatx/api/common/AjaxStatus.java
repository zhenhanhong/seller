 package cn.rfatx.api.common;

 import java.io.Serializable;

 /**
  * 客户端与客户的交互状态
  */
 public class AjaxStatus implements Serializable
 {
   private Boolean success;
   private String code;
   private String message;
   public AjaxStatus(Boolean success) { this.success = success; }
   
   public AjaxStatus(Boolean success, String message) {
     this.success = success;
     this.message = message;
   }
   
   public Boolean isSuccess() { return this.success; }
   
   public void setSuccess(Boolean success) {
     this.success = success;
   }
   
   public String getCode() { return this.code; }
   
   public void setCode(String code) {
     this.code = code;
   }
   
   public String getMessage() { return this.message; }
   
   public void setMessage(String message) {
     this.message = message;
   }
 }
