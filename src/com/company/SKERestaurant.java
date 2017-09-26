package com.company;
import java.util.Scanner;

public class SKERestaurant {

    public static int totalPriceMenu(int menuSKE , int quantify){
        int pricePizza = 0 , priceChicken = 0 , priceWater = 0;
        int priceTotal = 0;
        if(menuSKE == 1){
            pricePizza = 250 * quantify;
            priceTotal += pricePizza;
        }else if(menuSKE == 2){
            priceChicken = 120 * quantify;
            priceTotal += priceChicken;
        }else if(menuSKE == 3){
            priceWater = 45 * quantify;
            priceTotal += priceWater;
        }
        return priceTotal;
    }

    public static int priceEachMenu(int menu , int quantity){
        int price = 0 ;
        if(menu == 1) price = quantity * 250;
        else if(menu == 2) price = quantity * 120;
        else if(menu == 3) price = quantity * 45;
        return price;
    }


    public static String numTOstring(int menu){
        String toString = "";
       if(menu == 1){
           toString = "Pizza";
       }else if(menu == 2){
           toString = "Chicken";
       }else if(menu == 3){
           toString = "Coke";
       }
       return toString;
    }


    public static boolean showTotal(int quantity , int choice){

        Scanner scan = new Scanner(System.in);
        int menuSKE;
        System.out.print("Enter your Choice: ");
        menuSKE = scan.nextInt();
        if(menuSKE == 4) {
            System.out.println("+------- Menu ------+-- Qty --+-- Price --+");

            int[] arrayFood = new int[4];
            arrayFood[0] = 1;
            arrayFood[1] = 2;
            arrayFood[2] = 3;

            for (int i = 1; i <= choice; i++) {

                System.out.printf("|  %10s \t\t| %5d\t | %7d\t  |%n", numTOstring(arrayFood[menuSKE - 1]), quantity, priceEachMenu(menuSKE, quantity));
            }
            System.out.println("-------------------------------------------");
            System.out.printf("| Total\t\t\t\t\t\t | %10d |%n", totalPriceMenu(menuSKE, quantity));
            System.out.println("-------------------------------------------");
        }
        return true;

    }




    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int[][] menu = new int[3][2];

//        [name][quantity]

        System.out.println("--------- Welcome to SKE Restaurant ---------");
        System.out.printf("1.) Pizza\t\t %5d Bath.%n" , 250);
        System.out.printf("2.) Chickens\t %5d Bath.%n" , 120);
        System.out.printf("3.) Coke\t\t %5d Bath.%n" , 45);
        System.out.println("4.) Total");
        System.out.println("5.) Exit");
        int menuSKE;
        int quantity = 0;
        int choice = 0;
        int[] arrayFood = new int[4];
        arrayFood[0] = 1;
        arrayFood[1] = 2;
        arrayFood[2] = 3;

//        do{
//
//            showTotal(quantity , choice);
//                System.out.print("Enter Quantity: ");
//                quantity = scan.nextInt();
//
//
//
//
//
//
//            choice++;
//
//
//            System.out.println("");
//
//
//
//        }while(menuSKE == 1 || menuSKE == 2 ||menuSKE == 3 || menuSKE == 4);
    }
}
