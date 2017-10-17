import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

    public class SKERestaurant {

        static Scanner scan = new Scanner(System.in);
        static int pizzQtotal, chicQtotal, totalPrice = 0;
        static int coke, coffee, greenTea, orange = 0;
        static int[][] menuArray = new int[6][2];
        static Random rand = new Random();

        static int coupon[] = new int[]{10, 20, 50};
        static int couponRand = rand.nextInt(3) + 1;


        public static void menu() {
            System.out.println("----------Welcome to SKE Restaurant----------");
            System.out.printf("|1.) Pizza\t\t %5d Bath.\t\t\t\t|%n", 250);
            System.out.printf("|2.) Chickens\t %5d Bath.\t\t\t\t|%n", 120);
            System.out.printf("|3.) Water Refill %4d Bath.\t\t\t\t|%n", 45);
            System.out.println("|\t - Coke\t\t\t\t\t\t\t\t\t|");
            System.out.println("|\t - Green Tea\t\t\t\t\t\t\t|");
            System.out.println("|\t - Black Coffee\t\t\t\t\t\t\t|");
            System.out.println("|\t - Orange Juice\t\t\t\t\t\t\t|");
            System.out.println("|4.) Total\t\t\t\t\t\t\t\t\t|");
            System.out.println("|5.) Exit <Promotion for KU student>\t\t|");
            System.out.println("---------------------------------------------");
            System.out.println("");
        }

        public static void menuWater() {
            System.out.println("-----------------------------");
            System.out.println("|\tMenu Water Refill\t\t|");
            System.out.println("|\t\t1.) Coke\t\t\t|");
            System.out.println("|\t\t2.) Green tea\t\t|");
            System.out.println("|\t\t3.) Black Coffee\t|");
            System.out.println("|\t\t4.) Orange Juice\t|");
            System.out.println("-----------------------------");

            System.out.print("Water Refill(45 Bath): ");
        }

        public static void program() {
            int choiceMenu = 0;
            while (choiceMenu != 5) {
                System.out.print("Enter your choice : ");
                choiceMenu = scan.nextInt();
                if (choiceMenu == 5) {
                    break;
                } else if (choiceMenu == 1 || choiceMenu == 2) {
                    System.out.print("Enter quantity : ");
                    int quantity = scan.nextInt();
                    if (choiceMenu == 1) {
                        pizzQtotal += quantity;
                    } else if (choiceMenu == 2) {
                        chicQtotal += quantity;
                    }
                    menuArray[0][0] = pizzQtotal;
                    menuArray[0][1] = pizzQtotal * 250;
                    menuArray[1][0] = chicQtotal;
                    menuArray[1][1] = chicQtotal * 120;
                    menuArray[2][0] = coke;
                    menuArray[2][1] = coke * 45;
                    menuArray[3][0] = greenTea;
                    menuArray[3][1] = greenTea * 45;
                    menuArray[4][0] = coffee;
                    menuArray[4][1] = coffee * 45;
                    menuArray[5][0] = orange;
                    menuArray[5][1] = orange * 45;
                } else if (choiceMenu == 3) {
                    menuWater();
                    int water = scan.nextInt();
                    System.out.print("Enter quantity : ");
                    int quantity = scan.nextInt();
                    if (water == 1) {
                        coke += quantity;
                    } else if (water == 2) {
                        greenTea += quantity;
                    } else if (water == 3) {
                        coffee += quantity;
                    } else if (water == 4) {
                        orange += quantity;
                    }
                    menuArray[0][0] = pizzQtotal;
                    menuArray[0][1] = pizzQtotal * 250;
                    menuArray[1][0] = chicQtotal;
                    menuArray[1][1] = chicQtotal * 120;
                    menuArray[2][0] = coke;
                    menuArray[2][1] = coke * 45;
                    menuArray[3][0] = greenTea;
                    menuArray[3][1] = greenTea * 45;
                    menuArray[4][0] = coffee;
                    menuArray[4][1] = coffee * 45;
                    menuArray[5][0] = orange;
                    menuArray[5][1] = orange * 45;
                } else if (choiceMenu == 4) {
                    table();
                }

            }

        }

        public static void table() {
            System.out.println("+------- Menu ------+-- Qty --+-- Price --+");
            if (pizzQtotal > 0) {
                System.out.printf("|%s \t\t\t\t| %5d\t | %7d\t  |%n", "Pizza", menuArray[0][0], menuArray[0][1]);
                totalPrice += menuArray[0][1];
            }

            if (chicQtotal > 0) {
                System.out.printf("|%s \t\t\t| %5d\t | %7d\t  |%n", "Chicken", menuArray[1][0], menuArray[1][1]);
                totalPrice += menuArray[1][1];
            }

            if (coke > 0) {
                System.out.printf("|%s \t\t\t\t| %5d\t | %7d\t  |%n", "Coke", menuArray[2][0], menuArray[2][1]);
                totalPrice += menuArray[2][1];
            }

            if (greenTea > 0) {
                System.out.printf("|%s \t\t\t| %5d\t | %7d\t  |%n", "Green Tea", menuArray[3][0], menuArray[3][1]);
                totalPrice += menuArray[3][1];
            }
            if (coffee > 0) {
                System.out.printf("|%s \t\t| %5d\t | %7d\t  |%n", "Black Coffee", menuArray[4][0], menuArray[4][1]);
                totalPrice += menuArray[4][1];
            }
            if (orange > 0) {
                System.out.printf("|%s \t\t| %5d\t | %7d\t  |%n", "Orange Juice", menuArray[5][0], menuArray[5][1]);
                totalPrice += menuArray[5][1];
            }


            System.out.println("-------------------------------------------");
            System.out.printf("| Total\t\t\t\t\t\t | %10d |%n", total(menuArray[0][1], menuArray[1][1], menuArray[2][1], menuArray[3][1], menuArray[4][1], menuArray[5][1]));
            System.out.println("-------------------------------------------");
            System.out.println("");


        }

        public static int total(int pizza, int chicken, int coke, int greenTea, int coffee, int orange) {
            int totalMenu = pizza + chicken + coke + greenTea + coffee + orange;
            return totalMenu;
        }

        public static String promotionMenu() {
            System.out.println("----------------------------------------------------------------------");
            System.out.printf("|\t\t\t%30s\t\t\t\t |%n", "+++++++ Promotion for September !! ++++++++");
            System.out.printf("|\t\t%30s\t\t\t\t |%n", "+++ This Promotion is only for KU Student!! +++");
            System.out.printf("|%s|%n", "SKE Restaurant will random a coupon(10% , 20% , 50%) for KU student.");
            System.out.println("----------------------------------------------------------------------");
            System.out.print("Are you KU student? (Y/N) : ");
            String pressKU = scan.next();
            return pressKU;
        }

        public static void promotionForKU(String pressKu) {
            int total = total(menuArray[0][1], menuArray[1][1], menuArray[2][1], menuArray[3][1], menuArray[4][1], menuArray[5][1]);
            if (pressKu.equalsIgnoreCase("Y")) {
                int kuRand = coupon[couponRand - 1];

                System.out.printf("You get a coupon %d%% discount!!%n", kuRand);

                int totalPay = total - (((total) * kuRand) / 100);

                System.out.println("-----------------------------------------");
                System.out.printf("|\t\t\t Total price = %5d \t\t|%n", totalPay);
                System.out.println("-----------------------------------------");
                System.out.print(" Cash customer pay : ");
                int payCustomer = scan.nextInt();

                if (payCustomer >= totalPay) {
                    System.out.println("-----------------------------------------");
                    System.out.printf("|\t Change        : %5d \t\t\t\t|%n", payCustomer - totalPay);
                    System.out.println("--------------Thanks you-----------------");
                } else {
                    int payCustomer1 = 0;
                    while (payCustomer1 < totalPay) {
                        System.out.println("|\t\t # Money is not enough ,");
                        System.out.print(" Cash customer pay : ");
                        payCustomer1 = scan.nextInt();

                    }
                    System.out.println("-----------------------------------------");
                    System.out.printf("|\t Change        : %5d \t\t\t\t|%n", payCustomer1 - totalPay);
                    System.out.println("--------------Thanks you-----------------");
                }
            } else {
                System.out.println("-----------------------------------------");

                System.out.printf("|\t\t\t Total price = %5d \t\t|%n", total);
                System.out.println("-----------------------------------------");
                System.out.print(" Cash customer pay : ");
                int payCustomer = scan.nextInt();
                if (payCustomer >= total) {
                    System.out.println("-----------------------------------------");
                    System.out.printf("|\t Change        : %5d \t\t\t\t|%n", payCustomer - total);
                    System.out.println("--------------Thanks you-----------------");
                } else {
                    int payCustomer2 = 0;
                    while (payCustomer2 < total) {
                        System.out.println("|\t\t # Money is not enough ,");
                        System.out.print(" Cash customer pay : ");
                        payCustomer2 = scan.nextInt();
                    }
                    System.out.println("-----------------------------------------");
                    System.out.printf("|\t Change        : %5d \t\t\t\t|%n", payCustomer2 - total);
                    System.out.println("--------------Thanks you-----------------");
                    System.out.println();
                }
            }
        }

        public static void main(String[] args) {
            menu();
            program();
            promotionForKU(promotionMenu());


        }

    }