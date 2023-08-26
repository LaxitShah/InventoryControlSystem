package j2ee.lab01;

public class ItemNotFound extends Exception {
    private int itemCode;

    public ItemNotFound(int itemCode) {
        this.itemCode = itemCode;
    }

    public int getItemCode() {
        return itemCode;
    }
}

