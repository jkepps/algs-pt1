import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
public class Permutation {
  public static void main(String[] args) {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    int k = Integer.parseInt(args[0]);
    while (!StdIn.isEmpty()) {
      queue.enqueue(StdIn.readString());
    }
    Iterator<String> iterator = queue.iterator();
    for (int i = 0; i < k; i++) {
      StdOut.println(iterator.next());
    }
  }
}