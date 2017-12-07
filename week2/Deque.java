import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {
  private Node first;
  private Node last;
  private int size;

  public Deque() {
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void addFirst(Item item) {
    if (item == null) throw new IllegalArgumentException("Item can not be null");
    if (size == 0) createInitialNode(item);
    else {
      Node oldFirst = first;
      first = new Node();
      first.item = item;
      first.next = oldFirst;
      oldFirst.previous = first;
    }
    size++;
  }

  public void addLast(Item item) {
    if (item == null) throw new IllegalArgumentException("Item can not be null");
    if (size == 0) createInitialNode(item);
    else {
      Node oldLast = last;
      last = new Node();
      last.item = item;
      oldLast.next = last;
      last.previous = oldLast;
    }
    size++;
  }

  public Item removeFirst() {
    if (size == 0) throw new NoSuchElementException("The queue is empty");
    Item item = first.item;
    if (size == 1) {
      first = null;
      last = null;
    }
    else {
      first = first.next;
      first.previous = null;
    }
    size--;
    return item;
  }

  public Item removeLast() {
    if (size == 0) throw new NoSuchElementException("The queue is empty");
    Item item = last.item;
    if (size == 1) {
      first = null;
      last = null;
    } else {
      last = last.previous;
      last.next = null;
    }
    size--;
    return item;
  }

  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private void createInitialNode(Item item) {
    Node node = new Node();
    node.item = item;
    first = node;
    last = node;
  }


  public static void main(String[] args) {

  }

  private class Node {
    Node next;
    Node previous;
    Item item;
  }

  private class ListIterator implements Iterator<Item> {
    private Node current = first;

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {
      throw new UnsupportedOperationException("This operation is not supported");
    }

    public Item next() {
      if (current == null) throw new NoSuchElementException("There are no more items in the queue");
      Item item = current.item;
      current = current.next;
      return item;
    }
  }
}