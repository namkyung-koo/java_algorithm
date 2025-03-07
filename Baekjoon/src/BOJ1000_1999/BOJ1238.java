package BOJ1000_1999; // 1238 - 파티

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238 {

    static int N, M, X, T;
    static Map<Integer, List<int[]>> graph;
    static int[][] distance;
    static final int INF = 10000000;
    static PriorityQueue<int[]> pq;

    // 1. 모든 N에서 X까지의 최단 거리를 저장(dijkstra 알고리즘 함수)
    // 2. X에서 각각의 N까지의 거리를 더한 뒤, Math.max() 호출
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new HashMap<>();
        int A, B;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(A, new ArrayList<>());
            graph.get(A).add(new int[]{B, T});
        }
        distance = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            distance[i] = dijkstra(i);
        }
        int[] sumDistance = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sumDistance[i] = distance[i][X] + distance[X][i];
        }
        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (max < sumDistance[i])
                max = sumDistance[i];
        }
        System.out.println(max);
    }

    static int[] dijkstra(int start) {
        int[] distance = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            distance[i] = INF;
        }
        distance[start] = 0;
        pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int vertex = cur[0];
            int dist = cur[1];

            if (distance[vertex] < dist) continue;

            for (int[] edge : graph.getOrDefault(vertex, new ArrayList<>())) {
                int next = edge[0];
                int nextDist = edge[1];
                int newDist = distance[vertex] + nextDist;

                if (newDist < distance[next]) {
                    distance[next] = newDist;
                    pq.add(new int[]{next, newDist});
                }
            }
        }
        return distance;
    }
}
