package com.lcc.demo.reference;

import java.lang.ref.SoftReference;

/**
 * @author lcc
 * @version 2018/12/12
 */
@SuppressWarnings("Duplicates")
public class SoftReferenceTest {

  public static void main(String[] args) {

    LccReferenceTest test = new LccReferenceTest("测试");
    SoftReference<LccReferenceTest> reference = new SoftReference<>(test);
    test = null;
    System.gc();
    for (int i = 0; ; i++) {
      if (i == 10000) {
        break;
      }
    }
    System.out.println(reference.get());

    String test1 = "测试1";
    SoftReference<String> reference1 = new SoftReference<>(test1);
    test1 = null;
    System.gc();
    for (int i = 0; ; i++) {
      if (i == 10000) {
        break;
      }
    }
    System.out.println(reference1.get());

    SoftReference<LccReferenceTest> reference2 = new SoftReference<>(new LccReferenceTest("测试2"));
    System.gc();
    for (int i = 0; ; i++) {
      if (i == 10000) {
        break;
      }
    }
    System.out.println(reference2.get());

    SoftReference<String> reference3 = new SoftReference<>("测试3");
    System.gc();
    for (int i = 0; ; i++) {
      if (i == 10000) {
        break;
      }
    }
    System.out.println(reference3.get());

    SoftReference<Integer> reference4 = new SoftReference<>(10086);
    System.gc();
    for (int i = 0; ; i++) {
      if (i == 10000) {
        break;
      }
    }
    System.out.println(reference4.get());
  }
}
