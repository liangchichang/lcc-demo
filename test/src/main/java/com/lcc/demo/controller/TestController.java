package com.lcc.demo.controller;

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

  @PostMapping("/test")
  public void test(@RequestBody String s) throws InterruptedException {
    System.out.println(s);
    synchronized (s.intern()){
      while (true){
        Thread.sleep(1000);
        System.out.println("正在执行"+Thread.currentThread().getName());
      }
    }
  }
}
