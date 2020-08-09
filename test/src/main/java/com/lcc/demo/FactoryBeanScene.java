package com.lcc.demo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lcc
 * @version 2020/8/7
 */
public class FactoryBeanScene {

  public static void main(String[] args) {
    BeanFactory context = new ClassPathXmlApplicationContext("application.xml");
    Object bean = context.getBean("&factoryBeanTest");
    System.out.println("是否FactoryBeanTest：" + (bean instanceof FactoryBeanTest));
    System.out.println("是否LccObject：" + (bean instanceof LccObject));
  }

  private static final class FactoryBeanTest implements FactoryBean<LccObject> {

    @Override
    public LccObject getObject() throws Exception {
      LccObject test = new LccObject();
      test.setName("测试一下");
      test.setAge(15);
      return test;
    }

    @Override
    public Class<?> getObjectType() {
      return FactoryBeanTest.class;
    }
  }

  private static class LccObject {

    private String name;
    private int age;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }
  }
}
