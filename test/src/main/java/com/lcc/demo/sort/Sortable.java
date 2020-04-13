package com.lcc.demo.sort;

/**
 * @author lcc
 * @version 2020/4/5
 */
public interface Sortable {

  void sort(int[] array);

  boolean isStable();

  boolean isSortInPlace();

  String getTimeComplexity();
}
