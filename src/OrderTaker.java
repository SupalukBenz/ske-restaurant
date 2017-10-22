package src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderTaker {
    public static void main(String[] args) throws IOException {
        RestaurantManager.init();

        Scanner scanner = new Scanner(System.in);
        int orderNumSize = RestaurantManager.getMenuItems().length;
        int[] order = new int[orderNumSize];
        int totalOrder = 0;
        double total = 0;
        System.out.printf("-----------Welcome to SKE-Restaurant-------------%n");

        for (int i = 0; i < orderNumSize; i++) {
            System.out.printf("|%d.)%20s    %10.1f Bath\t\t|%n", i + 1, RestaurantManager.getMenuItems()[i], RestaurantManager.getPrices()[i]);
        }
        System.out.printf("|%d.) TOTAL  \t\t\t\t\t\t\t\t\t|%n", RestaurantManager.getMenuItems().length + 1);
        System.out.printf("|%d.) EXIT \t\t\t\t\t\t\t\t\t\t|%n", RestaurantManager.getMenuItems().length + 2);
        System.out.println("-------------------------------------------------");

        System.out.println();
        ArrayList<Integer> orderNum = new ArrayList<Integer>();

        while (true) {
            double qty = 0;
            System.out.println("Enter your choice : ");
            int orderNumber = scanner.nextInt();

            if (orderNumber <= orderNumSize) {
                orderNum.add(orderNumber);
                System.out.println("Enter quantity : ");
                qty = scanner.nextInt();
                order[orderNumber - 1] += qty;
                totalOrder++;

            } else if (orderNumber == orderNumSize + 1) {
                for (int i = 0; i < orderNumSize; i++) {
                    total += RestaurantManager.getPrices()[i] * order[i];
                }
                for (int i = 0; i < orderNum.size(); i++) {
                    RestaurantManager.recordOrder(orderNumSize, order, total);
                }
                File fileLog = new File("src/data/salesLog.log");
                Scanner scanLog = new Scanner(fileLog);
                while(scanLog.hasNextLine()){
                    System.out.println(scanLog.nextLine());
                }


            } else if (orderNumber == orderNumSize + 2) {
                System.out.println("Exit");
                break;
            }
        }
    }
}
