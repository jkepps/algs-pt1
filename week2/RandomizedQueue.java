import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] items;
  private int size;
  private int last;

  public RandomizedQueue() {
    size = 0;
    last = 0;
    items = (Item[]) new Object[1];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void enqueue(Item item) {
    if (item == null) throw new IllegalArgumentException("Item can not be null");
    if (size == items.length) resize(2 * items.length);
    last = size;
    items[size++] = item;
  }

  public Item dequeue() {
    if (size == 0) throw new NoSuchElementException("The queue is empty");
    int i = StdRandom.uniform(size);
    size--;
    Item item = items[i];
    items[i] = items[last];
    items[last--] = null;
    if (size > 0 && size == items.length / 4) resize(items.length / 2);
    return item;
  }

  public Item sample() {
    if (size == 0) throw new NoSuchElementException("The queue is empty");
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

  public static void main(String[] args) {

  }

  private class ListIterator implements Iterator<Item> {
    private int index;
    private Item[] copy;

    public ListIterator() {
      index = 0;
      copy = (Item[]) new Object[size];
      for (int i = 0; i < size; i++)
        copy[i] = items[i];
      StdRandom.shuffle(copy);
    }

    public boolean hasNext() {
      return index < size;
    }

    public void remove() {
      throw new UnsupportedOperationException("This operation is not supported");
    }

    public Item next() {
      if (index == size) throw new NoSuchElementException("There are no more items in the queue");
      Item item =  copy[index];
      index++;
      return item;
    }
  }
}