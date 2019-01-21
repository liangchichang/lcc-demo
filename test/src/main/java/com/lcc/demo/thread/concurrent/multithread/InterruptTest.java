package com.lcc.demo.thread.concurrent.multithread;

/**
 *
 * @author lcc
 * @version 2019/1/21
 */
public class InterruptTest {

  public static void main(String[] args) {
    Thread thread = Thread.currentThread();
    thread.interrupt();
    System.out.println("用Thread.isInterrupted()测试线程是否中断：" + thread.isInterrupted());
    System.out.println("用Thread.isInterrupted()测试线程是否中断：" + thread.isInterrupted());
    System.out.println("用Thread.isInterrupted()测试线程是否中断：" + thread.isInterrupted());

    System.out.println("---------------");

    System.out.println("用Thread.interrupted()测试线程是否中断：" + Thread.interrupted());
    System.out.println("用Thread.interrupted()测试线程是否中断：" + Thread.interrupted());
  }
}
