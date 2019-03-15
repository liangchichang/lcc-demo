package com.lcc.demo.controllertest.service;

import com.lcc.demo.controllertest.dao.SpringRetryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 *
 * @author lcc
 * @version 2019/3/15
 */
@Service
public class SpringRetryService {

  @Autowired
  private SpringRetryDao springRetryDao;

  @Retryable(value = Exception.class, backoff = @Backoff(delay = 1000),label = "")
  public void retryTest() throws Exception {
    System.out.println("测试retry!");
    springRetryDao.test();
  }
}
