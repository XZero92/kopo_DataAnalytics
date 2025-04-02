package java_16_2_ex2_interface;

public class Human {
    public static void main(String[] args) {
        // RemoteControl rc = new RemoteControl(); // 인터페이스는 객체 생성 불가
        RemoteControl rc; // 인터페이스는 참조변수로 사용 가능
        Searchable scbl;

        rc = new Television();
        rc.turnOn();
        rc.turnOff();
        rc.setVolume(5);

        rc = new Audio();
        rc.turnOn();
        rc.turnOff();
        rc.setVolume(7);

        rc = new SmartTelevision();
        rc.turnOn();
        rc.turnOff();
        rc.setVolume(3);

        scbl = new SmartTelevision();
        scbl.search("www.naver.com");
    }
}
