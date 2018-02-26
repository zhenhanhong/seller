/*    */ package cn.rfatx.app.util.xml;
/*    */ 
/*    */

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*    */

/*    */
/*    */ 
/*    */ 
/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XmlUtil
/*    */ {
/*    */   public static String getCenterStr(String xml, String xmlName)
/*    */   {
/* 17 */     String str = "";
/*    */     
/* 19 */     Pattern mpattern = Pattern.compile("(?<=" + xmlName + "\\>\\s?)([\\s\\S]*?)(?=\\s?\\</" + xmlName + ")");
/* 20 */     Matcher mmatcher = mpattern.matcher(xml);
/* 21 */     while (mmatcher.find()) {
/* 22 */       str = str + mmatcher.group();
/*    */     }
/* 24 */     str = str + "";
/* 25 */     return str;
/*    */   }

            public static String covObjectToXml(Object object){
                StringWriter writer = new StringWriter();
                try {
                    // 获得转换的上下文对象
                    JAXBContext context = JAXBContext.newInstance(object.getClass());
                    // 将对象转变为xml
                    Marshaller marshaller = context.createMarshaller();
                    // 是否格式化生成的xml串
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                    // 将对象转换为对应的XML
                    marshaller.marshal(object, writer);
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                return writer.toString();
            }

            public static Object covXmlToDto(Class dtoCls, String str){
                StringReader reader = new StringReader(str);
                Object object = null;
                try {
                    JAXBContext context = JAXBContext.newInstance(dtoCls);
                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    object = unmarshaller.unmarshal(reader);
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                return object;
            }
/*    */ }
