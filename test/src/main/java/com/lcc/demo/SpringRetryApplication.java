package com.lcc.demo;

import java.util.Collections;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.RetryState;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.policy.AlwaysRetryPolicy;
import org.springframework.retry.policy.CircuitBreakerRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableRetry
public class SpringRetryApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringRetryApplication.class, args);
  }

  @Bean
  public ApplicationRunner runner(TestRetry retry) {
    return args -> {
      try {
        for (int i = 0; i < 10; i++) {
          retry.test();
        }
      } catch (Exception e) {
        System.out.println("最终执行失败" + e);
      }
    };
  }

  @Component
  public static class TestRetry {

    private static final RetryTemplate TEMPLATE = new RetryTemplate();
    private static final String STATE_KEY = "key";
    private static final RetryState RETRY_STATE = new DefaultRetryState(STATE_KEY, true,
        new BinaryExceptionClassifier(Collections.singletonList(IllegalArgumentException.class)));

    static {
//      TEMPLATE.setRetryPolicy(new CircuitBreakerRetryPolicy());
      TEMPLATE.setRetryPolicy(new SimpleRetryPolicy());
      TEMPLATE.setListeners(new RetryListener[]{
          new RetryListener() {
            @Override
            public <T, E extends Throwable> boolean open(RetryContext context,
                RetryCallback<T, E> callback) {
              System.out.println("正在监听重试打开事件");
              return false;
            }

            @Override
            public <T, E extends Throwable> void close(RetryContext context,
                RetryCallback<T, E> callback, Throwable throwable) {
              System.out.println("正在监听重试关闭事件");
            }

            @Override
            public <T, E extends Throwable> void onError(RetryContext context,
                RetryCallback<T, E> callback, Throwable throwable) {
              System.out.println("正在监听重试抛出异常事件");
            }
          }
      });
    }


    public void test() {
      TEMPLATE.execute(context -> {
        System.out.println("执行线程：" + Thread.currentThread().hashCode());
        throw new IllegalArgumentException();
      }, RETRY_STATE);
    }
  }
}
