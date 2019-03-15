package com.lcc.demo.thread.lock.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author lcc
 * @version 2019/3/8
 */
public class MyLock implements Lock {

  private static final class Syn extends AbstractQueuedSynchronizer {

  }

  @Override
  public void lock() {

  }

  @Override
  public void lockInterruptibly() throws InterruptedException {

  }

  @Override
  public boolean tryLock() {
    return false;
  }

  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    return false;
  }

  @Override
  public void unlock() {

  }

  @Override
  public Condition newCondition() {
    return null;
  }
}
