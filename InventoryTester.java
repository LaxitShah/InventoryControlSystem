package j2ee.lab01;

import java.util.ArrayList;
import java.util.Scanner;

public class InventoryTester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        while (true) {
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. Add Stock");
            System.out.println("4. Withdraw Stock");
            System.out.println("5. Delete Item");
            System.out.println("6. View All Items");
            System.out.println("7. View Items Under Stock");
            System.out.println("8. Total Inventory Cost");
            System.out.println("9. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addItem(scanner, inventory);
                    break;
                case 2:
                    updateItem(scanner, inventory);
                    break;
                case 3:
                    addStock(scanner, inventory);
                    break;
                case 4:
                    withdrawStock(scanner, inventory);
                    break;
                case 5:
                    deleteItem(scanner, inventory);
                    break;
                case 6:
                    viewAllItems(inventory);
                    break;
                case 7:
                    viewItemsUnderStock(inventory);
                    break;
                case 8:
                    viewTotalInventoryCost(inventory);
                    break;
                case 9:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void addItem(Scanner scanner, Inventory inventory) {
        System.out.println("Enter Item Code: ");
        int itemCode = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Enter Item Description: ");
        String description = scanner.nextLine();

        System.out.println("Enter Quantity in Stock: ");
        int qtyInStock = scanner.nextInt();

        System.out.println("Enter Minimum Quantity in Stock: ");
        int minQtyInStock = scanner.nextInt();

        System.out.println("Enter Cost: ");
        double cost = scanner.nextDouble();

        Item item = new Item(itemCode, description, qtyInStock, minQtyInStock, cost);

        try {
            inventory.addItem(item);
            System.out.println("Item added successfully.");
        } catch (ItemAlreadyExists e) {
            System.out.println("Item with code " + e.getItemCode() + " already exists.");
        }
    }

    public static void updateItem(Scanner scanner, Inventory inventory) {
        System.out.println("Enter Item Code to update: ");
        int itemCode = scanner.nextInt();
        scanner.nextLine(); 

        try {
            Item existingItem = inventory.getItem(itemCode);
            System.out.println("Enter new Item Description: ");
            String description = scanner.nextLine();

            System.out.println("Enter new Quantity in Stock: ");
            int qtyInStock = scanner.nextInt();

            System.out.println("Enter new Minimum Quantity in Stock: ");
            int minQtyInStock = scanner.nextInt();

            System.out.println("Enter new Cost: ");
            double cost = scanner.nextDouble();

            Item updatedItem = new Item(itemCode, description, qtyInStock, minQtyInStock, cost);
            inventory.updateItem(updatedItem);
            System.out.println("Item updated successfully.");
        } catch (ItemNotFound e) {
            System.out.println("Item with code " + e.getItemCode() + " not found.");
        }
    }

    public static void addStock(Scanner scanner, Inventory inventory) {
        System.out.println("Enter Item Code to add stock: ");
        int itemCode = scanner.nextInt();
        scanner.nextLine(); 

        try {
            System.out.println("Enter quantity to add to stock: ");
            int quantityToAdd = scanner.nextInt();
            inventory.addStock(itemCode, quantityToAdd);
            System.out.println("Stock added successfully.");
        } catch (ItemNotFound e) {
            System.out.println("Item with code " + e.getItemCode() + " not found.");
        }
    }

    public static void withdrawStock(Scanner scanner, Inventory inventory) {
        System.out.println("Enter Item Code to withdraw stock: ");
        int itemCode = scanner.nextInt();
        scanner.nextLine(); 

        try {
            System.out.println("Enter quantity to withdraw from stock: ");
            int quantityToWithdraw = scanner.nextInt();
            inventory.withdrawStock(itemCode, quantityToWithdraw);
            System.out.println("Stock withdrawn successfully.");
        } catch (ItemNotFound e) {
            System.out.println("Item with code " + e.getItemCode() + " not found.");
        } catch (InsufficientStockException e) {
            System.out.println("Insufficient stock for item with code " + e.getItemCode()
                    + ". Available stock: " + e.getAvailableStock());
        }
    }

    public static void deleteItem(Scanner scanner, Inventory inventory) {
        System.out.println("Enter Item Code to delete: ");
        int itemCode = scanner.nextInt();
        scanner.nextLine(); 

        try {
            inventory.deleteItem(itemCode);
            System.out.println("Item deleted successfully.");
        } catch (ItemNotFound e) {
            System.out.println("Item with code " + e.getItemCode() + " not found.");
        }
    }

    public static void viewAllItems(Inventory inventory) {
        ArrayList<Item> items = inventory.getAllItems();
        if (items.isEmpty()) {
            System.out.println("No items in the inventory.");
        } else {
            for (Item item : items) {
                System.out.println(item.getItemCode());
            }
        }
    }

    public static void viewItemsUnderStock(Inventory inventory) {
        ArrayList<Item> underStockItems = inventory.getItemsUnderStock();
        if (underStockItems.isEmpty()) {
            System.out.println("No items under stock in the inventory.");
        } else {
            for (Item item : underStockItems) {
                System.out.println(item);
            }
        }
    }

    public static void viewTotalInventoryCost(Inventory inventory) {
        double totalCost = inventory.totalInventoryCost();
        System.out.println("Total Inventory Cost: " + totalCost);
    }
}
