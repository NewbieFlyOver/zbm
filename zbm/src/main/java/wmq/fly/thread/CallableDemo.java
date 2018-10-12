package wmq.fly.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableDemo {
	 //Callable和Runnbale一样代表着任务，区别在于Callable有返回值并且可以抛出异常。
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
	        System.out.println("Start:" + System.nanoTime());
	        
	        //执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
	        FutureTask<Long> futureTask = new FutureTask<Long>(new SumTask());
	        //执行线程方式一:
	        Executor executor=Executors.newSingleThreadExecutor();
	        executor.execute(futureTask);
	        //执行线程执行线程方式二:
	        //new Thread(futureTask).start();
	      
	        //Thread.sleep(10);
	        //取消线程,如果无法取消任务，则返回 false，这通常是由于它已经正常完成；否则返回 true
	        //futureTask.cancel(true);
	        try {
	    	 //取得返回值
		     System.out.println("返回值: "+futureTask.get());
		    // 如有必要，最多等待为使计算完成所给定的时间之后，获取其结果（如果结果可用）。
		     System.out.println("返回值: "+futureTask.get(1, TimeUnit.SECONDS));
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        //如果在任务正常完成前将其取消，则返回 true。
	        System.out.println("是否被取消： "+futureTask.isCancelled());
	        //如果任务已完成，则返回 true。 可能由于正常终止、异常或取消而完成，在所有这些情况中，此方法都将返回 true。
	        System.out.println("任务是否完成： "+futureTask.isDone());
	        
	        System.out.println("End:" + System.nanoTime());
	    }


}
