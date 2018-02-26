 package cn.rfatx.api.common;
 
 import java.io.Serializable;

 /**
  * 树交互所使用的数据类
  */
 public class JsTree implements Serializable
 {
   private static final long serialVersionUID = -283431484704582527L;
   private String id;
   private String parent;
   private String text;
   private String icon;
   private JsTreeState state = new JsTreeState();
   
   public String getId() { return this.id; }

   public void setId(String id) {
     this.id = id;
   }
   
   public String getParent() { return this.parent; }
   
   public void setParent(String parent) {
     this.parent = parent;
   }
   
   public String getText() { return this.text; }
   
   public void setText(String text) {
     this.text = text;
   }
   
   public String getIcon() { return this.icon; }
   
   public void setIcon(String icon) {
     this.icon = icon;
   }
   
   public JsTreeState getState() { return this.state; }
   
   public void setState(JsTreeState state) {
     this.state = state;
   }
 }
