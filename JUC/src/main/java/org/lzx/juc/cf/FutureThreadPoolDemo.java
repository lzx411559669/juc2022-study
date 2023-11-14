package org.lzx.juc.cf;

import java.util.concurrent.*;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.cf
 * @data 2023/11/11 10:45
 */
public class FutureThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //三个任务，目前开启多个异步任务线程来处理，请问耗时多少？

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        long startTime = System.currentTimeMillis();


        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}
            return "task1 over";
        });
        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace();}
            return "task2 over";
        });

        threadPool.submit(futureTask1);
        threadPool.submit(futureTask2);
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());


        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace();}

        long endTime = System.currentTimeMillis();
        System.out.println("--costTime：" + (endTime - startTime)+" 毫秒");

        System.out.println(Thread.currentThread().getName() + " 主线程执行完毕");

        System.out.println("不用异步任务线程耗时——————————————————————————");
        m1();
    }

    static void m1(){
         long startTime = System.currentTimeMillis();


        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}

        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace();}


        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace();}

        long endTime = System.currentTimeMillis();
        System.out.println("--costTime：" + (endTime - startTime)+" 毫秒");

    }
}
