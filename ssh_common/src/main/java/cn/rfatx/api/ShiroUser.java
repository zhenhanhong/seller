 package cn.rfatx.api;



import com.google.common.base.Objects;

import java.io.Serializable;


 /**
  * 用户认证器类
  */
 public class ShiroUser
   implements Serializable
 {
   private static final long serialVersionUID = -1373760761780840081L;
   public Long id;
   public String loginName;
   public String name;

   public ShiroUser(Long id, String loginName, String name)
   {
     this.id = id;
     this.loginName = loginName;
     this.name = name;
   }

   public Long getId() { return this.id; }

   public String getName() {
/* 24 */     return this.name;
/*    */   }

   public String getLoginName() { return this.loginName; }




   public String toString()
   {
     return this.loginName;
   }



   public int hashCode()
   {
     return Objects.hashCode(new Object[] { this.loginName });
   }



   public boolean equals(Object obj)
   {
     if (this == obj) {
       return true;
     }
     if (obj == null) {
       return false;
     }
     if (getClass() != obj.getClass()) {
       return false;
     }
     ShiroUser other = (ShiroUser)obj;
     if (this.loginName == null) {
       if (other.loginName != null) {
         return false;
       }
     } else if (!this.loginName.equals(other.loginName)) {
       return false;
     }
     return true;
   }
 }
