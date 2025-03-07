package BOJ11000_11999; // 11404 - 플로이드

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

public class BOJ11404 {

    static int n, m;
    static Map<Integer, List<int[]>> graph;
    static int[][] distance;
    static int INF = 2000000000;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        graph = new HashMap<>();
        int a, b, c;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(a, new ArrayList<>());
            graph.get(a).add(new int[]{b, c});
        }

        distance = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            distance[i] = dijkstra(i);
            for (int j = 1; j <= n; j++) {
                if (distance[i][j] == INF) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(distance[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    static int[] dijkstra(int start) {
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            d[i] = INF;
        }
        d[start] = 0;

        pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int vertex = cur[0];
            int vertexDist = cur[1];

            if (d[vertex] < vertexDist) continue;

            for (int[] edge : graph.getOrDefault(vertex, new ArrayList<>())) {
                int next = edge[0];
                int cost = edge[1];
                int newDist = d[vertex] + cost;

                if (newDist < d[next]) {
                    d[next] = newDist;
                    pq.add(new int[]{next, newDist});
                }
            }
        }
        return d;
    }
}
