import java.util.Scanner;
public class Area {
    public static void main (String[] args) {
        double width; 
        double length;

        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入矩形的宽度：");
        width = scanner.nextDouble();

        System.out.print("请输入矩形的长度：");
        length = scanner.nextDouble();


        double area = width * length;
        System.out.println("矩形的面积为："+area);

        scanner.close();
    }
}
