package BOJ16000_16999; // 16982 - 뱀과 사다리 게임

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ16928 {

    static int N, M;
    static int[] board = new int[101];
    static boolean[] visited = new boolean[101];
    static Deque<int []> deque;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 100 ; i++)
            board[i] = i;

        // 사다리 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x] = y;
        }

        // 뱀 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            board[u] = v;
        }

        System.out.println(bfs());
    }

    static int bfs() {
        deque = new ArrayDeque<>();
        deque.addLast(new int[]{1, 0});
        visited[1] = true;

        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int pos = cur[0];
            int cnt = cur[1];

            if (pos == 100)
                return cnt;

            for (int i = 1; i <= 6; i++) {
                int next = pos + i;

                if (next > 100)
                    continue;
                next = board[next];
                if (!visited[next]) {
                    visited[next] = true;
                    deque.addLast(new int[]{next, cnt + 1});
                }
            }
        }
        return -1;
    }
}
