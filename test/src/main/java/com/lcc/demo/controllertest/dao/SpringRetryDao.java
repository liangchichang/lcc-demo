package com.lcc.demo.controllertest.dao;

import org.springframework.stereotype.Component;

/**
 *
 * @author lcc
 * @version 2019/3/15
 */
@Component
public class SpringRetryDao {

  public void test() throws Exception {
    throw new Exception("测试一下");
  }
}
