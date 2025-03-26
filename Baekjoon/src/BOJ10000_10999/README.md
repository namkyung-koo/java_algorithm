# BOJ 10000번 ~ 10999번 문제 풀이

## 📌 BOJ 10845 - 큐
- **문제 링크**: [백준 10845번](https://www.acmicpc.net/problem/10845)
- **풀이 요약**: ArrayDeque을 사용하여 해결

## 📌 BOJ 10828 - 스택
- **문제 링크**: [백준 10828번](https://www.acmicpc.net/problem/10828)
- **풀이 요약**: 처음에는 ArrayDeque를 사용하여 코드를 짰지만 시간 제한 0.5를 넘기는 문제가 발생했다.
- **개선 방법**
  1. ArrayDeque 대신 LinkedList 사용. pollLast(), getLast() 연산을 자주 사용하면 LinkedList가 더 적합하다.
  2. split(" ") 대신 substring() 이용. split()은 내부적으로 배열을 생성하는 비용이 크다.
  3. BufferedReader 사용. Scanner는 동기화 처리가 되어 있어 속도가 느리다.

## 📌 BOJ 10816 - 슷자 카드2
- **문제 링크**: [백준 10816번](https://www.acmicpc.net/problem/10816)
- **풀이 요약**: 단순 비교를 통해 몇 장을 가지고 있는지 출력. 시간 제한이 핵심
- **개선 방법**
1. 이중 반복문으로 단순 비교를 수행. 시간 초과 문제 발생
2. HashMap을 사용해서 인덱스로 접근. 시간 초과 문제 발생
3. 입출력 데이터가 많을 때는, BufferedReader와 StringBuilder를 사용하는 것이 좋다.

## 📌 BOJ 10814 - 나이순 정렬
- **문제 링크**: [백준 10814번](https://www.acmicpc.net/problem/10814)
- **풀이 요약**: 키,값을 오름차순으로 저장하는 TreeMap을 사용하되, 키가 같은 경우 값을 들어온 순서대로 저장해야 함. 값 저장에는 LinkedList가 적합하다고 생각.
- **알게된 점**
1. Entry는 Map 내부 인터페이스이며, static으로 설정되어 있어 Map 객체 없이도 호출할 수 있다.
2. map의 putIfAbsent 메서드는 key가 이미 존재하면 아무 동작도 하지 않고 기존 값을 반환한다. key가 없다면 새로운 (key, value)를 저장하고 null을 반환한다.
3. 따라서 기존의 value가 덮어씌어지지 않도록 방지하고 싶을 때 유용하다.

## 📌 BOj 10026 - 적록색약
- **문제 링크**: [백준 10026번](https://www.acmicpc.net/problem/10026)
- **풀이 요약**: 일반인의 기준으로는 들어온 값 그대로 너비 우선 탐색을 했고, 적록색약의 경우에는 초록색을 전부 빨간색으로 변경한 뒤 같은 너비 우선 탐색 함수를 실행했다.
- **개선 방법**: 굳이 입력 데이터의 값을 수정하지 않고 조건문 분기로 하는 방법이 나았을 수도 있다.

## 📌 BOJ10971 - 외판원 순회 2
- **문제 링크**: [백준 10971번](https://www.acmicpc.net/problem/10971)