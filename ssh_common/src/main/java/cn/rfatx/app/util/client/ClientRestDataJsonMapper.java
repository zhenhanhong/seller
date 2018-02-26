 package cn.rfatx.app.util.client;
 


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;


 /**
  * 客户端数据对象转换
  */
 public class ClientRestDataJsonMapper
   extends ObjectMapper
 {
   public ClientRestDataJsonMapper()
   {
     configure(Feature.ALLOW_SINGLE_QUOTES, true);

     configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
     
 
 
 
     getSerializerProvider().setNullValueSerializer(new JsonSerializer()
     {
       public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
         jg.writeString("");
       }
     });
   }
 }

