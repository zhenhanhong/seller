package cn.rfatx.shiro;



import cn.rfatx.shiro.tag.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;


/**
 * shior标签免费配置标记
 */




public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer
{
  public void afterPropertiesSet() throws IOException, TemplateException
  {
     super.afterPropertiesSet();
    getConfiguration().setSharedVariable("shiro", new ShiroTags());
   }
 }


