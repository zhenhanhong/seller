 package cn.rfatx.shiro.tag;

 /**
  * 缺少许可标签
  */
 public class LacksPermissionTag
   extends PermissionTag
 {
   protected boolean showTagBody(String p)
   {
    return !isPermitted(p);
   }
 }


