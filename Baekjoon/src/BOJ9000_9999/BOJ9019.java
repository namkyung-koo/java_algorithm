package BOJ9000_9999; // 9019 - DSLR

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ9019 {

    static int T;
    static int A, B;
    static Deque<Integer> deque;
    static Map<Integer, String> map;
    static char[] op = new char[]{'D', 'S', 'L', 'R'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            deque = new ArrayDeque<>();
            map = new HashMap<>();
            deque.addLast(A);
            map.put(A, "");

            while (!deque.isEmpty()) {
                int cur = deque.pollFirst();

                for (char nextOp : op) {
                    int next;

                    if (nextOp == 'D')
                        next = (cur * 2) % 10000;
                    else if (nextOp == 'S')
                        next = cur == 0 ? 9999 : cur - 1;
                    else if (nextOp == 'L')
                        next = operateL(cur);
                    else
                        next = operateR(cur);
                    if (!map.containsKey(next)) {
                        map.put(next, map.get(cur) + nextOp);
                        deque.addLast(next);
                    }
                    if (next == B) {
                        System.out.println(map.get(next));
                        deque.clear();
                        break;
                    }
                }
            }
        }
    }

    static int operateL(int cur) {
        int d1, d2, d3, d4;

        d4 = cur % 10;
        d3 = (cur % 100 - d4) / 10;
        d2 = (cur % 1000 - d4 - d3) / 100;
        d1 = (cur % 10000 - d4 - d3 - d2) / 1000;
        return ((d2 * 10 + d3) * 10 + d4) * 10 + d1;
    }

    static int operateR(int cur) {
        int d1, d2, d3, d4;

        d4 = cur % 10;
        d3 = (cur % 100 - d4) / 10;
        d2 = (cur % 1000 - d4 - d3) / 100;
        d1 = (cur % 10000 - d4 - d3 - d2) / 1000;
        return ((d4 * 10 + d1) * 10 + d2) * 10 + d3;
    }
}