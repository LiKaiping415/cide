package com.qxwz.qle.future;

import com.qxwz.qle.atomic.HttpCommonUtil;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class CompleteFutureTest {

    @Autowired
    HttpCommonUtil httpCommonUtil;

    @Test
    public void getCombinedResponse() throws ExecutionException, InterruptedException {

    }

    public void addParallTask(Supplier supplier) {
        CompletableFuture.supplyAsync(supplier);
    }

    /*Supplier supplier1 = new Supplier() {
        @Override
        public Object get() {
            return sendRequest1();
        }
    };*/

    static long startTime = System.currentTimeMillis();
    @Test
    public void parallelRequestSendTest() {
        System.out.println("开始执行");
        startTime = System.currentTimeMillis();
        HttpGet httpGet = new HttpGet("https://www.qxwz.com/bffportal/proxy/cms/v2/api/datas/query?tag=portal-home-v2");
        HttpGet httpGet2 = new HttpGet("https://www.qxwz.com/bffportal/proxy/omsconfigservice/api/hotProduct/open/list?channel=PC&isOperating=true");
        System.out.println("准备http请求时间：" + (System.currentTimeMillis() - startTime));
        sendNormalRequest(httpGet,httpGet2);
        System.out.println("总耗时：" + (System.currentTimeMillis() - startTime));
    }

    public Map<Object, Object> parallelRequestSend(HttpRequestBase... httpEntityEnclosingRequestBase) {
        List<CompletableFuture> futures = new ArrayList<>();
        long begin = System.currentTimeMillis();
        for (HttpRequestBase entityEnclosingRequestBase : httpEntityEnclosingRequestBase) {
            System.out.println("准备开始并行任务:" + (System.currentTimeMillis() - startTime));
            futures.add(CompletableFuture.supplyAsync(() -> {
                return new HttpCommonUtil().sendHttpRequest(entityEnclosingRequestBase);
            }));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[]{}));
        System.out.println("所有并行任务执行完毕:" + (System.currentTimeMillis() - startTime));
        for (CompletableFuture future : futures) {
            try {
                Map map = (Map)future.get();
                System.out.println("获取一个并行任务结果:" + (System.currentTimeMillis() - startTime));
                System.out.println(map);
                //System.out.println(System.currentTimeMillis() - begin);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("整体结束：" + (System.currentTimeMillis() - begin));
        return null;
    }

    public Map sendNormalRequest(HttpRequestBase... httpEntityEnclosingRequestBase) {
        for (HttpRequestBase httpRequestBase : httpEntityEnclosingRequestBase) {
            System.out.println(new HttpCommonUtil().sendHttpRequest(httpRequestBase));
        }
        return null;
    }
}
