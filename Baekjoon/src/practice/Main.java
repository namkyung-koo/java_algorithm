package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, N);
        dfs(size, r, c);
    }

    static void dfs(int size, int r, int c) {

        if (size == 1) {
            System.out.println(count);
            return;
        }

        int half = size / 2;

        if (r < half && c < half) {
            dfs(half, r, c);
        } else if (r < half && c >= half) {
            count += half * half;
            dfs(half, r, c - half);
        } else if (r >= half && c < half) {
            count += 2 * half * half;
            dfs(half, r - half, c);
        } else {
            count += 3 * half * half;
            dfs(half, r - half, c - half);
        }
    }
}