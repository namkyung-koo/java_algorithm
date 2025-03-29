package BOJ17000_17999; // 17182 - 우주 탐사선

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17182 {

    static int N, K;
    static final int INF = 100000000;
    static int[][] distance;
    static int minTime = INF;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stz.nextToken());
        K = Integer.parseInt(stz.nextToken());

        distance = new int[N][N];
        for (int u = 0; u < N; u++) {
            stz = new StringTokenizer(br.readLine());
            for (int v = 0; v < N; v++) {
                distance[u][v] = Integer.parseInt(stz.nextToken());
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
        dfs(K, 1 << K, 0);

        System.out.println(minTime);
    }

    public static void dfs(int cur, int visited, int cost) {
        if (visited == (1 << N) - 1) {
            minTime = Math.min(minTime, cost);
            return;
        }

        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) != 0) continue;
            dfs(next, visited | 1 << next, cost + distance[cur][next]);
        }
    }
}
