package org.example.lambda;

@FunctionalInterface
interface MyFunction2 {
    void run();
}

public class LambdaEx2 {
    static void execute(MyFunction2 f) {
        f.run();
    }

    static MyFunction2 getMyFunction2() {
        return () -> System.out.println("f3.run()");
    }

    public static void main(String[] args) {
        MyFunction2 f1 = () -> System.out.println("f1.run()");

        MyFunction2 f2 = new MyFunction2() {
            public void run() {
                System.out.println("f2.run()");
            }
        };

        MyFunction2 f3 = getMyFunction2();

        f1.run();
        f2.run();
        f3.run();

        execute(f1);
        execute(getMyFunction2());

        execute(() -> System.out.println("f4.run()"));
    }
}
