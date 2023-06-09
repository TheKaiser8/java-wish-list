package org.lessons.java.christmas;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Wishlist {

    private final static String FILE_PATH = "./resources/giftlist.txt";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Crea la tua lista regali di Natale");
        List<String> giftList = new ArrayList<>();

        boolean stop = false;
        do {
            System.out.print("Inserisci il nome del tuo regalo (stop per uscire dal programma): ");
            String gift = scan.nextLine().toLowerCase();
            if (gift.equals("stop")) {
                stop = true;
            } else {
                try {
                    if (!gift.isBlank()) {
                        giftList.add(gift);
                        System.out.print("La tua lista contiene " + giftList.size());
                        System.out.println(giftList.size() == 1 ? " regalo" : " regali");
                        System.out.print("Vuoi aggiungere un altro regalo? (premi s per continuare) ");
                        String choice = scan.nextLine();
                        if (!choice.equalsIgnoreCase("s")) stop = true;
                    }
                } catch (Exception e) {
                    System.out.println("Errore, il nome del regalo non può essere vuoto");
                }
            }
        } while (!stop);

        if(giftList.isEmpty()) System.out.println("La lista dei tuoi regali è vuota");
        else {
            Collections.sort(giftList);
            System.out.println("La lista ordinata dei tuoi regali di Natale: " + giftList);
        }
        scan.close();

        System.out.println("\n"); // spazio tra esercizio e bonus

        // scrivo i dati della lista su file
        // apro un FileWriter
        FileWriter writer = null;
        try {
            writer = new FileWriter(FILE_PATH);
            // BufferedWriter buffer = new BufferedWriter(writer); // con buffer se il file contenesse tanti elementi (+ efficiente)
            // itero sull'ArrayList dei regali

            System.out.println("----- Lettura dati da file -----");
            for (int i = 0; i < giftList.size() ; i++) {
                writer.write("Regalo n°" + (i + 1) + ": " + giftList.get(i) + "\n");
            }
            System.out.print("La tua lista contiene " + giftList.size());
            System.out.println(giftList.size() == 1 ? " regalo" : " regali");
        } catch (IOException e) {
            System.out.println("Non è stato possibile scrivere il file.");
        } finally {
            try {
                writer.close();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Prova lettura del file (riga per riga) con try-with-resources
        try (Scanner reader = new Scanner(new File(FILE_PATH))) {
            // itero sulle righe del file finché non terminano --> hasNextLine()
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato.");
        }
    }
}
