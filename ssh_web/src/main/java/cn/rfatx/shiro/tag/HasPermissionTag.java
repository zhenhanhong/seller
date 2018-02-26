 package cn.rfatx.shiro.tag;

 /**
  * 许可标签
  */

 public class HasPermissionTag
   extends PermissionTag
 {
   protected boolean showTagBody(String p)
   {
     return isPermitted(p);
   }
 }


