package wmq.fly.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * 用URLConnection向其他服务器发送请求的工具
 *
 */

public class ScHttpRequest {
	
	/**
	 * 
	 * @param requestUrl url地址
	 * @param method 请求方式：post、get
	 * @param params 参数
	 * @return 响应体
	 * @throws IOException
	 */
	public static String sendHttpRequest(String requestUrl, String method,
            Map<String, String> params) throws IOException {
         
       StringBuffer requestParams = new StringBuffer();
        
       if (params != null && params.size() > 0) {
           Iterator<String> paramIterator = params.keySet().iterator();
           while (paramIterator.hasNext()) {
               String key = paramIterator.next();
               String value = params.get(key);
               requestParams.append(URLEncoder.encode(key, "UTF-8"));
               requestParams.append("=").append(URLEncoder.encode(value, "UTF-8"));
               requestParams.append("&");
           }
       }
        
       URL url = new URL(requestUrl);
       URLConnection urlConn = url.openConnection();
       urlConn.setUseCaches(false);
             
       // the request will return a response
       urlConn.setDoInput(true);

       if ("POST".equals(method)) {
    	   // set request method to POST
    	   urlConn.setDoOutput(true);
       } else {
    	   // set request method to GET
    	   urlConn.setDoOutput(false);
       }
            
       // only post will send out params???
       if ("POST".equals(method) && params != null && params.size() > 0) {
    	   OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream(), "UTF-8");
    	   writer.write(requestParams.toString());
    	   writer.flush();   
    	   writer.close();
       }

       // reads response, store line by line in an array of Strings
       BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

       StringBuffer response = new StringBuffer();
       String line = "";
       while ((line = reader.readLine()) != null) {
    	   response.append(line);
       }

       reader.close();
       
       System.out.println("ClientSide receiving\r\n " + response.toString());

       return response.toString();
    }

}
