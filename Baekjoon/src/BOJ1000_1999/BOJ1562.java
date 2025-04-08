package BOJ1000_1999; // 1562 - 계단 수

import java.util.Scanner;

public class BOJ1562 {

    static int N;
    static final int MOD = 1000000000;
    static int[][][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());

        /**
         * i : i 자리 숫자
         * j : 끝나는 숫자
         *      ex) j = 9 이라면, 10 0000 0000
         *          j = 7 이라면, 00 1000 0000
         *
         * k : 마크 된 숫자 (2진수를 10진수로 변환한 숫자)
         *      ex) i = 2, j = 3, k = 28(00 0001 1100) [2, 3, 4]
         *      두 자리 숫자 중, 4로 끝나면서 2 또는 3 또는 4가 포함된 계단의 수는 ?
         *      답: 24, 34 (dp[2][4][20] + dp[2][4][24] = 2개)
         */
        dp = new int[N + 1][11][1 << 10]; // dp = new int[i][j][k]
        for (int i = 1; i < 10; i++)
            dp[1][i][1 << i] = 1;

        dfs();

        int sum = 0;
        for (int j = 0; j < 10; j++) {
            sum += dp[N][j][1023] % MOD;
            sum %= MOD;
        }

        System.out.println(sum);
    }

    public static void dfs() {

        // 자리 수 1부터 N까지
        for (int i = 2; i < N + 1; i++) {
            // 이동 가능한 숫자 1부터 9까지
            for (int j = 0; j < 10; j++) {
                //가능한 방문 집합 비트 0부터 1023까지
                for (int k = 0; k < 1024; k++) {
                    int bit = k | (1 << j);
                    if (j == 0)
                        dp[i][j][bit] +=  dp[i - 1][j + 1][k] % MOD;
                    else if (j == 9)
                        dp[i][j][bit] += dp[i - 1][j - 1][k] % MOD;
                    else
                        dp[i][j][bit] += (dp[i - 1][j + 1][k] % MOD + dp[i - 1][j - 1][k] % MOD);
                }
            }
        }
    }
}