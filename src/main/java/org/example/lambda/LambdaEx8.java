package org.example.lambda;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaEx8 {
    static int[][] graph;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N;
    static boolean[][] visited;
    public static void main(String[] args) {

        // Function 인터페이스의 default 메서드 결합 T -> R -> V 따라서 결과는 T -> V 와 같다.
        // A.andThen(B) 는 A -> B, A.compose(B) 는 B -> A
        // static identity() 는 항등함수를 나타내는데, map()으로 변환작업 할 때 사용. 그 외에는 사용 X

        // 문자열을 숫자로 변환하는 function1, 숫자를 2진 문자열로 변환하는 함수 g
        Function<String, Integer> f = (s) -> Integer.parseInt(s);
        Function<Integer, String> g = (i) -> Integer.toBinaryString(i);

        Function<String, String> h = f.andThen(g);

        System.out.println(h.apply("10"));

        // Predicate 의 결합
        // default and, or, negate(!), static isEqual
        // Predicate 의 결합을 사용해서 bfs 구현해보기

        // (0, 0) 에서, (N - 1, N - 1) 까지의 최단거리
        // -1 은 물, 0은 육지
        // 0, -1, 0, 0
        // 0, 0, 0, 0
        // -1, -1, 0, -1
        // 0, 0, 0, 0

        N = 4;
        graph = new int[N][N];
        graph[0] = new int[]{0, -1, 0 ,0};
        graph[1] = new int[]{0, 0, 0, 0};
        graph[2] = new int[]{-1, -1, 0, -1};
        graph[3] = new int[]{0, 0, 0, 0};
        visited = new boolean[N][N];

        bfs();
        System.out.println(graph[N - 1][N - 1]);
    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});


        // 두 개를 한 번에 람다로 구현할 순 없을까?
        BiPredicate<Integer, Integer> init = (x, y) -> x >= 0 && y >= 0;
        BiPredicate<Integer, Integer> condition = init.and((x, y) -> x < N && y < N)
                .and((x, y) -> !visited[x][y])
                .and((x, y) -> graph[x][y] != -1);



        while(!q.isEmpty()) {
            int[] position = q.poll();
            int px = position[0];
            int py = position[1];
            visited[px][py] = true;

            for(int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if(condition.test(nx, ny)) {
                    q.offer(new int[]{nx, ny});
                    graph[nx][ny] = graph[px][py] + 1;
                }
            }
        }
    }
}
