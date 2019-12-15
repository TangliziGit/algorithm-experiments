package Experiment2;

import Experiment3.GAOperations;
import javafx.util.Pair;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class AlgorithmComparision {

    @Test
    public void test() {
        int n = 4;
        int[][] graph = {
                { -1, -1, -1, -1, -1 },
                { -1, -1, 9, 19, 13 },
                { -1, 21, -1, -1, 14 },
                { -1, 1, 40, -1, 17 },
                { -1, 41, 80, 10, -1 }
        };

        TestUtil.recordTime(() -> {
            System.out.println("Branch and bound algorithm");
            BranchAndBoundForTsp algo = new BranchAndBoundForTsp(graph, n);
            int ans = algo.solve();
            assertEquals(34, ans);
            return ans;
        });

        TestUtil.recordTime(() -> {
            System.out.println("Backtrace algorithm");
            BacktraceForTsp backtraceForTsp = new BacktraceForTsp();
            backtraceForTsp.backtrack4TSP(graph, n);
            assertEquals(34, backtraceForTsp.bestc);
            return backtraceForTsp.bestc;
        });
    }

    @Test
    public void testWithRandomTestCase() {
        // int[] scales = new int[]{10, 20, 40, 80, 100, 120, 160, 180, 200, 500};
        Map<Integer, Pair<Double, Integer>> backtraceRecord = new HashMap<>();
        Map<Integer, Pair<Double, Integer>> branchAndBoundRecordRestMinCost = new HashMap<>();
        Map<Integer, Pair<Double, Integer>> branchAndBoundRecordTwoMinCost = new HashMap<>();
        Map<Integer, Pair<Double, Integer>> GARecord = new HashMap<>();

        IntStream scale = IntStream.iterate(2, i -> i + 1);
        scale.limit(15).forEach(number -> {
            int[][] graph = TestUtil.createTspTestCase(number);
            int[][] graphUnpad = TestUtil.disablePadding(graph);

            System.out.println("\nScale: " + number);
            GARecord.put(number, TestUtil.recordTime(() -> {
                GAOperations gaOperations = new GAOperations(graphUnpad, number);
                return gaOperations.solve(200);
            }, 25));

            branchAndBoundRecordTwoMinCost.put(number, TestUtil.recordTime(() -> {
                BranchAndBoundForTsp algo = new BranchAndBoundForTsp(graph, number);
                algo.setLowerBoundStrategy(2);
                return algo.solve();
            }, 25));

            branchAndBoundRecordRestMinCost.put(number, TestUtil.recordTime(() -> {
                BranchAndBoundForTsp algo = new BranchAndBoundForTsp(graph, number);
                algo.setLowerBoundStrategy(1);
                return algo.solve();
            }, 25));

            backtraceRecord.put(number, TestUtil.recordTime(() -> {
                BacktraceForTsp backtraceForTsp = new BacktraceForTsp();
                backtraceForTsp.backtrack4TSP(graph, number);
                return backtraceForTsp.bestc;
            }, 25));

            System.out.println("GA: " + GARecord);
            System.out.println("branch and bound(RestMinCost): " + branchAndBoundRecordRestMinCost);
            System.out.println("branch and bound(TwoMinCost): " + branchAndBoundRecordTwoMinCost);
            System.out.println("backtrace: " + backtraceRecord);
        });

    }

}
