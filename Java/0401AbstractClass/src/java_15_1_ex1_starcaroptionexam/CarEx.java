package java_15_1_ex1_starcaroptionexam;

public class CarEx {
    public static void main(String[] args) {
        StarCar carH = new StarCarHighGrade(CarSpecs.COLOR_RED, CarSpecs.TIRE_WIDE, CarSpecs.DISPLACEMENT_2200, CarSpecs.HANDLE_POWER);
        StarCar carL = new StarCarLowGrade(CarSpecs.COLOR_BLUE, CarSpecs.TIRE_NORMAL, CarSpecs.DISPLACEMENT_2000, CarSpecs.HANDLE_POWER);

        System.out.println("[StarCar 저사양 옵션 및 세금]");
        System.out.println("--------------------------");
        carL.getSpec();

        System.out.println("[StarCar 고사양 옵션 및 세금]");
        System.out.println("--------------------------");
        carH.getSpec();
    }
}