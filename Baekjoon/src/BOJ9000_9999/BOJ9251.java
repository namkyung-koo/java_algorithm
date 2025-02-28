package BOJ9000_9999; // 9251 - LCS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9251 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s1 = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String s2 = st.nextToken();

        int l1 = s1.length();
        int l2 = s2.length();

        int[][] dp = new int[l1 + 1][l2 + 1];

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[l1][l2]);
    }
}

/*
ABCD
AXCD

dp[1][1] = 1;
dp[1][2] = Math.max(dp[0][2], dp[1][1]);
dp[1][2] = Math.max(0, 1); => 1
dp[1][2] = 1;
dp[1][3] = 1;
dp[1][4] = 1;

dp[2][1] = max(dp[1][1], dp[2][0]) => 1
dp[2][2] = max(dp[1][2], dp[2][1]) => 1
dp[2][3] = max(dp[1][3], dp[2][2]) => 1
dp[2][4] = max(dp[1][4], dp[2][3]) => 1

dp[3][1] = max(dp[2][1], dp[3][0]) => 1
dp[3][2] = max(dp[2][2], dp[3][1]) => 1
dp[3][3] = dp[2][2] + 1 => 2
dp[3][4] = max(dp[2][4], dp[3][3]) => 2

dp[4][1] = max(dp[3][1], dp[4][0]) => 1
dp[4][2] = max(dp[3][2], dp[4][1]) => 1
dp[4][3] = max(dp[3][3], dp[4][2]) => 2
dp[4][4] = dp[3][3] + 1 => 3

dp[len1][len2] == 3;
*/