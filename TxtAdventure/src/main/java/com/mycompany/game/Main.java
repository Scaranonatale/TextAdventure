package com.mycompany.game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner myScanner = new Scanner(System.in);
        File score = new File();
        Game game = new Game(); //crea nuovo oggetto game
        String choice;

        game.printTitle();
        do {
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("Fai la tua scelta\n");
            System.out.println("0) Esci");
            System.out.println("1) Gioca");
            System.out.println("2) Storico");
            System.out.println("-----------------------------");
            System.out.print("> ");

            choice = myScanner.next();
            if(!"0".equals(choice) && !"1".equals(choice) && !"2".equals(choice) && !"esci".equals(choice) && !"quit".equals(choice)){
                        System.out.println("Non ho capito...\nDigita una delle tre opzioni."); //controlli del menù
           }
            if("esci".equals(choice) || "quit".equals(choice)){
           choice = "0";
           }
            switch (choice) {
                case "1":
                    game.play();
                    break;
                case "2":
                    try {
                    System.out.println();   
                    score.read("score.txt");
                } catch (FileNotFoundException e) {
                    System.out.println("\nNon c'é alcun punteggio recente.\n");
                }
                break;
            }
        } while (!"0".equals(choice));
    }
}
