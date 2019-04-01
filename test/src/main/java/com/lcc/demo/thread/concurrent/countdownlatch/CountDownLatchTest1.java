package com.lcc.demo.thread.concurrent.countdownlatch;

import com.lcc.demo.thread.Utils.LccThreadPool;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author lcc
 * @version 2019/4/1
 */
public class CountDownLatchTest1 {

  public static void main(String[] args) throws InterruptedException {

    CountDownLatch countDownLatch = new CountDownLatch(3);
    for (int i = 0; i < 3; i++) {
      LccThreadPool.getTheadPool().execute(() -> {
        System.out.println("一点都不count down");
        throw new RuntimeException("123");
      });
    }
    countDownLatch.await();
    System.out.println("测试");
  }
}
