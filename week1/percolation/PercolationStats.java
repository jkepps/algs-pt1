import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private static final double CONSTANT_95 = 1.96;
  private double[] percolationFractions;
  private int totalTrials;
  private double mean;
  private double stddev;

  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) throw new IllegalArgumentException("Invalid dimension");
    percolationFractions = new double[trials];
    totalTrials = trials;
    for (int i = 0; i < trials; i++) {
      Percolation trial = new Percolation(n);
      while (!trial.percolates()) {
        trial.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
      }
      percolationFractions[i] = (double) trial.numberOfOpenSites() / Math.pow(n, 2);
    }
    mean = StdStats.mean(percolationFractions);
    stddev = StdStats.stddev(percolationFractions);
  }

  public double mean() {
    return mean;
  }

  public double stddev() {
    return stddev;
  }

  public double confidenceLo() {
    return mean() - (CONSTANT_95 * stddev() / Math.sqrt(totalTrials));
  }

  public double confidenceHi() {
    return mean() + (CONSTANT_95 * stddev() / Math.sqrt(totalTrials));
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int t = Integer.parseInt(args[1]);
    PercolationStats stats = new PercolationStats(n, t);
    StdOut.println("mean                    = " + stats.mean());
    StdOut.println("stddev                  = " + stats.stddev());
    StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
  }
}