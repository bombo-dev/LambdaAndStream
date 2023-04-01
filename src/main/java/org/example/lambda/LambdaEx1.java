package org.example.lambda;

class TestEx {
    static int min(int z, int x, int y, MyFunction f) {
        return Math.min(z, f.max(x, y));
    }
}

public class LambdaEx1 {
    public static void main(String[] args) {
        MyFunction f = new MyFunction() {
            @Override
            public int max(int a, int b) {
                return Math.max(a, b);
            }
        };

        // 참조 변수에 담는다면 ; 생략 불가
        MyFunction f2 = (a, b) -> Math.max(a, b);

        System.out.println(f2.max(10, 11));

        int result = TestEx.min(2, 5, 7, (x, y) -> Math.max(x, y));
        System.out.println(result);

    }

}

@FunctionalInterface
interface MyFunction {

    int max(int a, int b);
}

@FunctionalInterface
interface Test {
    int min(int a, MyFunction f);
}
