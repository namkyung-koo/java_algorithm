package BOJ11000_11999; // 11657 - 타임머신

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ11657 {

    static int N, M;
    static int A, B, C;
    static final int INF = 1000000000;
    static long[] distance;
    static List<int[]> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            edges.add(new int[]{A, B, C});
        }
        distance = new long[N + 1];
        Arrays.fill(distance, INF);
        distance[1] = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                if (distance[u] != INF && distance[v] > distance[u] + w) {
                    distance[v] = distance[u] + w;
                }
            }
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            if (distance[u] != INF && distance[v] > distance[u] + w) {
                System.out.println(-1);
                return;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            if (distance[i] == INF) {
                sb.append("-1\n");
            } else {
                sb.append(distance[i]).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
