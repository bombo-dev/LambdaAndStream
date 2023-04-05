package org.example.stream;

import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

class Student2 {
    String name;
    boolean isMale;
    int hak;
    int ban;
    int score;

    Student2(String name, boolean isMale, int hak, int ban, int score) {
        this.name = name;
        this.isMale = isMale;
        this.hak = hak;
        this.ban = ban;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public boolean isMale() {
        return isMale;
    }

    public int getHak() {
        return hak;
    }

    public int getBan() {
        return ban;
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        return String.format("[%s, %s, %d학년 %d반, %3d점", name, isMale ? "남":"여", hak, ban, score);
    }

    enum Level { HIGH, MID, LOW }
}
public class StreamEx7 {
    public static void main(String[] args) {
        Student2[] stuArr = {
                new Student2("나자바", true, 1, 1, 300),
                new Student2("김자바", false, 1, 1, 250),
                new Student2("박자바", true, 1, 1, 200),
                new Student2("하자바", false, 1, 2, 150),
                new Student2("구자바", true, 1, 2, 100),
                new Student2("문자바", false, 1, 2, 50),
                new Student2("이자바", false, 1, 2, 100),
                new Student2("감자바", false, 1, 3, 150),
                new Student2("고자바", true, 1, 3, 200),
                new Student2("유자바", true, 1, 3, 250),

                new Student2("나자바", true, 2, 1, 300),
                new Student2("김자바", false, 2, 1, 250),
                new Student2("박자바", true, 2, 1, 200),
                new Student2("하자바", false, 2, 2, 150),
                new Student2("구자바", true, 2, 2, 100),
                new Student2("문자바", false, 2, 2, 50),
                new Student2("이자바", false, 2, 2, 100),
                new Student2("감자바", false, 2, 3, 150),
                new Student2("고자바", true, 2, 3, 200),
                new Student2("유자바", true, 2, 3, 250)
        };

        System.out.println("1. 단순분할(성별로 분할)");
        Map<Boolean, List<Student2>> studentByGender = Stream.of(stuArr)
                .collect(partitioningBy(Student2::isMale));

        List<Student2> maleStudent = studentByGender.get(true);
        List<Student2> femaleStudent = studentByGender.get(false);

        System.out.println("====== 남학생 분류 ======");
        for(Student2 s : maleStudent) {
            System.out.println(s);
        }

        System.out.println("====== 여학생 분류 ======");
        for(Student2 s : femaleStudent) {
            System.out.println(s);
        }
        System.out.println("2. 단순분할 + 통계(성별 학생수)");
        Map<Boolean, Long> studentCountByGender = Stream.of(stuArr)
                .collect(partitioningBy(Student2::isMale, counting()));

        System.out.println("남학생 수 : " + studentCountByGender.get(true));
        System.out.println("여학생 수 : " + studentCountByGender.get(false));

        // 분할 시, Optional 출력
        System.out.println("3. 단순분할 + 통계(성별 1등)");
        Map<Boolean, Optional<Student2>> topScoreByGender = Stream.of(stuArr)
                .collect(partitioningBy(Student2::isMale, maxBy(Comparator.comparing(Student2::getScore))));

        System.out.println("남학생 1등 : " + topScoreByGender.get(true));
        System.out.println("여학생 1등 : " + topScoreByGender.get(false));

        System.out.println("3. 단순분할 + 통계(성별 1등) Optional 제거");
        // 분할 시, Optional 제거
        Map<Boolean, Student2> topScoreByGender2 = Stream.of(stuArr)
                .collect(partitioningBy(Student2::isMale, collectingAndThen(maxBy(Comparator.comparingInt(Student2::getScore)), Optional::get)));

        System.out.println("남학생 1등 :" + topScoreByGender2.get(true));
        System.out.println("여학생 1등 :" + topScoreByGender2.get(false));

        System.out.println("4. 다중분할(성별 불합격자, 100점 이하)");
        Map<Boolean, Map<Boolean, List<Student2>>> failedStuByGender = Stream.of(stuArr)
                .collect(partitioningBy(Student2::isMale, partitioningBy(s -> s.getScore() <= 100)));

        List<Student2> failedMaleStu = failedStuByGender.get(true).get(true);
        List<Student2> failedFemaleStu = failedStuByGender.get(false).get(true);

        for(Student2 s : failedMaleStu) System.out.println(s);
        for(Student2 s : failedFemaleStu) System.out.println(s);
    }
}
