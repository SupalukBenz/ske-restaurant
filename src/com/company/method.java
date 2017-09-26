package com.company;
import java.util.Scanner;
public class method {

    public static int a(int num) {
        System.out.println(num + " in a");
        d(num);
        System.out.println("get " + num + " in a");
        return num;
    }

    public static int b(int num) {
        System.out.println(num + " in b");
        int tempNum = num;
        for (int i = 0; i < tempNum; i++) {
            System.out.print(num+" ");
            num--;
        }
        System.out.println("\nget " + num + " in b");
        return num;
    }

    public static int c(int num) {
        System.out.println(num + " in c");
        int newNum = e(num);
        if (newNum == 0) {
            System.out.println("num is 0");
        }
        System.out.println("get " + newNum + " in c");
        return num + newNum;
    }

    public static int d(int num) {
        System.out.println(num + " in d");
        num += 2;
        num = b(num);
        System.out.println("get " + num + " in d");
        return c(num);
    }

    public static int e(int num) {
        System.out.println(num + " in e");
        if (num >= 4) {
            System.out.println("greater than 4");
            return num*2;
        } else {
            System.out.println("less than 4");
            return 0;
        }
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        for(int i = 1 ; i <= 10 ; i++){
            System.out.println(i);
            a(i);
            System.out.println("");
            b(i);
            System.out.println("");
            c(i);
            System.out.println("");
            d(i);
            System.out.println("");
            e(i);
            System.out.println("");


        }
    }

}
