package com.lcc.demo.sort;

import java.util.Arrays;

/**
 * @author lcc
 * @version 2020/4/5
 */
public class SortClient {

  private static Sortable sortable = new QuickSort();

  public static void main(String[] args) {

    int[] ss = new int[]{1, 9, 123908, 345, 5678, 213, 3, 6, 2, 8, 5, 3, 5, 99999, 5, 5, 5, 5, 55555555,333};
    sortable.sort(ss);
    System.out.println(Arrays.toString(ss));
  }
}
