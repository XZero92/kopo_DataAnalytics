package List;

import java.util.ArrayList;

public class Person {
    private String name;

    Person(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<Person>();

        people.add(new Person("홍길동"));
        people.add(new Person("만득이"));

        for (Person p: people) {
            System.out.println(p.getName());
        }
    }
}
