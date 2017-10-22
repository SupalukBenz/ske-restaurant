package src;

import com.sun.deploy.util.ArrayUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class RestaurantManager {
    private static String[] menuItems;
    private static double[] price;

    private static ArrayList<String> menuArr = new ArrayList<String>();
    private static ArrayList<String> menu = new ArrayList<String>();
    private static ArrayList<Double> priceArr = new ArrayList<Double>();
    static void init() throws FileNotFoundException {
        String filename = "data/menu.txt";
        try {
            setMenu(filename);

        }catch (Exception e){
            System.out.print("Couldn't open output file " + filename);
        }
    }

    public static String[] getMenuItems() throws FileNotFoundException {
        return menuItems;
    }

    public static double[] getPrices() throws FileNotFoundException {
        return price;
    }


    public static void recordOrder ( int orderNumber, int[] order, double total) throws IOException{

        String logFile = "src/data/salesLog.log";
        OutputStream os = null;
        try {
            os = new FileOutputStream(logFile);
        }catch (FileNotFoundException e){
            System.out.println("Couldn't open output file " + logFile);
            return;
        }
        PrintStream print = new PrintStream(os);
        print.printf("+-------------%s-----------+-----%s-----+------%s------+%n", "Menu", "QTY", "Price");
        for(int i = 0 ; i < orderNumber ; i++) {
            if (order[i] > 0) print.printf("|%20s        |      %5d  | %10.1f\t\t |%n", RestaurantManager.getMenuItems()[i], order[i], RestaurantManager.getPrices()[i] * order[i]);
        }
        print.println("-------------------------------------------------------------");

//        print.println("Order number: " + orderNumber);
//        for(String str : order){
//            System.out.println(str);
//        }
        print.printf("|%s %30s| %10.1f\t\t |%n" , "Total Price" , " " , total);
        print.println("-------------------------------------------------------------");
        print.println("Some test output");
        print.printf("The time is : %tT\n" ,  System.currentTimeMillis());
        print.close();
    }


    static void setMenu(String filename) throws FileNotFoundException{

        int count = 0;
        File file = new File(filename);
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            for (String str : scan.nextLine().split(";")) {
                menuArr.add(str);
            }
        }

        int[] checkDelete = new int[100];
        int countCheck = 0;
        for (int i = 0; i < menuArr.size(); i++) {
            if (i % 2 == 0) {
                menu.add((menuArr.get(i)));
                if (menu.get(count).contains("#")) {
                    checkDelete[countCheck] = count;
                    countCheck++;
                }
                count++;
            } else {
                priceArr.add(Double.parseDouble(menuArr.get(i)));
            }
        }
        for (int i = 0; i < countCheck; i++) {
            menu.remove(checkDelete[i]);
            priceArr.remove(checkDelete[i]);
        }
        menuItems = menu.parallelStream().toArray(String[]::new);
        price = priceArr.stream().mapToDouble(Double::doubleValue).toArray();
    }

    public static void main(String[] args) throws FileNotFoundException {
        RestaurantManager.init();
    }
}

