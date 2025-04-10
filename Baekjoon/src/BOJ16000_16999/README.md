# BOJ 16000번 ~ 16999번 문제 풀이

## 📌 BOJ 16928 - 뱀과 사다리 게임
- **문제 링크**: [백준 16928번](https://www.acmicpc.net/problem/16928)
- **풀이 요약**: bfs를 활용. 101칸의 보드판을 만든 뒤, 해당 인덱스와 맞춘 숫자를 대입.
  - 사다리와 뱀의 경우 해당 보드판의 인덱스에 이동 후 위치를 저장.
  - bfs 함수 호출은 단 한 번. 시작 위치와 이동 횟수를 정수형 배열로 deque에 넣는다.
  - 현재 위치에서 1 ~ 6칸 이동한 후, 사다리나 뱀이 있는 경우 이동된 위치로 갱신
  - 이동된 칸이 처음이라면 deque에 추가
-  **아쉬웠던 점**: bfs보다 dfs를 이용하여 해당 count 값을 최소값과 비교하면서 치환하는 식으로 해결하려 했다.
  - 이 후 막혀서 결국 해답지를 봤다.
  - 최단 거리 탐색에는 bfs가 효율적이라는 사실을 알게됐다!

## 📌 BOJ 16236 - 아기 상어
- **문제 링크**: [백준 16236번](https://www.acmicpc.net/problem/16236)
- **풀이 요약**: 조건식에 대한 코드를 잘 작성하지 못해 결국 지피티에게 풀이 방법을 도움 받았다.
- **헤맸던 점**
  - BFS 탐색을 수행할 때마다 새로 visited 배열을 초기화 했어야 했다.
  - 문제의 조건인 가장 가까운 물고기(최단 거리) - 위쪽 - 왼쪽부터 우선 순위를 주어 탐색해야 했다.
    - 단순히 dy, dx 배열을 상좌우하로 설정하면 해결될 줄 알았다. 그러나 우선 순위 큐를 사용해야만 했다.
    - 2, 3순위 조건을 정하는 thenComparingInt() 메서드를 처음 사용해봤다. 이 때 1순위 조건을 설정할 때는 객체의 타입을 적시해줘야 하는 것 같다.(예시. (int[] a) -> a[2])
  - fish == size 조건에서 eat++을 수행하는 것이 아닌, fish < size 조건에서만 eat++을 수행하도록 수정해야 했다.
  - 마지막으로 아기 상어가 이동한 후 map[ny][nx] = 0 처리해줘야 했다.
- **느낀 점**: 문제 이해부터 논리의 흐름대로 코드를 구현(특히 조건문 작성) 하는 데 많이 부족하다고 느꼈다..


## 📌 BOJ 16991 - 외판원 순회 3
- **문제 링크**: [백준 16991번](https://www.acmicpc.net/problem/16991)
- **풀이 요약**: 외판원 순회 문제. 각 도시 간 간선의 비용이 유클리드 거리(sqrt((x1 - x2)^2 + (y1 - y2)^2)로 변경됨
  - 모든 도시를 연결한 완전 그래프이므로, 간선을 양방향으로 추가할 필요가 없다.