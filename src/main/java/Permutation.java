import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author weimin02
 * @date 2018/9/13
 * @project algorithms
 */
public class Permutation {
    /**
     * Write a client program Permutation.java that takes an integer k as a command-line argument;
     * reads in a sequence of strings from standard input using StdIn.readString();
     * and prints exactly k of them, uniformly at random.
     * Print each item from the sequence at most once.
     *
     * @param args
     */
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> stringRandomizedQueue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            stringRandomizedQueue.enqueue(s);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(stringRandomizedQueue.dequeue());
        }

    }
}
