package java_17_1_ex1_robotexam;

public class Main {
    public static void main(String[] args) {
        Toy toy = null;
        /*
        // 비행기
        toy = new AirplaneImpl();
        toy.introduce();
        // 마징가
        toy = new MazingerImpl();
        toy.introduce();
        // 곰돌이
        toy = new PoohImpl();
        toy.introduce();
        */

        AirplaneImpl airplane = new AirplaneImpl();
        MazingerImpl mazinger = new MazingerImpl();
        PoohImpl pooh = new PoohImpl();

        Person child = new Person();
        child.playToy(airplane);
        child.playToy(mazinger);
        child.playToy(pooh);
    }
}

class Person {
    void playToy(Toy toy) {
        toy.introduce();
    }
}