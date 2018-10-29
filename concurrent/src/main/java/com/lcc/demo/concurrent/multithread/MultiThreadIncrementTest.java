package com.lcc.demo.concurrent.multithread;

import com.lcc.demo.Utils.LccThreadPool;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/10/29
 */
public class MultiThreadIncrementTest {

  private int count = 0;

  public void notSafeAction() {
    int previous;
    int after;

    while (count < 1000000) {
      previous = count++;
      after = count;
      if (previous != after - 1) {
        System.out.println("previous=" + previous + "---after=" + after);
      }
    }
  }

  public static void main(String[] args) {
    MultiThreadIncrementTest incrementTest = new MultiThreadIncrementTest();
    Thread thread1 = new Thread(() -> incrementTest.notSafeAction());
    Thread thread2 = new Thread(() -> incrementTest.notSafeAction());
    thread1.start();
    thread2.start();
//    LccThreadPool.getTheadPool().execute(() -> incrementTest.notSafeAction());
    System.out.println("线程结束");
  }
}