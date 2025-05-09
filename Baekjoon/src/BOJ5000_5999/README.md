# BOJ 7000번 ~ 7999번 문제 풀이

## 📌 BOJ 5430 - AC
- **문제 링크**: [백준 5430번](https://www.acmicpc.net/problem/5430)
- **풀이 요약**: deque를 사용해 R이 등장할 때마다 플래그를 on/off 하여 pollFirst()나 pollLast()를 호출한다.
- **예외 처리**: substring을 하는 부분에서 빈 배열이 들어올 때 인덱스를 넝머 접근하는 경우가 있어 런타임에러가 발생했다.
  - 이 경우에는 들어온 token의 길이를 재 조건문으로 분기한 뒤 빈 배열이 들어왔을 때는 StringTokenizer에 빈 문자열만 전달한다.

## 📌 BOJ 5972 - 택배 배송
- **문제 링크**: [백준 5972번](https://www.acmicpc.net/problem/5972)
- **풀이 요약**: 기본 다익스트라 알고리즘 + 우선순위 큐 구조를 따름
- **생각하지 못한 반레**
  - 처음에 소의 범위가 0 <= 소 <= 1000으로 주어져, 소에게 줄 최소 여물을 저장하는 정수형 배열을 시작점을 제외하고는 모두 10000으로 초기화했다.
  - 정점과 간선의 개수가 100과 99로 각각 주어지고 가중치가 1000이라 가정했을 때, 최악의 경우 최단 거리는 99000이 될 수 있기에, 10000으로 초기화를 하면 갱신이 제대로 안될 수 있다.
  - 따라서 안전한 INF 값으로 1000000000을 사용하는 것이 좋다.

## 📌 BOJ  -
- **문제 링크**:
- **풀이 요약**: 