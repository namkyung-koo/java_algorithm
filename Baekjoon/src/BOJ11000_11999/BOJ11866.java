package BOJ11000_11999; // 11866 요세푸스 문제0

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BOJ11866 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        List<Integer> list = new LinkedList<>();

        // 1열로 쭉 앉아있다고 생각 1, 2, 3, 4, 5, 6, 7
        for (int i = 1; i <= n; i++)
            list.add(i);

        Iterator<Integer> it = list.iterator();

        int removed;

        System.out.print("<");

        while (!list.isEmpty()) {
            for (int i = 0; i < k - 1; i++) {
                if (!it.hasNext())
                    it = list.iterator();
                it.next();
            }
            if (!it.hasNext())
                it = list.iterator();
            removed = it.next();
            System.out.print(removed);
            it.remove();

            if (!list.isEmpty()) {
                System.out.print(", ");
            }
        }
        System.out.println(">");
    }
}
