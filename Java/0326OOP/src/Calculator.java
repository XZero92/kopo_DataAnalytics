public class Calculator {
    public Calculator() {
        powerOn();
    }
    void powerOn() {
        System.out.println("전원이 켜집니다.");
    }
    int plus(int x, int y) {
        int result = x + y;
        return result;
    }

    static double PI = 3.14159265358979323846;

    static double calc(double radius) {
        double area = radius * radius * PI;
        System.out.println(this.strCalc + area);

        return area;
    }
}
