package org.example.lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LambdaEx6 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            list.add(i);
        }

        // Consumer 매개변수 있고, 반환 값 없음 accept
        // 반대되는 Supplier 는 매개변수는 없고, 반환 값만 있음 get
        // 공급자 vs 소비자
        list.forEach(i -> System.out.print(i + ","));
        System.out.println();

        // Predicate 조건 람다, test
        list.removeIf(x -> {
            System.out.print(x + " 삭제 여부 : ");
            System.out.println(x % 2 == 0 || x % 3 == 0);
            return x % 2 == 0 || x % 3 == 0;
        });

        System.out.println(list);

        // Function 의 일종이다. 메서드 apply
        // UnaryOperator 매개변수와 반환값이 일치한다.
        // BinaryOperator 매개변수 2개와 반환값이 일치한다.
        list.replaceAll(i -> i * 10);
        System.out.println(list);

        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");

        // BiConsumer 매개변수 2개 반환값 없음
        map.forEach((k, v) -> System.out.println("{" + k + "," + v + "}"));
        System.out.println();
    }
}
