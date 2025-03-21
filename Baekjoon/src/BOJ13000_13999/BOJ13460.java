package BOJ13000_13999; // 13460 - 구슬 탈출 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ13460 {

    static int R, C;
    static String[] board;
    static Deque<int[]> deque = new ArrayDeque<>();
    static int[] dy = new int[]{1, -1, 0, 0};
    static int[] dx = new int[]{0, 0, -1, 1};
    static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new String[R];
        visited = new boolean[R][C][R][C];
        int ry = 0, rx = 0, by = 0, bx = 0;
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine();
            for (int j = 0; j < C; j++) {
                if (board[i].charAt(j) == 'R') {
                    ry = i;
                    rx = j;
                } else if (board[i].charAt(j) == 'B') {
                    by = i;
                    bx = j;
                }
            }
        }
        deque.add(new int[]{ry, rx, by, bx});
        visited[ry][rx][by][bx] = true;

        int count = 0;

        while (!deque.isEmpty()) {
            if (count > 10) {
                System.out.println(-1);
                return;
            }
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int[] cur = deque.poll();
                ry = cur[0]; rx = cur[1];
                by = cur[2]; bx = cur[3];

                // 성공 조건
                if (board[ry].charAt(rx) == 'O' && board[by].charAt(bx) != 'O') {
                    System.out.println(count);
                    return;
                }
                // 실패 조건
                if (board[by].charAt(bx) == 'O')
                    continue;

                for (int d = 0; d < 4; d++) {
                    int[] rMove = move(ry, rx, dy[d], dx[d]);
                    int[] bMove = move(by, bx, dy[d], dx[d]);

                    // 파란 공이 구멍에 빠지면 제외
                    if (board[bMove[0]].charAt(bMove[1]) == 'O')
                        continue;

                    // 구슬끼리 겹치면 위치 변경. cnt가 더 크다면 뒤에서 출발했다는 것이니까 해당 구슬이 뒤에 위치하게 조정한다.
                    if (rMove[0] == bMove[0] && rMove[1] == bMove[1]) {
                        if (rMove[2] > bMove[2]) {
                            rMove[0] -= dy[d];
                            rMove[1] -= dx[d];
                        } else {
                            bMove[0] -= dy[d];
                            bMove[1] -= dx[d];
                        }
                    }

                    if (!visited[rMove[0]][rMove[1]][bMove[0]][bMove[1]]) {
                        visited[rMove[0]][rMove[1]][bMove[0]][bMove[1]] = true;
                        deque.add(new int[]{rMove[0], rMove[1], bMove[0], bMove[1]});
                    }
                }
            }
            count++;
        }
        System.out.println(-1);
    }
    public static int[] move(int y, int x, int dy, int dx) {
        int cnt = 0;
        while (board[y + dy].charAt(x + dx) != '#' && board[y].charAt(x) != 'O') {
            y += dy;
            x += dx;
            cnt++;
        }
        return new int[]{y, x, cnt};
    }
}
