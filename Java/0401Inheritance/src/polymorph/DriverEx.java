package polymorph;

public class DriverEx {
    public static void main(String[] args) {
        Driver driver = new Driver();
        Taxi taxi = new Taxi();
        Bus bus = new Bus();

        driver.drive(taxi);
        driver.drive(bus);
    }
}
