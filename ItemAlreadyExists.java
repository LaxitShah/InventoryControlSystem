package j2ee.lab01;

public class ItemAlreadyExists extends Exception {
    private int itemCode;

    public ItemAlreadyExists(int itemCode) {
        this.itemCode = itemCode;
    }

    public int getItemCode() {
        return itemCode;
    }
}

