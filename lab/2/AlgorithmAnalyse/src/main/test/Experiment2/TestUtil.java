package Experiment2;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class TestUtil {

    public static int[][] createTspTestCase(int cityNumber) {
        int[][] edges = new int[cityNumber+1][cityNumber+1];

        for (int i = 0; i <= cityNumber; i++)
            edges[0][i] = edges[i][0] = -1;

        Random random = new Random();
        for (int y = 1; y <= cityNumber; y++)
            for (int x = y; x <= cityNumber; x++){
                int rand = Math.max(-1, random.nextInt(200) - 10);

                edges[y][x] = edges[x][y] = rand;
            }

        return edges;
    }

    public static double recordTime(Runnable func, int time) {
        double avg = 0;
        for (int i=0; i<time; i++){
            Instant before = Instant.now();
            func.run();

            int duration = Duration.between(before, Instant.now()).getNano();
            System.out.println("Time costed(s): " + duration);
            avg += duration;
        }
        return avg/time;
    }

    public static double recordTime(Runnable func) {
        return recordTime(func, 1);
    }
}
