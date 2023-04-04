package org.example.stream;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx5 {
    public static void main(String[] args) {
        String[] strArr = {"Inheritance", "Java", "Lambda", "stream", "OptionalDouble",
                "IntStream", "count", "sum"};

        Stream.of(strArr).forEach(System.out::println);

        boolean noEmptyStr = Stream.of(strArr).noneMatch(s -> s.length() == 0);
        Optional<String> sWord = Stream.of(strArr).filter(s -> s.charAt(0) == 's').findFirst();

        System.out.println("noEmptyStr : " + noEmptyStr);
        System.out.println("sWord : " + sWord.get());

        IntStream intStream1 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream2 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream3 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream4 = Stream.of(strArr).mapToInt(String::length);

        //count, sum, max, min
        // 첫 연산시에는 identity 와 연산한다.
        int count = intStream1.reduce(0, (a, b) -> a + 1);
        int sum = intStream2.reduce(0, (a, b) -> a + b);
        // identity 없이 계산을 하게 되면 OptionalInt 를 반환한다.
        int max = intStream3.reduce(Integer.MIN_VALUE, (a, b) -> a < b ? b : a);
        int min = intStream4.reduce(Integer.MAX_VALUE, (a, b) -> a > b ? b : a);

        System.out.println("count = " + count);
        System.out.println("sum = " + sum);
        System.out.println("max = " + max);
        System.out.println("min = " + min);


    }
}
