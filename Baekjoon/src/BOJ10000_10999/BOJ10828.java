package BOJ10000_10999; // 10828 스택

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class BOJ10828 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Deque<Integer> stack = new LinkedList<>();

        int n = Integer.parseInt(sc.nextLine());
        String s = "";

        for (int i = 0; i < n; i++) {

            s = sc.nextLine();

            if (s.startsWith("push"))
                stack.addLast(Integer.parseInt(s.substring(5)));
            else if (s.equals("pop"))
                System.out.println(stack.isEmpty() ? -1 : stack.removeLast());
            else if (s.equals("top"))
                System.out.println(stack.isEmpty() ? -1 : stack.getLast());
            else if (s.equals("size"))
                System.out.println(stack.size());
            else if (s.equals("empty"))
                System.out.println(stack.isEmpty() ? 1 : 0);
        }
    }
}
