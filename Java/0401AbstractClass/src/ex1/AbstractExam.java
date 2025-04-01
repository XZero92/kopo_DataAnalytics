package ex1;

public class AbstractExam {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.sound(); // [멍멍]

        Animal cat = new Cat();
        cat.sound(); // [야옹]
    }
}
