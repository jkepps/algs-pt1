import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;

public class Percolation {
  private int[][] grid;
  private WeightedQuickUnionUF uf;
  private int totalNodes;
  private int totalOpenSites;
  private int gridDimension;

  // sets private variables 
  public Percolation (int n) {
    if (n <= 0) throw new IllegalArgumentException("Invalid dimension");
    gridDimension = n;
    totalNodes = (n * n) + 2;
    totalOpenSites = 0;
    grid = new int[n][n];
    uf = new WeightedQuickUnionUF(totalNodes);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) grid[i][j] = 0;
    }
  }

  // opens the site at the given coordinates and
  // connects it to any surrounding sites that are open
  public void open(int row, int col) {
    checkArgs(row, col);
    if(!isOpen(row, col)) totalOpenSites++;
    grid[row-1][col-1] = 1;
    int i = computeIndex(row, col);
    // if location is in the first row, connect the node to the top virtual node
    if (row == 1) uf.union(0, i);
    // else if the location is in the last row, connect the node to the bottom virtual node
    else if (row == gridDimension) uf.union(totalNodes - 1, i);
    else {
      // connect to above node if open
      if (isOpen(row + 1, col)) uf.union(i, computeIndex(row + 1, col));
      // connect to below node if open
      if (isOpen(row - 1, col)) uf.union(i, computeIndex(row - 1, col));
    }
    // connect to the right node if open
    if (col < gridDimension && isOpen(row, col + 1)) uf.union(i, computeIndex(row, col + 1));
    // connect to the left node if open
    if (col > 1 && isOpen(row, col - 1)) uf.union(i, computeIndex(row, col - 1));
  }

  // checks if the site at the given coordinates is open
  public boolean isOpen(int row, int col) {
    checkArgs(row, col);
    return grid[row-1][col-1] == 1;
  }

  // checks if the site at the given coordinates is full
  public boolean isFull(int row, int col) {
    checkArgs(row, col);
    int i = computeIndex(row, col);
    return uf.connected(0, i);
  }

  // returns the number of open sites in the system
  public int numberOfOpenSites() {
    return totalOpenSites;
  }

   // Returns true if the system percolates
  public boolean percolates() {
    return uf.connected(0, totalNodes - 1);
  }

  // Converts 2D coordinates of the grid to 1D index
  private int computeIndex(int row, int col) {
    return ((row - 1) * gridDimension) + col;
  }

  // Checks row and col arguments
  private void checkArgs(int row, int col) {
    if (row < 1 || col < 1 || row > gridDimension || col > gridDimension) throw new IllegalArgumentException("Invalid arguments");
  }

  public static void main(String[] args) {}
}