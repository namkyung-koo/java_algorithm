package BOJ2000_2999; // 2467 - 용액

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2467 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer stz = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stz.nextToken());
        }

        int left = 0;
        int right = N - 1;

        int liquid = Math.abs(arr[left] + arr[right]);
        int resLeft = arr[left];
        int resRight = arr[right];

        while (left < right) {
            int tmp = arr[left] + arr[right];
            if (Math.abs(tmp) < liquid) {
                liquid = Math.abs(tmp);
                resLeft = arr[left];
                resRight = arr[right];
            }
            if (tmp < 0)
                left++;
            else
                right--;
        }
        System.out.println(resLeft + " " + resRight);
    }
}
