package com.lcc.demo.thread.Utils;

import com.lcc.demo.thread.concurrent.threadpool.LccThreadFactory;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 * @author Lcc
 * @version 2018/10/29
 */
public class LccThreadPool  {

  private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(2, 4, 60,
      TimeUnit.SECONDS, new ArrayBlockingQueue<>(4), new LccThreadFactory("lcc"),
      new CallerRunsPolicy());

  public static ThreadPoolExecutor getTheadPool(){
    return EXECUTOR;
  }
}
