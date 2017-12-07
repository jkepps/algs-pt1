import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
public class RandomizedQueue<Item> implements Iterable<Item> {
  Item[] items;
  int size;

  public RandomizedQueue() {
    size = 0;
    items = (Item[]) new Object[1];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void enqueue(Item item) {
    if (size == items.length) resize(2 * items.length);
    items[size++] = item;
  }

  public Item dequeue() {
    int i = StdRandom.uniform(size);
    size--;
    Item item = items[i];
    items[i] = null;
    if (size > 0 && size == items.length / 4) resize(items.length / 2);
    return item;
  }

  public Item sample() {
    int i = StdRandom.uniform(size);
    return items[i];
  }

  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private void resize(int capacity) {
    Item[] copy = (Item[]) new Object[capacity];
    for (int i = 0; i < size; i++)
      copy[i] = items[i];
    items = copy;
  }

  public static void main(Strings[] args) {

  }

  private class ListIterator implements Iterator<Item> {
    private int index;

    public ListIterator() {
      index = 0;
    }

    public boolean hasNext() {
      return index < size;
    }

    public void remove() {
      throw new UnsupportedOperationException("This operation is not supported");
    }

    public Item next() {
      return items[index];
    }
  }
}