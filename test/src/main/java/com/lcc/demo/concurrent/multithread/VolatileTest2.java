package com.lcc.demo.concurrent.multithread;

/**
 * @author Lcc
 * @version 2018/10/30
 */
public class VolatileTest2 {

  public static void main(String[] args) throws InterruptedException {
    VolatileTestTask task = new VolatileTestTask();
    task.start();
    Thread.sleep(1000);
    task.stopRun();
    System.out.println(Thread.currentThread().getName() + "线程结束");
  }
}

class VolatileTestTask extends Thread {

  private boolean run = true;

  @Override
  public void run() {
    while (isRun()) {

    }
    System.out.println(Thread.currentThread().getName() + "线程死循环结束");
  }

  public boolean isRun() {
    return run;
  }

  public void stopRun() {
    this.run = false;
  }
}
