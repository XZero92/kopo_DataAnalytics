package typeconvert;

public class ChildExam {
    public static void main(String[] args) {
        Parent parent = new Child();
        parent.field1 = "ㅎㄱㄷ";
        parent.method1();
        parent.method2();
        //parent.field2 = "ㅎㅅㅇ";
        //parent.method3();

        Child child = (Child) parent;
        child.field2 = "ㅎㅅㅇ";
        child.method3();
    }
}
