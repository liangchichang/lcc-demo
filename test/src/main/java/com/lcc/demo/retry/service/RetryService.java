package com.lcc.demo.retry.service;

import com.lcc.demo.retry.RetryEnter;
import com.lcc.demo.retry.dao.RetryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.backoff.FixedBackOffPolicy;
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

  static {
    FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
    backOffPolicy.setBackOffPeriod(50000L);
    RETRY_TEMPLATE.setBackOffPolicy(backOffPolicy);
  }

  public String test() throws Exception {
    return RETRY_TEMPLATE.execute(context -> {
      Integer integer = RetryEnter.COUNT_TIMES.get();
      System.out.println("线程:" + Thread.currentThread().getName() + "当前重试第：" + integer + "次");
      RetryEnter.COUNT_TIMES.set(integer + 1);
      return retryDao.getString();
    });
  }
}
