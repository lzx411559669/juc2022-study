package org.lzx.juc.cf;

import java.util.concurrent.*;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.cf
 * @data 2023/11/11 12:23
 */
public class CompletableFutureUseDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "come in");
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int result = ThreadLocalRandom.current().nextInt(10);
                if (result > 5) {
                    int i = 10 / 0;
                }
                return result;
            },executorService).whenComplete((v, e) -> {
                //v是计算结果，e是异常，如果没有异常，打印v
                if (e == null) {
                    System.out.println("计算完成 更新系统：" + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常：" + e.getCause() + "\t" + e.getMessage());
                return null;
            });
            System.out.println(Thread.currentThread().getName() + "main 先去完成其他任务");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }


        //主线程睡眠3秒, 让其他线程执行完毕,否则主线程会立即退出，导致其他线程没有执行，或者使用自定义线程池
        //try { TimeUnit.MILLISECONDS.sleep(3000); } catch (InterruptedException e) { e.printStackTrace();}
    }
}
