import java.util.Random;
import java.util.Scanner;

public class SKERestaurant {
    static Scanner scan = new Scanner(System.in);
    public static void menu(){
        System.out.println("--------- Welcome to SKE Restaurant ---------");
        System.out.printf("|1.) Pizza\t\t %5d Bath.\t\t\t\t|%n" , 250);
        System.out.printf("|2.) Chickens\t %5d Bath.\t\t\t\t|%n" , 120);
        System.out.printf("|3.) Water Refill %4d Bath.\t\t\t\t|%n" , 45);
        System.out.println("|\t - Coke\t\t\t\t\t\t\t\t\t|");
        System.out.println("|\t - Green Tea\t\t\t\t\t\t\t|");
        System.out.println("|\t - Black Coffee\t\t\t\t\t\t\t|");
        System.out.println("|\t - Orange Juice\t\t\t\t\t\t\t|");
        System.out.println("|4.) Total\t\t\t\t\t\t\t\t\t|");
        System.out.println("|5.) Exit <Promotion for KU student>\t\t|");
        System.out.println("---------------------------------------------");
        System.out.println("");
    }
    public static void eachWater(int cokeQuan1 , int greenQuan , int coffQuan , int oranQuan){
        if(cokeQuan1 > 0 ) System.out.printf("|%s \t\t\t\t| %5d\t | %7d\t  |%n", "Coke" , cokeQuan1 , cokeQuan1*45);
        if(greenQuan > 0) System.out.printf("|%s \t\t\t| %5d\t | %7d\t  |%n", "Green tea" , greenQuan , greenQuan*45);
        if(coffQuan > 0) System.out.printf("|%s \t\t| %5d\t | %7d\t  |%n", "Black Coffee" , coffQuan , coffQuan*45);
        if(oranQuan > 0) System.out.printf("|%s \t\t| %5d\t | %7d\t  |%n", "Orange Juice" , oranQuan , oranQuan*45);
    }

    public static int totalPrice(int pizzQuan , int chickenQuan , int cokeQuan){
        int total = (pizzQuan * 250) + (chickenQuan * 120 ) + (cokeQuan * 45);
        return total;
    }

    public static void eachCase(int pizzQuan , int chickenQuan , int cokeQuan , int cokeQuan1 , int greenQuan , int coffQuan , int oranQuan){

        System.out.println("+------- Menu ------+-- Qty --+-- Price --+");
        if(pizzQuan > 0) System.out.printf("|%s \t\t\t\t| %5d\t | %7d\t  |%n", "Pizza" , pizzQuan , pizzQuan*250);
        if(chickenQuan > 0) System.out.printf("|%s \t\t\t| %5d\t | %7d\t  |%n", "Chicken" , chickenQuan , chickenQuan*120);
        if(cokeQuan > 0) eachWater(cokeQuan1 , greenQuan , coffQuan , oranQuan);
        System.out.println("-------------------------------------------");
        System.out.printf("| Total\t\t\t\t\t\t | %10d |%n", totalPrice(pizzQuan , chickenQuan , cokeQuan));
        System.out.println("-------------------------------------------");
        System.out.println("");
    }

    public static void program() {
        Random rand = new Random();
        int coupon[] = new int[]{10, 20, 50};
        int couponRand = rand.nextInt(3) + 1;

        int menuChoice = 0;
        int pizzaQuan = 0;
        int chickenQuan = 0;
        int cokeQuan = 0;
        int waterChoice = 0;
        int cokeQuan1 = 0, greenQuan = 0, coffQuan = 0, oranQuan = 0;
        while (true) {

            System.out.print("Enter your Choice: ");
            menuChoice = scan.nextInt();

            if (menuChoice == 5) {
                break;
            } else if (menuChoice == 4) {
                eachCase(pizzaQuan, chickenQuan, cokeQuan, cokeQuan1, greenQuan, coffQuan, oranQuan);
            } else if (menuChoice == 1 || menuChoice == 2) {
                System.out.print("Enter Quantity: ");
                int quantity = scan.nextInt();
                System.out.println("");
                if (menuChoice == 1) {
                    pizzaQuan += quantity;
                } else if (menuChoice == 2) {
                    chickenQuan += quantity;
                }

            } else if (menuChoice == 3) {
                System.out.println("-----------------------------");
                System.out.println("|\tMenu Water Refill\t\t|");
                System.out.println("|\t\t1.) Coke\t\t\t|");
                System.out.println("|\t\t2.) Green tea\t\t|");
                System.out.println("|\t\t3.) Black Coffee\t|");
                System.out.println("|\t\t4.) Orange Juice\t|");
                System.out.println("-----------------------------");

                System.out.print("Water Refill(45 Bath): ");
                waterChoice = scan.nextInt();
                System.out.print("Enter Quantity: ");
                int quantity = scan.nextInt();
                if (waterChoice == 1) cokeQuan1 += quantity;
                if (waterChoice == 2) greenQuan += quantity;
                if (waterChoice == 3) coffQuan += quantity;
                if (waterChoice == 4) oranQuan += quantity;
                cokeQuan += quantity;
                System.out.println("");
            } else if (menuChoice != 1 && menuChoice != 2 && menuChoice != 3 && menuChoice != 4 && menuChoice != 5) {
                System.out.println("\n\t# Incorrect menu\n");

            }
        }

        promotionMenu();
        System.out.print("Are you KU student? (Y/N) : ");
        String pressKU = scan.next();

        System.out.println("");
        if (pressKU.equalsIgnoreCase("y")) {
            int kuRand = coupon[couponRand - 1];
            System.out.printf("You get a coupon %d%% discount!!%n", kuRand);

            System.out.println("-----------------------------------------");
            System.out.printf("|\t\t\t Total price = %5d \t\t|%n", (totalPrice(pizzaQuan, chickenQuan, cokeQuan) - (((totalPrice(pizzaQuan, chickenQuan, cokeQuan)) * kuRand) / 100)));
            System.out.println("-----------------------------------------");
            System.out.print(" Cash customer pay : ");
            int payCustomer = scan.nextInt();
            if (payCustomer >= totalPrice(pizzaQuan, chickenQuan, cokeQuan) - (((totalPrice(pizzaQuan, chickenQuan, cokeQuan)) * kuRand) / 100)) {
                System.out.println("-----------------------------------------");
                System.out.printf("|\t Change        : %d \t\t\t\t|%n", payCustomer - (totalPrice(pizzaQuan, chickenQuan, cokeQuan) - (((totalPrice(pizzaQuan, chickenQuan, cokeQuan)) * kuRand) / 100)));
                System.out.println("--------------Thanks you-----------------");
            } else {
                int payCustomer1 = 0;
                while (payCustomer1 <= totalPrice(pizzaQuan, chickenQuan, cokeQuan) - (((totalPrice(pizzaQuan, chickenQuan, cokeQuan)) * kuRand) / 100)) {
                    System.out.println("|\t\t # Money is not enough ,");
                    System.out.print(" Cash customer pay : ");
                    payCustomer1 = scan.nextInt();

                }
                System.out.println("-----------------------------------------");
                System.out.printf("|\t Change        : %d \t\t\t\t|%n", payCustomer1 - (totalPrice(pizzaQuan, chickenQuan, cokeQuan) - (((totalPrice(pizzaQuan, chickenQuan, cokeQuan)) * kuRand) / 100)));
                System.out.println("--------------Thanks you-----------------");
            }


        } else {
            System.out.println("-----------------------------------------");
            System.out.printf("|\t\t\t Total price = %5d \t\t|%n", (totalPrice(pizzaQuan, chickenQuan, cokeQuan)));
            System.out.println("-----------------------------------------");
            System.out.print(" Cash customer pay : ");
            int payCustomer = scan.nextInt();
            if (payCustomer >= totalPrice(pizzaQuan, chickenQuan, cokeQuan)) {
                System.out.println("-----------------------------------------");
                System.out.printf("|\t Change        : %d \t\t\t\t|%n", payCustomer - (totalPrice(pizzaQuan, chickenQuan, cokeQuan)));
                System.out.println("--------------Thanks you-----------------");
            } else {
                int payCustomer1 = 0;
                while (payCustomer1 <= totalPrice(pizzaQuan, chickenQuan, cokeQuan)) {
                    System.out.println("|\t\t # Money is not enough ,");
                    System.out.print(" Cash customer pay : ");
                    payCustomer1 = scan.nextInt();

                }
                System.out.println("-----------------------------------------");
                System.out.printf("|\t Change        : %d \t\t\t\t|%n", payCustomer1 - (totalPrice(pizzaQuan, chickenQuan, cokeQuan)));
                System.out.println("--------------Thanks you-----------------");
            }
        }
    }

    public static void promotionMenu(){
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("|\t\t\t%30s\t\t\t\t |%n" , "+++++++ Promotion for September !! ++++++++");
        System.out.printf("|\t\t%30s\t\t\t\t |%n" ,"+++ This Promotion is only for KU Student!! +++");
        System.out.printf("|%s|%n" , "SKE Restaurant will random a coupon(10% , 20% , 50%) for KU student.");
        System.out.println("----------------------------------------------------------------------");

    }

    public static void main(String[] args){
        menu();
        program();

    }




}
