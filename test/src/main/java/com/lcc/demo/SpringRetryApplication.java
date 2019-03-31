package com.lcc.demo;

import com.lcc.demo.retry.exception.StatefulRetryException;
import com.lcc.demo.retry.exception.StatelessRetryException;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.RetryState;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringRetryApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringRetryApplication.class, args);
  }

  @Bean
  public ApplicationRunner runner(StateLessRetry stateLessRetry, StatefulRetry statefulRetry) {
    return args -> {
//      System.out.println(">>>>>>>无状态重试");
//      try {
//        stateLessRetry.test();
//      } catch (StatelessRetryException e) {
//        System.out.println("无状态重试完毕");
//      }
      System.out.println("==================================");
      System.out.println("有状态重试");
      statefulRetry.test();
    };
  }

  @Component
  public static class StateLessRetry {

    private static final RetryTemplate TEMPLATE = new RetryTemplate();

    static {
      TEMPLATE.setRetryPolicy(new SimpleRetryPolicy());
      TEMPLATE.setListeners(new RetryListener[]{
          new RetryListener() {
            @Override
            public <T, E extends Throwable> boolean open(RetryContext context,
                RetryCallback<T, E> callback) {
              System.out.println("监听重试开始");
              return true;
            }

            @Override
            public <T, E extends Throwable> void close(RetryContext context,
                RetryCallback<T, E> callback, Throwable throwable) {
              System.out.println("监听重试结束");
            }

            @Override
            public <T, E extends Throwable> void onError(RetryContext context,
                RetryCallback<T, E> callback, Throwable throwable) {
              System.out.println("抛出异常");
            }
          }
      });
    }


    void test() throws StatelessRetryException {
      TEMPLATE.execute(context -> {
        System.out.println(">>>>无状态重试，执行线程：" + Thread.currentThread().hashCode());
        throw new StatelessRetryException();
      });
    }
  }

  @Component
  public static class StatefulRetry {

    private static final RetryState STATE = new DefaultRetryState("key", false,
        new BinaryExceptionClassifier(Collections.singletonList(StatefulRetryException.class)));
    private static final RetryTemplate TEMPLATE = new RetryTemplate();
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);

    void test() throws Exception {
      TEMPLATE.execute(context -> {
        System.out.println(">>>有状态重试，执行线程：" + Thread.currentThread().hashCode());
        int i = ATOMIC_INTEGER.getAndIncrement();
        if (i % 2 == 0) {
          throw new StatefulRetryException();
        } else {
          throw new IOException();
        }
      }, STATE);
    }

  }

}
