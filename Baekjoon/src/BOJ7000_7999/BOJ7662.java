package BOJ7000_7999; // 7662 - 이중 우선순위 큐

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ7662 {

    static int T, k, n;
    static PriorityQueue<Integer> priorityQueueLowest;
    static PriorityQueue<Integer> priorityQueueHighest;
    static PriorityQueue<Integer> pq;
    static Map<Integer, Integer> deleteMap;
    static StringBuilder sb;
    static char op;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            priorityQueueLowest = new PriorityQueue<>();
            priorityQueueHighest = new PriorityQueue<>(Collections.reverseOrder());
            deleteMap = new HashMap<>();
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                op = st.nextToken().charAt(0);
                n = Integer.parseInt(st.nextToken());
                if (op == 'I') {
                    priorityQueueLowest.add(n);
                    priorityQueueHighest.add(n);
                    deleteMap.put(n, deleteMap.getOrDefault(n, 0) + 1);
                } else if (op == 'D') {
                    pq = n == 1 ? priorityQueueHighest : priorityQueueLowest;
                    if (!pq.isEmpty()) {
                        while (!pq.isEmpty() && deleteMap.getOrDefault(pq.peek(), 0) == 0)
                            pq.poll();

                        if (!pq.isEmpty()) {
                            int removed = pq.poll();
                            deleteMap.put(removed, deleteMap.getOrDefault(removed, 0) - 1);
                        }
                    }
                }
            }
            sb = new StringBuilder();
            while (!priorityQueueHighest.isEmpty() && deleteMap.getOrDefault(priorityQueueHighest.peek(), 0) == 0)
                priorityQueueHighest.poll();
            while (!priorityQueueLowest.isEmpty() && deleteMap.getOrDefault(priorityQueueLowest.peek(), 0) == 0)
                priorityQueueLowest.poll();

            if (!priorityQueueHighest.isEmpty() && !priorityQueueLowest.isEmpty()) {
                sb.append(priorityQueueHighest.poll()).append(" ").append(priorityQueueLowest.poll());
            } else {
                sb.append("EMPTY");
            }
            System.out.println(sb.toString());
        }
    }
}
