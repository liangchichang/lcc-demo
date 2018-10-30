package com.lcc.demo.normaltest;

import java.util.ArrayList;

/**
 * @author Lcc
 * @version 2018/10/29
 */
public class ArrayLengthTest {

  public static void main(String[] args) {

    String[] ss = new String[10];
    System.out.println(ss.length);

    ArrayList<String> strings = new ArrayList<>(10);
    strings.add("123");
    strings.add("345");
    System.out.println(strings.size());
  }
}
