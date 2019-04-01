package com.lcc.demo;

import com.lcc.demo.retry.exception.StatefulRetryException;
import com.lcc.demo.retry.exception.StatelessRetryException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.RetryState;
import org.springframework.retry.policy.AlwaysRetryPolicy;
import org.springframework.retry.policy.CircuitBreakerRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringRetryApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringRetryApplication.class, args);
  }

  @Bean
  @Order(1)
  public ApplicationRunner runner(StateLessRetry stateLessRetry, StatefulRetry statefulRetry,
      CircuitBreakerRetry circuitBreakerRetry) {
    return args -> {
      for (int i = 1; i <= 3; i++) {
        System.out.println(String.format(">>>>>第%s次无状态重试开始", i));
        try {
          stateLessRetry.test();
        } catch (StatelessRetryException e) {
          System.out.println("捕获异常：" + e.getClass().getName());
        }
        System.out.println(String.format(">>>>>第%s次无状态重试完毕", i));
      }

      try {
        for (int i = 0; i < 3; i++) {
          System.out.println();
        }
        System.out.println("================这是一条分割线==================");

        System.out.println("对 【StatefulRetryException】 异常进行有状态重试");
        for (; ; ) {
          try {
            statefulRetry.test();
          } catch (StatefulRetryException e) {
          }
        }
      } catch (Exception e) {

      }

      for (int j = 0; j < 3; j++) {
        System.out.println();
      }
      System.out.println("================又是一条分割线==================");

      System.out.println("熔断重试策略");
      circuitBreakerRetry.test();
    };
  }

  @Component
  public static class StateLessRetry {

    private static final RetryTemplate TEMPLATE = new RetryTemplate();
    int i = 1;

    static {
      Map<Class<? extends Throwable>, Boolean> unRetryables = new HashMap<>();
      unRetryables.put(StatelessRetryException.class, true);
      SimpleRetryPolicy policy = new SimpleRetryPolicy(3, unRetryables);
      TEMPLATE.setRetryPolicy(policy);
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
        System.out.println(
            String.format(">>>>无状态重试第%s次，执行线程：%s", i++, Thread.currentThread().hashCode()));
        throw new StatelessRetryException();
      });
    }
  }

  @Component
  public static class StatefulRetry {

    private static final RetryState STATE = new DefaultRetryState("key", false,
        new BinaryExceptionClassifier(Collections.singletonList(StatefulRetryException.class)));
    private static final RetryTemplate TEMPLATE = new RetryTemplate();
    int j = 1;

    void test() throws Exception {
      AtomicInteger atomicInteger = new AtomicInteger(1);
      TEMPLATE.execute(context -> {
        int i = atomicInteger.getAndIncrement();
        System.out.println(String.format(">>>有状态重试第%s次，执行线程：%s", j++,
            Thread.currentThread().hashCode()));
        if (i % 2 == 0) {
          System.out.println("抛出异常：StatefulRetryException");
          throw new StatefulRetryException();
        } else {
          System.out.println("抛出异常：IOException");
          throw new IOException();
        }
      }, STATE);
    }
  }

  @RestController
  @RequestMapping("/lcc/retry")
  public static class CircuitBreakerRetry {

    private static final RetryState STATE = new DefaultRetryState("circuitBreak", false);
    private static final RetryTemplate TEMPLATE = new RetryTemplate();
    int j = 1;

    static {
      CircuitBreakerRetryPolicy policy = new CircuitBreakerRetryPolicy(new SimpleRetryPolicy());
      policy.setOpenTimeout(1000);
      policy.setResetTimeout(5000L);
      TEMPLATE.setRetryPolicy(policy);
    }

    @GetMapping("/circuitBreak")
    public void test() throws InterruptedException {
      for (int i = 0; i < 10; i++) {
        Thread.sleep(200L);
        try {
          TEMPLATE.execute(context -> {
            System.out.println(String.format(">>>熔断重试第%s次，执行线程：%s", j++, Thread.currentThread().hashCode()));
            System.out.println();
            throw new IOException();
          }, STATE);
        } catch (Exception e) {
          System.out.println(e);
        }
      }
    }
  }
}
