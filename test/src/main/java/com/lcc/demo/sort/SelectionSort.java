package com.lcc.demo.sort;

/**
 * 选择排序
 *
 * @author lcc
 * @version 2020/4/5
 */
public class SelectionSort implements Sortable {


  /**
   * Selection sort: repeatedly pick the smallest element to append to the result.
   *
   * @param array array to sort
   */
  @Override
  public void sort(int[] array) {
    if (array == null || array.length <= 1) {
      return;
    }

    for (int i = 0; i < array.length; i++) {
      int j = i + 1;
      int min = array[i];
      int minIndex = i;
      //选出最小的下标
      for (; j < array.length; j++) {
        if (array[j] < min) {
          min = array[j];
          minIndex = j;
        }
      }
      //如果i为最小，直接跳
      if (minIndex == i) {
        continue;
      }
      //交换位置
      array[minIndex] = array[i];
      array[i] = min;
    }
  }

  /**
   * 是否稳定？
   * 非稳定，因为相同数值的元素有可能交换位置
   */
  @Override
  public boolean isStable() {
    return false;
  }

  /**
   * 是否原地排序？
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
    return "O(n^2)";
  }
}
