package BOJ11000_11999; // 11779 - 최소비용 구하기 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11779 {

    static int n, m;
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(new int[]{v, w});
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());

        int[] distance = new int[n + 1];
        Arrays.fill(distance, INF);
        distance[s] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{s, 0});
        int[] path = new int[n + 1];
        Arrays.fill(path, -1);

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            if (distance[curNode] < curDist) continue;

            for (int[] edge : graph.getOrDefault(curNode, new ArrayList<>())) {
                int nextNode = edge[0];
                int nextDist = edge[1];
                int newDist = distance[curNode] + nextDist;

                if (distance[nextNode] > newDist) {
                    distance[nextNode] = newDist;
                    path[nextNode] = curNode;
                    pq.add(new int[]{nextNode, newDist});
                }
            }
        }
        if (path[e] == -1) {
            System.out.println(-1);
            return;
        }

        System.out.println(distance[e]);
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(e);
        int now = 0;
        while (now != s) {
            now = path[deque.peekFirst()];
            deque.addFirst(now);
        }
        System.out.println(deque.size());
        for (Integer i : deque) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
