import java.util.Scanner;

public class Weight {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter weight in kilograms: ");
        double kilograms = scanner.nextDouble();

        double totalPounds = kilograms * 2.20462;
        int pounds = (int) totalPounds;

        double decimalPounds = totalPounds - pounds;
        double ounces = decimalPounds * 16;

        System.out.printf("Equivalent imperial weight is %d lb %.1f oz", pounds, ounces);
    }
}
