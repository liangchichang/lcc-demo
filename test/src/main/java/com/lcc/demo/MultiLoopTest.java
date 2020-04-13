package com.lcc.demo;

/**
 * 多重for循环测试
 *
 * @author lcc
 * @version 2020/4/1
 */
public class MultiLoopTest {

  static char[] chars = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

  public static void main(String[] args) {
//    int targetBase = 36;
//    long number = 12923543123175L;
//
//    StringBuilder sb = new StringBuilder();
//    while (number != 0) {
//      int remainder = (int) (number % targetBase);
//      sb.append(chars[remainder]);
//      number = number / targetBase;
//    }
//    String result = sb.reverse().toString();
//    System.out.println(result);

    long t1, t2;
    long a1 = 0, a2 = 0;

    System.out.println("循环1");
    t1 = System.currentTimeMillis();
    for (int i = 0; i < 1000000; i++) {
      for (int j = 0; j < 100; j++) {
        for (int k = 0; k < 10; k++) {
          a1++;
        }
      }
    }
    t2 = System.currentTimeMillis();
    System.out.println("运行时间:" + (t2 - t1) + "(ms);执行次数" + a1);

    System.out.println("循环2");
    t1 = System.currentTimeMillis();
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 100; j++) {
        for (int k = 0; k < 1000000; k++) {
          a2++;
        }
      }
    }
    t2 = System.currentTimeMillis();//end
    System.out.println("运行时间:" + (t2 - t1) + "(ms);执行次数" + a2);

    System.out.println("比较次数是否相等：" + (a1 == a2));

    System.out.println("优化循环1");
    int i2, j2, k2;
    long aa = 0;
    t1 = System.currentTimeMillis();
    for (i2 = 0; i2 < 10000000; i2++) {
      for (j2 = 0; j2 < 1000; j2++) {
        for (k2 = 0; k2 < 10; k2++) {
          aa = aa + 1;
        }
      }
    }
    t2 = System.currentTimeMillis();
    System.out.println("运行时间:" + (t2 - t1) + "(ms);执行次数" + aa);

    System.out.println("优化循环2");
    int i1, j1, k1;
    long aa1 = 0;
    t1 = System.currentTimeMillis();
    for (i1 = 0; i1 < 10; i1++) {
      for (j1 = 0; j1 < 1000; j1++) {
        for (k1 = 0; k1 < 10000000; k1++) {
          aa1 = aa1 + 1;
        }
      }
    }
    t2 = System.currentTimeMillis();
    System.out.println("运行时间:" + (t2 - t1) + "(ms);执行次数" + aa1);
    System.out.println("比较次数是否相等：" + (aa == aa1));
  }
}
