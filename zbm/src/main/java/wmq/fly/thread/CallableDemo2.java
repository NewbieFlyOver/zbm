package wmq.fly.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import wmq.fly.thread.CallableDemo.SumTask;

public class CallableDemo2 {
	 //相较于实现 Runnable 接口的方式，区别在于Callable有返回值并且可以抛出异常。
	 static class SumTask implements Callable<Long> {
	        @Override
	        public Long call() throws Exception {
	            long sum = 0;
	            for (int i = 0; i < 5; i++) {
	            	 sum += i;
	                System.out.println("childern: "+i);
	            }
	            System.out.println("sum: "+ sum);
	            return sum;
	        }
	    }

	    public static void main(String[] args) throws ExecutionException, InterruptedException {
	      //执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
	        FutureTask<Long> futureTask = new FutureTask<Long>(new SumTask());
	        //执行线程方式一:
	        /*Executor executor=Executors.newSingleThreadExecutor();
	        executor.execute(futureTask);*/
	        //执行线程执行线程方式二:
	        new Thread(futureTask).start();
	       
	        for (int i = 0; i < 5; i++) {
                System.out.println("main: "+i);
            }
	        System.out.println("返回值： "+futureTask.get());
	        
	    }


}
