package src.restaurant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * RestaurantManager class is manager of OrderTaker class
 * read menu from file and collect in menu and price array
 * save record order
 *
 * @author Supaluk Jaroensuk
 */

public class RestaurantManager {

    private static String[] menu;
    private static double[] price;
    static ClassLoader classLoader = RestaurantManager.class.getClassLoader();
    final static String fileMenu = "src/data/menu.txt";
    final static String fileLog = "src/data/salesLog.txt";


    public static String[] getMenuItems(){
        init();
        return menu;
    }

    public static double[] getPrice(){
        init();
        return price;
    }

    /**
     * Set a menu read from a menu file and add to the array of menu and price.
     * @param fileMenu is a filename of menu file.
     */
    static void setMenu(String fileMenu) {

        BufferedReader br = null;
        List<String> split = new ArrayList<String>();
        int count =0;
        int loopMenu = 0;
        try {

            InputStream inputStream = classLoader.getResourceAsStream(fileMenu);

            br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = br.readLine()) != null){
                if(line.charAt(0) != '#') {
                   split.add(line);
                    count++;
                }
            }

            menu = new String[count];
            price = new double[count];

            for(int i = 0 ; i < split.size(); i++){
                String[] str = split.get(i).split(";");
                price[loopMenu] = Double.parseDouble(str[1]);
                menu[loopMenu] = str[0];
                loopMenu++;
            }

        }catch (IOException e){
            System.out.println("Can't access file.");

        }finally {
            try {
               if(br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    static int getLatestOrderNumber()  {
        int orderNumber = 1;
        InputStream in = classLoader.getResourceAsStream(fileLog);
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

    /**
     * Record a order. It appends the order to salesLog.txt
     * @param orderNumber is the latest order number.
     * @param order is a array of menu's quantity.
     * @param total is a order's total price.
     */
    public static void recordOrder(int orderNumber , int[] order , double total) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(fileLog , true);
        }catch (Exception e){
            System.out.println("Can't access to " + fileLog);
        }
        PrintStream printOut = new PrintStream(os);
        printOut.println("Order number : " + orderNumber);
        printOut.printf("Qty %-30s %10s %10s%n" , "Menu" , "Price" , "Total");
        for(int i = 0 ; i < order.length ; i++){
            if(order[i] != 0){
                printOut.printf("(%d) %-30s %10.2f  %10.2f Bath%n" , order[i] , menu[i] , price[i] , order[i]*price[i]);
            }
        }
        printOut.printf("%36s Subtotal  %10.2f Bath%n" , "" , total);
        printOut.printf("Time : %tT%n" , System.currentTimeMillis());
        printOut.close();

    }



    public static void init(){
        setMenu(fileMenu);
    }


}
