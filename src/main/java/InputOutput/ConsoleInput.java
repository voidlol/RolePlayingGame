package InputOutput;

import java.util.Scanner;

public class ConsoleInput implements Input {

    private final Scanner scanner = new Scanner(System.in, "CP866");

    @Override
    public String getString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    @Override
    public int getMenuItem(String message, int range) {
        int item = Integer.parseInt(this.getString(message));
        if (item > range) {
            throw new OutOfMenuIndexException(String.format("Введите число от 1 до %d!", range));
        }
        return item;
    }
}
