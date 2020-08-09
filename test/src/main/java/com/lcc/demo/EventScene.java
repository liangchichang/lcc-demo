package com.lcc.demo;

import java.util.concurrent.CountDownLatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author lcc
 * @version 2020/7/30
 */
@SpringBootApplication
public class EventScene {

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  public static void main(String[] args) throws InterruptedException {
    new SpringApplication(EventScene.class).run(args);

    CountDownLatch countDownLatch = new CountDownLatch(1);
    new Thread(() -> {
      for (int i = 10000000; i != 0; i--) {

      }
      System.out.println("开始解锁!");
      countDownLatch.countDown();
    }).start();
    countDownLatch.await();
    System.out.println("主线程执行!");
  }

  @Bean
  public ApplicationRunner eventRunner() {
    return args -> {
      TestEvent testEvent = new TestEvent("测试一下");
      applicationEventPublisher.publishEvent(testEvent);
    };
  }


  public static final class TestEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public TestEvent(Object source) {
      super(source);
    }
  }

  @Component
  public static final class TestListener implements ApplicationListener<TestEvent> {

    @Override
    public void onApplicationEvent(TestEvent testEvent) {
      System.out.println("监听到了事件" + testEvent.getSource().toString());
    }
  }
}
