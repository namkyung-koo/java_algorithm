package BOJ2000_2999; // 2635 - 치즈

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2638 {

    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (true) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == -1)
                        board[i][j] = 0;
                }
            }
            markOutsideAir();
            List<int[]> meltList = findMeltCheese();

            if (meltList.isEmpty())
                break;
            meltCheese(meltList);
            time++;
        }
        System.out.println(time);
    }

    // BFS로 외부 공기를 -1로 마킹
    static void markOutsideAir() {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[N][M];

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        board[0][0] = -1;  // (0,0)은 항상 외부 공기

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                    if (board[nx][ny] == 0) { // 외부 공기만 -1로 변경
                        board[nx][ny] = -1;
                        queue.offer(new int[]{nx, ny});
                    }
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static List<int[]> findMeltCheese() {
        List<int[]> meltList = new ArrayList<>();

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (board[x][y] == 1) {
                    int count = 0;
                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        if (board[nx][ny] == -1)
                            count++;
                    }
                    if (count >= 2)
                        meltList.add(new int[]{x, y});
                }
            }
        }
        return meltList;
    }

    static void meltCheese(List<int[]> meltList) {
        for (int[] pos : meltList) {
            int x = pos[0], y = pos[1];
            board[x][y] = 0;
        }
    }
}
