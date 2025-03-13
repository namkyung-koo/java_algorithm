package BOJ1000_1999; // 1167 - 트리의 지름

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

public class BOJ1167 {

    static int V;
    static final int INF = 2000000000;
    static Map<Integer, List<int[]>> graph;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        StringTokenizer st;

        graph = new HashMap<>();
        int u, v, w;
        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            graph.put(u, new ArrayList<>());
            while (true) {
                v = Integer.parseInt(st.nextToken());
                if (v == -1)
                    break;
                w = Integer.parseInt(st.nextToken());
                graph.get(u).add(new int[]{v, w});
            }
        }

        int[] distance = dijkstra(1);
        int index = getLongestNode(distance);
        distance = dijkstra(index);
        index = getLongestNode(distance);
        System.out.println(distance[index]);
    }

    public static int getLongestNode(int[] distance) {

        int max = -1;
        int index = 0;
        for (int i = 1; i <= V; i++) {
            if (max < distance[i]) {
                max = distance[i];
                index = i;
            }
        }
        return index;
    }

    public static int[] dijkstra(int start) {

        int[] distance = new int[V + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            if (distance[curNode] < curDist)
                continue;

            for (int[] edge : graph.getOrDefault(curNode, new ArrayList<>())) {
                int nextNode = edge[0];
                int nextDist = edge[1];
                int newDist = distance[curNode] + nextDist;

                if (newDist < distance[nextNode]) {
                    distance[nextNode] = newDist;
                    pq.add(new int[]{nextNode, newDist});
                }
            }
        }
        return distance;
    }
}
