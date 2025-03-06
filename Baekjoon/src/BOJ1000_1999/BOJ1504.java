package BOJ1000_1999; // 1504 - 특정한 최단 경로

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1504 {

    static int N, E;
    static Map<Integer, List<int[]>> graph;
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }

        int a, b, c;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] dist1 = dijkstra(1);
        int[] distV1 = dijkstra(v1);;
        int[] distV2 = dijkstra(v2);

        long shortestPath1 = (long) dist1[v1] + distV1[v2] + distV2[N];
        long shortestPath2 = (long) dist1[v2] + distV2[v1] + distV1[N];
        long result = Math.min(shortestPath1, shortestPath2);
        System.out.println(result >= INF ? -1 : result);
    }

    static int[] dijkstra(int start) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int vertex = cur[0];
            int dist = cur[1];

            if (distance[vertex] < dist) continue;

            for (int[] edge : graph.getOrDefault(vertex, new ArrayList<>())) {
                int next = edge[0];
                int cost = edge[1];

                int newDist = distance[vertex] + cost;
                if (newDist < distance[next]) {
                    distance[next] = newDist;
                    pq.add(new int[]{next, newDist});
                }
            }
        }
        return distance;
    }
}
