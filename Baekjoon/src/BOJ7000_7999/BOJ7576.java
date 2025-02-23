package BOJ7000_7999; // 7576 - 토마토

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ7576 {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] tomatoes = new int[n][m];
        int[][] visited = new int[n][m];
        Deque<int[]> q = new ArrayDeque<>();

        // 데이터 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                tomatoes[i][j] = Integer.parseInt(st.nextToken());
                if (tomatoes[i][j] == 1) {
                    q.addLast(new int[] {i, j});
                    visited[i][j] = 1;
                }
            }
        }

        int day = -1;

        while (!q.isEmpty()) {
            int size = q.size();
            day++;

            for (int i = 0; i < size; i++) {
                int[] current = q.pollFirst();
                int x = current[0];
                int y = current[1];

                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && tomatoes[nx][ny] == 0 && visited[nx][ny] == 0) {
                        tomatoes[nx][ny] = 1;
                        visited[nx][ny] = 1;
                        q.addLast(new int[] {nx, ny});
                    }
                }
            }

        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tomatoes[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(day);
    }
}
