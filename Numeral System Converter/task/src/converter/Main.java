package converter;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int sourceRadix = scanner.nextInt();
        scanner.nextLine();

        String[] numInputString = scanner.nextLine().split("\\.");

        long integerPart = getIntPartAsDecimal(sourceRadix, numInputString[0]);
        double fracPart = 0;
        if (numInputString.length > 1){
            fracPart = getFracPartAsDecimal(sourceRadix, numInputString[1]);
        }

        double numAsDecimal = integerPart + fracPart;

        int targetRadix = scanner.nextInt();
        if(sourceRadix > 36 || targetRadix > 36){
            System.out.println("Radix too large");
            return;
        }

        String result;
        if(numInputString.length < 1 || targetRadix == 1){
            result = getIntegerTarget(targetRadix, integerPart);
        }else {
            result = getIntegerTarget(targetRadix, integerPart) + getFracTarget(targetRadix, fracPart);
        }
        System.out.println(result);
    }

    private static String getFracTarget(int targetRadix, double num) {
        String output = ".";
        double multiplied = num * targetRadix;

        for (int i = 0; i < 5; i++) {
            //Wtf am I doing here? Take the part INFRONT of the "." in multiplied (as String), parse it to
            //a decimal Long, then I convert it toString in the specified Target radix, and append it to the back
            //of my String after the fraction.
            long intPartOfMultiplication = Long.parseLong(String.valueOf(multiplied).split("\\.")[0]);
            output = output.concat(Long.toString(intPartOfMultiplication, targetRadix));


            double fracPartOfMultiplication = Double.parseDouble("0." + String.valueOf(multiplied).split("\\.")[1]);

            multiplied = fracPartOfMultiplication * targetRadix;
        }
        return output;
    }

    private static String getIntegerTarget(int targetRadix, long num) {
        switch (targetRadix){
            case 1:
                while (num > 0){
                    System.out.print(1);
                    num--;
                }
                return "";
            default:
                return Long.toString(num, targetRadix);
        }
    }

    private static double getFracPartAsDecimal(int sourceRadix, String num) {
        if(sourceRadix == 1) return 0;
        double output = 0;
        for (int i = 0; i < num.length(); i++) {
            long currentNum = Long.parseLong(Character.toString(num.charAt(i)), sourceRadix);
            //System.out.println("in fraction num: " + currentNum);
            output += currentNum / Math.pow(sourceRadix, i+1);
        }
        return output;
    }

    private static long getIntPartAsDecimal(int sourceRadix, String num) {
        long output = 0;
        switch (sourceRadix){
            case 1:
                for (int i = 0; i <= (int) (Math.log10(Long.parseLong(num))); i++) {
                    output++;
                }
                break;
            default:
                output = Long.parseLong(num, sourceRadix);
        }
        return output;
    }


}
