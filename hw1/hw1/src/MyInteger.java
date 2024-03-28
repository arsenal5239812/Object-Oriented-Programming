public class MyInteger {
    private final int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isEven() {
        return isEven(this.value);
    }

    public boolean isOdd() {
        return isOdd(this.value);
    }

    public static boolean isEven(int value) {
        return value % 2 == 0;
    }

    public static boolean isOdd(int value) {
        return value % 2 != 0;
    }

    public boolean equals(int anotherValue) {
        return this.value == anotherValue;
    }

    public boolean equals(MyInteger anotherInteger) {
        return anotherInteger != null && this.value == anotherInteger.getValue();
    }

    public static int parseInt(char[] value) {
        return Integer.parseInt(new String(value));
    }

    public static int parseInt(String value) {
        return Integer.parseInt(value);
    }

    // Client Test in Main
    public static void main(String[] args) {
        MyInteger myInt = new MyInteger(9);

        System.out.println("Value is even? " + myInt.isEven());
        System.out.println("Value is odd? " + myInt.isOdd());
        System.out.println("Value equals 9? " + myInt.equals(9));

        MyInteger anotherInt = new MyInteger(10);
        System.out.println("Value equals anotherInt? " + myInt.equals(anotherInt));

        char[] charNumbers = {'1', '2', '3'};
        System.out.println("Parsed int from char[]: " + MyInteger.parseInt(charNumbers));

        String stringNumber = "456";
        System.out.println("Parsed int from String: " + MyInteger.parseInt(stringNumber));

        System.out.println("Static isEven 12? " + MyInteger.isEven(12));
        System.out.println("Static isOdd 13? " + MyInteger.isOdd(13));
    }
}
