package BOJ12000_12865; // 12865 - 평범한 배낭

import java.util.Scanner;

public class BOJ12865 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 물건 개수
        int K = sc.nextInt(); // 배낭 용량
        int[] weight = new int[N];
        int[] value = new int[N];

        for (int i = 0; i < N; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

        int[] dp = new int[K + 1]; // 1차원 DP 배열 사용

        for (int i = 0; i < N; i++) {
            for (int w = K; w >= weight[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weight[i]] + value[i]);
            }
        }

        System.out.println(dp[K]); // 배낭의 최대 무게에서 얻을 수 있는 최대 가치 출력
    }
}
