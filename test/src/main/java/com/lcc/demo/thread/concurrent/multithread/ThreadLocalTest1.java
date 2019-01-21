package com.lcc.demo.thread.concurrent.multithread;

/**
 * @author lcc
 * @version 2018/11/12
 *
 * ThreadLocal测试1.
 * 验证：ThreadLocal是线程的副本.
 */
public class ThreadLocalTest1 extends Thread {

  private ThreadLocal<String> threadLocal = new ThreadLocal<>();

  private ThreadLocalTest1(String name) {
    super(name);
  }

  public String getThreadLocalString() {
    return this.threadLocal.get();
  }

  public static void main(String[] args) {

    ThreadLocalTest1 test1 = new ThreadLocalTest1("测试1");
    ThreadLocalTest1 test2 = new ThreadLocalTest1("测试2");
    ThreadLocalTest1 test3 = new ThreadLocalTest1("测试3");
    ThreadLocalTest1 test4 = new ThreadLocalTest1("测试4");

    test1.threadLocal.set("线程测试1");
    test2.threadLocal.set("线程测试2");
    test3.threadLocal.set("线程测试3");
    test4.threadLocal.set("线程测试4");

    System.out.println("测试1：" + test1.threadLocal + "--value:" + test1.getThreadLocalString());
    System.out.println("测试2：" + test2.threadLocal + "--value:" + test2.getThreadLocalString());
    System.out.println("测试3：" + test3.threadLocal + "--value:" + test3.getThreadLocalString());
    System.out.println("测试4：" + test4.threadLocal + "--value:" + test4.getThreadLocalString());

    System.out.println("-----------------------------");

    test1.start();
    test2.start();
    test3.start();
    test4.start();
  }

  @Override
  public void run() {
    System.out.println(
        Thread.currentThread().getName() + "：" + this.threadLocal + "--value:" + this
            .getThreadLocalString());
  }
}
