package com.lcc.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/test/{s}")
  public String test(@PathVariable String s) {
    System.out.println(s);
    return "success";
  }
}
