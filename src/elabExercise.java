//elab-source: RobotDirection.java
import java.util.Scanner;

class elabExcercise {
    static String command;
    static int north = 0, south = 0, east = 0, west = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input String command for robot: ");
        command = in.next().toLowerCase() + "@";
        int commandAmount = commandSplitCount(command);
        System.out.println("Robot will");
        for (int i = 0; i < commandAmount - 1; i++) {
            directionSummary(command.charAt(0), addUpDirection(command));
        }
        System.out.printf("Final robot location: x = %d, y = %d.", east - west, north - south);
    }


    public static void directionSummary(char direction, int addUp) {
        switch (direction) {
            case 'n' :
                System.out.printf("Move to North %d unit(s).%n", addUp);
                north += addUp;
                break;
            case 's' :
                System.out.printf("Move to South %d unit(s).%n", addUp);
                south += addUp;
                break;
            case 'e' :
                System.out.printf("Move to East %d unit(s).%n", addUp);
                east += addUp;
                break;
            case 'w' :
                System.out.printf("Move to West %d unit(s).%n", addUp);
                west += addUp;
                break;
        }
    }

    public static int commandSplitCount(String command) {
        int count = 0;
        char holder = 0;
        for (int i = 0; i < command.length(); i++) {
            if (i == 0) {
                count++;
                holder = command.charAt(i);
            } else {
                if (command.charAt(i) != holder) {
                    count++;
                    holder = command.charAt(i);
                }
            }
        }
        return count;
    }

    public static int addUpDirection(String command) {
        int count = 0;
        char holder = 0;
        while (count == 0 || holder == command.charAt(count)) {
            holder = command.charAt(count);
            count++;
            if (command.charAt(count) == '@') break;
        }
        elabExcercise.command = command.substring(count);
        return count;
    }

}