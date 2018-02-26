 package cn.rfatx.shiro.tag;


 /**
  * 缺少角色标签
  */

 public class LacksRoleTag extends RoleTag
 {
   protected boolean showTagBody(String roleName) {
    boolean hasRole = (getSubject() != null) && (getSubject().hasRole(roleName));
     return !hasRole;
   }
 }


