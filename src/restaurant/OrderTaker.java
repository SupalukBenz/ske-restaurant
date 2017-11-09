package src.restaurant;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class OrderTaker {

    static final Scanner scan = new Scanner(System.in);
    static String[] menu;
    static double[] prices;
    static int[] order;
    public static void main(String[] args) throws IOException {
        RestaurantManager.init();
        menu = RestaurantManager.getMenuItems();
        prices = RestaurantManager.getPrices();
        printMenu();
        command();


    }

    public static void printMenu()throws FileNotFoundException{

        System.out.println("╔--------------------------------------------╗");
        System.out.println("   ╭  Welcome to ⓈⓀⒺ ʀestaurant ✘  ╯    ");
        System.out.println();

        for(int i = 0 ; i < menu.length ; i++){
            System.out.printf("  [%2d] %-25s%7s%5.0f%n",i+1, menu[i] , " ",prices[i]);
        }
        System.out.println();
        System.out.println("  [p] Print total order ");
        System.out.println("  [e] Edit menu in order");
        System.out.println("  [c] Cancel menu in order");
        System.out.println("  [m] Manage mode");
        System.out.println();
        System.out.println("  [o] Check out");
        System.out.println("╚--------------------------------------------╝");

    }

    public static void command() throws IOException{
        order = new int[menu.length];
        String choice = "";
        while(!choice.equals("o")) {
            System.out.println("Enter your choice : ");
            choice = scan.next();

            if (choice.charAt(0) > 48 && choice.charAt(0) < 57) {

                int choiceNumber = Integer.parseInt(choice);
                System.out.println("Enter your quantity : ");
                int qty = scan.nextInt();

                order[choiceNumber - 1] += qty;

                RestaurantManager.recordOrder(RestaurantManager.getLatestOrderNumber() , order ,
                        totalPrice());
                continue;
            }
            switch (choice) {
                case "p":
                    printTotal(totalPrice());
                    break;
                case "e" :
                    editMenu();
                    break;
                case "c":
                    cancelMenu();
                    break;
                case "m":
                    RestaurantManager.manager();
                    printMenu();
                    break;
            }
        }
        receipt();
    }

    public static void printTotal(double total){
        System.out.printf("+-------------%s---------------+-----%s-----+------%s------+%n", "Menu", "QTY", "Price");
        for(int i = 0 ; i < order.length ; i++){
            if (order[i] > 0) System.out.printf("|%-32s|%12d | %16.1f|%n", RestaurantManager.getMenuItems()[i], order[i], RestaurantManager.getPrices()[i] * order[i]);
        }
        System.out.println("------------------------------------------------------------------");
        System.out.printf("|%-10s %34s| %16.1f|%n" , "Total Price" , " " , total);
        System.out.println("------------------------------------------------------------------");
    }

    public static void editMenu(){
        System.out.println("Edit menu number : ");
        int number = scan.nextInt();

        System.out.println(menu[number - 1] + " total qty = " +  order[number - 1]);
        System.out.println("Edit quantity : ");
        int newQty = scan.nextInt();

        order[number - 1] = newQty;
        System.out.println("- Successful -");
        System.out.println(menu[number - 1] + " total qty = " +  order[number - 1]);


    }

    public static void cancelMenu(){
        System.out.println("Cancel menu number : ");
        int number = scan.nextInt();

        order[number - 1] = 0;
        System.out.println("- Successful -");
        System.out.println("Cancel " + menu[number - 1]);
    }

    public static void receipt(){
        System.out.println("--------------------------------------------------");
        System.out.printf("%36s%n" ,"╭  ⓈⓀⒺ ʀestaurant ✘  ╯");
        System.out.printf(" %tT%n " , System.currentTimeMillis());
        System.out.println();
        System.out.println("Cashier : SUPALUK");
        System.out.printf("%5s %d%n" , "Order: " ,RestaurantManager.getLatestOrderNumber());
        System.out.println();
        System.out.printf("  %-20s %5s %10s%n" , "menu" , "qty" , "price");
        for(int i = 0 ; i < order.length ; i++) {
            if(order[i] > 0) System.out.printf("  %-20s %5d %10.2f%n" , menu[i] , order[i] , prices[i]*order[i]);
        }
        System.out.println();
        System.out.printf("Total price : %15.2f%n", totalPrice() );
        System.out.println();
        System.out.println("------------------Thank you-----------------------");

    }

    public static double totalPrice(){
        double total = 0;
        for(int i = 0 ; i < order.length ; i++){
            if(order[i] > 0) {
                double p = prices[i]*order[i];
                total += p;
            }
        }
        return total;
    }


}