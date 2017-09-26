package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListMid {
    static Scanner scan = new Scanner(System.in);
    static List<Integer> elementArray = new ArrayList<Integer>();

    public static int max() {
        int max = 0;
        for (int x = 0; x < elementArray.size(); x++) {
            if (max < elementArray.get(x)) max = elementArray.get(x);
        }
        return max;
    }

    public static int min() {
        int min = elementArray.get(0);
        for (int x = 0; x < elementArray.size(); x++) {
            if (min > elementArray.get(x)) min = elementArray.get(x);
        }
        return min;
    }

    public static void newArray() {
        elementArray.clear();
        System.out.print("How many element? : ");
        int index = scan.nextInt();
        for (int i = 0; i < index; i++) {
            System.out.printf("Enter element %d :", i + 1);
            int n = scan.nextInt();
            elementArray.add(i, n);
        }
    }

    public static boolean checkRepeat() {
        boolean isRepeat = false;
        for (int x = 0; x < elementArray.size(); x++) {
            for (int y = 0; y < elementArray.size(); y++) {
                if (elementArray.get(x) == elementArray.get(y) && x != y) {
                    isRepeat = true;
                    break;
                }
            }
        }
        return isRepeat;
    }

    public static void removeNum(int remove) {
        int removeIndex = elementArray.indexOf(remove);
        elementArray.remove(removeIndex);
        System.out.print("New array is {");
        for (int x = 0; x < elementArray.size(); x++) {
            System.out.printf("%d ", elementArray.get(x));
        }
        System.out.println("}");
    }

    public static void sortNum() {
        List<Integer> newsortArray = new ArrayList<Integer>();
        int x = elementArray.get(0);
        int sort = elementArray.size();
        int min = 0;

        for (int i = 0; i < sort; i++) {
            min = min();

            newsortArray.add(i, min);
            int position = elementArray.indexOf(min);
            elementArray.remove(position);
        }
        elementArray.clear();
        elementArray.addAll(newsortArray);
    }


    public static void main(String[] args) {
        newArray();
        char menu = 0;
        while (menu != 'q') {
            System.out.println("Select menu : ");
            System.out.print("ma(x), (m)in, (n)ew, n(o)t repeat, (r)omove, (s)ort, (q)uit : ");
            menu = scan.next().charAt(0);
            switch (menu) {
                case 'x':
                    System.out.printf("Max is %d", max());
                    System.out.println();
                    break;
                case 'm':
                    System.out.printf("Min is %d", min());
                    System.out.println();
                    break;
                case 'n':
                    newArray();
                    break;
                case 'o':
                    for (int x = 0; x < elementArray.size(); x++) {
                        System.out.printf("%d ", elementArray.get(x));
                    }
                    if (checkRepeat()) System.out.print("is repeated");
                    else System.out.print("is not repeated");
                    System.out.println();
                    break;
                case 's':
                    sortNum();
                    for (int x = 0; x < elementArray.size(); x++) {
                        System.out.printf("%d ", elementArray.get(x));
                    }
                    System.out.println();
                    break;
                case 'r':
                    System.out.print("Enter a number to be remove : ");
                    int remove = scan.nextInt();
                    removeNum(remove);
                    System.out.println();
            }
        }
        System.out.print("Bye bye!");
    }
}

