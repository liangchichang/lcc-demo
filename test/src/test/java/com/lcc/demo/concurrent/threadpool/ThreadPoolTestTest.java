package com.lcc.demo.concurrent.threadpool;

import com.lcc.demo.ConcurrentApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/10/27
 */
@SpringBootTest(classes = ConcurrentApplication.class)
@RunWith(SpringRunner.class)
public class ThreadPoolTestTest {

  @Autowired
  private ThreadPoolTest threadPoolTest;
  @Autowired
  private ThreadPoolTest1 threadPoolTest1;

  /**
   * 线程池测试.
   */
  @Test
  public void executorsTest() {
    threadPoolTest.executorsTest();
  }

  /**
   * Spring依赖注入单例测试.
   */
  @Test
  public void springSingleTest() {
    System.out.println(threadPoolTest.executor == threadPoolTest1.executor);
  }
}