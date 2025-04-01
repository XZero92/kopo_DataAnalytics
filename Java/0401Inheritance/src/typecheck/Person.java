package typecheck;

public class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    public void walk() {
        System.out.println(name + "이(가) 걷습니다.");
    }
}
