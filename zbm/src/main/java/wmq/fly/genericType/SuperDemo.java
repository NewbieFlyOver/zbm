package wmq.fly.genericType;

import java.util.ArrayList;
import java.util.List;

public class SuperDemo {
	
	public static void main(String[] args) {
		//List<? super T> ：能存不能取
		/*
		继承关系（父<--子）：BigBook<--Book<--BookColor
		<? super T> 表示: 类型下界（Java Core中叫超类型限定），表示参数化类型(?)是此类型(T)的超类型（父类型），直至Object;
	      	因为下界规定了元素的最小粒度的下限，实际上是放松了容器元素的类型控制。既然元素是BigBook的基类，那往里存粒度比BigBook小的都可以。
			<? super T>,因为它有下限,故我们可以马上得出,如果向其中添加T类型的对象是没问题的.因为<? super T>是T的某个父类,将子类T赋值给父类没任何问题
			super只能添加BigBook和BigBook的子类，不能添加BigBook的父类,读取出来的东西只能存放在Object类里
		*/

		List<? super BigBook> bigBookList = new ArrayList<BigBook>();
		
		Book book = new Book();
		book.setName("book");
		book.setType("bookType");
		bigBookList.add(book);
		
		//编译报错：不能确定，它是Object还是Book类型。但无论是填充为Object还是Book，它必然是Object的子类！
		//所以Object object = bigBookList.get(0);是不报错的。因为 bigBookList.get(0);肯定是Object的子类；
		//这里虽然看起来是能取的，但取出来一个Object类型，是毫无意义的。所以我们认为super通配符：能存不能取；
		
		//Book tempBook = bigBookList.get(0);//编译报错

		Object object = bigBookList.get(0);
		
		for(Object bb: bigBookList) {
			System.out.println(bb.toString());
		}

	}
}
