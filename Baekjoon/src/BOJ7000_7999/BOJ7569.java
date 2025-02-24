package BOJ7000_7999; // 7569 - 토마토

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ7569 {

    static int M, N, H;
    static int[][][] tomatoes;
    static boolean[][][] visited;
    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1}; // 높이(H) 방향

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomatoes = new int[H][N][M];
        visited = new boolean[H][N][M];
        Deque<int[]> deque = new ArrayDeque<>();

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    tomatoes[h][n][m] = Integer.parseInt(st.nextToken());
                    if (tomatoes[h][n][m] == 1) {
                        visited[h][n][m] = true;
                        deque.addLast(new int[] {h, n, m});
                    }
                }
            }
        }

        int day = -1;

        while (!deque.isEmpty()) {
            int size = deque.size();
            day++;

            for (int i = 0; i < size; i++) {
                int[] current = deque.pollFirst();
                int h = current[0], x = current[1], y = current[2];

                for (int j = 0; j < 6; j++) {
                    int nh = h + dz[j];
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if (nh >= 0 && nh < H && nx >= 0 && nx < N && ny >= 0 && ny < M
                            && tomatoes[nh][nx][ny] == 0 && !visited[nh][nx][ny]) {
                        visited[nh][nx][ny] = true;
                        tomatoes[nh][nx][ny] = 1;
                        deque.addLast(new int[] {nh, nx, ny});
                    }
                }
            }
        }

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (tomatoes[h][n][m] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        System.out.println(day);
    }
}
