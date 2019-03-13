package com.lcc.demo.controller;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 *
 * @author lcc
 * @version 2019-03-10
 */
@Service
public class TestService {

  private volatile int version = 0;

  public void testHandle(String code) throws InterruptedException {
    code = code.intern();
    synchronized (code) {
      int version = this.version;
      Thread.sleep(2000);
      if (version != this.version) {
        throw new RuntimeException("版本号不一致!栈内版本：" + version + "，全局版本号：" + this.version);
      }
      this.version++;
      System.out.println("成功结束!版本号：" + this.version);
    }
  }

  @PostConstruct
  private void test(){
    System.out.println("测试post!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
  }
}
