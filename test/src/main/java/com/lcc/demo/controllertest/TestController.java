package com.lcc.demo.controllertest;

import com.lcc.demo.controllertest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lcc
 * @version 2019-02-15
 */
@RestController
@RequestMapping("/lcc")
public class TestController {

  @Autowired
  private TestService testService;

  @PostMapping("/test")
  public void test(@RequestBody String s) throws InterruptedException {
    System.out.println(s);
    testService.testHandle(s);
  }
}
