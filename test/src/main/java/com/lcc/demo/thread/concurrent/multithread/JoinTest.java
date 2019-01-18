package com.lcc.demo.thread.concurrent.multithread;

/**
 * @author Lcc
 * @version 2019/1/18
 *
 * 线程Join()测试.
 */
public class JoinTest implements Runnable {

  public static void main(String[] args) throws InterruptedException {
    Thread test1 = new Thread(new JoinTest());
    Thread test2 = new Thread(new JoinTest());
    Thread test3 = new Thread(new JoinTest());

//    test1.start();
    test2.start();
//    test3.start();

//    test1.join();
    test2.join(1000);
//    test3.join(2000, 5000000);
    System.out.println(Thread.currentThread().getName() + "执行完成");
  }

  @Override
  public void run() {
    try {
      Thread.sleep(5000);
      System.out.println(Thread.currentThread().getName() + "执行完成");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
