package com.lcc.demo.datastracture;

/**
 * @author lcc
 * @version 2018/11/19
 *
 * 单向链表
 */
public class LccSinglyLinkedList<T> {

  private transient Node<T> first;

  public LccSinglyLinkedList() {
    this.first = null;
  }

  /**
   * 获取链表大小.
   */
  public int size() {
    int size = 0;
    Node<T> tNode = first;
    while (tNode != null) {
      tNode = tNode.next;
      size++;
    }
    return size;
  }

  /**
   * 判断是否为空.
   */
  public boolean isEmpty() {
    return first == null;
  }

  /**
   * 头部添加.
   */
  public boolean addFirst(T t) {
    first = new Node<>(t, first);
    return true;
  }

  /**
   * 末尾添加.
   */
  public boolean addLast(T t) {
    if (first == null) {
      first = new Node<>(t, null);
      return true;
    }
    Node<T> tNode = this.first;
    while (tNode.next != null) {
      tNode = tNode.next;
    }
    tNode.next = new Node<>(t, null);
    return true;
  }

  /**
   * 添加到指定节点.
   */
  public boolean add(T t, int index) {
    if (index < 0 ||index>)
  }

  /**
   * 按值删除(只删除第一次出现的值).
   */
  public boolean delete(T t) {
    Node<T> node = findByValue(t);
    if (node == null) {
      return false;
    }
    Node<T> tNode = findPreviousByNode(node);
    tNode.next = node.next;
    return true;
  }

  /**
   * 删除指定位置的索引.
   */
  public boolean delete(int index) {
  }

  /**
   * 获取最后的值.
   */
  public T getLast() {
    return last == null ? null : last.item;
  }

  public T getfirst() {
    return first == null ? null : first.item;
  }

  private Node<T> findByIndex(int index) {
    Node<T> tNode = this.first;
    int flag = 0;
    while (tNode != null && flag++ != index) {
      tNode = tNode.next;
    }
    return tNode;
  }

  private Node<T> findByValue(T t) {
    Node<T> node = this.first;
    while (node != null && !node.item.equals(t)) {
      node = node.next;
    }
    return null;
  }

  private Node<T> findPreviousByNode(Node<T> node) {
    Node<T> tNode = this.first;
    while (tNode != null && !tNode.next.equals(node)) {
      tNode = tNode.next;
    }
    return tNode;
  }

  private static class Node<T> {

    T item;
    Node<T> next;

    Node(T item, Node<T> next) {
      this.item = item;
      this.next = next;
    }
  }
}
