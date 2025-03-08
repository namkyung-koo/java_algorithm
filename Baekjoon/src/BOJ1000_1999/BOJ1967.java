package BOJ1000_1999; // 1967 - 트리의 지름

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

public class BOJ1967 {

    static int n;
    static Map<Integer, List<int[]>> graph = new HashMap<>();
    static int[][] distance;
    static PriorityQueue<int[]> pq;
    static final int INF = 100000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        int u, v, w;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }
        int[] distToA = findLongestPath(1);
        int a = findFarthestNode(distToA);
        int[] distToB = findLongestPath(a);
        int b = findFarthestNode(distToB);
        int ret = distToB[b] == INF ? 0 : distToB[b];
        System.out.println(ret);
    }

    private static int findFarthestNode(int[] dist) {
        int max =  0, index = 0;
        for (int i = 1; i <= n; i++) {
            if (max < dist[i]) {
                max = dist[i];
                index = i;
            }
        }
        return index;
    }


    private static int[] findLongestPath(int start) {
        int[] dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = INF;
        }
        dist[start] = 0;

        pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            if (dist[curNode] < curDist) continue;

            for (int[] edge : graph.getOrDefault(curNode, new ArrayList<>())) {
                int nextNode = edge[0];
                int nextDist = edge[1];
                int newDist = dist[curNode] + nextDist;

                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    pq.add(new int[]{nextNode, newDist});
                }
            }
        }
        return dist;
    }
}
