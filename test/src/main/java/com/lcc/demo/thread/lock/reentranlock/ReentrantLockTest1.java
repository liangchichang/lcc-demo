package com.lcc.demo.thread.lock.reentranlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lcc
 * @version 2019/1/15
 */
public class ReentrantLockTest1 {

  private static final Lock LOCK = new ReentrantLock(true);

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new Thread(new RunImpl());
    Thread thread2 = new Thread(new RunImpl());
    Thread thread3 = new Thread(new RunImpl());
    Thread thread4 = new Thread(new RunImpl());
    Thread thread5 = new Thread(new RunImpl());
    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();
    thread5.start();

    Thread.sleep(1000);

//    thread1.interrupt();
//    thread2.interrupt();
//    thread3.interrupt();
//    thread4.interrupt();
    thread5.interrupt();

    thread1.join();
    thread2.join();
    thread3.join();
    thread4.join();
    thread5.join();
  }

  private static void test() throws InterruptedException {
//    System.out.println(Thread.currentThread().getName() + "---尝试获取锁");
//    synchronized ("1") {
//      System.out.println(Thread.currentThread().getName() + "----获取锁成功");
//      Thread.sleep(100000);
//    }

    System.out.println(Thread.currentThread().getName() + "---尝试获取锁");
    try {
      LOCK.lockInterruptibly();
      System.out.println(Thread.currentThread().getName() + "----获取锁成功");
      Thread.sleep(5000);
      System.out.println(Thread.currentThread().getName() + "---释放锁成功");
    }finally {
      System.out.println(Thread.currentThread().getName() + "---finally执行");
      LOCK.unlock();
    }
  }

  private static class RunImpl implements Runnable{

    @Override
    public void run() {
      try {
        test();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
