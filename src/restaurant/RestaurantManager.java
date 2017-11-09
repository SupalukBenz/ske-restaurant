package src.restaurant;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class RestaurantManager {

    static ArrayList<String> menu = new ArrayList<String>();
    static ArrayList<Double> prices = new ArrayList<Double>();
    static ClassLoader loader = RestaurantManager.class.getClassLoader();
    static Scanner scan = new Scanner(System.in);
    final static String FILE_MENU = "src/data/menu.txt";
    final static String FILE_LOG = "src/data/salesLog.log";


    static void setMenu(String filename) throws FileNotFoundException{

        InputStream inputStream = loader.getResourceAsStream(filename);

        if(inputStream == null){
            System.out.println("Could not access file " + filename);
            return;
        }

        Scanner reader = new Scanner(inputStream);

        while(reader.hasNextLine()){
            String line = reader.nextLine().trim();

            if(line.charAt(0) != '#'){
                menu.add(line.split(";")[0]);
                prices.add(Double.parseDouble(line.split(";")[1]));
            }
        }
        reader.close();
    }

    public static String[] getMenuItems(){
        return menu.toArray(new String[menu.size()]);
    }

    public static double[] getPrices(){
        double[] price = new double[prices.size()];
        int count = 0;
        for(double i : prices){
            price[count] = i;
            count++;
        }

        return price;
    }

    public static void recordOrder(int orderNumber , int[] order , double total) throws IOException{
        FileWriter fw = new FileWriter(FILE_LOG, true);
        PrintWriter printOut = null;
        try {
            printOut = new PrintWriter(fw);
        }catch (Exception e){
            System.out.println("Couldn't open output file " + FILE_LOG);
            return;
        }

        printOut.println("Order number : " + orderNumber);
        printOut.printf("Qty %-30s %10s %10s%n" , "Menu" , "Price" , "Total");
        for(int i = 0 ; i < order.length ; i++){
            if(order[i] != 0){
                printOut.printf("(%d) %-30s %10.2f  %10.2f Bath%n" , order[i] , menu.get(i) , prices.get(i) , order[i]*prices.get(i));
            }
        }
        printOut.printf("%36s Subtotal  %10.2f%n" , "" , total);
        printOut.printf("Time : %tT%n" , System.currentTimeMillis());
        printOut.println();
        printOut.close();
    }


    static int getLatestOrderNumber()  {
        int orderNumber = 1;
        InputStream in = loader.getResourceAsStream(FILE_LOG);
        if(in == null) {
            orderNumber = 1;
        }else{

            Scanner s = new Scanner(in);
            while(s.hasNextLine()){
                String line = s.nextLine();
                if(line.startsWith("Order")){
                    try {
                        orderNumber = Integer.parseInt(line.split(":")[1].trim()) + 1;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return orderNumber;

    }


    static void addMenu(String fileName)throws IOException{

        System.out.println("  Add menu ");

        System.out.print("menu title : ");
        String menuTitle = scan.next();

        System.out.print("menu price : ");
        double menuPrice = scan.nextDouble();

        FileWriter fw = new FileWriter(FILE_MENU,true);

        PrintWriter pw = null;

        for(String menuList : menu){
            if(menuTitle.equals(menuList)){
                System.out.println("Menu is already exit");
                return;
            }
        }
        try {
            pw = new PrintWriter(fw);
        }catch (Exception e){
            System.out.println("Couldn't open " + fileName);
            return;
        }
        pw.print(menuTitle + ";" + menuPrice);
        pw.println();
        menu.add(menuTitle);
        prices.add(menuPrice);
        pw.close();
    }

    static void init()throws FileNotFoundException{
        setMenu(FILE_MENU);

    }

    public static void manager()throws IOException{
        String input = "";
        while(!input.equals("x")) {

            System.out.println("[a] Add menu");
            System.out.println("[x] EXIT");

            System.out.println("Enter your choice : ");
            input = scan.next();

            switch (input) {
                case "a":
                    addMenu(FILE_MENU);
                    break;
                case "x":
                    return;

            }
        }
    }


}