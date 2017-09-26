package com.company;
//elab-source:RobotMoving.java
import java.util.Scanner;
public class RobotMoving {
    static String command;
    static int north = 0 , south = 0 , east = 0 , west = 0;

    public static int period(String command){
        int countPeriod = 0;
        char period = 0;
        for(int x = 0 ; x <= command.length() - 1 ; x++){
            if(x == 0){
                countPeriod++;
                period = command.charAt(x);

            }else{
                if(command.charAt(x) != period){
                    period = command.charAt(x);
                    countPeriod++;
                }
            }
        }return countPeriod;
    }

    public static int countrepeat(String command){
        int countRepeat = 0;
        char repeat = 0;
        while(countRepeat == 0 || repeat == command.charAt(countRepeat)){
            repeat = command.charAt(countRepeat);
            countRepeat++;
            if(command.charAt(countRepeat) == '#') break;
        }
        RobotMoving.command = command.substring(countRepeat);
        return countRepeat;
    }

    public static void moving(char command , int repeat){

        switch (command){
            case 'n':
                north+=repeat;
                System.out.printf("Move to North %d unit(s).%n" , repeat);
                break;
            case 's':
                south+=repeat;
                System.out.printf("Move to South %d unit(s).%n" , repeat);
                break;
            case 'e':
                east+=repeat;
                System.out.printf("Move to East %d unit(s).%n" , repeat);
                break;
            case 'w':
                west+=repeat;
                System.out.printf("Move to West %d unit(s).%n" , repeat);
                break;

        }

    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Input String command for robot: ");
        command = scan.next().toLowerCase() + "#";
        int commandPeriod = period(command);

        System.out.println("Robot will");
        for(int i = 0 ; i < commandPeriod - 1 ; i++) {
            moving(command.charAt(0), countrepeat(command));
        }
        System.out.printf("Final robot location: x = %d, y = %d." , east - west , north - south);

    }

}
