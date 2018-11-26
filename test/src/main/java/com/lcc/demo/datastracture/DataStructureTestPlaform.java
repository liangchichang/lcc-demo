package com.lcc.demo.datastracture;

/**
 * @author lcc
 * @version 2018/11/27
 */
public class DataStructureTestPlaform {

  public static void main(String[] args) {

    LccSinglyLinkedList<String> list = new LccSinglyLinkedList<>();
    list.addFirst("1");
    list.add("2", 0);
    list.addFirst("3");
    list.delete(0);
    System.out.println(list.size());
    System.out.println(list.getfirst());
    System.out.println(list.getLast());
  }
}
