package org.lessons.java.christmas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Wishlist {
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
    }
}
