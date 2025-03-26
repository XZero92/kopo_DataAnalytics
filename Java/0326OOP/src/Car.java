public class Car {
    String company = "현대자동차";
    String model = "N74";
    String color = "실버";
    int maxSpeed = 300;
    int speed = 120;

    Car() {

    }
    Car (String company) {
        this.company = company;
    }
    Car (String company, String model) {
        this.company = company;
        this.model = model;
    }
    Car (String company, String model, String color) {
        this.company = company;
        this.model = model;
        this.color = color;
    }
}
