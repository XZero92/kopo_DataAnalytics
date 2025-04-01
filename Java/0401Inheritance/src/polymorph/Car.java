package polymorph;

public class Car {
    public Tire tire;

    public void run() {
        tire.roll();
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.tire = new Tire();
        car.run();

        car.tire = new HankookTire();
        car.run();

        car.tire = new NexenTire();
        car.run();
    }
}
