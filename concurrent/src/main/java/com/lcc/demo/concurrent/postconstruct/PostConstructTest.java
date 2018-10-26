package com.lcc.demo.concurrent.postconstruct;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/10/26
 */
@Component
public class PostConstructTest {

  @PostConstruct
  public void init() {
    System.out.println("初始化");
  }
}
