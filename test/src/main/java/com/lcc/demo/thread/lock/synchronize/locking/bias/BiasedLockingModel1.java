package com.lcc.demo.thread.lock.synchronize.locking.bias;

/**
 * 偏斜锁模型1.
 *
 * @author lcc
 * @version 2019/2/1
 */
public class BiasedLockingModel1 extends Thread {

  public static int count = 0;

  public static void main(String[] args) throws InterruptedException {

    //500个线程竞争
    for (int i = 0; i < 500; i++) {
      //1.如果把for注释掉，则在单线程下无竞争环境，线程访问synchronized时为偏斜锁
      //
      Thread thread = new BiasedLockingModel1();
      thread.start();
    }
    Thread.sleep(10000);
    System.out.println("count=" + count);
  }

  @Override
  public void run() {
    for (int j = 0; j < 1000; j++) {
      //重新进入synchronized 1000次
      test();
    }
  }

  /**
   * static synchronized锁的是类对象.
   */
  private static synchronized void test() {
    //对象头偏斜锁标识：无锁为0、偏斜锁为1，其他锁为无。
    //偏斜锁：线程访问synchronized时会在同步对象的对象头和线程栈帧的锁记录处设置线程id
    //1.当同一线程重复访问synchronized时，不会出现加锁和解锁的CAS操作，只会检查对象头的线程id是否当前线程，检查成功，则表示线程已经获得了锁。
    //2.如果多个线程非并发地、前后地访问synchronized时，当后一个线程访问时，对象头中的线程id因为是前一个线程的，因此检查肯定失败。
    //  此时JVM会检查同步对象的对象头的偏斜锁标识是否为1，如果是1，则用CAS操作设置将对象头的偏斜锁指向当前线程。
    //3.如果偏斜锁标识为无，则用CAS操作竞争锁，进行锁升级。
    System.out.println("执行线程为：" + Thread.currentThread().getName());
    count++;
  }
}
