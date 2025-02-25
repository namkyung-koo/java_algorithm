package BOJ10000_10999; // 10026 - 적록색약

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ10026 {

    static int N;
    static String[] grid;
    static Deque<int[]> deque;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static int normal;
    static int abnormal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        grid = new String[N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            grid[i] = st.nextToken();
        }

        normal = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                    normal++;
                }
            }
        }
        System.out.print(normal + " ");

        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i].charAt(j) == 'G')
                    grid[i] = grid[i].replace('G', 'R');
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                    abnormal++;
                }
            }
        }
        System.out.print(abnormal);
    }

    private static void bfs(int x, int y) {
        deque = new ArrayDeque<>();
        deque.addLast(new int[]{x, y});
        visited[x][y] = true;

        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int cx = cur[0];
            int cy = cur[1];
            for (int j = 0; j < 4; j++) {
                int nx = cx + dx[j];
                int ny = cy + dy[j];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (grid[cx].charAt(cy) == grid[nx].charAt(ny) && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        deque.addLast(new int[]{nx ,ny});
                    }
                }
            }
        }
    }
}
