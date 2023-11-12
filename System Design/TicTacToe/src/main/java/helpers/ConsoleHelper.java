package helpers;

import java.util.Scanner;

public class ConsoleHelper {
    private static void print() {
        System.out.println("+----------------------------+");
        System.out.println("|   Welcome to Tic-Tac-Toe   |");
        System.out.println("+----------------------------+");
        System.out.println();
    }

    public static void printData(String data, boolean clear) {
        if (clear) {
            System.out.println();
            System.out.println("Hit Enter to Continue ...");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();

            cls();
            print();
        }

        System.out.println(data);
    }

    private static void cls() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            // Handle exceptions if necessary
        }
    }
}
