package BOJ1000_1999; // 1753 - 최단 경로

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

public class BOJ1753 {

    static final int INF = 1000000000;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    static int[] distance;
    static int V, E, start, u, v, w;
    static Map<Integer, List<int[]>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); // 정점의 개수
        E = Integer.parseInt(st.nextToken()); // 간선의 개수
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()); //  시작 정점

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(new int[]{v, w});
        }

        // 각 정점까지의 최단 거리의 배열을 생성 및 INF로 초기화
        distance = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            if (i == start) {
                distance[i] = 0;
                continue;
            }
            distance[i] = INF;
        }

        pq.add(new int[]{0, start}); // (현재까지의 최단 거리, 정점 번호)

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int dist = cur[0]; // 현재까지의 최단 거리
            int current = cur[1]; // 현재 방문하는 접점

            if (dist > distance[current]) // 방문한 정점의 최단 거리가 이미 갱신된 경우, 스킵
                continue;

            for (int[] edge : graph.getOrDefault(current, new ArrayList<>())) {
                int next = edge[0]; // 도착 정점
                int weight = edge[1]; // 간선 가중치
                int newDistance = distance[current] + weight; // 새로운 거리 계산

                if (newDistance < distance[next]) {
                    distance[next] = newDistance;
                    pq.add(new int[]{newDistance, next});
                }
            }
        }
        for (int i = 1; i < V + 1; i++) {
            if (distance[i] == 1000000000) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }
}