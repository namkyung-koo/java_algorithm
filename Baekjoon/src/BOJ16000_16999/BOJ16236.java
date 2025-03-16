package BOJ16000_16999; // 16236 - 아기 상어

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16236 {

    static int N;
    static int[][] map;
    static int[] dy = new int[]{-1, 0, 0, 1};
    static int[] dx = new int[]{0, -1, 1, 0};
    static int sharkY, sharkX, sharkSize = 2, eatCount = 0, time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int fish = Integer.parseInt(st.nextToken());
                map[i][j] = fish;
                if (fish == 9) {
                    sharkY = i;
                    sharkX = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            int[] target = bfs();
            if (target == null) break;

            sharkY = target[0];
            sharkX = target[1];
            time += target[2];
            eatCount++;

            if (eatCount == sharkSize) {
                sharkSize++;
                eatCount = 0;
            }
            map[sharkY][sharkX] = 0;
        }
        System.out.println(time);
    }

    static int[] bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator
                .comparingInt((int[] a) -> a[2])
                .thenComparingInt(a -> a[0])
                .thenComparingInt(a -> a[1])
        );

        boolean[][] visited = new boolean[N][N];
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{sharkY, sharkX, 0});
        visited[sharkY][sharkX] = true;

        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            int y = cur[0];
            int x = cur[1];
            int dist = cur[2];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny >= 0 && ny < N && nx >= 0 && nx < N && !visited[ny][nx]) {
                    if (map[ny][nx] <= sharkSize) {
                        visited[ny][nx] = true;
                        deque.add(new int[]{ny, nx, dist + 1});

                        if (map[ny][nx] > 0 && map[ny][nx] < sharkSize) {
                            pq.add(new int[]{ny, nx, dist + 1});
                        }
                    }
                }
            }
        }
        return pq.isEmpty() ? null : pq.poll();
    }
}