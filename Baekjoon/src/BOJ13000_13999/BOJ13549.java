package BOJ13000_13999; // 13549 - 숨바꼭질 3

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BOJ13549 {

    static int N, K;
    static Deque<int[]> deque = new ArrayDeque<>();
    static boolean[] visited = new boolean[100001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        deque.addLast(new int[]{N, 0});

        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int[] cur = deque.pollFirst();
                int now = cur[0];
                int cnt = cur[1];
                visited[now] = true;

                if (now == K) {
                    System.out.println(cnt);
                    break;
                }
                for (int j = 0; j < 3; j++) {
                    int next = 0;
                    if (j == 0) {
                        next = now * 2;
                        if (next <= 100000 && !visited[next])
                            deque.addLast(new int[]{next, cnt});
                    } else if (j == 1) {
                        next = now - 1;
                        if (next >= 0 && !visited[next])
                            deque.addLast(new int[]{next, cnt + 1});
                    } else {
                        next = now + 1;
                        if (next <= 100000 && !visited[next]) {
                            deque.addLast(new int[]{next, cnt + 1});
                        }
                    }
                    if (!deque.isEmpty() && deque.getLast()[0] == K) {
                        System.out.println(deque.getLast()[1]);
                        return;
                    }
                }
            }
        }
    }
}
