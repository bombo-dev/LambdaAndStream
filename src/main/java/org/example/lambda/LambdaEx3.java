package org.example.lambda;

@FunctionalInterface
interface MyFunction3 {
    void myMethod();
}

public class LambdaEx3 {
    public static void main(String[] args) {
        MyFunction3 f = () -> {};
        Object obj = (MyFunction3)() -> {};
        String str = ((Object)(MyFunction3)(() -> {})).toString();
        MyFunction3 f3 = new MyFunction3() {
            @Override
            public void myMethod() {
                System.out.println("zz");
            }
        };
        System.out.println(f3);

        System.out.println(f);
        System.out.println(obj);
        System.out.println(str);

//        System.out.println(() -> {});
        System.out.println((MyFunction3)() -> {});
        System.out.println(((Object)(MyFunction3)(() -> {})).toString());
    }
}
