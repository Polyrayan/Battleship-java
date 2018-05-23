package fr.battleship;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import bahroun.rayan.*;

public class TestIA {

    public static void main(String args[]) throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(new File("ai_proof.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("Nom IA n°1 ; Score IA n°1; Nom IA n°2; Score IA n°2\n");

        // Beginner vs Medium
        int beginnerP1 = 0, mediumP1 = 0;
        for (int i = 0; i < 100; i++) {
            IAlvl ia0 = new IAlvl("Beginner", 0);
            IAlvl ia1 = new IAlvl("Medium", 1);
            Partie partie = new Partie(ia0, ia1);
            partie.setGame(i);
            partie.newGameAuto(ia0, ia1);
            while (!partie.isFinished()) {
                String missileCoord = ((IAlvl) partie.getCurrentPlayer()).randomPos();
                ((IAlvl) partie.getCurrentPlayer()).attaquelvl(missileCoord, partie.getPassivePlayer());
                partie.changeRound();
            }
            System.out.println("partie n° " + (i + 1) + " terminée en " + partie.getRound()
                    + " rounds, le joueur qui commençait était : " + partie.getFirstPlayer());
            System.out.println("Winner : " + partie.winner().getName() + " gagne cette partie avec "
                    + partie.winner().getNbShipsAlive() + " bateau(x) qui sont encore en vie");
            if (partie.winner() == ia0)
                beginnerP1++;
            else
                mediumP1++;
        }

        // Part 2: Beginner vs Hard
        int beginnerP2 = 0;
        int hardP2 = 0;

        for (int i = 0; i < 100; i++) {
            IAlvl ia0 = new IAlvl("Beginner", 0);
            IAlvl ia2 = new IAlvl("Hard", 2);
            Partie partie2 = new Partie(ia0, ia2);
            partie2.setGame(i);
            partie2.newGameAuto(ia0, ia2);
            while (!partie2.isFinished()) {
                String missileCoord = ((IAlvl) partie2.getCurrentPlayer()).randomPoslvl();
                ((IAlvl) partie2.getCurrentPlayer()).attaquelvl(missileCoord, partie2.getPassivePlayer());
                partie2.changeRound();
            }
            System.out.println("partie n° " + (i + 1) + " terminée en " + partie2.getRound()
                    + " rounds, le joueur qui commençait était : " + partie2.getFirstPlayer());
            if (partie2.winner() == ia0)
                beginnerP2++;
            else
                hardP2++;
        }

        // Medium vs Hard
        int mediumP3 = 0;
        int hardP3 = 0;
        for (int i = 0; i < 100; i++) {
            IAlvl ia1 = new IAlvl("Medium", 1);
            IAlvl ia2 = new IAlvl("Hard", 2);
            Partie partie3 = new Partie(ia1, ia2);
            partie3.setGame(i);
            partie3.newGameAuto(ia1, ia2);
            while (!partie3.isFinished()) {
                String missileCoord = ((IAlvl) partie3.getCurrentPlayer()).randomPoslvl();
                ((IAlvl) partie3.getCurrentPlayer()).attaquelvl(missileCoord, partie3.getPassivePlayer());
                partie3.changeRound();
            }
            System.out.println("partie n° " + (i + 1) + " terminée en " + partie3.getRound()
                    + " rounds, le joueur qui commençait était : " + partie3.getFirstPlayer());
            if (partie3.winner() == ia1)
                mediumP3++;
            else
                hardP3++;
        }
        System.out.println("100 parties terminées : AI niveau 0 : " + beginnerP1 + ", IA niveau 1: " + mediumP1);
        System.out.println("100 parties terminées : AI niveau 0 : " + beginnerP2 + ", IA niveau 2 : " + hardP2);
        System.out.println("100 parties terminées : AI niveau 1 : " + mediumP3 + ", IA niveau 2 : " + hardP3);
        sb.append("IA beginner; ");sb.append(beginnerP1);sb.append(';');sb.append(" IA Medium; ");sb.append(mediumP1);sb.append("\n");
        sb.append("IA beginner; ");sb.append(beginnerP2);sb.append(';');sb.append(" IA Hard; ");sb.append(hardP2);sb.append("\n");
        sb.append(" IA Medium; ");sb.append(mediumP3);sb.append(';');sb.append(" IA Hard; ");sb.append(hardP3);sb.append("\n");
        pw.write(sb.toString());
        pw.close();

    }

}
