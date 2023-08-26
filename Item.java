package j2ee.lab01;

public class Item {
    private int itemCode;
    private String description;
    private int qtyInStock;
    private int minQtyInStock;
    private double cost;

    public Item(int itemCode, String description, int qtyInStock, int minQtyInStock, double cost) {
        this.itemCode = itemCode;
        this.description = description;
        this.qtyInStock = qtyInStock;
        this.minQtyInStock = minQtyInStock;
        this.cost = cost;
    }

    public Item(int itemCode, String description, double cost) {
        this(itemCode, description, 0, 0, cost);
    }


    public int getItemCode() {
        return itemCode;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQtyInStock() {
        return qtyInStock;
    }

    public int getMinQtyInStock() {
        return minQtyInStock;
    }


    public void addStock(int qty) {
        qtyInStock += qty;
    }

    public void withdrawStock(int qty) throws InsufficientStockException {
        if (qtyInStock >= qty) {
            qtyInStock -= qty;
        } else {
            throw new InsufficientStockException(itemCode, qtyInStock);
        }
    }

    public boolean isUnderStock() {
        return qtyInStock < minQtyInStock;
    }
}
