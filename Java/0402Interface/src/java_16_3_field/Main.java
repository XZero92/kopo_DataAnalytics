package java_16_3_field;

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car();

        myCar.tire = new HankookTire();
        myCar.run();

        myCar.tire = new NexenTire();
        myCar.run();
    }
}
