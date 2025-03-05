package BOJ5000_5999; // 5972 - 택배 배송

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

public class BOJ5972 {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int A, B, C;
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(A, new ArrayList<>());
            graph.putIfAbsent(B, new ArrayList<>());
            graph.get(A).add(new int[]{B, C});
            graph.get(B).add(new int[]{A, C});
        }

        // 시작점으로부터 최소 거리를 저장하는 배열 초기화
        int[] distance = new int[N + 1];
        distance[1] = 0;
        for (int i = 2; i <= N; i++) {
            distance[i] = 10000;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{1, 0}); // {현서 위치, 현재 소}

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int nowN = cur[0];
            int nowC = cur[1];

            if (distance[nowN] < nowC)
                continue;

            for (int[] values : graph.getOrDefault(nowN, new ArrayList<>())) {
                int nextN = values[0];
                int nextC = values[1];

                int newC = distance[nowN] + nextC;
                if (newC < distance[nextN]) {
                    distance[nextN] = newC;
                    pq.add(new int[]{nextN, newC});
                }
            }
        }
        System.out.println(distance[N]);
    }
}
