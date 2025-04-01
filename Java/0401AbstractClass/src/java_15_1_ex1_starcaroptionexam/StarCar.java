package java_15_1_ex1_starcaroptionexam;

public abstract class StarCar {
    protected String color;
    protected String tire;
    protected int displacement;
    protected String handle;

    StarCar(String color, String tire, int displacement, String handle) {
        this.color = color;
        this.tire = tire;
        this.displacement = displacement;
        this.handle = handle;
    }

    abstract void getSpec();
}
