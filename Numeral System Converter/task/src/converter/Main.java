package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long num = scanner.nextInt();
        int radix = scanner.nextInt();
        String prefix;
        switch (radix) {
            case 16:
                prefix = "0x";
                break;
            case 8:
                prefix = "0";
                break;
            case 2:
                prefix = "0b";
                break;
            default:
                prefix = "?";
        }

        switch (radix){
            case 16:
            case 8:
            case 2:
                System.out.println(prefix.concat(Long.toString(num, radix)));
                break;
            default:
                System.out.println("unspoorted radix");
        }


    }

    private static void getOctalLastDigit() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(n%8);
    }

    private static void getBinary() {
        long decimal = 1864;
        long binary = 0;

        long currentPow = 0;
        int currentPowOf = 0;
        for (int i = 0; true; i++) {
            long prevPow = currentPow;
            currentPow = (long) Math.pow(2, i);
            currentPowOf = i;
            if(currentPow > decimal) {
                currentPow = prevPow;
                currentPowOf--;
                break;
            }
        }
        //System.out.println("our largest pow value: " + currentPow);

        long remainder = decimal;
        //System.out.println("remainder at beginning: " + remainder);
        while(remainder > 0){
            if(remainder >= currentPow){
                binary += (long) Math.pow(10, currentPowOf);
                //System.out.printf("%d currently larger than %d, binary is now %d \n", remainder, currentPow, binary);
                remainder -= currentPow;
            }
            currentPowOf--;
            currentPow = (long) Math.pow(2, currentPowOf);
            //System.out.printf("Remainder now: %d, currentPow now: %d \n", remainder, currentPow);
        }
        System.out.printf("%d is equal to 0b%d", decimal, binary);
    }
}
