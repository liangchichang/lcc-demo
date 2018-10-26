package com.lcc.demo.concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/10/26
 */
@Configuration
public class ThreadPoolConfiguration {

  @Value("${lcc.threadPool.corePoolSize:4}")
  private int corePoolSize;
  @Value("${lcc.threadPool.maxPoolSize:8}")
  private int maxPoolSize;
  @Value("${lcc.threadPool.aliveTimes:60}")
  private long aliveTimes;
  @Value("${lcc.threadPool.blockingQueueSize:8}")
  private int blockingQueueSize;

  @Bean
  public Executor executorInit() {
    return new ThreadPoolExecutor(corePoolSize, maxPoolSize, aliveTimes, TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(blockingQueueSize),
        new LccThreadFactory(), new CallerRunsPolicy());
  }
}
