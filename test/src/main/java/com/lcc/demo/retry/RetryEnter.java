package com.lcc.demo.retry;

import com.lcc.demo.retry.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lcc
 * @version 2019/3/22
 */
@RestController
@RequestMapping("/retry")
public class RetryEnter {

  @Autowired
  private RetryService retryService;
  @Autowired
  private ThreadPoolTaskExecutor lccExecutor;
  public static final ThreadLocal<Integer> COUNT_TIMES = new ThreadLocal<>();
  public static final ThreadLocal<Integer> JOB = new ThreadLocal<>();

  @RequestMapping("/test")
  public String retryTest() {
    for (int i = 0; i < 10000000000L; i++) {
      int f = i;
      lccExecutor.execute(() -> {
        try {
          JOB.set(f);
          COUNT_TIMES.set(1);
          retryService.test();
        } catch (Exception e) {
          System.out.println(Thread.currentThread().getName() + "执行第" + JOB.get() + "个任务失败");
        }
      });
    }
    return "123";
  }
}
