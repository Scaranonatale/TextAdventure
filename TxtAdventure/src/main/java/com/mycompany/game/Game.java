package com.mycompany.game;

import com.mycompany.items.Book;
import com.mycompany.items.HealthPotion;
import com.mycompany.items.Item;
import com.mycompany.items.Knife;
import com.mycompany.items.Shovel;
import com.mycompany.parser.Command;
import com.mycompany.parser.Parser;
import com.mycompany.type.Inventory;
import com.mycompany.type.Player;
import com.mycompany.type.Room;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

public class Game {

    private final Parser parser;
    private final Player player;
    private Item items;
    private Room lobby, theatre, pub, lab, office, cantine, cave, kitchen, outside, darkness;
    Scanner myScanner = new Scanner(System.in);
    File instruction = new File();
    File score = new File();
    
   String name;

    /**
     * Crea il gioco e inizializza la sua mappa interna.
     */
    public Game() {
        player = new Player();
        items = new HealthPotion("", 0);
        items = new Knife("", 0);
        items = new Shovel("", 0);
        items = new Book("", 0);
        createRooms();
        createItems();
        parser = new Parser();
    }

    /**
     * Crea tutte le stanze e collega le loro uscite insieme.
     */
    private void createRooms() {
        // crea le stanze
        lobby = new Room("atrio", "nell'atrio dell'università.");
        theatre = new Room("teatro", "in un'aula di lettura.");
        pub = new Room("pub", "nel pub del campus.");
        lab = new Room("laboratorio", "nel laboratorio di informatica.");
        office = new Room("ufficio", "nell'ufficio di amministrazione informatica.");
        cantine = new Room("mensa", "nella mensa con molto cibo.");
        cave = new Room("grotta", "in una grotta, è davvero buio.");
        darkness = new Room("caverna", "nella caverna, ma qui non c'è luce");
        kitchen = new Room("cucina", "in cucina, con uno strano uomo incappucciato in piedi davanti a una finestra.");
        kitchen.setLocked(true);
        outside = new Room("esterno", "fuori dall'università.");
        outside.setLocked1(true);

        // inizializza le uscite
        lobby.setExit("est", theatre);
        lobby.setExit("sud", lab);
        lobby.setExit("ovest", pub);

        theatre.setExit("ovest", lobby);
        theatre.setExit("sopra", cantine);
        theatre.setExit("sotto", cave);

        pub.setExit("est", lobby);

        lab.setExit("nord", lobby);
        lab.setExit("est", office);

        office.setExit("ovest", lab);

        cantine.setExit("sotto", theatre);
        cantine.setExit("nord", kitchen);

        cave.setExit("sopra", theatre);
        cave.setExit("ovest", darkness);

        darkness.setExit("est", cave);

        kitchen.setExit("sud", cantine);
        kitchen.setExit("nord", outside);

        player.setCurrentRoom(lobby);
    }

    private void createItems() {

        // crea gli oggetti 
        Knife knife = new Knife("Un coltello davvero affilato con del sangue su di esso.", 1);
        HealthPotion healthpotion = new HealthPotion("Una pozione rossa usata per la guarigione.", 1);
        Shovel shovel = new Shovel("Una pala sporca, c'è ancora terra bagnata su di essa.", 1);
        Book book = new Book("Un libro con uno strano splendore.", 1);

        // assegna alle stanze gli oggetti
        Inventory labinv = lab.getInventory();
        labinv.addItem("coltello", knife);

        Inventory officeinv = office.getInventory();
        officeinv.addItem("libro", book);

        Inventory pubinv = pub.getInventory();
        pubinv.addItem("pozione", healthpotion);

        Inventory caveinv = cave.getInventory();
        caveinv.addItem("pala", shovel);
    }

    /**
     * Routine di gioco principale.
     * @throws java.io.IOException
     */
    public void play() throws IOException {
        printWelcome();

        // Qui legge ripetutamente i comandi e li esegue fino alla fine del gioco.
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            if (command != null) {
                finished = processCommand(command);
            }

        }
        System.out.println("Grazie per aver giocato. Alla prossima!");
    }

    
    public void printTitle(){
        System.out.println("");
        System.out.println("");
        System.out.println("888     888          d8b                                    d8b 888");
        System.out.println("888     888          Y8P                                    Y8P 888");
        System.out.println("888     888                                                     888");
        System.out.println("888     888 88888b.  888 888  888  .d88b.  888d888 .d8888b  888 888888 888  888");
        System.out.println("888     888 888 '88b 888 888  888 d8P  Y8b 888P'   88K      888 888    888  888");
        System.out.println("888     888 888  888 888 Y88  88P 88888888 888     'Y8888b. 888 888    888  888");
        System.out.println("Y88b. .d88P 888  888 888  Y8bd8P  Y8b.     888          X88 888 Y88b.  Y88b 888");
        System.out.println(" 'Y88888P'  888  888 888   Y88P    'Y8888  888      88888P' 888  'Y888  'Y88888");
        System.out.println("                                                                            888");
        System.out.println("                                                                       Y8b d88P");
        System.out.println("                                                                        'Y88P");
        System.out.println("888b    888 d8b          888      888");
        System.out.println("8888b   888 Y8P          888      888");
        System.out.println("88888b  888              888      888");
        System.out.println("888Y88b 888 888  .d88b.  88888b.  888888 88888b.d88b.   8888b.  888d888 .d88b.");
        System.out.println("888 Y88b888 888 d88P'88b 888 '88b 888    888 '888 '88b     '88b 888P'  d8P  Y8b");
        System.out.println("888  Y88888 888 888  888 888  888 888    888  888  888 .d888888 888    88888888");
        System.out.println("888   Y8888 888 Y88b 888 888  888 Y88b.  888  888  888 888  888 888    Y8b.");
        System.out.println("888    Y888 888  'Y88888 888  888  'Y888 888  888  888 'Y888888 888     'Y8888");
        System.out.println("                     888");
        System.out.println("                Y8b d88P");
        System.out.println("                 'Y88P'");
        System.out.println("");
        System.out.println("                            Scarano, Putignano");
        System.out.println("                                   2020\n");
    }
    
    /**
     * Stampa il messaggio di apertura per il giocatore.
     */
    private void printWelcome() throws IOException {
        try {
            System.out.println("\nQual è il tuo nome?");
            name = myScanner.next();
            System.out.println("\n\nStai dormendo...\n");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Ad un tratto percepisci che qualcosa non va.");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Senti un leggero formicolio sulla mano, inizi a sudare, il tuo battito accelera velocemenete...\n");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Apri gli occhi..\n");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Ti svegli...non sai come ma ti ritrovi alle porte di una vecchia università abbandonata..\nla tua mano è coperta di sangue e sanguina velocemente.\n");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Devi trovare qualcosa per fermare l'emorragia.");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Cammini intorno all'università...ma");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("quando entri nell'università la porta si chiude dietro di te...\n");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Devi fuggire velocemente e trovare aiuto!!\n");
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("-Digita 'aiuto' per l'Help menù.");
            System.out.println("-Digita 'istruzioni' per visualizzare le istruzioni del gioco.");
            System.out.println("-Altrimenti inzia digitando go/cammina/vai + 'direzione' oppure gli altri comandi principali.");
            System.out.println("------------------------------------------------------------------------------------------------\n");
            System.out.println(player.getCurrentRoom().getLongDescription());
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }  
    
    }
    /**
     * Dato un comando, lo elabora. Se questo comando termina il gioco, viene
     * restituito true altrimenti viene restituito false.
     */
    private boolean processCommand(Command command) throws IOException {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("Non riesco a capire...");
            return false;
        }

        String commandWord = command.getCommandWord();
        switch (commandWord) {
            case "aiuto":
            case "help":
                printHelp();
                break;
            case "go":
            case "cammina":
            case "vai":
                goRoom(command);
                break;
            case "suicidati":    
            case "esci":
            case "quit":
                wantToQuit = quit(command);
                break;
            case "inventario":
                player.printInv();
                break;
            case "guarda":
            case "look":
            case "esamina":
                if ("".equals(player.getCurrentRoom().getOnlyItem())) {
                    System.out.println("Non vedo niente di speciale in questa stanza.");
                } else {
                    System.out.println(player.getCurrentRoom().getItemInRoom());
                }   break;
            case "istruzioni":
                try {
                    instruction.read("istruzioni.txt");
                    System.out.println("\nPremi INVIO");
                    myScanner.nextLine();
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }   printHelp();
                break;
            case "prendi":
            case "take":
            case "raccogli":
                player.takeItem(command);
                player.IsAlive();
                break;
            case "lascia":
            case "drop":
                player.dropItem(command);
                break;
            case "salute":
            case "health":
            case "vita":
                System.out.println("La tua salute è: " + player.showHealth());
                break;
            case "usa":
            case "use":
            case "utilizza":
                player.useItem(command);
                break;
            default:
                break;
        }
        return wantToQuit;
    }

    // Implementazioni dei comandi dell'utente:
    /**
     * Stampa alcune informazioni iniziali.
     */
    private void printHelp() {
        System.out.println("Sei perso...Sei solo...Vaghi senza una meta nell'università.");
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("I comandi da utilizzare sono:");
        System.out.println("istruzioni - aiuto - inventario - go+direzione - guarda - prendi - lascia - usa - salute - esci");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Prova ad andare in una direzione. Se c'è un'uscita, entra nella nuova
     * stanza, altrimenti stampa un messaggio di errore.
     */
    private void goRoom(Command command) throws IOException {
        if (!command.hasSecondWord()) {
            // Se non viene specificato dove andare
            System.out.println("Andare dove?");
            return;
        }

        String direction = command.getSecondWord();

        // Prova a lasciare la stanza attuale.
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        Room currentRoom = player.getCurrentRoom();

        if (nextRoom == null) {
            System.out.println("Da quella parte non puoi andare.");
        } else {
            if (nextRoom.getLocked()) {
                System.out.println("La porta è chiusa a chiave, è necessario trovare un modo per entrare.");
                return;
            }

            if (nextRoom.getLocked1()) {
                System.out.println("C'è uno strano uomo in piedi davanti alla finestra, uccidilo per uscire!!");
                return;
            }
            player.setCurrentRoom(nextRoom);
            if (nextRoom == outside) {
                
                score.write(name + ": " + player.showHealth(), "score.txt");
                
                System.out.println("Ci sei riuscito!!! Sei scappato e hai trovato la strada più vicina.");
                System.out.println("\nCOMPLIMENTI\n\n");

                System.out.println("ooooooooooo      ooooo      oooo   oooo      ooooooooooo ");
                System.out.println(" 888              888        8888o  88        888    88  ");
                System.out.println(" 888ooo8          888        88 888o88        888ooo8    ");
                System.out.println(" 888              888        88   8888        888    oo  ");
                System.out.println("o888o            o888o      o88o    88       o888ooo8888 ");
                System.exit(0);
            }
            player.dealDamage(1);
            player.IsAlive();
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println("");
            System.out.println("La tua mano continua a perdere sangue...Fai presto");
            System.out.println("Salute: " + player.showHealth());
        }
    }

    /**
     * "Esci" è stato inserito. Controlla il resto del comando per vedere se ha
     * davvero abbandonato il gioco. Restituisce vero, se questo comando esce
     * dal gioco, falso altrimenti.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Per uscire, digita solamente 'esci'. ");
            return false;
        } else {
            return true;  // segnala che si vuole uscire
        }
    }    
}
