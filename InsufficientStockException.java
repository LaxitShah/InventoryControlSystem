package j2ee.lab01;

public class InsufficientStockException extends Exception {
    private int itemCode;
    private int availableStock;

    public InsufficientStockException(int itemCode, int availableStock) {
        this.itemCode = itemCode;
        this.availableStock = availableStock;
    }

    public int getItemCode() {
        return itemCode;
    }

    public int getAvailableStock() {
        return availableStock;
    }
}