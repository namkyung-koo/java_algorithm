package BOJ16000_16999; // 16991 - 외판원 순회 3

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16991 {

    static int N;
    static final double INF = 1000000000;
    static double[][] memo;
    static List<List<double[]>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] coordinate = new int[N][2];
        for (int city = 0; city < N; city++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stz.nextToken());
            int y = Integer.parseInt(stz.nextToken());
            coordinate[city] = new int[]{x, y};
        }

        for (int u = 0; u < N; u++) {
            double x1 = coordinate[u][0];
            double y1 = coordinate[u][1];
            graph.add(new ArrayList<>());
            for (int v = 0; v < N; v++) {
                if (u == v)
                    continue;
                double x2 = coordinate[v][0];
                double y2 = coordinate[v][1];
                double cost = getCost(x1, y1, x2, y2);
                graph.get(u).add(new double[]{v, cost});
            }
        }
        memo = new double[(int) N][(int) 1 << N];
        for (int r = 0; r < N; r++)
            Arrays.fill(memo[r], -1);

        System.out.println(tsp(0, 1));
    }

    public static double tsp(int current, int visited) {
        if (visited == (1 << N) - 1) {
            for (double[] edge : graph.get(current)) {
                if (edge[0] == 0)
                    return edge[1];
            }
            return INF;
        }

        if (memo[current][visited] != -1)
            return memo[current][visited];

        double minCost = INF;
        for (double[] edge : graph.get(current)) {
            int next = (int) edge[0];
            double cost = edge[1];

            if ((visited & (1 << next)) == 0) {
                int newVisit = visited | (1 << next);
                minCost = Math.min(minCost, cost + tsp(next, newVisit));
            }
        }
        memo[current][visited] = minCost;
        return minCost;
    }

    public static double getCost(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
