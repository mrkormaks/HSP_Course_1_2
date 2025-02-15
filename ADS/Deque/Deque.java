import java.util.*;

public class Deque<T> {
  // Внутренний класс для узла двусвязного списка
  private static class Node<T> {
    T value;
    Node<T> next;
    Node<T> prev;

    Node(T value) {
      this.value = value;
    }
  }

  private Node<T> head;
  private Node<T> tail;
  private int size;

  public Deque() {
    head = null;
    tail = null;
    size = 0;
  }

  // Добавление элемента в голову (O(1) за счёт двусвязного списка)
  public void addFront(T item) {
    Node<T> newNode = new Node<>(item);
    if (head == null) {
      // Если список пуст, то новый элемент становится и головой, и хвостом
      head = tail = newNode;
    } else {
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
    }
    size++;
  }

  // Добавление элемента в хвост (O(1) за счёт двусвязного списка)
  public void addTail(T item) {
    Node<T> newNode = new Node<>(item);
    if (tail == null) {
      // Если список пуст, то новый элемент становится и головой, и хвостом
      head = tail = newNode;
    } else {
      newNode.prev = tail;
      tail.next = newNode;
      tail = newNode;
    }
    size++;
  }

  // Удаление элемента из головы (O(1) за счёт двусвязного списка)
  public T removeFront() {
    if (head == null) return null;
    T value = head.value;
    head = head.next;
    if (head != null) {
      head.prev = null;
    } else {
      tail = null;
    }
    size--;
    return value;
  }

  // Удаление элемента из хвоста (O(1) за счёт двусвязного списка)
  public T removeTail() {
    if (tail == null) return null;
    T value = tail.value;
    tail = tail.prev;
    if (tail != null) {
      tail.next = null;
    } else {
      head = null;
    }
    size--;
    return value;
  }

  // Возвращает размер очереди
  public int size() {
    return size;
  }
}


