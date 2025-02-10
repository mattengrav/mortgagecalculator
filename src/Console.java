import java.text.NumberFormat;
import java.util.Scanner;

public class Console {
    private static Scanner scanner = new Scanner(System.in);
    public static double readInput(String prompt){
        return scanner.nextDouble();
    }
    public static double readInput(String prompt, double min, double max) {
        double output;
        System.out.print(prompt);
        output = scanner.nextDouble();
        while (output < min || output > max) {
            System.out.print(prompt);
            output = scanner.nextDouble();
        }
        return output;
    }

    public static String convertToCurrency(double amount){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String result = currency.format(amount);
        return result;
    }
}
