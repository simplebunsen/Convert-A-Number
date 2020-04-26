package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sourceRadix = scanner.nextInt();
        scanner.nextLine();
        long num = 0;
        if (sourceRadix == 1) {
            long tempnum = scanner.nextInt();
            for (int i = 0; i <= (int) (Math.log10(tempnum)); i++) {
                num++;
            }
        } else {
            num = Long.parseLong(scanner.nextLine(), sourceRadix);
        }
        int targetRadix = scanner.nextInt();

        if(sourceRadix > 36 || targetRadix > 36){
            System.out.println("Radix too large");
            return;
        }


        if (targetRadix == 1) {
            while (num > 0) {
                System.out.print(1);
                num--;
            }
        } else {
            System.out.println(Long.toString(num, targetRadix));
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
        int currentPowOf;
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
