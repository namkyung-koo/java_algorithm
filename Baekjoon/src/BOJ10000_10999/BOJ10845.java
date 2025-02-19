package BOJ10000_10999; // 10845 큐

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BOJ10845 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        Deque<Integer> deque = new ArrayDeque<>();

        String input = "";

        String[] s;

        for (int i = 0; i < n; i++) {

            input = sc.nextLine();
            if (input.startsWith("push")) {
                s = input.split(" ");
                deque.addLast(Integer.parseInt(s[1]));
                continue;
            }

            if (input.equals("empty")) {
                System.out.println(deque.isEmpty() ? 1 : 0);
                continue;
            } else if (input.equals("size")) {
                System.out.println(deque.size());
                continue;
            }

            if (deque.isEmpty()) {
                System.out.println(-1);
            } else {
                if (input.equals("pop")) {
                    System.out.println(deque.pop());
                } else if (input.equals("front")) {
                    System.out.println(deque.getFirst());
                } else if (input.equals("back")) {
                    System.out.println(deque.getLast());
                }
            }

        }
    }
}
