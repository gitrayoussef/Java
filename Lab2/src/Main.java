import java.util.Scanner;

public class Main {
    public static void main(String[] arsg) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number: ");
        byte number = scanner.nextByte();
        if(number%3==0 && number%5==0)
            System.out.println("Fizz Buzz");
        else if (number%5==0)
            System.out.println("Fizz");
        else if (number%3==0)
            System.out.println("Buzz");
        else
            System.out.println(number);
    }
}