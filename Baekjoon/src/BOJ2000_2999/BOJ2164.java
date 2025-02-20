package BOJ2000_2999;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ2164 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Deque<Integer> deque = new ArrayDeque<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++)
            deque.addLast(i);

        int now = 0;

        while (deque.size() > 1) {
            if (deque.size() > 1)
                deque.pollFirst();
            now = deque.pollFirst();
            deque.addLast(now);
        }
        System.out.println(deque.getFirst());
    }
}

