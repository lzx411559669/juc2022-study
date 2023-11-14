package org.lzx.juc.cf;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.cf
 * @data 2023/11/11 11:12
 */
public class FutureApiDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "come in ");
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task over";
        });

        new Thread(futureTask, "thread-1").start();

        System.out.println(Thread.currentThread().getName() + "main 线程指定其他任务");

//        System.out.println(futureTask.get());//这样会有阻塞的可能，在程序没有计算完毕的情况下。

//        System.out.println(futureTask.get(3, TimeUnit.SECONDS));//只会等待3s，如果在3s内没有计算完毕，则会抛出异常

        //轮询方式查询任务是否完成
        while (true) {
           if (futureTask.isDone()){
               System.out.println(futureTask.get());
               break;
           }else {
               try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace();}
               System.out.println("正在处理中，不要催了，越催越慢,再催熄火！！");
           }
        }
    }
}
