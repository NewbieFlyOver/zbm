package wmq.fly.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
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
	 * @param method     请求方式：post、get
	 * @param params     参数
	 * @return 响应体
	 * @throws IOException
	 */
	public static String sendHttpRequest(String requestUrl, String method, Map<String, String> params)
			throws IOException {

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

	/**
	 * 
	 * @param requrl url地址 请求方式：post
	 * @param params 参数(Json 类型的，如：String param =
	 *               "{\"page\":\"1\",\"rows\":\"1\"}";)
	 * @return 响应体
	 * @throws IOException
	 */
	public static String sendHttpRequest(String requrl, String param) {
		URL url;
		String sTotalString = "";
		try {
			url = new URL(requrl);
			URLConnection connection = url.openConnection();

			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			// connection.setRequestProperty("Content-Type", "text/xml");
			// 在Http协议消息头中，使用Content-Type来表示具体请求中的媒体类型信息。根据需要进行修改
			connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			// connection.setRequestProperty("Content-Length", body.getBytes().length+"");
			connection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");

			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
			out.write(param); // 向页面传递数据。post的关键所在！
			out.flush();
			out.close();
			// 一旦发送成功，用以下方法就可以得到服务器的回应：
			String sCurrentLine;

			sCurrentLine = "";
			sTotalString = "";
			InputStream l_urlStream;
			l_urlStream = connection.getInputStream();
			// 输出
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				sTotalString += sCurrentLine + "\r\n";

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(sTotalString);
		return sTotalString;
	}

	public static void main(String[] args) throws IOException {
		String param = "{" + "\"page\":\"1\"," + "\"rows\":\"1\"" + "}";

		String reStrBody = sendHttpRequest("http://localhost:8090/huiyangche/sysManagement/selectSysRotation.do",
				param);
		System.out.println(reStrBody);
	}

}
