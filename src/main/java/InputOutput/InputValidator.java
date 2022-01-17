package InputOutput;

public class InputValidator implements Input {

    private final Input input;

    public InputValidator(Input input) {
        this.input = input;
    }

    @Override
    public String getString(String message) {
        return this.input.getString(message);
    }

    @Override
    public int getMenuItem(String message, int range) {
        int item = -1;
        do {
            try {
                item = this.input.getMenuItem(message, range);
            } catch (OutOfMenuIndexException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException nfe) {
                System.out.println("Введите число!");
            }
        } while (item < 1 || item > range);
        return item;
    }
}
