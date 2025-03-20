package BOJ14000_14999; // 14938 - 서강그라운드

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

public class BOJ14938 {

    // 최단 경로를 구한 다음에 수색 범위 >= 거리 모두 더하기
    static int n, m, r;
    static int[][] distance;
    static final int INF = 1000000000;
    static Map<Integer, List<int[]>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        int[] items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }

        distance = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            distance[i] = dijkstra(i);
        }
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] += items[i];
            for (int j = 1; j <= n; j++) {
                if (i != j && distance[i][j] <= m) {
                    sum[i] += items[j];
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(sum[i], max);
        }
        System.out.println(max);
    }

    public static int[] dijkstra(int s) {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, INF);
        distance[s] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{s, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int dist = cur[1];

            if (distance[node] < dist)
                continue;

            for (int[] edge : graph.getOrDefault(node, new ArrayList<>())) {
                int nextNode = edge[0];
                int nextDist = edge[1];
                int newDist = distance[node] + nextDist;

                if (distance[nextNode] > newDist) {
                    distance[nextNode] = newDist;
                    pq.add(new int[]{nextNode, newDist});
                }
            }
        }
        return distance;
    }
}
