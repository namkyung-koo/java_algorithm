package BOJ10000_10999; // 10971 - 외판원 순회 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ10971 {

    static int N;
    static final int INF = 1000000000;
    static List<List<int[]>> graph = new ArrayList<>();
    static int[][] dpTable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++)
            graph.add(new ArrayList<>());

        for (int u = 0; u < N; u++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            for (int v = 0; v < N; v++) {
                int w = Integer.parseInt(stz.nextToken());
                if (w == 0)
                    continue;
                graph.get(u).add(new int[]{v, w});
            }
        }

        dpTable = new int[N][1 << N];
        for (int row = 0; row < N; row++)
            Arrays.fill(dpTable[row], -1);

        int minCost = tsp(0, 1);
        System.out.println(minCost);
    }

    public static int tsp(int current, int visited) {
        if (visited == (1 << N) - 1) {
            for (int[] edge : graph.get(current)) {
                if (edge[0] == 0) // 출발 도시로 돌아가는 경로가 있다면
                    return edge[1];
            }
            return INF;
        }
        // 방문한 적이 있다면 return 해당 비용
        if (dpTable[current][visited] != -1)
            return dpTable[current][visited];

        int minCost = INF;
        for (int[] edge : graph.get(current)) {
            int next = edge[0];
            int cost = edge[1];

            if ((visited & (1 << next)) == 0) { // 다음 도시를 아직 방문하지 않았다면
                int newVisited = visited | (1 << next); // 방문 처리
                minCost = Math.min(minCost, cost + tsp(next, newVisited));
            }
        }
        dpTable[current][visited] = minCost;
        return minCost;
    }
}
