package wmq.fly.genericType;

import java.util.ArrayList;
import java.util.List;

public class ExtendsDemo {
	
	public static <T> void main(String[] args) {
		//List<? extends T> 能取不能存
		/*
		继承关系（父<--子）：BigBook<--Book<--BookColor
		上界<? extends T>表示: 类型的上界，表示参数化类型的(?)可能是T 或是 T的子类
			1. 因为编译器并不知道list到底是哪个类(只有在运行的时候才能确定指代的哪个类),如果list是Book,那么list.add(BigBook)就将一个父类赋值给子类了,是错误的.		
			2. 不能往里存，只能往外取,因为代表的是一个范围内的某个类,但是却不确定是哪个类。
		*/
		
		/*
		List<? extends BigBook> bookList = new ArrayList<BigBook>();
		Book book = new Book();
		bookList.add(book); //编译出错
		*/
		List<Book> bookList = new ArrayList<Book>();
		Book book = new Book();
		book.setName("book");
		book.setType("bookType");
		bookList.add(book);
		
		List<? extends BigBook> bookList01 = bookList;
		
		for(BigBook bb: bookList01) {
			System.out.println(bb.toString());
		}
		
	}

}
