package com.lcc.demo.retry.dao;

import java.util.Random;
import org.springframework.stereotype.Component;

/**
 *
 * @author lcc
 * @version 2019/3/22
 */
@Component
public class RetryDao {


  public String getString() throws Exception {
    Random random = new Random(10);
    if (random.nextInt() < 5) {
      throw new Exception();
    }
    return "123";
  }
}
