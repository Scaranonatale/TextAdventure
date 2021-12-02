package com.mycompany.parser;

/**
 * Questa classe contiene informazioni su un comando che è stato emesso
 * dall'utente. Un comando è composto da due stringhe: una CommandWords e una
 * seconda parola (ad esempio, se il comando era "prendi torcia", quindi le due
 * stringhe ovviamente sono "prendi" e "torcia").
 *
 * Il modo in cui viene utilizzato è: i comandi sono già controllati per essere
 * validi da CommandWords. Se l'utente ha inserito un comando non valido (una
 * parola che non è nota) quindi la CommandWords è 'null'.
 *
 * Se il comando aveva solo una parola, la seconda parola è 'null'.
 *
 */
public class Command {

    private final String commandWord;
    private final String secondWord;

    /**
     * Crea un oggetto Command.La Prima e la seconda parola devono essere
     * fornite, ma uno (o entrambe) possono essere null.La CommandWords deve
     * essere null e indica che si trattava di un comando non riconosciuto da
     * questo gioco.
     *
     * @param firstWord
     * @param secondWord
     */
    public Command(String firstWord, String secondWord) {
        this.commandWord = firstWord;
        this.secondWord = secondWord;
    }

    /**
     * Restituisce la CommandWords (la prima parola) di questo comando.Se il
     * comando non è stato compreso, il risultato è null.
     *
     * @return
     */
    public String getCommandWord() {
        return commandWord;
    }

    /**
     * Restituisce la seconda parola di questo comando.Restituisce null se non
     * c'era seconda parola.
     *
     * @return
     */
    public String getSecondWord() {
        return secondWord;
    }

    /**
     * Restituisce vero se questo comando non è stato compreso.
     *
     * @return
     */
    public boolean isUnknown() {
        return (commandWord == null);
    }

    /**
     * Restituisce vero se il comando ha una seconda parola.
     *
     * @return
     */
    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}
