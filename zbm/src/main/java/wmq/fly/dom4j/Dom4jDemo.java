package wmq.fly.dom4j;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * 使用dom4j解析xml
 *
 */
public class Dom4jDemo {
	public static void main(String[] args) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		//读取xml配置文件
		Document read = saxReader.read(new File("C:\\ww\\workSpace\\zbm\\zbm\\stu.xml"));
		// 获取根节点
		Element rootElement = read.getRootElement();
		getNodes(rootElement);

	}

	static public void getNodes(Element rootElement) {
		//获取节点名称
		System.out.println("当前节点名称:" + rootElement.getName());
		// 获取属性ID
		List<Attribute> attributes = rootElement.attributes();
		for (Attribute attribute : attributes) {
			System.out.println("属性:" + attribute.getName() + "---" + attribute.getText());
		}
		//获取节点中的值
		if (!rootElement.getTextTrim().equals("")) {
			System.out.println(rootElement.getName() + "--" + rootElement.getText());
		}
		// 使用迭代器遍历
		Iterator<Element> elementIterator = rootElement.elementIterator();
		while (elementIterator.hasNext()) {
			Element next = elementIterator.next();
			getNodes(next);
		}
	}		
}
