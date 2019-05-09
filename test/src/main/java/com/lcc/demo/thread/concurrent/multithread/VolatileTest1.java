package com.lcc.demo.thread.concurrent.multithread;

/**
 * @author lcc
 * @version 2018/10/30
 *
 * volatile可见性实验1.
 * 1：在异步线程内，JVM为了效率一直都从工作内存中读取变量，因此陷入死循环
 * 2：测试把volatile变量赋值给临时变量是否会引起线程和主存中的拷贝
 */
public class VolatileTest1 {

  private static boolean stopThreadFlag1 = true;
  private static volatile boolean stopThreadFlag2 = true;

  private static VolatileTestUtil test1 = new VolatileTestUtil(true);
  private static volatile VolatileTestUtil test2 = new VolatileTestUtil(true);

  public static void main(String[] args) throws InterruptedException {

    //实验1：
    new Thread(() -> {
      while (stopThreadFlag1) {
        stopThreadFlag1 = stopThreadFlag2;
      }
      System.out.println(String.format("%s终止死循环", Thread.currentThread().getName()));
    }).start();
    Thread.sleep(10L);
    stopThreadFlag1 = false;
    stopThreadFlag2 = false;
    System.out.println(String.format("%s线程结束", Thread.currentThread().getName()));

    //实验2：
    new Thread(() -> {
      while (test1.isFalg()) {
        //把这行代码注释后会出现死循环
        test1 = test2;
      }
      System.out.println(String.format("%s终止死循环", Thread.currentThread().getName()));
    }).start();
    Thread.sleep(10L);
    test1.setFalg(false);
    test2.setFalg(false);
    System.out.println(String.format("%s线程结束", Thread.currentThread().getName()));
  }
}

class VolatileTestUtil {

  private boolean falg;

  public boolean isFalg() {
    return falg;
  }

  public void setFalg(boolean falg) {
    this.falg = falg;
  }

  public VolatileTestUtil(boolean falg) {
    this.falg = falg;
  }
}
