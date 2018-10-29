package com.lcc.demo.concurrent.multithread;

import com.lcc.demo.lock.synchronizedd.DeadLockTest;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/10/29
 */
public class VolatileTest {

  private static volatile boolean stopThreadFlag;

  public static void main(String[] args) throws InterruptedException {

    DeadLockTest.EXECUTOR.execute(() -> {
      int i = 0;
      while (!stopThreadFlag) {
        i++;
        System.out.println(i);
      }
      System.out.println("终止死循环");
    });

    Thread.sleep(1000L);
    stopThreadFlag = true;
  }
}
