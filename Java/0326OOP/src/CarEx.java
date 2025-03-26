public class CarEx {
    public static void main(String[] args) {
        Car myCar = new Car();

        System.out.println("제조사: " + myCar.company);
        System.out.println("모델명: " + myCar.model);
        System.out.println("색상: " + myCar.color);
        System.out.println("최고속도: " + myCar.maxSpeed);
        System.out.println("현대속도: " + myCar.speed);

        myCar.speed = 60;
        System.out.println("수정된 현재속도: " + myCar.speed);

        Car mySecondCar = new Car("현대자동차", "i30", "lightblue");

        System.out.println("제조사: " + mySecondCar.company);
        System.out.println("모델명: " + mySecondCar.model);
        System.out.println("색상: " + mySecondCar.color);
        System.out.println("최고속도: " + mySecondCar.maxSpeed);
        System.out.println("현대속도: " + mySecondCar.speed);
    }
}
