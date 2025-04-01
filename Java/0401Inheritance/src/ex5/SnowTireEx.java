package ex5;

public class SnowTireEx {
    public static void main(String[] args) {
        SnowTire snowTire = new SnowTire();
        Tire tire = new Tire();

        snowTire.run();
        tire.run();

        tire = snowTire;
        tire.run();
    }
}
