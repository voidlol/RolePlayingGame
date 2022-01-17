package InputOutput;

import java.util.Scanner;

public class ConsoleInput implements Input {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    @Override
    public int getMenuItem(String message, int range) {
        int item = -1;
        do {
            try {
                item = Integer.parseInt(getString(message));
            } catch (NumberFormatException e) {
                System.out.println("Введите число!");
            }
        } while (item < 1 && item >= range);
        return item;
    }
}
