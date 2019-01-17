package com.lcc.demo.thread.concurrent.multithread;

/**
 * @author lcc
 * @version 2019/1/17
 *
 * ThreadLocal测试1.
 * 验证：ThreadLocal是线程的副本.
 */
public class ThreadLocalTest1 extends Thread {

  private ThreadLocal<String> threadLocal = new ThreadLocal<>();

  private ThreadLocalTest1(String name) {
    super(name);
    this.threadLocal.set(name);
  }

  public String getThreadLocalString() {
    return this.threadLocal.get();
  }

  public static void main(String[] args) {

    //虽然被放到ThreadLocal中，但实际key是线程对象
    ThreadLocalTest1 test1 = new ThreadLocalTest1("线程测试1");
    ThreadLocalTest1 test2 = new ThreadLocalTest1("线程测试2");
    ThreadLocalTest1 test3 = new ThreadLocalTest1("线程测试3");
    ThreadLocalTest1 test4 = new ThreadLocalTest1("线程测试4");

    System.out.println("test1：" + test1.threadLocal + test1.getThreadLocalString());
    System.out.println("test2：" + test2.threadLocal + test2.getThreadLocalString());
    System.out.println("test3：" + test3.threadLocal + test3.getThreadLocalString());
    System.out.println("test4：" + test4.threadLocal + test4.getThreadLocalString());

    System.out.println("-----------------------------");

    test1.start();
    test2.start();
    test3.start();
    test4.start();
  }

  @Override
  public void run() {
    System.out.println(
        Thread.currentThread().getName() + "：" + this.threadLocal + this.getThreadLocalString());
  }
}
