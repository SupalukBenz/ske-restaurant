package com.company;
import java.util.Scanner;

public class preQuiz {

    public static char grade(double score){
        char grade = 0;
        if(score >= 80) grade = 'A';
        else if(score >= 70) grade = 'B';
        else if(score >= 60) grade = 'C';
        else if(score >= 50) grade = 'D';
        else if(score < 50) grade = 'F';

        return grade;
    }

    public static void main (String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.println("How many room?");
        int menyRoom = scan.nextInt();

        System.out.println("How many student?");
        int menyStudent = scan.nextInt();



        String name[][] = new String[menyRoom][menyStudent];
        double score[][] = new double[menyRoom][menyStudent];



        for(int i = 1 ; i <= menyRoom ; i++){
            System.out.println("Room " + i);
            for(int x = 1 ; x <= menyStudent ; x++){
                System.out.printf("Enter student %d name :%n" , x);
                name[i - 1][x - 1] = scan.next();

                System.out.printf("Enter student %d score :%n" , x);
                score[i - 1][x - 1] = scan.nextDouble();

            }
        }
        double sum[] = new double[menyRoom];

        for(int x = 1 ; x <= menyRoom ; x++){
            System.out.printf("\t================ Room %d ================%n" ,x);
                for(int i = 1 ; i <= ((menyRoom * menyStudent)/menyRoom) ; i++){
                    System.out.printf("%s, score = %.0f, grade = %c%n" , name[x - 1][i - 1] , score[x - 1][i - 1] , grade(score[x - 1][i - 1]));
                    sum[x-1] += score[x-1][i-1];
                }
                System.out.printf("Sum of Room %d = %.0f%n" , x , sum[x-1]);
            if(x == menyRoom){
                System.out.println("======================================Means======================================");
                for(int y = 1 ; y <= menyRoom ; y++){
                    System.out.printf("Mean score of Room %d = %.2f%n" , y , sum[y-1]/menyStudent);
                }
            }
        }


        
    }





}
