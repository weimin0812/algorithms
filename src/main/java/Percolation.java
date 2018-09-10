import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author weimin02
 * @date 2018/9/8
 * @project algorithms
 */
public class Percolation {
    private final int n;
    private final int topIndex;
    private final int bottomIndex;
    private final boolean[] isOpen;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final WeightedQuickUnionUF judgeFullUF;
    private int numberOfOpenSites = 0;


    /**
     * create n-by-n grid, with all sites blocked
     *
     * @param n
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        this.n = n;
        topIndex = 0;
        bottomIndex = n * n + 1;
        isOpen = new boolean[n * n + 2];
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        judgeFullUF = new WeightedQuickUnionUF(n * n + 1);
        isOpen[topIndex] = true;
        isOpen[bottomIndex] = true;

    }

    private int indexOf(int row, int col) {
        if (row < 1 || row > n) {
            throw new IllegalArgumentException("row index i out of bounds");
        }

        if (col < 1 || col > n) {
            throw new IllegalArgumentException("col index i out of bounds");
        }

        return (row - 1) * n + col;
    }

    /**
     * open site (row, col) if it is not open already
     *
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        int currentIndex = indexOf(row, col);
        if (isOpen(row, col)) {
            return;
        }

        isOpen[currentIndex] = true;
        numberOfOpenSites++;
        if (row == 1) {
            weightedQuickUnionUF.union(topIndex, currentIndex);
            judgeFullUF.union(topIndex, currentIndex);
        }

        if (row == n) {
            weightedQuickUnionUF.union(bottomIndex, currentIndex);
        }

        unionNeighbor(row, col, row - 1, col);
        unionNeighbor(row, col, row + 1, col);
        unionNeighbor(row, col, row, col - 1);
        unionNeighbor(row, col, row, col + 1);
    }

    private void unionNeighbor(int row, int col, int neighborRow, int neighborCol) {
        if (neighborRow > 0 && neighborRow <= n && neighborCol > 0 && neighborCol <= n && isOpen(neighborRow, neighborCol)) {
            weightedQuickUnionUF.union(indexOf(row, col), indexOf(neighborRow, neighborCol));
            judgeFullUF.union(indexOf(row, col), indexOf(neighborRow, neighborCol));
        }
    }

    /**
     * is site (row, col) open?
     *
     * @param row
     * @param col
     * @return
     */
    public boolean isOpen(int row, int col) {
        return isOpen[indexOf(row, col)];
    }

    /**
     * is site (row, col) full?
     *
     * @param row
     * @param col
     * @return
     */
    public boolean isFull(int row, int col) {
        return judgeFullUF.connected(topIndex, indexOf(row, col));
    }

    /**
     * number of open sites
     *
     * @return
     */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /**
     * does the system percolate?
     *
     * @return
     */
    public boolean percolates() {
        return weightedQuickUnionUF.connected(topIndex, bottomIndex);
    }

}
