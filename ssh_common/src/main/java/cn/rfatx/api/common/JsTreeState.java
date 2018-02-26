 package cn.rfatx.api.common;
 
 import java.io.Serializable;


 /**
  * 树交互所产生的状态类
  */
 public class JsTreeState
   implements Serializable
 {
   private static final long serialVersionUID = -561197024090900858L;
   private Boolean opened = Boolean.valueOf(false);
   private Boolean disabled = Boolean.valueOf(false);
   private Boolean selected = Boolean.valueOf(false);
   
   public Boolean getDisabled()
   {
     return this.disabled;
   }
   
    public void setDisabled(Boolean disabled) { this.disabled = disabled; }
   
   public Boolean getSelected() {
     return this.selected;
   }
   
   public void setSelected(Boolean selected) { this.selected = selected; }
   
   public Boolean getOpened() {
     return this.opened;
   }
   
   public void setOpened(Boolean opened) { this.opened = opened; }
 }
