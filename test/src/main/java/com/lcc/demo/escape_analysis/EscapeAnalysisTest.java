package com.lcc.demo.escape_analysis;

/**
 * 逃逸分析测试.
 *
 * 测试指令:
 * /Users/lcc/dev_tools/jdk/jdk-11.0.2/Contents/Home/bin/java -XX:+DoEscapeAnalysis -XX:+PrintGC /Users/lcc/work_space/java_workspace/personal/lcc_demo/test/src/main/java/com/lcc/demo/escape_analysis/EscapeAnalysisTest.java
 *
 * @author lcc
 * @version 2019-07-01
 */
public class EscapeAnalysisTest {

  /**
   * 另一个类的引用
   */
  private EscapeTest escapeTest;

  /**
   * 构造函数
   */
  public EscapeAnalysisTest() {
    //当仅创建对象，而不分配给引用时，利用逃逸分析在栈上分配。
    //new EscapeTest();

    //分配给外部引用时，该对象在堆上创建。
    escapeTest = new EscapeTest();

  }

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    for (int i = 0; i < 100000000; i++) {
      new EscapeAnalysisTest();
    }
    System.out.println(System.currentTimeMillis() - start);
  }

  public EscapeTest getEscapeTest() {
    return escapeTest;
  }

  public void setEscapeTest(EscapeTest escapeTest) {
    this.escapeTest = escapeTest;
  }

  private static class EscapeTest {

  }
}