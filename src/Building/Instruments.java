package Building;

import java.util.Scanner;

public interface Instruments {

    static int enterNumInt(){
        Scanner sc = new Scanner(System.in);
        int n = 0;
        while(sc.hasNext()){
            if(sc.hasNextInt()){
                n = sc.nextInt();
                break;
            }
            else {
                System.out.println("Enter the number!");
                sc.next();
            }
        }
        return n;
    }

    static double enterNumDouble(){
        Scanner sc = new Scanner(System.in);
        double n = 0;
        while(sc.hasNext()){
            if(sc.hasNextDouble()){
                n = sc.nextDouble();
                break;
            }
            else {
                System.out.println("Enter the number!");
                sc.next();
            }
        }
        return n;
    }
}
