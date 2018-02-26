 package cn.rfatx.api;
 
 import cn.rfatx.entity.AuditEntity;

 /**
  * 数据监听类
  */
 public class EntityListener
 {
   /**
    * 拦截器（实体插入到数据库过程中触发）
    * @param ae 实体监听数据
    */
   @javax.persistence.PrePersist
   public void prePersist(AuditEntity ae)
   {
     ae.setCreateTime(Long.valueOf(System.currentTimeMillis()));
     ae.setUpdateTime(Long.valueOf(System.currentTimeMillis()));
     ae.setCreateId(id());
     ae.setUpdateId(id());
   }

   /**
    * 拦截器（更新实体后触发）
    * @param ae 实体监听数据
      */
   @javax.persistence.PreUpdate
   public void preUpdate(AuditEntity ae) { ae.setUpdateId(id());
     ae.setUpdateTime(Long.valueOf(System.currentTimeMillis()));
   }
   
   private Long id() {
     try { ShiroUser user = (ShiroUser)org.apache.shiro.SecurityUtils.getSubject().getPrincipal();
       if (user != null) {
         return user.getId();
       }
       return Long.valueOf(0L);
     }
     catch (Exception e) {}
     return Long.valueOf(0L);
   }
 }
