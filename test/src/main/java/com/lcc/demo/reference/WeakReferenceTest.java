package com.lcc.demo.reference;

import java.lang.ref.WeakReference;

/**
 * @author lcc
 * @version 2018/12/12
 */
@SuppressWarnings("Duplicates")
public class WeakReferenceTest {

  private static LccReferenceTest lccReferenceTest;

  public static void main(String[] args) {

    LccReferenceTest test = new LccReferenceTest("测试");
    lccReferenceTest = test;
    WeakReference<LccReferenceTest> reference = new WeakReference<>(test);
    test = null;
    System.gc();
    for (int i = 0; ; i++) {
      if (i == 10000) {
        break;
      }
    }
    System.out.println("测试结果：" + reference.get());

    String test1 = new String("测试1");
    WeakReference<String> reference1 = new WeakReference<>(test1);
    test1 = null;
    System.gc();
    for (int i = 0; ; i++) {
      if (i == 10000) {
        break;
      }
    }
    System.out.println("测试1结果：" + reference1.get());

    WeakReference<LccReferenceTest> reference2 = new WeakReference<>(new LccReferenceTest("测试2"));
    System.gc();
    for (int i = 0; ; i++) {
      if (i == 10000) {
        break;
      }
    }
    System.out.println("测试2结果：" + reference2.get());

    WeakReference<String> reference3 = new WeakReference<>("测试3");
    System.gc();
    for (int i = 0; ; i++) {
      if (i == 10000) {
        break;
      }
    }
    System.out.println("测试3结果：" + reference3.get());

    WeakReference<Integer> reference4 = new WeakReference<>(10086);
    System.gc();
    for (int i = 0; ; i++) {
      if (i == 10000) {
        break;
      }
    }
    System.out.println("测试4结果：" + reference4.get());
  }
}
