package com.lcc.demo.thread.lock.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 非重入锁
 *
 * @author lcc
 * @version 2019/3/8
 */
public class MyLock implements Lock {

  private static final class Syn extends AbstractQueuedSynchronizer {

    @Override
    protected boolean isHeldExclusively() {
      return getState() == 1;
    }

    @Override
    protected boolean tryAcquire(int arg) {
      if (compareAndSetState(0, 1)) {
        setExclusiveOwnerThread(Thread.currentThread());
        return true;
      }
      return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
      if (getState() == 0) {
        throw new IllegalMonitorStateException();
      }
      setExclusiveOwnerThread(null);
      setState(0);
      return true;
    }
  }

  private final Syn syn = new Syn();

  @Override
  public void lock() {
    syn.acquire(1);
  }

  @Override
  public void lockInterruptibly() throws InterruptedException {
    syn.acquireInterruptibly(1);
  }

  @Override
  public boolean tryLock() {
    return syn.tryAcquire(1);
  }

  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    return syn.tryAcquireNanos(1, unit.toNanos(time));
  }

  @Override
  public void unlock() {
    syn.release(1);
  }

  @Override
  public Condition newCondition() {
    return syn.new ConditionObject();
  }
}
