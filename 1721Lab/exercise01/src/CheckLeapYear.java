import java.util.Scanner;

public class CheckLeapYear {
    public static void main(String[] args){
        int year;
        Scanner scanner = new Scanner(System.in);
        year = scanner.nextInt();

        if(year%4==0&&year%100!=0||year%400==0){
            System.out.println("It is a leap year");
        }

        else{
            System.out.println("It is not a leap year");
        }
    }
}
