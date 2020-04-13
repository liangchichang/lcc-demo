package com.lcc.demo.sort;

import java.util.Arrays;

/**
 * @author lcc
 * @version 2020/4/11
 */
public class QuickSort implements Sortable {

  @Override
  public void sort(int[] array) {
    if (array == null || array.length < 1) {
      return;
    }
    doSort(array, 0, array.length - 1);
  }


  private void doSort(int[] array, int startIndex, int endIndex) {
    if (startIndex >= endIndex) {
      return;
    }
    int pivot = getPivotIndexAndSort(array, startIndex, endIndex);
    doSort(array, startIndex, pivot - 1);
    doSort(array, pivot + 1, endIndex);
  }

  private int getPivotIndexAndSort(int[] array, int startIndex, int endIndex) {
    //单向扫描
//    int pivot = array[endIndex];
//    int slowMark = startIndex, fastMark = startIndex;
//    while (fastMark < endIndex) {
//      if (array[fastMark] < pivot) {
//        if (fastMark > slowMark) {
//          swap(array, fastMark, slowMark);
//        }
//        slowMark++;
//      }
//      fastMark++;
//    }
//    swap(array, slowMark, endIndex);
//    return slowMark;

    //双向扫描
    int pivot = array[endIndex], leftCursor = startIndex, rightCursor = endIndex - 1;
    while (leftCursor < rightCursor) {
      while (leftCursor < rightCursor && array[leftCursor] <= pivot) {
        leftCursor++;
      }
      while (leftCursor < rightCursor && array[rightCursor] > pivot) {
        rightCursor--;
      }
      if (leftCursor > rightCursor) {
        break;
      }
      swap(array, leftCursor, rightCursor);
    }
    if (leftCursor < endIndex) {
      swap(array, leftCursor, endIndex);
    }
    return leftCursor;
  }

  private void swap(int[] array, int index1, int index2) {
    int tmp = array[index1];
    array[index1] = array[index2];
    array[index2] = tmp;
  }

  @Override
  public boolean isStable() {
    return false;
  }

  @Override
  public boolean isSortInPlace() {
    return false;
  }

  @Override
  public String getTimeComplexity() {
    return null;
  }
}
