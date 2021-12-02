package com.mycompany.items;

import com.mycompany.parser.Command;
import com.mycompany.type.Player;

public class HealthPotion extends Item {

    private boolean usedHealth;

    public HealthPotion(String itemDescription, int weigth) {
        super(itemDescription, weigth);
        usedHealth = true;

    }

    public void useHealthPotion(Player player) {
        if (usedHealth == true) {
            player.heal(5);
            System.out.println("Ti sei curato per 5 punti salute.");
            System.out.println("Salute: " + player.showHealth());
            usedHealth = false;
        } else {
            System.out.println("Non puoi guarire con una pozione vuota... ");
        }

    }

    /**
     *
     * @param command
     * @param player
     */
    @Override
    public void useItem(Command command, Player player) {
        String key = player.getInventory().containsItem(this);
        if ("pozione".equals(key)) {
            useHealthPotion(player);
        }
    }
    
    
    @Override
    public String getDesc(){
    return itemDescription;
    }
}
