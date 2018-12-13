package wxutils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;  

public class XmlUtil {

	private static final String PREFIX_XML = "<xml>";

	private static final String SUFFIX_XML = "</xml>";

	private static final String PREFIX_CDATA = "<![CDATA[";

	private static final String SUFFIX_CDATA = "]]>";

	/**
	 * 转化成xml, 单层无嵌套
	 * 
	 * @param map
	 * @param isAddCDATA
	 * @return
	 */
	public static String xmlFormat(Map<String, String> parm, boolean isAddCDATA) {

		StringBuffer strbuff = new StringBuffer(PREFIX_XML);
		if (CollectionUtil.isNotEmpty(parm)) {
			for (Entry<String, String> entry : parm.entrySet()) {
				strbuff.append("<").append(entry.getKey()).append(">");
				if (isAddCDATA) {
					strbuff.append(PREFIX_CDATA);
					if (StringUtil.isNotEmpty(entry.getValue())) {
						strbuff.append(entry.getValue());
					}
					strbuff.append(SUFFIX_CDATA);
				} else {
					if (StringUtil.isNotEmpty(entry.getValue())) {
						strbuff.append(entry.getValue());
					}
				}
				strbuff.append("</").append(entry.getKey()).append(">");
			}
		}
		return strbuff.append(SUFFIX_XML).toString();
	}

	/**
	 * 解析xml
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static Map<String, String> xmlParse(String xml) throws XmlPullParserException, IOException {
		Map<String, String> map = null;
		if (StringUtil.isNotEmpty(xml)) {
			InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
			XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
			pullParser.setInput(inputStream, "UTF-8"); // 为xml设置要解析的xml数据
			int eventType = pullParser.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					map = new HashMap<String, String>();
					break;
				case XmlPullParser.START_TAG:
					String key = pullParser.getName();
					if (key.equals("xml"))
						break;
					String value = pullParser.nextText().trim();
					map.put(key, value);
					break;
				case XmlPullParser.END_TAG:
					break;
				}
				eventType = pullParser.next();
			}
		}
		return map;
	}
	
	
	// ===========================【将xml格式的字符串转化map对象】==============================
	
	/**
     * 将微信服务器发送的Request请求中Body的XML解析为Map
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseRequestXmlToMap(HttpServletRequest request) throws Exception {
        // 解析结果存储在HashMap中
        Map<String, String> resultMap;
        InputStream inputStream = request.getInputStream();
        resultMap = parseInputStreamToMap(inputStream);
        return resultMap;
    }

    /**
     * 将输入流中的XML解析为Map
     *
     * @param inputStream
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static Map<String, String> parseInputStreamToMap(InputStream inputStream) throws DocumentException, IOException {
        // 解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        //得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        //遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        //释放资源
        inputStream.close();
        return map;
    }

    /**
     * 将String类型的XML解析为Map
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseXmlStringToMap(String str) throws Exception {
        Map<String, String> resultMap;
        InputStream inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        resultMap = parseInputStreamToMap(inputStream);
        return resultMap;
    }
	
}
