package com.lcc.demo.reference;

import java.lang.ref.WeakReference;

/**
 * @author lcc
 * @version 2018/12/12
 *
 * 测试1：仍有其他强引用就不会被垃圾回收器回收.
 * 测试2：堆上的对象.
 * 测试3：只有弱引用指向则会被回收.
 * 测试4：直接创建一个字符串，String首先new出一个对象，放入常量池.
 * 测试5：指向被Integer缓存的对象.
 * 测试6：未被缓存的对象.
 */
@SuppressWarnings("Duplicates")
public class WeakReferenceTest {

  private static LccReferenceTest lccReferenceTest;

  public static void main(String[] args) throws InterruptedException {

    LccReferenceTest test = new LccReferenceTest("测试1");
    lccReferenceTest = test;
    WeakReference<LccReferenceTest> reference = new WeakReference<>(test);
    test = null;
    System.gc();
    Thread.sleep(1000);
    System.out.println("测试1结果：" + reference.get());

    String test1 = new String("测试2");
    WeakReference<String> reference1 = new WeakReference<>(test1);
    test1 = null;
    System.gc();
    Thread.sleep(1000);
    System.out.println("测试2结果：" + reference1.get());

    WeakReference<LccReferenceTest> reference2 = new WeakReference<>(new LccReferenceTest("测试3"));
    System.gc();
    Thread.sleep(1000);
    System.out.println("测试3结果：" + reference2.get());

    WeakReference<String> reference3 = new WeakReference<>("测试4");
    System.gc();
    Thread.sleep(1000);
    System.out.println("测试4结果：" + reference3.get());

    WeakReference<Integer> reference4 = new WeakReference<>(127);
    System.gc();
    Thread.sleep(1000);
    System.out.println("测试5结果：" + reference4.get());

    WeakReference<Integer> reference5 = new WeakReference<>(128);
    System.gc();
    Thread.sleep(1000);
    System.out.println("测试6结果：" + reference5.get());
  }
}
