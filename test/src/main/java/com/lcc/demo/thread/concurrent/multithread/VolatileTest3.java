package com.lcc.demo.thread.concurrent.multithread;

/**
 * @author lcc
 * @version 2018/10/30
 *
 * volatile不保证原子性实验.
 */
public class VolatileTest3 extends Thread {

  private static volatile int count = 0;

  public static void main(String[] args) throws InterruptedException {

    for (int i = 0; i < 100; i++) {
      VolatileTest3 test = new VolatileTest3();
      test.start();
    }
    Thread.sleep(2000);
    System.out.println(count);
  }

//  private void count100() {
//    for (int i = 0; i < 100; i++) {
//      count++;
//    }
//  }

  private static synchronized void count100() {
    for (int i = 0; i < 100; i++) {
      count++;
    }
  }

  @Override
  public void run() {
    count100();
  }
}
