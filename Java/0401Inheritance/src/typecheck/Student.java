package typecheck;

public class Student extends Person{
    public int studentNo;

    public Student(String name, int studentNo) {
        super(name);
        this.studentNo = studentNo;
    }

    public void study() {
        System.out.println(name + "이(가) 공부합니다.");
    }
}
