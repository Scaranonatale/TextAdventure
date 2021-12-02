/*
 *Questa classe gestisce i file txt sia per la sola lettura delle istruzioni
 *sia per la permanenza dei dati del punteggio giocatore
 */
package com.mycompany.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class File {

    Scanner myScanner = new Scanner(System.in);

    /**
     *
     * @param source
     * @throws java.io.FileNotFoundException
     */
    public void read(String source) throws FileNotFoundException, IOException {

        try ( FileReader file = new FileReader(source)) {
            BufferedReader read = new BufferedReader(file);

            String riga = read.readLine();

            while (riga != null) {
                System.out.println(riga);
                riga = read.readLine();
            }
        }
    }

    public void write(String a, String source) throws IOException {
        java.io.File f = new java.io.File(source);

        if (f.exists()) {
            FileOutputStream fos = new FileOutputStream(source, true);
            try ( PrintWriter scrivi = new PrintWriter(fos)) {
                scrivi.append("" + a);

            }

        } else if (f.createNewFile()) {
            try ( PrintWriter scrivi = new PrintWriter(f)) {
                scrivi.print(a);
                scrivi.close();
            }
        }
    }
}
