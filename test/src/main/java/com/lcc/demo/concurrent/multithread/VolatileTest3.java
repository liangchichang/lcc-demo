package com.lcc.demo.concurrent.multithread;

/**
 *
 * @author lcc
 * @version 2018/10/30
 *
 * volatile关键字不具有原子性实验
 */
public class VolatileTest3 extends Thread {

  private static volatile int count = 0;

  public static void main(String[] args) {

    for (int i = 0; i < 100; i++) {
      VolatileTest3 test = new VolatileTest3();
      test.start();
    }
  }

//  private void count100() {
//    for (int i = 0; i < 100; i++) {
//      count++;
//    }
//  }

  private synchronized static void count100() {
    for (int i = 0; i < 100; i++) {
      count++;
    }
    System.out.println("count=" + count);
  }

  @Override
  public void run() {
    count100();
  }
}
