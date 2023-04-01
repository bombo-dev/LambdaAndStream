package org.example.lambda;


public class LambdaEx4 {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.method(100);
        // 내부 클래스 한 번 다시 확인
    }
}

class Outer {
    int val = 10;


    class Inner {
        int val = 20;

        void method(int i) {
            int val = 30;


            MyFunction3 f = () -> {
                System.out.println("i : " + i); // final 만 가능 지역 변수를 수정하지 않으면 final 로 간주
                System.out.println("val : " + val);
                System.out.println("this.val : " + ++this.val);
                System.out.println("outer.val : " + ++Outer.this.val);
            };

            f.myMethod();
        }
    }
}
