package Game;

public enum Items {
    POTION(25);

    int price;

    Items(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
