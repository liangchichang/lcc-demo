package com.lcc.demo.sort;

/**
 * 冒泡排序
 *
 * @author lcc
 * @version 2020/4/5
 */
public class BubbleSort implements Sortable {

  /**
   * Bubble sort: repeatedly compare neighbor pairs and swap if necessary.
   *
   * @param array array to sort
   */
  @Override
  public void sort(int[] array) {
    if (array == null || array.length <= 1) {
      return;
    }
    for (int i = 0; i < array.length; i++) {
      boolean isSwap = false;
      for (int j = 0; j < array.length - i - 1; j++) {
        if (array[j] > array[j + 1]) {
          int tmp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = tmp;
          isSwap = true;
        }
      }
      if (!isSwap) {
        break;
      }
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

  /**
   * 时间复杂度
   * 最好：O(n)
   * 最坏：O(n^2)
   * 平均：O(n^2)
   */
  @Override
  public String getTimeComplexity() {
    return null;
  }
}
