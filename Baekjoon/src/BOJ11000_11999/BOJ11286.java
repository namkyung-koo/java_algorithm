package BOJ11000_11999; // 11286 - 절댓값 힙

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11286 {

    static int N;
    static PriorityQueue<int []> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x;

        N = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int ret = o1[0] - o2[0];
                if (ret == 0) {
                    return o1[1] - o2[1];
                }
                return ret;
            }
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            if (x == 0) {
                if (!pq.isEmpty()) {
                    System.out.println(pq.poll()[1]);
                } else {
                    System.out.println(0);
                }
            } else {
                pq.add(new int[]{Math.abs(x), x});
            }
        }
    }
}
