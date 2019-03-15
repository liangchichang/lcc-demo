package com.lcc.demo.controllertest;

import com.lcc.demo.controllertest.service.SpringRetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lcc
 * @version 2019/3/15
 */
@RestController()
@RequestMapping("/springRetry")
public class SpringRetryController {

  @Autowired
  private SpringRetryService springRetryService;

  @PostMapping("/declareTest")
  public void springRetryTest() {
    try {
      springRetryService.retryTest();
    } catch (Exception e) {
      System.out.println("打印测试结果!");
    }
  }
}
