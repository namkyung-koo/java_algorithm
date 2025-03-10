package BOJ1000_1999; // 1865 - 웜홀

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1865 {

    static int TC;
    static int N, M, W;
    static int S, E, T;
    static List<int[]> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        TC = Integer.parseInt(st.nextToken());

        for (int i = 0; i < TC; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            edges = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                S = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
                T = Integer.parseInt(st.nextToken());
                edges.add(new int[]{S, E, T});
                edges.add(new int[]{E, S, T}); // 양방향
            }
            for (int j = 0; j < W; j++) {
                st = new StringTokenizer(br.readLine());
                S = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
                T = Integer.parseInt(st.nextToken());
                edges.add(new int[]{S, E, -T}); // 단방향
            }

            if (!bellmanFord()) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }

    private static boolean bellmanFord() {
        int[] distance = new int[N + 1];

        for (int i = 1; i < N; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                if (distance[v] > distance[u] + w) {
                    distance[v] = distance[u] + w;
                }
            }
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            if (distance[v] > distance[u] + w)
                return true;
        }
        return false;
    }
}
