package com.lcc.demo.reference;

/**
 * @author lcc
 * @version 2018/12/12
 */
public class LccReferenceTest {

  private String name;

  public LccReferenceTest(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println(name + "执行了finalize方法");
  }

  @Override
  public String toString() {
    return "LccReferenceTest{" +
        "name='" + name + '\'' +
        '}';
  }
}
