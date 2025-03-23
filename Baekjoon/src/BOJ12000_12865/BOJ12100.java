package BOJ12000_12865; // 12100 - 2048 (Easy)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ12100 {

    static int N;
    static int[][] board;
    static Deque<int[][]> boards = new ArrayDeque<>();
    static int[] dy = new int[]{-1, 1, 0, 0};
    static int[] dx = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                int n = Integer.parseInt(st.nextToken());
                board[row][col] = n;
            }
        }

        // 최초의 게임 보드 저장
        boards.add(deepCopyBoard(board));

        for (int T = 0; T < 5; T++) {
            int mapSize = boards.size();

            for (int ms = 0; ms < mapSize; ms++) {
                int[][] prevMap = boards.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int[][] movedMap = move(prevMap, dir);
                    boards.add(movedMap);
                }
            }
        }
        int ret = 0;
        for (int[][] finalBoard : boards) {
            ret = Math.max(ret, getMaxValue(finalBoard));
        }
        System.out.println(ret);
    }


    static int[][] move(int[][] original, int dir) {

        int[][] movedMap = new int[N][N];

        switch (dir) {
            case 0: movedMap = mergeTop(deepCopyBoard(original)); break;
            case 1: movedMap = mergeBottom(deepCopyBoard(original)); break;
            case 2: movedMap = mergeLeft(deepCopyBoard(original)); break;
            case 3: movedMap = mergeRight(deepCopyBoard(original)); break;
        }
        return movedMap;
    }

    public static int[][] mergeRight(int[][] board) {
        for (int row = 0; row < N; row++) {
            boolean[] merged = new boolean[N];
            for (int col = N - 1; col > 0; col--) {
                if (board[row][col] == 0) continue;
                for (int k = col - 1; k >= 0; k--) {
                    if (board[row][k] == 0) continue;
                    if (board[row][k] == board[row][col] && !merged[col] && !merged[k]) {
                        board[row][col] *= 2;
                        board[row][k] = 0;
                        merged[col] = true;
                    }
                    break;
                }
            }
        }
        return moveRight(board); // 병합 후 이동
    }

    public static int[][] moveRight(int[][] board) {
        for (int row = 0; row < N; row++) {
            int idx = N - 1;
            int[] newRow = new int[N];
            for (int col = N - 1; col >= 0; col--) {
                if (board[row][col] != 0) {
                    newRow[idx--] = board[row][col];
                }
            }
            board[row] = newRow;
        }
        return board;
    }

    public static int[][] mergeLeft(int[][] board) {
        for (int row = 0; row < N; row++) {
            boolean[] merged = new boolean[N];
            for (int col = 0; col < N - 1; col++) {
                if (board[row][col] == 0) continue;
                for (int k = col + 1; k < N; k++) {
                    if (board[row][k] == 0) continue;
                    if (board[row][k] == board[row][col] && !merged[col] && !merged[k]) {
                        board[row][col] *= 2;
                        board[row][k] = 0;
                        merged[col] = true;
                    }
                    break;
                }
            }
        }
        return moveLeft(board);
    }

    public static int[][] moveLeft(int[][] board) {
        for (int row = 0; row < N; row++) {
            int idx = 0;
            int[] newRow = new int[N];
            for (int col = 0; col < N; col++) {
                if (board[row][col] != 0) {
                    newRow[idx++] = board[row][col];
                }
            }
            board[row] = newRow;
        }
        return board;
    }

    public static int[][] mergeBottom(int[][] board) {
        for (int col = 0; col < N; col++) {
            boolean[] merged = new boolean[N];
            for (int row = N - 1; row > 0; row--) {
                if (board[row][col] == 0) continue;
                for (int k = row - 1; k >= 0; k--) {
                    if (board[k][col] == 0) continue;
                    if (board[k][col] == board[row][col] && !merged[row] && !merged[k]) {
                        board[row][col] *= 2;
                        board[k][col] = 0;
                        merged[row] = true;
                    }
                    break;
                }
            }
        }
        return moveBottom(board);
    }

    public static int[][] moveBottom(int[][] board) {
        for (int col = 0; col < N; col++) {
            int idx = N - 1;
            int[] newCol = new int[N];
            for (int row = N - 1; row >= 0; row--) {
                if (board[row][col] != 0) {
                    newCol[idx--] = board[row][col];
                }
            }
            for (int row = 0; row < N; row++) {
                board[row][col] = newCol[row];
            }
        }
        return board;
    }

    public static int[][] mergeTop(int[][] board) {
        for (int col = 0; col < N; col++) {
            boolean[] merged = new boolean[N];
            for (int row = 0; row < N - 1; row++) {
                if (board[row][col] == 0) continue;
                for (int k = row + 1; k < N; k++) {
                    if (board[k][col] == 0) continue;
                    if (board[k][col] == board[row][col] && !merged[row] && !merged[k]) {
                        board[row][col] *= 2;
                        board[k][col] = 0;
                        merged[row] = true;
                    }
                    break;
                }
            }
        }
        return moveTop(board);
    }

    public static int[][] moveTop(int[][] board) {
        for (int col = 0; col < N; col++) {
            int idx = 0;
            int[] newCol = new int[N];
            for (int row = 0; row < N; row++) {
                if (board[row][col] != 0) {
                    newCol[idx++] = board[row][col];
                }
            }
            for (int row = 0; row < N; row++) {
                board[row][col] = newCol[row];
            }
        }
        return board;
    }

    public static int[][] deepCopyBoard(int[][] original) {
        int[][] copied = new int[N][N];
        for (int i = 0; i < N; i++) {
            copied[i] = original[i].clone();
        }
        return copied;
    }

    public static int getMaxValue(int[][] board) {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, board[i][j]);
            }
        }
        return max;
    }
}
