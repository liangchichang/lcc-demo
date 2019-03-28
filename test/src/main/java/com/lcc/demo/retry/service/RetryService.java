package com.lcc.demo.retry.service;

import com.lcc.demo.retry.RetryEnter;
import com.lcc.demo.retry.dao.RetryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.RetryState;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.policy.CircuitBreakerRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author lcc
 * @version 2019/3/22
 */
@Service
public class RetryService {

  @Autowired
  private RetryDao retryDao;
  private static final RetryTemplate RETRY_TEMPLATE = new RetryTemplate();
  private static final String STATE_KEY = "key";
  private static final RetryState RETRY_STATE = new DefaultRetryState(STATE_KEY, true);


  static {
    CircuitBreakerRetryPolicy policy = new CircuitBreakerRetryPolicy(new SimpleRetryPolicy());
    policy.setOpenTimeout(1000L);
    policy.setResetTimeout(3000L);
    RETRY_TEMPLATE.setRetryPolicy(policy);
    RETRY_TEMPLATE.setListeners(new RetryListener[]{
        new RetryListener() {
          @Override
          public <T, E extends Throwable> boolean open(RetryContext context,
              RetryCallback<T, E> callback) {
            System.out.println("正在监听重试打开事件");
            return true;
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

  static {
//    FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
//    backOffPolicy.setBackOffPeriod(50000L);
//    RETRY_TEMPLATE.setBackOffPolicy(backOffPolicy);
  }

//  @Retryable(Exception.class)
//  public String test() throws Exception {
//    test1();
//    return "123";
//  }

  @Retryable(include = Exception.class, stateful = true)
  public String test1() throws Exception {
    System.out.println("123");
    throw new Exception("123");
  }

  public String test() throws Exception {
    return RETRY_TEMPLATE.execute(context -> {
      Integer integer = RetryEnter.COUNT_TIMES.get();
      System.out.println("线程:" + Thread.currentThread().getName() + "当前重试第：" + integer + "次");
      RetryEnter.COUNT_TIMES.set(integer + 1);
      throw new Exception();
    }, RETRY_STATE);
  }
}
