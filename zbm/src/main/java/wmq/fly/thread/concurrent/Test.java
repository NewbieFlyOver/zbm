package wmq.fly.thread.concurrent;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  几类集合线程是否安全的比较，可以查看源码 
 *
 */
public class Test {
	public static void main(String[] args) {
		//集合
		Vector vector = new Vector();
		vector.add("asdf");
		vector.get(0);
		
		ArrayList list = new ArrayList();
		list.add("asdf");
		list.get(0);
		
		LinkedList linkedList = new LinkedList();
		linkedList.add("asd");
		
		//map
		HashMap<Object, Object> hashMap = new HashMap<Object , Object>();
		hashMap.put("", "");
		
		Hashtable<Object, Object> hashtable = new Hashtable<Object , Object>();
		hashtable.put("", "");
		
		Collections.synchronizedCollection(list);
		
		//并发包中的，ConcurrentHashMap是线程安全的，与Hashtable相比效率更高，其内部默认分成16个Hashtable，每个Hashtable的锁各不相同。
		ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<Object,Object>();
		concurrentHashMap.put("", "");
	}

}
