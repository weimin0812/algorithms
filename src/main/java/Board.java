import java.util.LinkedList;
import java.util.List;

/**
 * @author weimin02
 * @date 2018/9/26
 * @project algorithms
 */
public class Board {

    private final int[][] blocks;
    private final int n;
    private int blankRow;
    private int blankCol;


    /**
     * construct a board from an n by n array of blocks
     * where blocks[i][j] = block in row i, column j
     *
     * @param blocks
     */
    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new IllegalArgumentException("Blocks can't be null");
        }

        this.blocks = copyOf(blocks);
        n = blocks.length;
        blankRow = -1;
        blankCol = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break;
                }
            }
        }
    }

    private int[][] copyOf(int[][] matrix) {
        int[][] copy = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                copy[i][j] = matrix[i][j];
            }
        }

        return copy;
    }

    /**
     * board dimension n
     *
     * @return
     */
    public int dimension() {
        return n;
    }

    /**
     * number of blocks out of place
     *
     * @return
     */
    public int hamming() {
        int result = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (i == blankRow && j == blankCol) {
                    continue;
                }

                if (manhattan(i, j) != 0) {
                    result++;
                }
            }
        }

        return result;
    }

    private int manhattan(int i, int j) {
        int value = blocks[i][j] - 1;
        return Math.abs(i - value / n) + Math.abs(j - value % n);
    }

    /**
     * sum of Manhattan distances between blocks and goal
     *
     * @return
     */
    public int manhattan() {
        int result = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (i == blankRow && j == blankCol) {
                    continue;
                }

                result += manhattan(i, j);
            }
        }

        return result;
    }

    /**
     * is this board the goal board?
     *
     * @return
     */
    public boolean isGoal() {
        return hamming() == 0;
    }

    /**
     * a board that is obtained by exchanging any pair of blocks
     *
     * @return
     */
    public Board twin() {
        int[][] copy = copyOf(blocks);
        if (blankRow != 0) {
            swap(copy, 0, 0, 0, 1);
        } else {
            swap(copy, 1, 0, 1, 1);
        }
        return new Board(copy);
    }

    private void swap(int[][] matrix, int rowA, int colA, int rowB, int colB) {
        int t = matrix[rowA][colA];
        matrix[rowA][colA] = matrix[rowB][colB];
        matrix[rowB][colB] = t;
    }

    /**
     * does this board equal y?
     *
     * @param y
     * @return
     */
    @Override
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }

        if (!(y instanceof Board)) {
            return false;
        }

        Board that = (Board) y;
        if (this.n != that.n) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.blocks[i][j] != that.blocks[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * all neighboring boards
     *
     * @return
     */
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new LinkedList<>();
        if (blankRow > 0) {
            int[][] north = copyOf(blocks);
            swap(north, blankRow, blankCol, blankRow - 1, blankCol);
            neighbors.add(new Board(north));
        }

        if (blankRow < n - 1) {
            int[][] south = copyOf(blocks);
            swap(south, blankRow, blankCol, blankRow + 1, blankCol);
            neighbors.add(new Board(south));
        }

        if (blankCol > 0) {
            int[][] west = copyOf(blocks);
            swap(west, blankRow, blankCol, blankRow, blankCol - 1);
            neighbors.add(new Board(west));
        }

        if (blankCol < n - 1) {
            int[][] east = copyOf(blocks);
            swap(east, blankRow, blankCol, blankRow, blankCol + 1);
            neighbors.add(new Board(east));
        }

        return neighbors;
    }

    /**
     * string representation of this board (in the output format specified below)
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(blocks[i][j] + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * unit tests (not graded)
     *
     * @param args
     */
    public static void main(String[] args) {
    }

}
