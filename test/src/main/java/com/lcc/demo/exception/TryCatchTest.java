package com.lcc.demo.exception;

/**
 *
 * @author lcc
 * @version 2019/3/27
 */
public class TryCatchTest {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    runInTry();
    System.out.println(String.format("执行测试1代码时间为：%s", System.currentTimeMillis() - start));

    start = System.currentTimeMillis();
    runOutTry();
    System.out.println(String.format("执行测试2代码时间为：%s", System.currentTimeMillis() - start));
  }

  private static void runInTry() {
    int i = 0;
    while (i < 1000000) {
      try {
        i++;
        count100000();
        count100000();
        count100000();
        count100000();
        count100000();
        count100000();
        count100000();
        count100000();
        count100000();
        count100000();
        count100000();
        throw new Exception();
      } catch (Exception ignored) {

      }
    }
  }

  private static void runOutTry() {
    int i = 0;
    while (i < 1000000) {
      try {
        i++;
        throw new Exception();
      } catch (Exception ignored) {
      }
      count100000();
      count100000();
      count100000();
      count100000();
      count100000();
      count100000();
      count100000();
      count100000();
      count100000();
      count100000();
      count100000();
    }
  }


  private static void count100000() {
    int i = 0;
    while (i < 100000) {
      i++;
    }
  }
}
