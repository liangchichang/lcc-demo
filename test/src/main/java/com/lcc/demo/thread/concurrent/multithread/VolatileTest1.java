package com.lcc.demo.thread.concurrent.multithread;

import com.lcc.demo.thread.Utils.LccThreadPool;

/**
 * @author lcc
 * @version 2018/10/30
 *
 * volatile可见性实验1.
 * 在异步线程内，JVM为了效率一直都从工作内存中读取变量，因此陷入死循环
 */
public class VolatileTest1 {

  //  private static boolean stopThreadFlag = true;
  private static volatile boolean stopThreadFlag = true;

  public static void main(String[] args) throws InterruptedException {

    LccThreadPool.getTheadPool().execute(() -> {
      while (stopThreadFlag) {
      }
      System.out.println(String.format("%s终止死循环", Thread.currentThread().getName()));
    });
    Thread.sleep(10L);
    stopThreadFlag = false;
    System.out.println(String.format("%s线程结束", Thread.currentThread().getName()));
    LccThreadPool.getTheadPool().shutdown();
  }
}
