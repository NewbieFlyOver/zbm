package wmq.fly.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 *  自定义注解的使用
 *
 */

//定义类注解
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface Table{
	String name() default "";
}
//定义属性注解
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface property{
	String value() default "";
	int length() default 0;
	String[] arrary() default {};
	
}

@Table(name="one_table")
public class AnnotationDemo {

	@property(value="name",length=2)
	private String name;
	@property(value="age",length=1)
	private String age;
	@property(value="addr", arrary= {"1","2"})
	private String addr;
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> forName = Class.forName("wmq.fly.annotation.AnnotationDemo");
		//获取表名
		Table declaredAnnotation = forName.getDeclaredAnnotation(Table.class);
		//获取注解中name的值
		String tableName = declaredAnnotation.name();
		StringBuffer sb = new StringBuffer("select ");
		//获取当前所有属性
		Field[] declaredFields = forName.getDeclaredFields();
		for(Field field : declaredFields) {
			property declaredAnnotation2 = field.getDeclaredAnnotation(property.class);
			//获取注解中value的值
			String propertyName = declaredAnnotation2.value();
			sb.append(propertyName+", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(" from "+tableName);
		System.out.println(sb.toString());
	}
}




