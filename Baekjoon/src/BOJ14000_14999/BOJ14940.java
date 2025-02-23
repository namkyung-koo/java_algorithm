package BOJ14000_14999; // 14940 - 쉬운 최단거리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ14940 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[][] dist = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Deque<int[]> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    deque.addLast(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int count = 0;

        while (!deque.isEmpty()) {
            int size = deque.size();
            count++;
            for (int i = 0; i < size; i++) {
                int[] current = deque.pollFirst();
                int x = current[0];
                int y = current[1];

                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] != 0 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        dist[nx][ny] += count;
                        deque.addLast(new int[] {nx, ny});
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0 && !visited[i][j])
                    System.out.print(-1 + " ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}
