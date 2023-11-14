package org.lzx.juc.cf;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.cf
 * @data 2023/11/11 10:16
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>(new MyThread());
        Thread thread = new Thread(futureTask);
        System.out.println("开启线程");
        thread.start();
        System.out.println("主线程：获取结果");
        System.out.println(futureTask.get());
        long startTime = System.currentTimeMillis();

        long endTime = System.currentTimeMillis();
        System.out.println("--costTime：" + (endTime - startTime) + " 毫秒");

    }
}

class MyThread implements Callable {

    @Override
    public String call() throws Exception {
        //三秒后返回结果
        TimeUnit.SECONDS.sleep(3);

        return "call 结果";
    }

}
