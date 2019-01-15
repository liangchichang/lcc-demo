package com.lcc.demo.thread.lock.synchronizedd;

/**
 * @author lcc
 * @version 2019/1/15
 *
 * 典型失败案例。
 */
public class FailCase1 extends Thread {

  private final long value;
  private static long flag;

  private FailCase1(long value) {
    super("线程--" + value);
    this.value = value;
  }

  @Override
  public void run() {
    while (true) {
      flag = value;
      synchronized ("1") {
        if (flag != 0 && flag != 1) {
          System.out.println(
              String.format("%s诡异地跳出循环，flag的值：%s", Thread.currentThread().getName(), flag));
          break;
        }
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new FailCase1(0);
    Thread thread2 = new FailCase1(1);
    thread1.start();
    thread2.start();
    sleep(10000);
    System.exit(1);
  }
}
