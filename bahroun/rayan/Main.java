package bahroun.rayan;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // # bateau
        // ! bateau touché
        // O tir ennemi dans l'eau
        // ¤ tir allier dans l'eau
        // AIVersusAI(2);
        int choix = 0;
        while (choix != 1 && choix != 2) {
            System.out.println("faites un choix : 1 pour joueur contre joueur ou 2 pour joueur contre IA");
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);
            choix = sc.nextInt();
        }
        if (choix == 1) {
            System.out.println("Vous avez choisi le mode 1, une partie joueur contre joueur va commencer :");
            humanVersusHuman();
        }
        if (choix == 2) {
            System.out.println("vous avez choisi le mode 2 : joueur contre IA");
            System.out.println("choisissez maintenant le niveau de difficulté de l'ia ");
            int choixlvl = -1;
            while (choixlvl != 0 && choixlvl != 1 && choixlvl != 2) {
                System.out.println("0 : Beginner, 1 : Medium, 2 : Hard");
                @SuppressWarnings("resource")
                Scanner sc = new Scanner(System.in);
                choixlvl = sc.nextInt();
            }
            if (choixlvl == 0) {
                System.out.println("une partie commence contre l'IA beginner");
                PlayerVersusAI(0);
            } else if (choixlvl == 1) {
                System.out.println("une partie commence contre l'IA medium");
                PlayerVersusAI(1);
            } else if (choixlvl == 2) {
                System.out.println("une partie commence contre l'IA hard");
                PlayerVersusAI(2);
            }
        }
    }

    public static boolean replay() {
        boolean playAgain;
        System.out.println("");
        System.out.println("Voulez-vous rejouer ? oui : 0 ou non : 1");
        Scanner sc = new Scanner(System.in);
        int reponse = sc.nextInt();
        if (reponse == 0)
            playAgain = true;
        else
            playAgain = false;
        sc.close();
        return playAgain;
    }

    @SuppressWarnings("resource")
    public static void PlayerVersusAI(int nb) {
        boolean playAgain = true;
        int i = 0;
        String name;
        String missileCoord = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Joueur 1, entrez un nom : ");
        name = sc.nextLine();

        while (playAgain) {
            IAlvl ia3 = new IAlvl("IA3", nb);
            Joueur j1 = new Joueur(name);
            Partie partie = new Partie(ia3, j1);
            partie.setGame(i);
            partie.newGamePvsIA();
            int touched;
            System.out.println("le joueur " + partie.getFirstPlayer() + " commence cette partie ");
            while (!partie.isFinished()) {
                System.out.println("");
                System.out.println(partie.getCurrentPlayer().getName() + " est en train de jouer..");
                if (partie.getCurrentPlayer() == partie.getPlayer1()) {
                    System.out.println(partie.getCurrentPlayer().getName() + " envoie un missile ..");
                    missileCoord = ((IAlvl) partie.getCurrentPlayer()).randomPoslvl();
                    touched = ((IAlvl) partie.getCurrentPlayer()).attaquelvl(missileCoord, partie.getPassivePlayer());
                    if (touched == 1) {
                        System.out.println("Touché");
                    } else if (touched == 0) {
                        System.out.println("raté");
                    } else if (touched == -1) {
                        System.out.println("tu avais déjà tiré ici");
                    }
                } else if (partie.getCurrentPlayer() == partie.getPlayer2()) {
                    System.out.println("");
                    System.out.println(partie.getCurrentPlayer().getName() + " c'est à toi de jouer ");
                    // affichage carte bateaux + tir ennemis
                    System.out.println("Carte des bateaux de " + partie.getCurrentPlayer().getName());
                    partie.getCurrentPlayer().gridMyShips();
                    // affichage des tirs effectués
                    System.out.println("Carte des tirs de " + partie.getCurrentPlayer().getName());
                    partie.getCurrentPlayer().gridMyShots();
                    System.out.println("");
                    System.out.println("Choisissez une position où tirer votre missile ");
                    missileCoord = sc.nextLine();
                    touched = partie.getCurrentPlayer().sendMissile(missileCoord, partie.getPassivePlayer());
                    if (touched == 1) {
                        System.out.println("Touché");
                    } else if (touched == 0) {
                        System.out.println("Raté");
                    } else if (touched == -1) {
                        System.out.println("Tu as déjà tiré ici !");
                    }
                }
                partie.changeRound();
                System.out.println("round n°" + partie.getRound());
            }
            System.out.println("partie n° " + (i + 1) + " terminée en " + partie.getRound()
                    + " rounds, le joueur qui commençait était : " + partie.getFirstPlayer());
            System.out.println("Winner : " + partie.winner().getName() + " gagne cette partie avec "
                    + partie.winner().getNbShipsAlive() + " bateau(x) qui sont encore en vie");
            playAgain = replay();
            i++;

        }
    }

    @SuppressWarnings("resource")
    public static void humanVersusHuman() {
        // PARTIE CLASSIQUE JvsJ

        Scanner sc = new Scanner(System.in);
        String name1;
        String name2;
        String missileCoord = "";
        System.out.println("Player 1, enter your name: ");
        name1 = sc.nextLine();
        Joueur j1 = new Joueur(name1);
        System.out.println("Player 2, enter your name: ");
        name2 = sc.nextLine();
        Joueur j2 = new Joueur(name2);
        int i = 0;
        boolean playAgain = true;
        while (playAgain) {
            Partie partie = new Partie(j1, j2);
            partie.setGame(i);
            partie.newGame();
            int touched;

            while (!partie.isFinished()) {
                System.out.println("");
                System.out.println("Get ready " + partie.getCurrentPlayer().getName() + "!");
                // affichage carte bateau + tir recus
                System.out.println("Carte des bateaux du " + partie.getCurrentPlayer().getName());
                partie.getCurrentPlayer().gridMyShips();
                // affichage des tirs effectues
                System.out.println("Carte des tirs du " + partie.getCurrentPlayer().getName());
                partie.getCurrentPlayer().gridMyShots();
                // do {
                System.out.println("Where would you like to send a missile ? ");
                missileCoord = sc.nextLine();

                System.out.println("Enter a missile coordinate: ");
                touched = partie.getCurrentPlayer().sendMissile(missileCoord, partie.getPassivePlayer());
                if (touched == 1) {
                    System.out.println("Nice shot!");
                } else if (touched == 0) {
                    System.out.println("Missile Failed !");
                } else if (touched == -1) {
                    System.out.println("a missile attacked this position  !");
                }
                if (partie.changeRound())
                    System.out.println(partie.toString());
            }
            System.out.println("partie n° " + (i + 1) + " terminée en " + partie.getRound()
                    + " rounds, le joueur qui commençait était : " + partie.getFirstPlayer());
            System.out.println("Winner : " + partie.winner().getName() + " gagne cette partie avec "
                    + partie.winner().getNbShipsAlive() + " bateau(x) qui sont encore en vie");
            playAgain = replay();
            i++;
        }
    }
}