package org.lzx.juc.cf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.cf
 * @data 2023/11/11 12:52
 */
public class CompletableFutureMallDemo {

   static   List<NetMall> mallList = Arrays.asList(new NetMall("淘宝"), new NetMall("京东"), new NetMall("当当"));
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> prices = getPrices(mallList, "mysql");
        for (String price : prices) {
            System.out.println(price);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("--costTime：" + (endTime - startTime)+" 毫秒");

         long startTime1 = System.currentTimeMillis();
        List<String> prices2 = getPriceByCompletableFuture(mallList, "mysql");

        for (String price : prices2) {
            System.out.println(price);
        }

        long endTime1 = System.currentTimeMillis();
         System.out.println("--costTime：" + (endTime1 - startTime1)+" 毫秒");

    }

    /**
     * step by step
     * @param mallList
     * @param productName
     * @return
     */
    static List<String> getPrices(List<NetMall> mallList, String productName){

        return mallList.stream().map(netMall -> netMall.calcPrice(productName)).map(price->{
            return String.format("《" + productName + "》" + "in %s price is %.2f",productName, price);
        }).collect(java.util.stream.Collectors.toList());
    }

    /**
     * all in
     * 把list里面的内容映射给CompletableFuture()
     * @param mallList
     * @param productName
     * @return
     */
    static List<String> getPriceByCompletableFuture(List<NetMall> mallList, String productName){
        return mallList
                .stream()
                .map(netMall -> CompletableFuture.supplyAsync(()->{
                    return String.format("《" + productName + "》" + "in %s price is %.2f",productName, netMall.calcPrice(productName));
                }))
                .collect(Collectors.toList())
                .stream()
                .map(e->e.join())
                .collect(Collectors.toList());

    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)// 链式编程
class Student{

    private String name;

    private int age;

    private String marjor;
}

@NoArgsConstructor
@Data
class NetMall{
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName){
       try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace();}
       return ThreadLocalRandom.current().nextDouble()*2+productName.charAt(0);
    }
}