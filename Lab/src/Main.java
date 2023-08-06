import java.text.NumberFormat;
import java.util.Scanner;

public  class Main {
    final static byte  monthsInYear = 12;
    final static byte  percentage = 100;

    public static void main(String[] args) {
        int principal;
        float annualInterest;
        byte period;

        principal = (int) readValue("Principal ($1K - $1M): ",1000,1000000);
        annualInterest = (float) readValue("Annual Interest: ",0.01,30);
        period = (byte) readValue("Period (Year): ",1,30);

        System.out.println("\nMortgage");
        System.out.println("--------");
        double mortgage = calculateMortgage(principal, annualInterest, period);
        System.out.println("Monthly Payments: " + NumberFormat.getCurrencyInstance().format(mortgage));

        System.out.println("\nPayment Schedule");
        System.out.println("----------------");
        printSchedule(principal,annualInterest,period);
    }

    public static double readValue(String prompt,double min,double max) {
        Scanner scanner = new Scanner(System.in);
        double variable;
        while (true) {
            System.out.print(prompt);
            variable = scanner.nextDouble();
            if(variable >= min && variable <= max)
                break;
            System.out.println("Enter a value between " + NumberFormat.getIntegerInstance().format(min) + " and " + NumberFormat.getIntegerInstance().format(max));
        }
        return variable;
    }

    public static double calculateMortgage(int principal,float annualInterest,byte period) {
        int numberOfPayment = period * monthsInYear;
        float monthlyRate = annualInterest / (monthsInYear * percentage);
        return (principal * ((monthlyRate * Math.pow(1+monthlyRate,numberOfPayment)) / (Math.pow(1+monthlyRate,numberOfPayment) - 1)));
    }

    public static double calculateRemainBalance(int principal,float annualInterest,byte period, int numberOfDonePayments) {
        int numberOfPayment = period * monthsInYear;
        float monthlyRate = annualInterest / (monthsInYear * percentage);
        return (principal * (Math.pow(1+monthlyRate,numberOfPayment)-Math.pow(1+monthlyRate,numberOfDonePayments)) / (Math.pow(1+monthlyRate,numberOfPayment)-1));
    }

    public static void printSchedule(int principal,float annualInterest,byte period) {
        int numberOfPayment = period * monthsInYear;
        for(int i=1;i<=numberOfPayment;i++) {
            System.out.println(i+" ");
            double remainBalance = calculateRemainBalance(principal,annualInterest,period,i);
            System.out.println(NumberFormat.getCurrencyInstance().format(remainBalance));
        }
    }

}