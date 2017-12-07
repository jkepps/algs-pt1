import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
public class Permutation {
  public static void main(String[] args) {
    Deque<String> queue = new Deque<String>();
    int k = Integer.parseInt(args[0]);
    // String str = StdIn.readString();
    while (!StdIn.isEmpty()) {
      queue.addFirst(StdIn.readString());
      // str += '\n' + StdIn.readString();
    }
    Iterator<String> iterator = queue.iterator();
    while (iterator.hasNext()) {
      StdOut.println(iterator.next());
    }
    while (queue.size() > 0) {
      StdOut.println(queue.removeLast());
    }
  }
}