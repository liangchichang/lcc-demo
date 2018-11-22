package com.lcc.demo.datastracture;

/**
 * @author lcc
 * @version 2018/11/19
 *
 * 单向链表
 */
public class SinglyLinkedListTest<T> {

  private transient static int nodeCount = 0;
  private transient Node<T> first;
  private transient Node<T> last;


  public SinglyLinkedListTest() {
    this.first = null;
    this.last = null;
  }

  public SinglyLinkedListTest(T t) {
    Node<T> node = new Node<>(t, null);
    this.first = node;
    this.last = node;
  }

  public int size() {
    return nodeCount;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public boolean add(T t) {
    Node<T> node = new Node<>(t, null);
    if(first == null){
      this.first = node;
      this.last = node;
    }else{
      this.last.next = node;
      this.last = node;
    }
    return true;
  }

//  public boolean add(int index) {
//
//  }
//
//  public boolean delete(T t) {
//
//  }
//
//  public boolean delete(int index) {
//
//  }

  public T getLast() {
    return last == null ? null : last.item;
  }

  private static class Node<T> {

    T item;
    Node<T> next;

    public Node(T item, Node<T> next) {
      this.item = item;
      this.next = next;
    }
  }
}
