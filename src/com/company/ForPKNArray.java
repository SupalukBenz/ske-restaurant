package com.company;
import java.util.Scanner;
public class ForPKNArray {

    public static int max(int max, int currentNum){
        if(currentNum > max) return currentNum;
        else return max;
    }

    public static int min(int min , int curenNum){
        if(curenNum < min) return curenNum;
        else return min;
    }




    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int count = 0;
        System.out.print("Index : ");
        int index = scan.nextInt();
        int indexArrays[] = new int[index];
        int sum = 0;

        int max = 0;
        int min = 0;
        for(int x = 0; x<index; x++ ){
            System.out.println("Number : ");
            indexArrays[x] = scan.nextInt();
            count++;

            sum += indexArrays[x];

            max = max(max, indexArrays[x]);
            min = min(indexArrays[0] , indexArrays[x] );


        }

        System.out.printf("Max number is : %d%n" , max);
        System.out.printf("Min number is : %d%n" , min);

        System.out.printf("Total : %d" ,sum);




    }
}
