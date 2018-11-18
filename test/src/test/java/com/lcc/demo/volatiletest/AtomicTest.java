package com.lcc.demo.volatiletest;

/**
 * @author Lcc
 * @version 2018/11/10
 *
 * 这个测试为了测试在不同位数jvm下long和double类型操作的原子性
 */
public class AtomicTest extends Thread {


    // 正确写法
//  private final long value;
//  private static long unAtomictLongValue;
//
//  public AtomicTest(long value) {
//    this.value = value;
//  }
//
//  @Override
//  public void run() {
//    while (!Thread.interrupted()) {
//      unAtomictLongValue = value;
//      System.out.println(Thread.currentThread().getName() + "疯狂赋值中");
//    }
//  }
//
//  public static void main(String[] args) throws InterruptedException {
//    Thread thread1 = new AtomicTest(0);
//    Thread thread2 = new AtomicTest(1);
//    thread1.start();
//    thread2.start();
//    thread1.join();
//    thread2.join();
//
//    while (unAtomictLongValue == 0 || unAtomictLongValue == 1) {
//
//    }
//    thread1.interrupt();
//    thread2.interrupt();
//    System.out.println("111");
//  }


  // 这种写法多线程修改同一变量导致表达式中的判断语句在cpu时间片切换下异常，
  private final long value;
  private static long unAtomictLongValue;

  public AtomicTest(long value) {
    super("线程--" + value);
    this.value = value;
  }

  @Override
  public void run() {
    int i = 0;
    while (true) {
      unAtomictLongValue = value;
      synchronized ("1") {
        if (unAtomictLongValue != 0 && unAtomictLongValue != 1) {
          System.out.println(
              Thread.currentThread().getName() + "跳出循环" + (unAtomictLongValue != 0L) + "----" + (
                  unAtomictLongValue != 1L) + "----" + (unAtomictLongValue != 0
                  && unAtomictLongValue != 1) + "----" + i++);
        }
      }
    }
  }

  private synchronized void check() {
    if (unAtomictLongValue != 0 && unAtomictLongValue != 1) {
      System.out.println(
          Thread.currentThread().getName() + "跳出循环" + (unAtomictLongValue != 0L) + "----" + (
              unAtomictLongValue != 1L) + "----" + (unAtomictLongValue != 0
              && unAtomictLongValue != 1));
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new AtomicTest(0);
    Thread thread2 = new AtomicTest(1);
    thread1.start();
    thread2.start();
  }
}

