package wmq.fly.genericType;

/**
 *  用于总结记录
 *
 */
public class Conclusion {
	/*
	 * 
	◆ 如果你想从一个数据类型里获取数据，使用 ? extends 通配符（能取不能存）
	◆ 如果你想把对象写入一个数据结构里，使用 ? super 通配符（能存不能取）
	◆ 如果你既想存，又想取，那就别用通配符。可以用泛型T
	
	类型绑定：extends
		有时候，你会希望泛型类型只能是某一部分类型，比如操作数据的时候，你会希望是Number或其子类类型。这个想法其实就是给泛型参数添加一个界限。
		其定义形式为：
		<T extends BoundingType>
		此定义表示T应该是BoundingType的子类型（subtype）。T和BoundingType可以是类，也可以是接口。另外注意的是，此处的”extends“表示的子类型，
		不等同于继承。一定要非常注意的是，这里的extends不是类继承里的那个extends！两个根本没有任何关联。在这里extends后的BoundingType可以是类，
		也可以是接口，意思是说，T是在BoundingType基础上创建的，具有BoundingType的功能。目测是JAVA的开发人员不想再引入一个关键字，所以用已有的
		extends来代替而已。
		类型绑定有两个作用：1、对填充的泛型加以限定 2、使用泛型变量T时，可以使用BoundingType内部的函数。
		
		有关绑定限定的用法，其实我们可以同时绑定多个绑定,用&连接，
		比如：public static <T extends Fruit&Serializable> String getFruitName(T t){    return t.getName();}
		再加深下难度，如果我们有多个泛型，每个泛型都带绑定，那应该是什么样子的呢：
		public static <T extends Comparable & Serializable, U extends Runnable> T foo(T a, U b){	…………}
		大家应该看得懂，稍微讲一下：这里有两个泛型变量T和U,将T与Comparable & Serializable绑定，将U与Runnable绑定。


	无边界通配符？则只能用于填充泛型变量T，表示通配任何类型

	参考博客（写的非常好，值得观看）：https://blog.csdn.net/harvic880925/article/details/49883589
	 */
}
