package trycatch;

public class ExceptionThrowsExam {
    public static void main(String[] args) {
        Person person = new Person();
        try {
            person.setAge(-30);
        } catch (InsufficientException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        try {
            findClass();
        } catch (ClassNotFoundException e) {
            System.out.println("예외 - " + e.getMessage());
        }
    }

    public static void findClass() throws ClassNotFoundException {
        Class.forName("java.lang.String2");
    }
}
