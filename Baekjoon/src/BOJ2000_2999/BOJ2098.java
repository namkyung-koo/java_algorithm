package BOJ2000_2999; // 2098 - 외판원 순회

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2098 {

    static int N;
    static final int INF = 1000000000;
    static int[][] dpTable;
    static List<List<int[]>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++)
            graph.add(new ArrayList<>());

        for (int i = 0; i < N; i++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int w = Integer.parseInt(stz.nextToken());
                if (i == j || w == 0) // w == 0: 길이 없다
                    continue;
                graph.get(i).add(new int[]{j, w});
            }
        }
        dpTable = new int[N][1 << N];
        for (int i = 0; i < N; i++)
            Arrays.fill(dpTable[i], -1);

        System.out.println(travelingSalesmanProblem(0, 1));
    }

    public static int travelingSalesmanProblem(int current, int visited) {
        if (visited == (1 << N) - 1) {
            // 모든 도시를 방문. 현재 도시에서 시작 도시로 돌아감.
            for (int[] edge : graph.get(current)) {
                if (edge[0] == 0) // 시작 도시(0)와 연결됨.
                    return edge[1]; // 시작 도시(0)까지의 거리 반환
            }
            return INF; // 시작 도시로 길이 없음.
        }

        if (dpTable[current][visited] != -1)
            return dpTable[current][visited];

        int minCost = INF;
        for (int[] edge : graph.get(current)) {
            int next = edge[0];
            int cost = edge[1];

            if ((visited & (1 << next)) == 0) { // 다음 도시를 아직 방문하지 않았다면
                int newVisited = visited | (1 << next);
                minCost = Math.min(minCost, cost + travelingSalesmanProblem(next, newVisited));
            }
        }
        dpTable[current][visited] = minCost;
        return minCost;
    }
}
