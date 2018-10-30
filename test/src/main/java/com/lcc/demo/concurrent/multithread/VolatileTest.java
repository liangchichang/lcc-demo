package com.lcc.demo.concurrent.multithread;

import com.lcc.demo.Utils.LccThreadPool;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/10/29
 */
public class VolatileTest {

  private static volatile boolean stopThreadFlag;


  public static void main(String[] args) throws InterruptedException {

    LccThreadPool.getTheadPool().execute(() -> {
      int i = 0;
      while (!stopThreadFlag) {
        i++;
      }
      System.out.println(String.format("%s终止死循环,i=%s", Thread.currentThread().getName(), i));
    });
    Thread.sleep(10L);
    stopThreadFlag = true;
    System.out.println(String.format("%s线程结束", Thread.currentThread().getName()));
    LccThreadPool.getTheadPool().shutdown();
  }
}
