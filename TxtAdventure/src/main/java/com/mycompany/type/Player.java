package com.mycompany.type;

import com.mycompany.items.Enemy;
import com.mycompany.items.Item;
import com.mycompany.parser.Command;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Player {

    private Room currentRoom;
    private int health;
    private Enemy enemy;
    private Inventory inventory;

    public Player() {
        health = 15;
        setEnemy(new Enemy());
        inventory = new Inventory();
    }

    /**
     * @return stanza corrente
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void dealDamage(int damage) {
        this.health -= damage;
    }

    public void heal(int healAmount) {
        this.health += healAmount;
    }

    public int showHealth() {
        return health;
    }

    public void IsAlive() {
        if (health <= 0) {     //Se la vita del personaggio scende a 0 muore
            System.out.println("\nStavi impiegando troppo tempo e sei morto a causa della perdita di sangue...\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("@@@@@                                        @@@@@");
            System.out.println("@@@@@@@                                      @@@@@@@");
            System.out.println("@@@@@@@           @@@@@@@@@@@@@@@            @@@@@@@");
            System.out.println(" @@@@@@@@       @@@@@@@@@@@@@@@@@@@        @@@@@@@@");
            System.out.println("     @@@@@     @@@@@@@@@@@@@@@@@@@@@     @@@@@");
            System.out.println("       @@@@@  @@@@@@@@@@@@@@@@@@@@@@@  @@@@@");
            System.out.println("         @@  @@@@@@@@@@@@@@@@@@@@@@@@@  @@");
            System.out.println("            @@@@@@@    @@@@@@    @@@@@@");
            System.out.println("            @@@@@@      @@@@      @@@@@");
            System.out.println("            @@@@@@      @@@@      @@@@@");
            System.out.println("             @@@@@@    @@@@@@    @@@@@");
            System.out.println("              @@@@@@@@@@@  @@@@@@@@@@");
            System.out.println("               @@@@@@@@@@  @@@@@@@@@");
            System.out.println("           @@   @@@@@@@@@@@@@@@@@   @@");
            System.out.println("           @@@@  @@@@ @ @ @ @ @@@@  @@@@");
            System.out.println("          @@@@@   @@@ @ @ @ @ @@@   @@@@@");
            System.out.println("        @@@@@      @@@@@@@@@@@@@      @@@@@");
            System.out.println("      @@@@          @@@@@@@@@@@          @@@@");
            System.out.println("   @@@@@              @@@@@@@              @@@@@");
            System.out.println("  @@@@@@@                                 @@@@@@@");
            System.out.println("   @@@@@                                   @@@@@");
            System.exit(0);
        }
    }

    public boolean isAlive() {
        if (health <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return inventario
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * @param inventory
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    private String getItemString() {
        String returnString = "";
        Set<String> keys = inventory.getInventory().keySet();
        for (String string : keys) {
            returnString += " " + string;
        }
        return returnString;
    }

    public Item getItem(String key) {
        return (Item) inventory.getInventory().get(key);
    }

    public void takeItem(Command command) {

        if (!command.hasSecondWord()) {
            // Se non viene specificato cosa raccogliere
            System.out.println("Non ho capito...indica quale oggetto prendere.");
            return;
        }

        String item = command.getSecondWord();

        Item takenItem = getCurrentRoom().getItem(item);

        if (takenItem == null) {
            System.out.println("Non esiste questo articolo qui.");
        } else {
            if (getCurrentRoom().getInventory().containsItem(takenItem) != null) {
                System.out.println("Hai preso: " + getCurrentRoom().getOnlyItem());
                System.out.println(getCurrentRoom().getItemDescString());
                String key = getCurrentRoom().getInventory().containsItem(takenItem);
                getCurrentRoom().getInventory().removeItem(takenItem);
                getInventory().addItem(key, takenItem);

            }
        }
    }

    public void dropItem(Command command) {

        if (!command.hasSecondWord()) {
            // Se non viene specificato cosa lasciare...
            System.out.println("Non ho capito...indica quale oggetto lasciare.");
            return;
        }

        String item = command.getSecondWord();

        Item takenItem = getItem(item);

        if (takenItem == null) {
            System.out.println("Non esiste questo articolo qui.");
        } else {
            if (getInventory().containsItem(takenItem) != null) {
                String key = getInventory().containsItem(takenItem);
                getInventory().removeItem(takenItem);
                getCurrentRoom().getInventory().addItem(key, takenItem);
            }
            System.out.println("Hai lasciato cadere: " + item);
        }
    }

    public void useItem(Command command) {

        if (!command.hasSecondWord()) {
            // Se non viene specificato cosa usare...
            System.out.println("Usa cosa?");
            return;
        }

        String item = command.getSecondWord();

        Item usedItem = getItem(item);

        if (usedItem == null) {
            System.out.println("Non hai questo oggetto.");
            return;
        }
        usedItem.useItem(command, this);

    }

    /**
     * @return nemico
     */
    public Enemy getEnemy() {
        return enemy;
    }

    /**
     * @param enemy
     */
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public void printInv() {
        inventory.printInventory();
    }
}
