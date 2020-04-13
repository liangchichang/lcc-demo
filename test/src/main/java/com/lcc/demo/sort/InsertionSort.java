package com.lcc.demo.sort;

/**
 * 插入排序
 *
 * @author lcc
 * @version 2020/4/5
 */
public class InsertionSort implements Sortable {

  /**
   * Insertion sort: repeatedly add new element to the sorted result.
   *
   * @param array array to sort
   */
  @Override
  public void sort(int[] array) {
    if (array == null || array.length <= 1) {
      return;
    }
    for (int i = 1; i < array.length; i++) {
      int selection = array[i];
      int j = i - 1;
      for (; j >= 0; j--) {
        if (array[j] > selection) {
          array[j + 1] = array[j];
        } else {
          break;
        }
      }
      array[j + 1] = selection;
    }
  }

  /**
   * 是否稳定
   */
  @Override
  public boolean isStable() {
    return true;
  }

  /**
   * 是否原地排序
   */
  @Override
  public boolean isSortInPlace() {
    return true;
  }

  @Override
  public String getTimeComplexity() {
    return "O(n^2)";
  }
}
