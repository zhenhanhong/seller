 package cn.rfatx.api.listener;
 
 import cn.rfatx.entity.EmployeeAuditEntity;

 /**
  * 员工数据监听类
  */
 public class EmployeeEntityListener
 {
   @javax.persistence.PrePersist
   public void prePersist(EmployeeAuditEntity ae) {
     ae.setCreateTime(Long.valueOf(System.currentTimeMillis()));
     ae.setUpdateTime(Long.valueOf(System.currentTimeMillis()));
   }
   
   @javax.persistence.PreUpdate
   public void preUpdate(EmployeeAuditEntity ae) { ae.setUpdateTime(Long.valueOf(System.currentTimeMillis())); }
 }
