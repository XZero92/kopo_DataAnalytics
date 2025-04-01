package java_15_1_ex1_starcaroptionexam;

public class StarCarLowGrade extends StarCar {
    private int tax;

    StarCarLowGrade(String color, String tire, int displacement, String handle) {
        super(color, tire, displacement, handle);
    }

    @Override
    void getSpec() {
        System.out.println("색상: " + this.color);
        System.out.println("타이어: " + this.tire);
        System.out.println("배기량: " + this.displacement);
        System.out.println("핸들: " + this.handle);

        if(this.displacement > 2000)
            tax = 2000;
        else
            tax = 1000;

        System.out.println("세금: " + tax);
    }
}
