package BOJ11000_11999; // 11650 좌표 정렬하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class BOJ11650 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 자바 TreeMap 사용
        Map<Integer, TreeSet<Integer>> map = new TreeMap<>();

        StringTokenizer st;

        int a, b;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            map.putIfAbsent(a, new TreeSet<>());
            map.get(a).add(b);
        }

        for (Map.Entry<Integer, TreeSet<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();
            for (int value : entry.getValue()) {
                System.out.println(key + " " + value);
            }
        }
    }
}
