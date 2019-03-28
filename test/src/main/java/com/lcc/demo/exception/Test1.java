package com.lcc.demo.exception;

import java.io.IOException;

/**
 *
 * @author lcc
 * @version 2019/3/26
 */
public class Test1 {

  public static void main(String[] args) {
    Test2 test2 = new Test2();
    try {
      test2.test2();
    } catch (Exception e) {
      if (e instanceof IOException) {
        e.printStackTrace();
      }else{
        System.out.println("123");
      }
    }
  }
}
