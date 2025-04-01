package typecheck;

public class StudentEx {
    public static void personInfo(Person person) {
        System.out.println(person.name);
        person.walk();

        if(person instanceof Student) {
            Student student = (Student) person;
            System.out.println(student.studentNo);
            student.study();
        }

        if(person instanceof Student student) {
            System.out.println(student.studentNo);
            student.study();
        }
    }
    public static void main(String[] args) {
        Person person1 = new Person("홍길동");
        personInfo(person1);
        System.out.println();

        Person person2 = new Student("홍순이", 30);
        personInfo(person2);
    }
}
