package wmq.fly.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  cas无锁机制
 *
 */
public class CASDemo {
	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger();
		atomicInteger.incrementAndGet();
	}

}
