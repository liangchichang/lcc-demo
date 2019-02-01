package com.lcc.demo.thread.lock.synchronize.communication;

/**
 * @author lcc
 * @version 2019/1/14
 */
public class WaitAndNotifyTest1 {

  public static void main(String[] args) {
    Killer killer1 = new Killer();
    Killer killer2 = new Killer();
    Killer killer3 = new Killer();
    killer1.start();
    killer2.start();
    killer3.start();
  }
}

class Killer extends Thread {

  private static int count = 0;

  @Override
  public void run() {
    synchronized ("1") {
      while (count++ < 5) {
        try {
          Thread.sleep(1000);
          "1".notify();
          System.out.println(String.format("杀手%s数到%s", Thread.currentThread().getName(), count));
          "1".wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println(String.format("杀手%s杀掉了其他人", Thread.currentThread().getName()));
      System.exit(1);
    }
  }
}