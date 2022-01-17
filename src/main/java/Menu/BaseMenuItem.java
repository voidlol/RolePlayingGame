package Menu;

public abstract class BaseMenuItem implements UserAction {

    private int key;
    private String name;

    public BaseMenuItem(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("(%d) %s", this.key, this.name);
    }
}
