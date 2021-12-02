package com.mycompany.type;

import com.mycompany.items.Item;
import java.util.HashMap;

public class Inventory {

    private HashMap<String, Item> inventory;
    private final int maxWeight;

    public Inventory() {
        inventory = new HashMap<>();
        maxWeight = 1;
    }

    public void addItem(String key, Item item) {
        inventory.put(key, item);
    }
    
    public Item getItem(String key) {
        return inventory.get(key);
    }

    public boolean removeItem(Item takenItem) {
        if (inventory.containsValue(takenItem)) {
            for (String key : inventory.keySet()) {
                if (inventory.get(key) == takenItem) {
                    inventory.remove(key, takenItem);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public HashMap<String, Item> getInventory() {
        return inventory;
    }

    public String containsItem(Item takenItem) {
        if (inventory.containsValue(takenItem)) {
            for (String key : inventory.keySet()) {
                if (inventory.get(key) != takenItem) {
                } else {
                    return key;
                }
            }
        }
        return null;
    }

    public void printInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Il tuo inventario è vuoto.");
        } else {
            System.out.println("Nel tuo inventario c'è: ");
            System.out.println(inventory.keySet());
        }
    }

}
