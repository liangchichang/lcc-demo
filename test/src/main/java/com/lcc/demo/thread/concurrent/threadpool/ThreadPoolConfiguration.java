package com.lcc.demo.thread.concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/10/26
 */
@Configuration
public class ThreadPoolConfiguration {

  @Value("${lcc.threadPool.corePoolSize:4}")
  private int corePoolSize;
  @Value("${lcc.threadPool.maxPoolSize:800000}")
  private int maxPoolSize;
  @Value("${lcc.threadPool.aliveTimes:60}")
  private int aliveTimes;
  @Value("${lcc.threadPool.blockingQueueSize:800000000}")
  private int blockingQueueSize;

//  @Bean
//  public Executor executorInit() {
//    return new ThreadPoolExecutor(corePoolSize, maxPoolSize, aliveTimes, TimeUnit.SECONDS,
//        new ArrayBlockingQueue<>(blockingQueueSize),
//        new LccThreadFactory("yby"), new CallerRunsPolicy());
//  }

  @Bean
  public ThreadPoolTaskExecutor lccExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setQueueCapacity(blockingQueueSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setCorePoolSize(corePoolSize);
    executor.setKeepAliveSeconds(aliveTimes);
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    return executor;
  }
}
