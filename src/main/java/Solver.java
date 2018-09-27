import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author weimin02
 * @date 2018/9/26
 * @project algorithms
 */
public class Solver {
    private boolean isSolvable;
    private MinPQ<SearchNode> minPQ;
    private SearchNode solutionNode;

    /**
     * find a solution to the initial board (using the A* algorithm)
     *
     * @param initial
     */
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("initial can't be null");
        }

        solutionNode = null;
        minPQ = new MinPQ<>();
        minPQ.insert(new SearchNode(initial, 0, null));

        while (true) {
            SearchNode currentNode = minPQ.delMin();
            Board currentBoard = currentNode.getBoard();

            if (currentBoard.isGoal()) {
                isSolvable = true;
                solutionNode = currentNode;
                break;
            }

            if (currentBoard.hamming() == 2 && currentBoard.twin().isGoal()) {
                isSolvable = false;
                break;
            }

            int moves = currentNode.getMoves();
            Board prevBoard = moves > 0 ? currentNode.getPrev().getBoard() : null;

            for (Board nextBoard : currentBoard.neighbors()) {
                if (prevBoard != null && prevBoard.equals(nextBoard)) {
                    continue;
                }

                minPQ.insert(new SearchNode(nextBoard, moves + 1, currentNode));
            }
        }
    }

    /**
     * is the initial board solvable?
     *
     * @return
     */
    public boolean isSolvable() {
        return isSolvable;
    }

    /**
     * min number of moves to solve initial board; -1 if unsolvable
     *
     * @return
     */
    public int moves() {
        return isSolvable() ? solutionNode.getMoves() : -1;
    }

    /**
     * sequence of boards in a shortest solution; null if unsolvable
     *
     * @return
     */
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }

        Deque<Board> solution = new LinkedList<>();
        SearchNode node = solutionNode;
        while (node != null) {
            solution.addFirst(node.getBoard());
            node = node.getPrev();
        }

        return solution;
    }

    /**
     * solve a slider puzzle (given below)
     *
     * @param args
     */
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private class SearchNode implements Comparable<SearchNode> {
        private final SearchNode prev;
        private final Board board;
        private final int moves;

        public SearchNode(Board board, int moves, SearchNode prev) {
            this.prev = prev;
            this.board = board;
            this.moves = moves;
        }

        public int priority() {
            return board.manhattan() + moves;
        }

        public int getMoves() {
            return moves;
        }

        public Board getBoard() {
            return board;
        }

        public SearchNode getPrev() {
            return prev;
        }

        @Override
        public int compareTo(SearchNode that) {
            return this.priority() - that.priority();
        }
    }
}
