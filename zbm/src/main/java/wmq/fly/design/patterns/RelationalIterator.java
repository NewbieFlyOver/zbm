package wmq.fly.design.patterns;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 迭代子模式:  关系模式 --》 类之间的关系
 *	   顾名思义，迭代器模式就是顺序访问聚集中的对象，一般来说，集合中非常常见，如果对集合类比较熟悉的话，理解本模式会十分轻松。
 * 这句话包含两层意思：一是需要遍历的对象，即聚集对象，二是迭代器对象，用于对聚集对象进行遍历访问。
 *	 
 *     此处我们貌似模拟了一个集合类的过程，感觉是不是很爽？其实JDK中各个类也都是这些基本的东西，加一些设计模式，再加一些优化放到一起的，
 * 只要我们把这些东西学会了，掌握好了，我们也可以写出自己的集合类，甚至框架！
 *
 */


interface Collection {
	
	public Iterator iterator();
	//取得集合元素
	public Object get(int i);
	//取得集合大小
	public int size();
}


interface Iterator {
	
	//前移  
    public Object previous();  
    //后移  
    public Object next();  
    //是否有下一个元素
    public boolean hasNext();  
    //取得第一个元素  
    public Object first();  
}

class MyCollection implements Collection {

	String[] str = {"A","B","C","D"};
	
	@Override
	public Iterator iterator() {
		return new MyIterator(this);
	}

	@Override
	public Object get(int i) {
		return str[i];
	}

	@Override
	public int size() {
		return str.length;
	}  
	
}

class MyIterator implements Iterator {
	
	private Collection collection;
	private int pos = -1;

	public MyIterator(Collection collection) {
		this.collection = collection;
	}
	
	@Override
	public Object previous() {
		if(pos>0) {
			pos--;
		}
		return collection.get(pos);
	}

	@Override
	public Object next() {
		if(pos < collection.size()-1 ) {
			pos++;
		}
		return collection.get(pos);
	}

	@Override
	public boolean hasNext() {
		if(pos < collection.size()-1 ) {
			return true;
		}
		return false;
	}

	@Override
	public Object first() {
		pos = 0 ;
		return collection.get(pos);
	}  
}

public class RelationalIterator {

	public static void main(String[] args) {
	    Collection collection = new MyCollection();
	    Iterator it = collection.iterator();
	    System.out.println(it.first());
	    
	    while(it.hasNext()) {
	    	System.out.println(it.next());
	    }
	    
	    System.out.println(it.previous());
	}
}



