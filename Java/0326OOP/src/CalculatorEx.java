public class CalculatorEx {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        int result = calc.plus(100, 55);
        System.out.println("result: " + result);

        System.out.println("정사각형 넓이: " + areaRectangle(100));
        System.out.println("직사각형 넓이: " + areaRectangle(100, 50));

    }

    private static double areaRectangle(double width) {
        return width * width;
    }

    private static double areaRectangle(double width, double height) {
        return width * height;
    }
}
