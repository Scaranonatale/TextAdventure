package com.mycompany.parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Parser {

    private final CommandWords commands;  // contiene tutte le parole di comando valide

    public Parser() {
        commands = new CommandWords();
    }

    public Command getCommand() {
        String inputLine = "";   // conterrà l'intera riga di input
        String word1;
        String word2;

        System.out.print("> ");

        /*
        *Uso un lettore bufferizzato in grado di gestire autonomamente
        *un flusso di byte e convertirlo in un flusso di caratteri, quindi
        *instanzio un oggetto di tipo BufferedReader
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = reader.readLine();  //readLine() consente la lettura di intere stringhe.
        } catch (java.io.IOException exc) {
            System.out.println("Si è verificato un errore durante la lettura: "
                    + exc.getMessage());
        }
        if (inputLine == null) {
            return null;
        }
        StringTokenizer tokenizer = new StringTokenizer(inputLine);

        if (tokenizer.hasMoreTokens()) {
            word1 = tokenizer.nextToken();      // ottiene la prima parola
        } else {
            word1 = null;
        }
        if (tokenizer.hasMoreTokens()) {
            word2 = tokenizer.nextToken();      // ottiene la seconda parola
        } else {
            word2 = null;
        }

        // Ora controlla se questa parola è conosciuta. In tal caso, crea un comando con esso. In caso contrario, crea un comando "null" (per comando sconosciuto).
        if (commands.isCommand(word1)) {
            return new Command(word1, word2);
        } else {
            return new Command(null, word2);
        }
    }

}
