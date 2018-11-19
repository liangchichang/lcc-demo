package com.lcc.demo.thread.serialization;

import com.alibaba.fastjson.JSON;

/**
 * @author Lcc
 * @version 2018/10/30
 */
public class TransientJsonTest {

  public static void main(String[] args) {

    Person person = new Person("lcc", 26, 100, "want to play");
    System.out.println(JSON.toJSONString(person));
  }
}

class Person {

  private String name;
  private int age;
  private transient int money;
  private transient String thinking;

  public Person(String name, int age, int money, String thinking) {
    this.name = name;
    this.age = age;
    this.money = money;
    this.thinking = thinking;
  }

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

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
  }

  public String getThinking() {
    return thinking;
  }

  public void setThinking(String thinking) {
    this.thinking = thinking;
  }
}
