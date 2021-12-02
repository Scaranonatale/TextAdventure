package com.mycompany.items;

import com.mycompany.parser.Command;
import com.mycompany.type.Player;
import com.mycompany.type.Room;

public class Shovel extends Item {

    public Shovel(String itemDescription, int weigth) {
        super(itemDescription, weigth);

    }

    public void useShovel(Player player) {
        if (player.getCurrentRoom().getName().equalsIgnoreCase("mensa")) {
            System.out.println("Hai abbattuto la porta con la pala.");
            Room current = player.getCurrentRoom();
            Room room = current.getExit("nord");
            if (room.getName().equalsIgnoreCase("cucina")) {
                room.setLocked(false);
            }
        } else {
            System.out.println("Non puoi usare la pala qui...Cosa vuoi fare? Scavare o qualcosa del genere.");
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
        if ("pala".equals(key)) {
            useShovel(player);
        }

    }

    @Override
    public String getDesc(){
    return itemDescription;
    }

}
