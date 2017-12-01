import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats{
  private double[] percolationFractions;

  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) throw new IllegalArgumentException("Invalid dimension");
    percolationFractions = new double[trials];
    for (int i = 0; i < trials; i++) {
      Percolation trial = new Percolation(n);
      while (!trial.percolates()) {
        trial.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
      }
      percolationFractions[i] = (double)trial.numberOfOpenSites() / Math.pow(n, 2);
    }
  }

  public double mean() {
    return StdStats.mean(percolationFractions);
  }

  public double stddev() {
    return StdStats.stddev(percolationFractions);
  }

  public double confidenceLo(int T) {
    return mean() - (1.96 * stddev() / Math.sqrt(T));
  }

  public double confidenceHi(int T) {
    return mean() + (1.96 * stddev() / Math.sqrt(T));
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);
    PercolationStats stats = new PercolationStats(n, T);
    StdOut.println("mean                    = " + stats.mean());
    StdOut.println("stddev                  = " + stats.stddev());
    StdOut.println("95% confidence interval = [" + stats.confidenceLo(T) + ", " + stats.confidenceHi(T) + "]");
  }
}