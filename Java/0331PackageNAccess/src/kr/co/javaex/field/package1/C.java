package kr.co.javaex.field.package1;

public class C {
    public void method() {
        A a = new A();
        a.field1 = 2; // public (접근 가능)
        a.field2 = 2; // default (접근 가능)
        //a.field3 = 2; // private (접근 불가)

        a.method1(); // public (접근 가능)
        a.method2(); // default (접근 가능)
        //a.method3(); // private (접근 불가)
    }

}
