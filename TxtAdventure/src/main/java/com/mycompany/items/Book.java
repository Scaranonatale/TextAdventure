package com.mycompany.items;

import com.mycompany.parser.Command;
import com.mycompany.type.Player;
import com.mycompany.type.Room;

public class Book extends Item {

    public Book(String itemDescription, int weigth) {
        super(itemDescription, weigth);

    }

    public void useBook(Player player) {
        if (player.getCurrentRoom().getName().equalsIgnoreCase("ufficio")) {
            System.out.println("C'è stato un incantesimo sul libro...ti sei bruciato.");
            System.out.println("perché il libro ti ha bruciato la mano... l'hai lasciato cadere.");
            player.dealDamage(5);
            System.out.println("Salute: " + player.showHealth());
            Room current = player.getCurrentRoom();
            Room room = current.getExit("ovest");
        } else {
            System.out.println("");
        }

    }

    
    @Override
    public String getDesc(){
    return itemDescription;
    }
    
    @Override
    public void useItem(Command command, Player player) {
        String key = player.getInventory().containsItem(this);
        if ("libro".equals(key)) {
            useBook(player);
        }

    }
}
