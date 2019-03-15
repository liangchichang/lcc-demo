package com.lcc.demo.controllertest.service;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *
 * @author lcc
 * @version 2019/3/13
 */
@Aspect
@Component
public class AopTest {

  @Pointcut("execution(* com.lcc.demo.controllertest.service.*.*(..))")
  public void pointCutTest(){

  }

  @After("pointCutTest()")
  public void aopTest(){
    System.out.println("插入了aop");
  }
}
