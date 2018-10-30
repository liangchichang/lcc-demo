package com.lcc.demo.concurrent.multithread;

import com.lcc.demo.Utils.LccThreadPool;

/**
 * @author lcc
 */
public class VolatileTest1 {

  private static boolean stopThreadFlag;

  public static void main(String[] args) throws InterruptedException {

    LccThreadPool.getTheadPool().execute(() -> {
      while (!stopThreadFlag) {
      }
      System.out.println(String.format("%s终止死循环", Thread.currentThread().getName()));
    });
    Thread.sleep(10L);
    stopThreadFlag = true;
    System.out.println(String.format("%s线程结束", Thread.currentThread().getName()));
    LccThreadPool.getTheadPool().shutdown();
  }
}
