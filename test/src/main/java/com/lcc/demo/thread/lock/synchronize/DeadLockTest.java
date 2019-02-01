package com.lcc.demo.thread.lock.synchronize;

import com.lcc.demo.thread.Utils.LccThreadPool;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/10/27
 */
public class DeadLockTest {

  public static void main(String[] args) {
    LccThreadPool.getTheadPool().execute(new DeadLockSample("lcc1", "lcc2"));
    LccThreadPool.getTheadPool().execute(new DeadLockSample("lcc2", "lcc1"));
  }

  private static class DeadLockSample implements Runnable {

    private final String lockName1;
    private final String lockName2;

    DeadLockSample(String lockName1, String lockName2) {
      this.lockName1 = lockName1;
      this.lockName2 = lockName2;
    }

    @Override
    public void run() {
      synchronized (lockName1.intern()) {
        try {
          System.out.println(String.format("%s获得锁%s", Thread.currentThread().getName(), lockName1));
          Thread.sleep(1000L);
          synchronized (lockName2.intern()) {
            System.out
                .println(String.format("%s获得锁%s", Thread.currentThread().getName(), lockName2));
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println(String.format("%s执行完毕", Thread.currentThread().getName()));
    }

  }
}
