package com.mycompany.items;

import com.mycompany.parser.Command;
import com.mycompany.type.Player;
import com.mycompany.type.Room;

public class Knife extends Item {

    public Knife(String itemDescription, int weigth) {
        super(itemDescription, weigth);

    }

    public void useKnife(Player player) {
        if (player.getCurrentRoom().getName().equalsIgnoreCase("cucina")) {
            Room current = player.getCurrentRoom();
            Room room = current.getExit("nord");
            if (player.getEnemy().isAlive() == true) {
                System.out.println("Smetti di pugnalarlo, è già morto!!");
            } else {
                System.out.println("Hai pugnalato l'uomo...ma respira ancora.");
                player.getEnemy().dealDamageEnemy(5);
                player.getEnemy().isAlive();
                if (player.getEnemy().isAlive() == true) {
                    System.out.println("Nel suo ultimo respiro ti colpisce, perdi 5 punti salute.");
                    player.dealDamage(5);
                    if (player.isAlive() == true) {
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
                    System.out.println("E' morto... ora puoi scappare.");
                    if (room.getName().equalsIgnoreCase("esterno")) {
                        room.setLocked1(false);
                    }
                }
            }

        } else {
            System.out.println("Perché diavolo dovresti usare un coltello qui!?");
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
        if ("coltello".equals(key)) {
            useKnife(player);
        }

    }

    @Override
    public String getDesc() {
        return itemDescription;
    }

}
