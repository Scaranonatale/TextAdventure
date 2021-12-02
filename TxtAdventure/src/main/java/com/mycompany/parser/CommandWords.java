package com.mycompany.parser;

/**
 * La classe CommandWords controlla la validità dei comandi inseriti in console.
 */
public class CommandWords {

    //array costante che contiene tutte le parole di comando valide
    private static final String validCommands[] = {
        "inventario", "istruzioni", "go", "vai", "cammina", "esci", "quit", "suicidati", "help", "aiuto", "guarda", "esamina", "look", "prendi", "raccogli", "take", "lascia", "drop", "salute", "health", "vita", "usa", "use", "utilizza"
    };

    /**
     * Controlla se una determinata stringa è una CommandWord valida.Restituisce
     * vero se lo è, falso se non lo è.
     *
     * @param aString
     * @return
     */
    public boolean isCommand(String aString) {
        for (String validCommand : validCommands) {
            if (validCommand.equals(aString)) {
                return true;
            }
        }
        // se la stringa non viene trovata nei comandi
        return false;
    }

}
