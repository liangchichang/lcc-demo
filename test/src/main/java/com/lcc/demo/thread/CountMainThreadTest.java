package com.lcc.demo.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 *
 * @author lcc
 * @version 2019/2/12
 */
public class CountMainThreadTest {

  public static void main(String[] args) {
    ThreadMXBean bean = ManagementFactory.getThreadMXBean();
    ThreadInfo[] threads = bean.dumpAllThreads(false, false);
    for (ThreadInfo thread : threads) {
      System.out.println(thread.getThreadId() + "---" + thread.getThreadName());
    }
  }
}
