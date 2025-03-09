package BOJ9000_9999; // 9663 - N-Queen

import java.util.Scanner;

public class BOJ9663 {

    static int N;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());

        int[] board = new int[N];
        backtrack(0, board);
        System.out.println(count);
    }

    private static void backtrack(int row, int[] board) {

        if (row == N) {
            count++;
            return;
        }

        for (int col = 0; col < N; col++) {
            board[row] = col;
            if (is_valid(row, board))
                backtrack(row + 1, board);
        }
    }

    private static boolean is_valid(int row, int[] board) {

        for (int i = 0; i < row; i++) {
            if (board[i] == board[row])
                return false;
            if (Math.abs(board[row] - board[i]) == Math.abs(row - i))
                return false;
        }
        return true;
    }
}
