package BOJ1000_1999; // 1102 - 발전소

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1102 {

    static int N, P;
    static final int INF = 1000000000;
    static int[][] dist;
    static int bitmask = 0;
    static int[] dpTable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(stz.nextToken());
            }
        }
        String s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'Y')
                bitmask |= (1 << i);
        }

        P = Integer.parseInt(br.readLine());
        if (Integer.bitCount(bitmask) == 0 && P > 0) {
            System.out.println(-1);
            return;
        }

        floyd();
        dpTable = new int[1 << N];
        Arrays.fill(dpTable, INF);
        int result = dfs(bitmask);
        System.out.println(result == INF ? -1 : result);
    }

    public static int dfs(int visited) {
        if (Integer.bitCount(visited) >= P)
            return 0;
        if (dpTable[visited] != INF)
            return dpTable[visited];

        int minCost = INF;
        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) == 0) {
                int next = visited | (1 << i);

                for (int j = 0; j < N; j++) {
                    if ((visited & (1 << j)) != 0) {
                        minCost = Math.min(minCost, dist[j][i] + dfs(next));
                    }
                }
            }
        }
        dpTable[visited] = minCost;
        return dpTable[visited];
    }

    public static void floyd() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }
}
