package src.restaurant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * SKERestaurant class get data by calling method from RestaurantManager.
 * Take order from user and save in salesLogs file.
 * Print total order.
 * @author Supaluk Jaroensuk
 */

public class SKERestaurant {

    private static String[] menu = RestaurantManager.getMenuItems();
    private static double[] price = RestaurantManager.getPrice();
    final static Scanner scan = new Scanner(System.in);
    static int latestNumber = RestaurantManager.getLatestOrderNumber();

    public static List<Integer> checkOrder(int[] order){
        List<Integer> check = new ArrayList<Integer>();
        System.out.println(" - Menu in order - ");
        for(int i = 0 ; i < order.length ; i++){
            if(order[i] != 0) {
                System.out.printf("[%d] . %s%n" , i+1 , menu[i]);
                check.add(i);
            }
        }
        return check;
    }

    public static void editOrder(int[] order){
        System.out.println();
        System.out.println("-------- EDIT MENU --------");
        List<Integer> check = checkOrder(order);
        int edit = 0;
        while(true) {
            System.out.print("Edit menu number : ");
            edit = scan.nextInt();
            if(check.contains(edit - 1)) break;
            System.out.println("# Incorrect number #");
        }
        System.out.printf("%s , total quantity is %d%n" , menu[edit - 1] , order[edit - 1]);
        System.out.print("Edit quantity : ");
        int newQty = scan.nextInt();
        order[edit - 1] = newQty;
        System.out.println("-------- Successful --------");
        System.out.println();

    }


    public static void cancelOrder(int[] order){
        System.out.println();
        System.out.println("-------- CANCEL MENU --------");
        List<Integer> check = checkOrder(order);
        int edit = 0;
        while(true) {
            System.out.print("Cancel menu number : ");
            edit = scan.nextInt();
            if(check.contains(edit - 1)) break;
            System.out.println("# Incorrect number #");
        }
        System.out.println("Cancel menu , " + menu[edit - 1]);
        order[edit - 1] = 0;
        System.out.println("-------- Successful --------");
        System.out.println();
    }

    public static void printTotal(int[] order , double total){
        System.out.printf("+-------------%s---------------+-----%s-----+------%s------+%n", "Menu", "QTY", "Price");
        for(int i = 0 ; i < order.length ; i++){
            if (order[i] > 0) System.out.printf("|%-32s|%12d | %16.1f|%n", menu[i], order[i], price[i] * order[i]);
        }
        System.out.println("------------------------------------------------------------------");
        System.out.printf("|%-10s %34s| %16.1f|%n" , "Total Price" , " " , total);
        System.out.println("------------------------------------------------------------------");
    }

    /**
     * return order's total price.
     */
    public static double total(int[] order){
        double totalPrice = 0;
        for(int i = 0 ; i < order.length ; i++){
            totalPrice += (price[i]*order[i]);
        }
        return totalPrice;
    }

    public static void receipt(int[] order , double totalPrice){
        Date date = new Date();
        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.printf("|%35s%14s|%n" ,"╭  ⓈⓀⒺ ʀestaurant ✘  ╯" , " ");
        System.out.printf("|%-51s|%n" , "");
        System.out.printf("|%-6s%tc%-17s|%n" ,"time:" , date , " ");
        System.out.printf("|%-36s%15s|%n" , "Cashier : SUPALUK" , " ");
        System.out.printf("|%-51s|%n" , "");
        System.out.printf("|%10s %3d%37s|%n" , "Order: " , latestNumber , " ");
        System.out.printf("|%-51s|%n" , "");
        System.out.printf("|  %-24s %5s %10s%8s|%n" , "menu" , "qty" , "price" , " ");
        for(int i = 0 ; i < order.length ; i++) {
            if(order[i] > 0) System.out.printf("|  %-24s %5d %8.2f Bath%5s|%n" , menu[i] , order[i] , price[i]*order[i] , " " );
        }
        System.out.printf("|%-51s|%n" , "");
        System.out.printf("|Total price : %8.2f Bath%24s|%n", totalPrice , " ");
        System.out.printf("|%-51s|%n" , "");
        System.out.println("--------------------Thank you------------------------");
    }

    public static void printMenu(){
        System.out.println("╔--------------------------------------------╗");
        System.out.println("   ╭  Welcome to ⓈⓀⒺ ʀestaurant ✘  ╯    ");
        System.out.println();

        for(int i = 0 ; i < menu.length ; i++){
            System.out.printf("  [%2d] %-25s%7s%5.0f%n",i+1, menu[i] , " ",price[i]);
        }
        System.out.println();
        System.out.println("  [p] Print total order ");
        System.out.println("  [e] Edit menu in order");
        System.out.println("  [c] Cancel menu in order");
        System.out.println();
        System.out.println("  [o] Check out");
        System.out.println("╚--------------------------------------------╝");
    }

    public static void takeOrder(){
        int[] order = new int[menu.length];
        printMenu();
        getItems(order);
        receipt(order , total(order));
    }

    /**
     * Input command from user.
     * Add order to array.
     * Proceed by calling method from inside class.
     */
    public static void getItems(int[] order){
        String command = "";
        while(!command.equals("o")){
            System.out.println();
            System.out.print("Enter your choice : ");
            command = scan.next();
            if (command.charAt(0) > 48 && command.charAt(0) <= 57) {

                int choiceNumber = Integer.parseInt(command);
                System.out.print("Enter your quantity : ");
                int qty = scan.nextInt();

                order[choiceNumber - 1] += qty;

                continue;

            }
            switch (command) {
                case "p":
                    printTotal(order,total(order));
                    break;
                case "e" :
                    editOrder(order);
                    break;
                case "c":
                    cancelOrder(order);
                    break;
            }
        }

        RestaurantManager.recordOrder(latestNumber , order , total(order));

    }

    public static void main(String[] args) {
        RestaurantManager.init();
        takeOrder();
    }

}
