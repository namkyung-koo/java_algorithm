package BOJ2000_2999; // 2206 - 벽 부수고 이동하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ2206 {

    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        String s;
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            for (int j = 0; j < M; j++)
                map[i][j] = s.charAt(j) - '0';
        }

        Deque<int[]> deque = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2];

        deque.addLast(new int[]{0, 0, 0, 1});
        visited[0][0][0] = true;

        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int row = cur[0];
            int col = cur[1];
            int wall = cur[2];
            int count = cur[3];

            if (row == N - 1 && col == M - 1) {
                System.out.println(count);
                return;
            }

            for (int j = 0; j < 4; j++) {
                int ny = row + dy[j];
                int nx = col + dx[j];
                if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                    if (map[ny][nx] == 0 && !visited[ny][nx][wall]) {
                        visited[ny][nx][wall] = true;
                        deque.addLast(new int[]{ny, nx, wall, count + 1});
                    } else if (map[ny][nx] == 1 && wall == 0 && !visited[ny][nx][1]) {
                        visited[ny][nx][1] = true;
                        deque.addLast(new int[]{ny, nx, 1, count + 1});
                    }
                }
            }
        }
        System.out.println(-1);
    }
}
