package com.lcc.demo.concurrent.threadpool;

import static org.junit.Assert.*;

import com.lcc.demo.concurrent.ConcurrentApplication;
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
  ThreadPoolTest threadPoolTest;

  @Test
  public void executorsTest() {
    threadPoolTest.executorsTest();
  }
}