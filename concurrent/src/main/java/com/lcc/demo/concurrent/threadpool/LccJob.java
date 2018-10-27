package com.lcc.demo.concurrent.threadpool;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/10/27
 */
public class LccJob implements Runnable {

  private final int i;

  public LccJob(int i) {
    this.i = i;
  }

  @Override
  public void run() {
    System.out.println(String.format("线程%s--执行任务%s", Thread.currentThread().getName(), i));
  }
}
