package com.lcc.demo.concurrent.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/10/26
 */
public class LccThreadFactory implements ThreadFactory {

  private final AtomicInteger integer = new AtomicInteger(1);
  private final String poolName;

  public LccThreadFactory(String poolName) {
    this.poolName = poolName;
  }

  @Override
  public Thread newThread(Runnable r) {
    return new Thread(r, String.format("线程池%s中的线程%s", poolName, integer.getAndIncrement()));
  }
}
