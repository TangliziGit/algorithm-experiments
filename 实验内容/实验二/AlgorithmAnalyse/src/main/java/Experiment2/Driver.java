package Experiment2;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Driver {

    public static void main(String[] args) {

        Map<Integer, Double> backtraceRecord = new HashMap<>();
        Map<Integer, Double> branchAndBoundRecordRestMinCost = new HashMap<>();
        Map<Integer, Double> branchAndBoundRecordTwoMinCost = new HashMap<>();

        IntStream scale = IntStream.iterate(2, i -> i + 1);
        scale.limit(Integer.parseInt(args[0]) - 1).forEach(number -> {
            int[][] graph = TestUtil.createTspTestCase(number);

            System.out.println("\nScale: " + number);
            branchAndBoundRecordTwoMinCost.put(number, TestUtil.recordTime(() -> {
                BranchAndBoundForTsp algo = new BranchAndBoundForTsp(graph, number);
                algo.setLowerBoundStrategy(2);
                int ans = algo.solve();
            }, 25));

            branchAndBoundRecordRestMinCost.put(number, TestUtil.recordTime(() -> {
                BranchAndBoundForTsp algo = new BranchAndBoundForTsp(graph, number);
                algo.setLowerBoundStrategy(1);
                int ans = algo.solve();
            }, 25));

            backtraceRecord.put(number, TestUtil.recordTime(() -> {
                BacktraceForTsp backtraceForTsp = new BacktraceForTsp();
                backtraceForTsp.backtrack4TSP(graph, number);
                int ans = backtraceForTsp.bestc;
            }, 25));
        });

        System.out.println("branch and bound(RestMinCost): " + branchAndBoundRecordRestMinCost);
        System.out.println("branch and bound(TwoMinCost): " + branchAndBoundRecordTwoMinCost);
        System.out.println("backtrace: " + backtraceRecord);
    }
}
