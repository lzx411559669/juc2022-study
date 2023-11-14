package org.lzx.juc.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.cf
 * @data 2023/11/13 20:08
 */
public class CompletableFutureApiDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }, threadPool).thenApply(f -> {
            System.out.println("第二步");
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("第三步");
            int i = 10 / 0;
            return f + 4;
//        }).thenApply(f->{
//            System.out.println("第三步");
//            int i = 10 / 0;
//            return f+4;
        }).whenComplete((f, e) -> {
            System.out.println(e.getCause());
            if (e == null) {
                System.out.println("计算结果：" + f);
            }
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            System.out.println(throwable.getCause() + "\t" + throwable.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + "主线程干其他事情");
    }
}
