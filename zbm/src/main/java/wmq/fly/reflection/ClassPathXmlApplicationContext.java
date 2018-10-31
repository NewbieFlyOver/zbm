package wmq.fly.reflection;

import java.lang.reflect.Field;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

public class ClassPathXmlApplicationContext {
	private String pathXml = null;

	public ClassPathXmlApplicationContext(String pathXml) {
		this.pathXml = pathXml;
	}

	public Object getBean(String beanId) throws Exception {
		if (StringUtils.isEmpty(beanId)) {
			throw new Exception("beanId is null");
		}
		//解析xml
		SAXReader saxReader = new SAXReader();
		//this.getClass().getClassLoader().getResource(pathXml)表示获取resource下的xml路径
		Document read = saxReader.read(this.getClass().getClassLoader().getResource(pathXml));
		// 获取到根节点
		Element rootElement = read.getRootElement();
		// 根节点下所有的子节点
		List<Element> elements = rootElement.elements();
		for (Element element : elements) {
			// 获取到节点上的属性
			String id = element.attributeValue("id");
			if (StringUtils.isEmpty(id)) {
				continue;
			}
			if (!id.equals(beanId)) {
				continue;
			}
			// 使用java反射机制初始化对象
			String beanClass = element.attributeValue("class");
			Class<?> forName = Class.forName(beanClass);
			Object newInstance = forName.newInstance();
			List<Element> propertyElementList = element.elements();
			//使用java的反射机制给私有属性赋值
			for (Element el : propertyElementList) {
				String name = el.attributeValue("name");
				String value = el.attributeValue("value");
				Field declaredField = forName.getDeclaredField(name);
				declaredField.setAccessible(true);
				declaredField.set(newInstance, value);
			}
			return newInstance;
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext classPath = new ClassPathXmlApplicationContext("applicationContext.xml");
		ReflectUser reflectUser = (ReflectUser) classPath.getBean("reflectUser");
		System.out.println(reflectUser.getName()+ "---" + reflectUser.getAddr());
	}
}
