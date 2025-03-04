package BOJ1000_1999; // 최소 비용 구하기

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ1916 {

    static int N, M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        M = Integer.parseInt(sc.nextLine());

        int[] distance = new int[N + 1];
        Map<Integer, List<int[]>> graph = new HashMap<>();

        int u, v, w;
        for (int i = 0; i < M; i++) {
            String input = sc.nextLine();
            String[] s = input.split(" ");
            u = Integer.parseInt(s[0]);
            v = Integer.parseInt(s[1]);
            w = Integer.parseInt(s[2]);
            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(new int[]{v, w});
        }

        String[] s = sc.nextLine().split(" ");
        int start = Integer.parseInt(s[0]), end = Integer.parseInt(s[1]);
        for (int i = 1; i <= N; i++) {
            if (i == start) {
                distance[i] = 0;
                continue;
            }
            distance[i] = 1000000000;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, start});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curCost = cur[0];
            int curCity = cur[1];

            if (curCost > distance[curCity])
                continue;

            for (int[] edge : graph.getOrDefault(curCity, new ArrayList<>())) {
                int nextCity = edge[0];
                int nextCost = edge[1];
                int sumCost = distance[curCity] + nextCost;

                if (sumCost < distance[nextCity]) {
                    distance[nextCity] = sumCost;
                    pq.add(new int[]{sumCost, nextCity});
                }
            }
        }
        System.out.println(distance[end]);
    }
}
