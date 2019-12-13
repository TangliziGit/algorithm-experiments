package Experiment2;

import javafx.util.Pair;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TestUtil {

    public static int[][] createTspTestCase(int cityNumber) {
        int[][] edges = new int[cityNumber+1][cityNumber+1];

        for (int i = 0; i <= cityNumber; i++)
            edges[0][i] = edges[i][0] = -1;

        Random random = new Random();
        for (int y = 1; y <= cityNumber; y++)
            for (int x = y; x <= cityNumber; x++){
                int rand = Math.max(0, random.nextInt(200) - 10);

                edges[y][x] = edges[x][y] = rand;
            }

        return edges;
    }

    public static int[][] disablePadding(int[][] graph) {
        int[][] res = new int[graph.length-1][graph[0].length - 1];

        for (int y=0; y<res.length; y++) {
            for (int x = 0; x < res[0].length; x++)
                res[y][x] = graph[y + 1][x + 1];
        }
        return res;
    }

    public static Pair<Double, Integer> recordTime(Supplier<Integer> func, int time) {
        double avg = 0;
        int ans = 0;
        for (int i=0; i<time; i++){
            Instant before = Instant.now();
            ans += func.get();

            int duration = Duration.between(before, Instant.now()).getNano();
            System.out.println("Time costed(s): " + duration);
            avg += duration;
        }
        return new Pair<>(avg/time, ans/time);
    }

    public static Pair<Double, Integer> recordTime(Supplier<Integer> func) {
        return recordTime(func, 1);
    }
}
