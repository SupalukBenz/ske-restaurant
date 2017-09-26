package com.company;
import java.util.Scanner;
import java.util.Random;



public class GuessingNumberQuiz3 {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();


        String play = "";
        while (!play.equalsIgnoreCase("Q")) {
            int n = rand.nextInt(999) + 0;
            System.out.println("Note: the random number is " + n);
            int guess = 0;
            int count = 0;
            while (guess != n) {
                System.out.print("Guess a number(0-999): ");
                guess = scan.nextInt();
                count++;
                if (guess == n) {
                    System.out.println("Correct Well done!");
                    System.out.println("Total tries: " + count);
                    break;

                } else {
                    if (guess < n) System.out.printf("Less than(Tries:%d)%n", count);
                    else if (guess > n) System.out.printf("More than(Tries:%d)%n", count);
                }


            }
            System.out.println("----------------");
            System.out.print("(P)lay again, (Q)uit:");
            play = scan.next();

        }
        System.out.print("Bye Bye!");
    }

}
