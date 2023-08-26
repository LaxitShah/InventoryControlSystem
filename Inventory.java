package j2ee.lab01;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private HashMap<Integer, Item> inventory;

    public Inventory() {
        inventory = new HashMap<Integer, Item>();
    }

    public Item getItem(int itemCode) throws ItemNotFound {
        Item item = inventory.get(itemCode);
        if (item == null) {
            throw new ItemNotFound(itemCode);
        }
        return new Item(item.getItemCode(), item.getDescription(), item.getQtyInStock(),
                item.getMinQtyInStock(), item.getCost());
    }

    public void addItem(Item item) throws ItemAlreadyExists {
        if (inventory.containsKey(item.getItemCode())) {
            throw new ItemAlreadyExists(item.getItemCode());
        }
        inventory.put(item.getItemCode(), item);
    }

    public void updateItem(Item item) throws ItemNotFound {
        if (!inventory.containsKey(item.getItemCode())) {
            throw new ItemNotFound(item.getItemCode());
        }
        inventory.put(item.getItemCode(), item);
    }

    public void addStock(int itemCode, int qty) throws ItemNotFound {
        Item item = getItem(itemCode);
        item.addStock(qty);
        inventory.put(itemCode, item);
    }

    public void withdrawStock(int itemCode, int qty) throws ItemNotFound, InsufficientStockException {
        Item item = getItem(itemCode);
        item.withdrawStock(qty);
        inventory.put(itemCode, item);
    }

    public void deleteItem(int itemCode) throws ItemNotFound {
        if (!inventory.containsKey(itemCode)) {
            throw new ItemNotFound(itemCode);
        }
        inventory.remove(itemCode);
    }

    public ArrayList<Item> getAllItems() {
        return new ArrayList<Item>(inventory.values());
    }

    public ArrayList<Item> getItemsUnderStock() {
        ArrayList<Item> underStockItems = new ArrayList<Item>();
        for (Item item : inventory.values()) {
            if (item.isUnderStock()) {
                underStockItems.add(item);
            }
        }
        return underStockItems;
    }

    public double totalInventoryCost() {
        double totalCost = 0.0;
        for (Item item : inventory.values()) {
            totalCost += (item.getCost() * item.getQtyInStock());
        }
        return totalCost;
    }
}
