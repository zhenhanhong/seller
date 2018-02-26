 package cn.rfatx.shiro.tag;
 


import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.util.Map;


 /**
  * 安全标签
  */





 public abstract class SecureTag implements freemarker.template.TemplateDirectiveModel
 {
   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
   {
     verifyParameters(params);
     render(env, params, body);
   }
   
   public abstract void render(Environment paramEnvironment, Map paramMap, TemplateDirectiveBody paramTemplateDirectiveBody) throws IOException, TemplateException;
   
   protected String getParam(Map params, String name) {
     Object value = params.get(name);
     
     if ((value instanceof SimpleScalar)) {
      return ((SimpleScalar)value).getAsString();
     }

     return null;
   }
   
   protected org.apache.shiro.subject.Subject getSubject() {
     return org.apache.shiro.SecurityUtils.getSubject();
   }
   
   protected void verifyParameters(Map params) throws freemarker.template.TemplateModelException
   {}
   
   protected void renderBody(Environment env, TemplateDirectiveBody body) throws IOException, TemplateException {
     if (body != null) {
       body.render(env.getOut());
     }
   }
 }


