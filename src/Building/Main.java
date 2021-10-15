package Building;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter the number of floors in the house: ");
        int n = enterNum();
        House house = new House(n);
        System.out.println(house.toString());
        System.out.println("House square - " + house.Square());
        System.out.println("Number of residents in the house - " + house.Tenants());
        System.out.println("Number of floors in the house - " + house.getNumFloor());
    }

    public static int enterNum(){
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

}
