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

  @Override
  public Thread newThread(Runnable r) {
    return new Thread(r, "线程" + integer.getAndIncrement());
  }
}
