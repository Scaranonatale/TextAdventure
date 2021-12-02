package com.mycompany.type;

import com.mycompany.items.HealthPotion;
import com.mycompany.items.Item;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/*
 * Una "Stanza" rappresenta una posizione nello scenario del gioco. 
 *È collegato ad altre stanze tramite le uscite. Per ogni 
 *uscita esistente, la stanza memorizza un riferimento alla stanza vicina.
 */
public class Room {

    private final Inventory inventory;
    private final Item item;
    private final String description;
    private boolean locked;
    private boolean locked1;
    private final HashMap<String, Room> exits;        // memorizza le uscite di questa stanza.
    private final String name;

    /**
     * Crea una stanza con "descrizione".Inizialmente, non ha
     * uscite."Descrizione" è qualcosa come "in una cucina" o "in un cortile
     * aperto".
     *
     * @param name
     * @param description
     */
    public Room(String name, String description) {
        this.name = name;
        inventory = new Inventory();
        item = new HealthPotion("", 0);
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Definisce un'uscita dalla stanza.
     *
     * @param direction
     * @param neighbor
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * Restituisce la descrizione della stanza (quello che è stato definito nel
     * costruttore)
     *
     * @return
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * Restituisce una lunga descrizione, nella forma: Sei nella cucina, con le
     * varie uscite.
     *
     * @return
     */
    public String getLongDescription() {
        return "Sei " + description + ".\n" + getExitString();
    }

    public String getItemInRoom() {
        return "In questa stanza è presente l'oggetto: " + getItemString();
    }

    public String getName() {
        return this.name;
    }

    public String getItemDesc() {
        return item.itemDescription;
    }

    public String getOnlyItem() {
        return getItemString();
    }

    /**
     * Restituisce una stringa che descrive le uscite della stanza, per esempio
     * "Direzioni possibili: nord ovest".
     */
    private String getExitString() {
        String returnString = "Direzioni possibili: ";
        Set<String> keys = exits.keySet();
        for (Iterator<String> iter = keys.iterator(); iter.hasNext();) {
            returnString += "   " + iter.next();
        }
        return returnString;
    }

    private String getItemString() {
        String returnString = "";
        Set<String> keys = inventory.getInventory().keySet();
        for (String string : keys) {
            returnString += " " + string;
        }
        return returnString;
    }

    public String getItemDescString() {
        String returnItemDescString = "";
        Set<String> itemDesc = inventory.getInventory().keySet();
        for (String string : itemDesc) {
            Item obj= inventory.getItem(string);
            returnItemDescString = obj.itemDescription;
        }
        return returnItemDescString;
    }

    public Item getItem(String key) {
        return (Item) inventory.getInventory().get(key);
    }

    /**
     * Restituisce la stanza che si raggiunge.Se non c'è una stanza in quella
     * direzione, restituisce null.
     *
     * @param direction
     * @return
     */
    public Room getExit(String direction) {
        return (Room) exits.get(direction);
    }

    /**
     * @return inventario
     */
    public Inventory getInventory() {
        return inventory;
    }

    public void setLocked(boolean state) {
        locked = state;
    }

    public boolean getLocked() {
        return locked;
    }

    /**
     * @return locked
     */
    public boolean getLocked1() {
        return locked1;
    }

    /**
     * @param locked1
     */
    public void setLocked1(boolean locked1) {
        this.locked1 = locked1;
    }

}
