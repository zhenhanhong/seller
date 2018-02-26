package cn.rfatx.atc.util.gdev;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class GDEVHttpClient {
	private static Logger log = LoggerFactory.getLogger(GDEVHttpClient.class);
	private static final int REQUEST_TIMEOUT = 60 * 1000;// 设置请求超时1分钟
	private static final int SO_TIMEOUT = 60 * 1000; // 设置等待数据超时时间1分钟
    public static final int HTTP_MAX_CONNECTIONS = 200;
	
	private static PoolingHttpClientConnectionManager connManager = null;
	private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SO_TIMEOUT).setConnectTimeout(REQUEST_TIMEOUT).build();//设置请求和传输超时时间
	private static CloseableHttpClient client;
	public static IdleConnectionMonitorThread monitor;
	static {
		connManager = new PoolingHttpClientConnectionManager();
		//http://jinnianshilongnian.iteye.com/blog/2089792
		connManager.setMaxTotal(HTTP_MAX_CONNECTIONS);//设置整个连接池最大连接数 
		connManager.setDefaultMaxPerRoute(connManager.getMaxTotal());
		
		client = HttpClients.custom().setConnectionManager(connManager).build();
		monitor = new IdleConnectionMonitorThread(connManager);
		monitor.start();//启动线程，10秒钟清空一次失效连接
	}
	

	
	
	public static String doPostForGDEV(String url, String params,String token) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		try {
			httpPost.addHeader("Authorization","Bearer " + token);//Bearer后有空格
			httpPost.addHeader("Content-Type","application/json;charset=utf-8");
		    httpPost.setEntity(new StringEntity(params,HTTP.UTF_8));  
		    
		    long startTime = System.currentTimeMillis();
			response = client.execute(httpPost);
			long endTime = System.currentTimeMillis();
			long t = endTime - startTime;
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				entity = response.getEntity();
				String returnStr = EntityUtils.toString(entity, HTTP.UTF_8);
				return returnStr;
			}else{
				log.error("url="+url+",params="+params+",getStatusCode="+response.getStatusLine().getStatusCode());
			}
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}finally{

			try {
				EntityUtils.consume(entity);
				httpPost.abort();
				if(response!=null){
					response.close();
				}
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			}
		}
		
		return null;
	}
	

	
	
/**
 * 这个线程负责使用连接管理器清空失效连接和过长连接
 * @author litianyi
 *
 * 2016年1月5日
 */
static class IdleConnectionMonitorThread extends Thread {
	    
	    private final HttpClientConnectionManager connMgr;
	    private volatile boolean shutdown;
	    
	    public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
	      super();
	      this.connMgr = connMgr;
	    }
	    @Override
	    public void run() {
	      try {
	        while (!shutdown) {
	          synchronized (this) {
	            wait(5000);
	            log.debug("IdleConnectionMonitorThread 清空失效连接...");
	            // 关闭失效连接
	            connMgr.closeExpiredConnections();
	            //关闭空闲超过10秒的连接
	            connMgr.closeIdleConnections(10, TimeUnit.SECONDS);
	          }
	        }
	      } catch (InterruptedException ex) {
	      }
	    }
	    
	    public void shutdown() {
	      shutdown = true;
	      synchronized (this) {
	        notifyAll();
	      }
	    }
	  }

}
