package Experiment2;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Branch and bound algorithm for TSP question.
 * Note all the array index means 1 -> n, the zero index is not used.
 *
 * @author ZhangChunxu
 */
public class BranchAndBoundForTsp {

    private final int NO_EDGE = -1;

    private int[][] graph;
    private int n;
    private PriorityQueue<Node> que;
    private int[] minOutEdgeCost;

    private int answer;
    private int[] ansVector;

    public BranchAndBoundForTsp(int[][] graph, int number){
        this.graph = graph;
        this.n = number;
        this.que = new PriorityQueue<>();
        this.minOutEdgeCost = new int[n+1];
        this.answer = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++)
            minOutEdgeCost[i] = getMinOutEdgeCost(i);
    }

    private int getMinOutEdgeCost(int x) {
        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) if (graph[x][i] != NO_EDGE)
            minCost = Math.min(minCost, graph[x][i]);
        return minCost;
    }

    private int computeLowerBoundByRestMinCost(Node node) {
        return node.restMinCost + node.cost;
    }

    private int computeLowerBoundByTwoMinCost(Node node) {
        if (node.size == 1) return 0;

        int bound = 0, size = node.size;
        int[] state = node.state;
        boolean[] vis = new boolean[n+1];
        vis[1] = true;
        for (int i=1; i<size; i++) {
            bound += 2 * graph[state[i - 1]][state[i]];
            vis[state[i]] = true;
        }

        int min = Integer.MAX_VALUE;
        for (int i=1; i<n; i++) {
            if (graph[1][i] == NO_EDGE || i == state[1]) continue;
            min = Math.min(min, graph[1][i]);
        }
        bound += min;

        min = Integer.MAX_VALUE;
        for (int i=1; i<n; i++) {
            if (graph[state[size-1]][i] == NO_EDGE || i == state[size-1]) continue;
            min = Math.min(min, graph[1][i]);
        }
        bound += min;

        for (int i=1; i<n; i++) if (!vis[i]) {
            int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
            for (int j=1; j<n; j++)
                if (graph[i][j] != NO_EDGE) {
                    if (first >= graph[i][j]){
                        second = first;
                        first = graph[i][j];
                    } else if (second > graph[i][j])
                        second = graph[i][j];
                }
            bound += first + second;
        }
        return bound/2;
    }

    private int computeUpperBound(int x, boolean[] vis, int size) {
        if (x == 1 && size == n) return 0;

        for (int i=1; i<=n; i++) if (!vis[i]) {
            if (graph[x][i] == NO_EDGE) continue;

            vis[i] = true;
            int ans = computeUpperBound(i, vis, size + 1);
            if (ans != -1) return ans + graph[x][i];
            vis[i] = false;
        }
        return -1;
    }

    public void setLowerBoundStrategy(int kind) {
        switch (kind) {
            case 1:
                Node.computer = this::computeLowerBoundByRestMinCost;
                break;
            case 2:
                Node.computer = this::computeLowerBoundByTwoMinCost;
                break;
            default:
                Node.computer = this::computeLowerBoundByRestMinCost;
        }
    }

    public int solve() {
        // initialize lower bound computer
        if (null == Node.computer)
            Node.computer = this::computeLowerBoundByRestMinCost;
        answer = computeUpperBound(1, new boolean[n+1], 0);
        Node startNode = new Node(
                IntStream.iterate(1, i -> i+1).limit(n).toArray(),
                1, 0,
                Arrays.stream(minOutEdgeCost).sum());
        que.add(startNode);

        System.out.println("start with upper bound: " + answer);
        while (!que.isEmpty()) {
            Node node = que.poll();

            if (node.size == n) {
                int backCost = graph[node.state[n-1]][1];
                if (backCost == NO_EDGE) continue;
                ansVector = node.state.clone();
                answer = node.cost + backCost;
                break;
            }

            int[] state = node.state;
            for (int i = node.size; i < n; i++)
                if (graph[state[node.size-1]][state[i]] != NO_EDGE) {
                    Node next = Node.nextFrom(node, i, this);

                    if (next.lowerBound >= answer) continue;
                    que.add(next);
                }
        }

        return answer;
    }

    public static class Node implements Comparable<Node> {

        static Function<Node, Integer> computer;
        int[] state; // Vector<Integer> state;
        int cost, restMinCost, lowerBound, size;

        Node(int[] state, int size, int cost, int restMinCost) {
            this.state = state;
            this.size = size;
            this.cost = cost;
            this.restMinCost = restMinCost;
            this.lowerBound = computer.apply(this);
        }

        static Node nextFrom(Node from, int nextPosition, BranchAndBoundForTsp outer) {
            // Vector<Integer> newState = new Vector<>(from.state);
            int[] newState = from.state.clone();
            int fromElement = from.state[from.size-1];
            int toElement = from.state[nextPosition];
            newState[nextPosition] = from.state[from.size];
            newState[from.size] = toElement;

            return new Node(newState, from.size + 1,
                    from.cost + outer.graph[fromElement][toElement],
                    from.restMinCost - outer.minOutEdgeCost[fromElement]);
        }

        void debug(String comment) {
            StringBuilder builder = new StringBuilder();
            builder.append(comment).append('\n')
                    .append("Size ").append(size)
                    .append(" State ").append(Arrays.toString(state)).append('\n')
                    .append("Cost ").append(cost)
                    .append(" RestCost ").append(restMinCost)
                    .append(" LowerBound ").append(lowerBound);
            System.out.println(builder);
        }

        void debug() {
            debug("");
        }

        @Override
        public int compareTo(Node x) {
            return -1*Integer.compare(lowerBound, x.lowerBound);
        }
    }
}
