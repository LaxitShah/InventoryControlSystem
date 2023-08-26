package j2ee.lab01;
import java.util.Scanner;

public class ItemTester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        Item item1 = createItem(scanner);
        Item item2 = createItem(scanner);
        Item item3 = createItem(scanner);


        displayItemInfo(item1);
        displayItemInfo(item2);
        displayItemInfo(item3);

        addStock(scanner, item1);
        setCost(scanner, item2);
        withdrawStock(scanner, item3);

        displayItemInfo(item1);
        displayItemInfo(item2);
        displayItemInfo(item3);

        scanner.close();
    }

    public static Item createItem(Scanner scanner) {
        System.out.println("Enter Item Code: ");
        int itemCode = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        System.out.println("Enter Item Description: ");
        String description = scanner.nextLine();

        System.out.println("Enter Quantity in Stock: ");
        int qtyInStock = scanner.nextInt();

        System.out.println("Enter Minimum Quantity in Stock: ");
        int minQtyInStock = scanner.nextInt();

        System.out.println("Enter Cost: ");
        double cost = scanner.nextDouble();

        return new Item(itemCode, description, qtyInStock, minQtyInStock, cost);
    }

    public static void displayItemInfo(Item item) {
        System.out.println("Item Code: " + item.getItemCode());
        System.out.println("Item Description: " + item.getDescription());
        System.out.println("Cost: $" + item.getCost());
        System.out.println("Quantity in Stock: " + item.getQtyInStock());
        System.out.println("Minimum Quantity in Stock: " + item.getMinQtyInStock());
        System.out.println("Under Stock: " + item.isUnderStock());
        System.out.println();
    }

    public static void addStock(Scanner scanner, Item item) {
        System.out.println("Enter quantity to add to stock: ");
        int quantityToAdd = scanner.nextInt();
        item.addStock(quantityToAdd);
    }

    public static void setCost(Scanner scanner, Item item) {
        System.out.println("Enter new cost: ");
        double newCost = scanner.nextDouble();
        item.setCost(newCost);
    }

    public static void withdrawStock(Scanner scanner, Item item) {
        System.out.println("Enter quantity to withdraw from stock: ");
        int quantityToWithdraw = scanner.nextInt();
        try {
            item.withdrawStock(quantityToWithdraw);
        } catch (InsufficientStockException e) {
            System.out.println("Insufficient stock for item with code " + e.getItemCode()
                    + ". Available stock: " + e.getAvailableStock());
        }
    }
}
