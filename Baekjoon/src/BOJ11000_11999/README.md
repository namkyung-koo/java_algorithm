# BOJ 11000번 ~ 11999번 문제 풀이

## 📌 BOJ 11866 - 요세푸스 문제0
- **문제 링크**: [백준 11866번](https://www.acmicpc.net/problem/11866)
- **풀이 요약**: LinkedList와 Iterator를 사용. iterator의 hasNext가 false이면, 마지막 요소를 가리키고 있으므로 list.iterator()를 호출해 다시 처음을 가리키도록 했다. 이후에 제거될 원소를 출력하고 remove 호출로 제거.

## 📌 BOJ 11650 - 좌표 정렬하기 
- **문제 링크**: [백준 11650번](https://www.acmicpc.net/problem/11650)
- **풀이 요약**: 자동으로 오름차순으로 정렬해주는 TreeMap과 TreeSet 자료 구조를 활용.
- **배운 점**: 출력을 하기 위해 Map과 Set 변수의 key, value 값을 호출하는 메서드에 익숙해질 것.

## 📌 BOJ 11286 - 절댓값 힙
- **문제 링크**: [백준 11286번](https://www.acmicpc.net/problem/11286)
- **풀이 요약**: 기존에 구현된 내림차순 우선순위 큐를 활용. {절대값, 원본} 데이터를 쌍으로 저장.
  - 우선 절대값을 기준으로 내림차순으로 정렬한다. 이 때 절대값이 같다면 원본 값의 비교를 수행한다.
  - compare 메서드를 오버라이딩 하는 것이 핵심

## 📌 BOJ 11404 - 플로이드
- **문제 링크**: [백준 11404번](https://www.acmicpc.net/problem/11404)
- **풀이 요약**: 1238번 - 파티와 동일하게 다익스트라 함수를 따로 만든 뒤 모든 정점에 대해서 최단 경로를 구했다.

## 📌 BOJ 11657 - 타임머신
- **문제 링크**: [백준 11657번](https://www.acmicpc.net/problem/11657)
- **풀이 요약**: 음의 간선이 존재할 수 있음으로 벨만-포드 알고리즘을 적용
- **헤맸던 점**
  1. distance[u]가 INF 일 때를 체크하지 않아서 INF - w 로 값이 변질되는 문제가 발생했음.
  2. 초기에 distance의 자료형을 int[]로 설정. 
     1. 하지만 입력 예제 범위에 따르면 최악의 경우 N = 500, M = 6000, C = -10000이라 가정할 때 500 * -10,000 = -4,990,000
     2. -4,990,000 * 500 = - 대략 -25억의 값이 들어갈 수 있음으로 언더플로우가 충분히 발생할 수 있음.

## 📌 BOJ  -
- **문제 링크**:
- **풀이 요약**: 

