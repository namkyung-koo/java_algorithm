package BOJ17000_17999; // 17144 - 미세먼지 안녕!

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ17144 {

    static int R, C, T;
    static Deque<int[]> airCleaner = new ArrayDeque<>();
    static int[][] room;
    static int[] dy = new int[]{1, -1, 0, 0};
    static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        room = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {
                    airCleaner.add(new int[]{i, j});
                }
            }
        }
        int fineDust = 0;

        for (int i = 0; i < T; i++) {
            expandFindDust();
            operateAirCleaner();
            fineDust = sumFineDust();
        }
        System.out.println(fineDust);
    }

    static int sumFineDust() {
        int sum = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] == -1)
                    continue;
                sum += room[i][j];
            }
        }
        return sum;
    }

    static void operateAirCleaner() {
        int upper = airCleaner.peekFirst()[0];
        int lower = airCleaner.peekLast()[0];

        for (int i = upper - 1; i > 0; i--) room[i][0] = room[i - 1][0];
        for (int i = 0; i < C - 1; i++) room[0][i] = room[0][i + 1];
        for (int i = 0; i < upper; i++) room[i][C - 1] = room[i + 1][C - 1];
        for (int i = C - 1; i > 1; i--) room[upper][i] = room[upper][i - 1];
        room[upper][1] = 0;

        for (int i = lower + 1; i < R - 1; i++) room[i][0] = room[i + 1][0];
        for (int i = 0; i < C - 1; i++) room[R - 1][i] = room[R - 1][i + 1];
        for (int i = R - 1; i > lower; i--) room[i][C - 1] = room[i - 1][C - 1];
        for (int i = C - 1; i > 1; i--) room[lower][i] = room[lower][i - 1];
        room[lower][1] = 0;

    }

    static void expandFindDust() {
        int[][] tempRoom = new int[R][C];

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if (room[y][x] > 0) {
                    int dust = room[y][x] / 5;
                    int count = 0;

                    for (int i = 0; i < 4; i++) {
                        int ny = y + dy[i];
                        int nx = x + dx[i];

                        if (ny >= 0 && ny < R && nx >= 0 && nx < C && room[ny][nx] != -1) {
                            tempRoom[ny][nx] += dust;
                            count++;
                        }
                    }
                    tempRoom[y][x] += room[y][x] - (dust * count);
                } else if (room[y][x] == -1) {
                    tempRoom[y][x] = -1;
                }
            }
        }
        room = tempRoom;
    }
}
