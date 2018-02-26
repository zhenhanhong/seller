 package cn.rfatx.shiro.tag;

 /**
  * 获得角色标签
  */


 public class HasRoleTag extends RoleTag
 {
   protected boolean showTagBody(String roleName) {
    return (getSubject() != null) && (getSubject().hasRole(roleName));
   }
 }


