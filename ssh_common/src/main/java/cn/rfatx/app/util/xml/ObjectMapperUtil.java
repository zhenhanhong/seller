/*    */ package cn.rfatx.app.util.xml;
/*    */ 
/*    */

import cn.rfatx.core.diva.mapper.JsonMapper;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/*    */
/*    */
/*    */

/*    */
/*    */ public class ObjectMapperUtil extends JsonMapper
/*    */ {
/*    */   private ObjectMapper mapper;
/*    */   
/*    */   public ObjectMapperUtil(Include include)
/*    */   {
/* 14 */     this.mapper = new ObjectMapper();
/*    */     
/* 16 */     if (include != null) {
/* 17 */       this.mapper.setSerializationInclusion(include);
/*    */     }
/*    */     
/* 20 */     this.mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
/*    */   }
/*    */   
/* 23 */   public ObjectMapperUtil() { this.mapper = new ObjectMapper(); }
/*    */   
/*    */   public ObjectMapper getMapper() {
/* 26 */     return this.mapper;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses)
/*    */   {
/* 39 */     return this.mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
/*    */   }
/*    */ }

