package Experiment3;

import org.junit.Test;

import java.util.Arrays;

public class GAOperationsTest {

    private final int[][] a = {
            {100, 3, 1, 5, 8},
            {3, 100, 6, 7, 9},
            {1, 6, 100, 4, 2},
            {5, 7, 4, 100, 3},
            {8, 9, 2, 3, 100}
    };

    private GAOperations gaOperations;

    @Test
    public void testSolve() {
        gaOperations = new GAOperations(a, 5);
        gaOperations.solve(300);

        int[][] props = gaOperations.props;
        double[] fitness = gaOperations.fitness;

        int maxIdx = 0;
        for (int i=0; i<fitness.length; i++) {
            if (fitness[i] < fitness[maxIdx]) maxIdx = i;
        }

        System.out.println(Arrays.toString(props[maxIdx]));
        System.out.println(getDistance(props[maxIdx]));
    }

    private double getDistance(int[] xs) {
        double ans = a[xs[xs.length-1]-1][xs[0]-1];
        for (int i=1; i<xs.length; i++)
            ans += a[xs[i-1]-1][xs[i]-1];
        return ans;
    }
}
