package BOJ9000_9999; // 9328 - 열쇠

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ9328 {

    static int t, h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            StringTokenizer stz = new StringTokenizer(br.readLine());
            h = Integer.parseInt(stz.nextToken());
            w = Integer.parseInt(stz.nextToken());
            char[][] map = new char[101][101];

            char[] s;
            for (int row = 0; row < h; row++) {
                s = br.readLine().toCharArray();
                char c;

                for (int col = 0; col < w; col++) {
                    c = s[col];
                    map[row][col] = c;
                }
            }

            boolean[] keys = new boolean[26];
            s = br.readLine().toCharArray();
            if (s[0] != '0') {
                for (int i = 0; i < s.length; i++) {
                    keys[s[i] - 'a'] = true;
                }
            }
            Deque<int[]> deque = new ArrayDeque<>();
            boolean[][] visited = new boolean[h][w];
            int docs = checkBorder(map, keys, deque, visited);
            System.out.println(bfs(map, keys, deque, visited, docs));
        }
    }

    public static int bfs(char[][] map, boolean[] keys, Deque<int[]> deque, boolean[][] visited, int docs) {

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        boolean changed = true;

        while (changed) {
            changed = false;

            while (!deque.isEmpty()) {
                int[] cur = deque.poll();
                int y = cur[0];
                int x = cur[1];
                visited[y][x] = true;

                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if (ny >= 0 && ny < h && nx >= 0 && nx < w && !visited[ny][nx]) {
                        char c = map[ny][nx];

                        if (c == '*') continue;

                        if (c == '.') {
                            visited[ny][nx] = true;
                            deque.add(new int[]{ny, nx});
                        }
                        else if (c == '$') {
                            docs++;
                            map[ny][nx] = '.';
                            visited[ny][nx] = true;
                            deque.add(new int[]{ny, nx});
                        }
                        else if (Character.isUpperCase(c)) {
                            if (keys[c - 'A']) {
                                map[ny][nx] = '.';
                                visited[ny][nx] = true;
                                deque.add(new int[]{ny, nx});
                            }
                        }
                        else if (Character.isLowerCase(c)) {
                            if (!keys[c - 'a']) {
                                keys[c - 'a'] = true;
                                map[ny][nx] = '.';
                                changed = true;
                            }
                            visited[ny][nx] = true;
                            deque.add(new int[]{ny, nx});
                        }
                    }
                }
            }
            if (changed) updateDequeAndVisited(map, keys, deque, visited);
        }
        return  docs;
    }

    public static void updateDequeAndVisited(char[][] map, boolean[] keys, Deque<int[]> deque, boolean[][] visited) {
        deque.clear();
        for (int i = 0; i < h; i++) {
            Arrays.fill(visited[i], false);
        }

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (row == 0 || row == h - 1 || col == 0 || col == w - 1) {
                    if (map[row][col] == '*' || visited[row][col])
                        continue;

                    char c = map[row][col];
                    if (c == '.' || c == '$' || (Character.isUpperCase(c) && keys[c - 'A']) || Character.isLowerCase(c)) {
                        deque.add(new int[]{row, col});
                        visited[row][col] = true;
                    }
                }
            }
        }
    }

    public static int checkBorder(char[][] map, boolean[] keys, Deque<int[]> deque, boolean[][] visited) {
        int docs = 0;
        char c;
        boolean changed = true;

        while (changed) {
            changed = false;
            deque.clear();

            for (int row = 0; row < h; row++) {
                for (int col = 0; col < w; col++) {
                    if (row == 0 || row == h - 1 || col == 0 || col == w - 1) {
                        c = map[row][col];

                        if (c == '*')
                            continue;

                        if (c == '$') {
                            docs++;
                            map[row][col] = '.';
                            changed = true;
                        } else if (Character.isLowerCase(c)) {
                            keys[c - 'a'] = true;
                            map[row][col] = '.';
                            changed = true;
                        } else if (Character.isUpperCase(c)) {
                            if (!keys[c - 'A'])
                                continue;
                            map[row][col] = '.';
                            changed = true;
                        }
                        deque.add(new int[]{row, col});
                        visited[row][col] = true;
                    }
                    if (changed)
                        break;
                }
                if (changed)
                    break;
            }
        }
        return docs;
    }
}
