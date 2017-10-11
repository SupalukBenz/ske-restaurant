package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class SkeRestaurantFile {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Integer> orderMenu = new ArrayList<Integer>();
    static ArrayList<Integer> orderPrice = new ArrayList<Integer>();
    static ArrayList<Integer> totalEachMenu = new ArrayList<Integer>();
    public static void main(String[] args) throws FileNotFoundException {
            int count = 0;
            int count1 = 0;
            File fileMenu = new File("Menu.txt");
            Scanner scan = new Scanner(fileMenu);
            ArrayList<String> lines = new ArrayList<String>();
            while (scan.hasNextLine()) {
                count++;
                lines.add(scan.nextLine());
            }
            File filePrice = new File("Price.txt");
            Scanner scanPrice = new Scanner(filePrice);
            ArrayList<String> linesPrice = new ArrayList<String>();

            while (scanPrice.hasNextLine()) {
                count1++;
                linesPrice.add(scanPrice.nextLine());
            }

            String[] menu = lines.toArray(new String[0]);
            String[] priceInt = linesPrice.toArray(new String[0]);
            int[] price = new int[priceInt.length];
            int x = 0;
            for (String str : priceInt) {
                price[x] = Integer.parseInt(str);
                x++;
            }

            printMenu(menu , price);

            order(menu , price);

            receipt(menu , price);

        }



        public static void printMenu(String[] menu , int[] price){
            System.out.printf("---------------Welcome to SKE-CAFE---------------%n");
            for(int i = 0 ; i < menu.length ; i++){
                System.out.printf("|%d.)%20s    %10d Bath\t\t|%n" ,i+1, menu[i] , price[i]);
            }
            System.out.println("|8.) TOTAL  \t\t\t\t\t\t\t\t\t|");
            System.out.println("|9.) EXIT \t\t\t\t\t\t\t\t\t\t|");
            System.out.println("-------------------------------------------------");
        }

        public static void order(String[] menu , int[] price){
            int choice = 0;

            while(choice!=9){
                System.out.println();
                System.out.print("Enter your choice : ");
                choice = sc.nextInt();
                if(choice >= 1 && choice <= 7){
                    orderMenu.add(choice - 1);
                    System.out.print("Enter your quantity : ");
                    int quantity = sc.nextInt();
                    orderPrice.add(quantity);

                    for(int i = 0 ;i < orderMenu.size() ; i++){
                        totalEachMenu.add(i , orderMenu.get(i));
                    }

                    for(int i = 0 ; i < orderMenu.size() ; i++){
                        for(int j = 0 ; j < orderMenu.size() ; j++){
                            if(i != j){
                                int sumtotal = 0;
                                if(orderMenu.get(i).equals(totalEachMenu.get(j))){
                                    int sum = orderPrice.get(j) + orderPrice.get(i);
                                    sumtotal += sum;
                                    orderPrice.add(i , sumtotal);
                                    orderMenu.remove(j);
                                }
                            }
                        }
                    }

                }else if(choice == 8){
                    tabel(menu , price);
                }
            }
        }

        public static void tabel(String[] menu , int[] price){
            int totalPrice = 0;
            System.out.printf("+-------------%s-----------+-----%s-----+------%s------+%n" , "Menu" , "QTY" , "Price");

            for(int i = 0 ; i < orderMenu.size() ; i++){
                System.out.printf("|%20s        |      %5d  | %10d\t\t |%n" ,menu[orderMenu.get(i)] , orderPrice.get(i) , eachPrice(orderMenu.get(i) , orderPrice.get(i)));
                totalPrice += eachPrice(orderMenu.get(i) , orderPrice.get(i));
            }

            System.out.println("-------------------------------------------------------------");
            System.out.printf("|%s %30s| %10d\t\t |%n" , "Total Price" , " " , totalPrice);
            System.out.println("-------------------------------------------------------------");


        }

        public static int eachPrice(int orderMenu , int qty){
            int priceMenu = 0;
            switch (orderMenu){
                case 0:
                    priceMenu = qty * 60;
                    break;

                case 1:
                    priceMenu = qty * 80;
                    break;

                case 2:
                    priceMenu = qty * 100;
                    break;

                case 3:
                    priceMenu = qty * 100;
                    break;

                case 4:
                    priceMenu = qty * 120;
                    break;

                case 5:
                    priceMenu = qty * 150;
                    break;

                case 6:
                    priceMenu = qty * 130;
                    break;
            }
            return priceMenu;
        }

        public static void receipt(String[] menu , int[] price){
            int totalPrice = 0;
            System.out.println("-------------------------------------------------------------");
            System.out.printf("|                        %s                           |%n" , "SKE-CAFE");
            System.out.printf("|                  %s                 |%n" , "Receipt/Tax Invoice(ABB)");
            System.out.printf("|     Staff : Supaluk                                       |%n");
            Date date = new Date();
            System.out.printf("|     %s                          |%n" , date.toString());
            System.out.printf("|                                                           |%n");
            for(int i = 0 ; i < orderMenu.size() ; i++){
                System.out.printf("|%20s        \t\t\t%10d Bath\t\t|%n" ,menu[orderMenu.get(i)] , eachPrice(orderMenu.get(i) , orderPrice.get(i)));
                totalPrice += eachPrice(orderMenu.get(i) , orderPrice.get(i));
            }
            System.out.printf("|                                                           |%n");


            int vat = ((totalPrice * 7) / 100);
            System.out.printf("|%s %30s%10d Bath\t\t|%n" , "VAT 7%" , " " , vat);
            System.out.printf("|%s %30s %10d Bath\t\t|%n" , "Total" , " " , totalPrice + vat);
            System.out.println("----------------------Thank  you-----------------------------");
        }


    }
