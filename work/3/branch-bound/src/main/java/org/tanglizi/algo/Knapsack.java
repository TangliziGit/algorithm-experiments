package org.tanglizi.algo;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Function;

/**
 * Branch and bound algorithm for TSP question.
 * Note all the array index means 1 -> n, the zero index is not used.
 *
 * @author ZhangChunxu
 */
public class Knapsack {

    private int[] values;
    private int[] weights;
    private int totalWeight, n;

    private PriorityQueue<Node> que;

    private int ansValue, ansWeight;
    private boolean[] ansVector;

    public Knapsack(int[] values, int[] weights, int totalWeight, int number){
        this.values = values;
        this.weights = weights;
        this.totalWeight = totalWeight;
        this.n = number;
        this.que = new PriorityQueue<>();
        this.ansValue = Integer.MAX_VALUE;
    }

    private int computeLowerBound() {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for (int i=0; i<n; i++)
            pairs.add(new Pair<>(values[i], weights[i]));
        pairs.sort((x, y) -> -1*Double.compare(x.getKey()/(double)x.getValue(), y.getKey()/(double)y.getValue()));

        int weight = totalWeight, value = 0;
        for (Pair<Integer, Integer> pair: pairs) {
            if (weight < pair.getValue()) continue;
            weight -= pair.getValue();
            value += pair.getKey();
        }
        return value;
    }

    private int computeUpperBound(Node node) {
        if (node.length == n) return node.value;

        int nextValue = values[node.length];
        int nextWeight = weights[node.length];
        return (int)(node.value + (totalWeight - node.weight) * (nextValue / (double)nextWeight));
    }

    public int solve() {
        Node.upperBoundComputer = this::computeUpperBound;
        ansValue = computeLowerBound();
        Node startNode = Node.start(n);
        que.add(startNode);

        System.out.println("start with lower bound: " + ansValue);
        while (!que.isEmpty()) {
            Node node = que.poll();

            if (node.length == n) {
                ansValue = node.value;
                ansWeight = node.weight;
                ansVector = node.state.clone();
                continue;
            }

            int i = node.length;
            Node newNodePick = Node.nextFrom(node, i, this);
            if (null != newNodePick && newNodePick.upperBound >= ansValue)
                que.add(newNodePick);

            Node newNodeUnpick = Node.nextFrom(node, -1, this);
            if (null != newNodeUnpick && newNodeUnpick.upperBound >= ansValue)
                que.add(newNodeUnpick);
        }

        return ansValue;
    }

    public static class Node implements Comparable<Node> {

        static Function<Node, Integer> upperBoundComputer;
        boolean[] state;
        int length;
        int value, weight, upperBound;

        public Node(boolean[] state, int length, int value, int weight) {
            this.state = state;
            this.length = length;
            this.value = value;
            this.weight = weight;
            this.upperBound = upperBoundComputer.apply(this);
        }

        static Node start(int n){
           return new Node(new boolean[n], 0, 0, 0);
        }

        static Node nextFrom(Node from, int select, Knapsack outer) {
            boolean[] newState = from.state.clone();
            if (select < 0){
                newState[from.length] = false;
                return new Node(from.state, from.length+1, from.value, from.weight);
            }

            newState[select] = true;
            int newValue = from.value + outer.values[select];
            int newWeight = from.weight + outer.weights[select];

            if (newWeight > outer.totalWeight)
                return null;
            return new Node(newState, select+1, newValue, newWeight);
        }

        @Override
        public String toString() {
            return "State(" + length + "): " + Arrays.toString(state) + "\n" +
                    "Value: " + value + "\n" +
                    "Weight: " + weight + "\n" +
                    "UpBound: " + upperBound + "\n";
        }

        @Override
        public int compareTo(Node x) {
            // max heap PriorityQueue
            return -1*Integer.compare(upperBound, x.upperBound);
        }
    }
}
