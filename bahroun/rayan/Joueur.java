package bahroun.rayan;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
    private ArrayList<Ship> myShips;
    private ArrayList<String> listOfMyShips;
    private String name;
    private Ship carrier;
    private Ship battleship;
    private Ship cruiser;
    private Ship submarine;
    private Ship destroyer;
    private int nbShipsAlive;
    private ArrayList<String> myShots;
    private ArrayList<String> myGoodShots;    
    private ArrayList<String> enemyShots;
    private boolean currentPlayer;

    public Joueur(String name) {
        this.name = name;
        nbShipsAlive = 5;
        listOfMyShips = new ArrayList<String>();
        myShots = new ArrayList<String>();
        enemyShots = new ArrayList<String>();
        myGoodShots = new ArrayList<String>();
    }

    //
    //
    //
    // methods
    //
    //
    //

    public void calculMyShips() {
        listOfMyShips.addAll(carrier.getMyShip());
        listOfMyShips.addAll(battleship.getMyShip());
        listOfMyShips.addAll(cruiser.getMyShip());
        listOfMyShips.addAll(submarine.getMyShip());
        listOfMyShips.addAll(destroyer.getMyShip());
    }

    public void gridMyShips() {
        Grid grid = new Grid(10, 10);
        for (int i = 0; i < listOfMyShips.size(); i++) {
            String st = listOfMyShips.get(i);
            Coordonnee st1 = new Coordonnee(st);
            grid.placerBateau(st1.getLine(), st1.getColumn());
        }
        for (int i = 0; i < enemyShots.size(); i++) {
            String st = enemyShots.get(i);
            Coordonnee st1 = new Coordonnee(st);
            grid.placerEnemyShots(st1.getLine(), st1.getColumn());

        }
        grid.affichage();
    }

    public void gridMyShots() {
        Grid grid = new Grid(10, 10);
        for (int i = 0; i < myShots.size(); i++) {
            String st = myShots.get(i);
            Coordonnee st1 = new Coordonnee(st);
            grid.placerMyShots(st1.getLine(), st1.getColumn());
        }
        for (int i = 0; i < myGoodShots.size(); i++) {
            String st = myGoodShots.get(i);
            Coordonnee st1 = new Coordonnee(st);
            grid.placerMyGoodShots(st1.getLine(), st1.getColumn());
        }
        grid.affichage();
    }

    public boolean positionUsed(Ship ship) {
        boolean posUsed = false;
        int size = ship.getType().getSize();
        int i = 0;
        while (!posUsed && i < size) {
            posUsed = listOfMyShips.contains(ship.getMyShip().get(i));
            i++;
        }
        return posUsed;
    }

    public void placement() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in); 
        Boolean isUsed;
        Ship carrier = null, battleship = null, cruiser = null, submarine = null, destroyer = null;

        // Carrier
        System.out.println(" Carrier (5 cases)");

        do {
            do {
                System.out.println("startCoord of your Carrier : ");
                String st = sc.nextLine();
                
                System.out.println("endCoord of your Carrier : ");
                String ed = sc.nextLine();
                carrier = new Ship(st, ed);
                if (!carrier.verifShip(st, ed)) {
                    System.out.println("ce n'est pas un bateau admissible");
                } else {
                    if (carrier.verifShip(st, ed) && carrier.shipSize(st, ed) == 5) {
                        carrier.setTypeOfShip(TypeOfShip.carrier);
                        carrier.setStartCoord(st);
                        carrier.setEndCoord(ed);
                    }
                }
                if (!carrier.verifShip(st, ed) || carrier.getType() != TypeOfShip.carrier) {
                    System.out.println("Réessaye avec des bonnes positions");
                }
            } while (!carrier.verifShip(carrier.getStartCoord(), carrier.getEndCoord())
                    || carrier.getType() != TypeOfShip.carrier);
            carrier = new Ship(carrier.getStartCoord(), carrier.getEndCoord());
            carrier.setTypeOfShip(TypeOfShip.carrier);
            isUsed = positionUsed(carrier);

            if (isUsed) {
                System.out.println("le bateau ne peut pas utiliser une case déjà prise par un autre de vos bateaux");
            }
        } while (isUsed);
        this.setCarrier(carrier);
        listOfMyShips.addAll(carrier.getMyShip());

        // Battleship
        System.out.println("battleship, (4 cases)");
        do {
            do {
                System.out.println("startCoord of your battleship: ");
                String st = sc.nextLine(); 
                System.out.println("endCoord of your battleship: ");
                String ed = sc.nextLine();
                battleship = new Ship(st, ed);
                if (!battleship.verifShip(st, ed)) {
                    System.out.println("ce n'est pas un bateau admissible");
                } else if (battleship.verifShip(st, ed) && battleship.shipSize(st, ed) == 4) {
                    battleship.setTypeOfShip(TypeOfShip.battleship);
                    battleship.setStartCoord(st);
                    battleship.setEndCoord(ed);

                }
                if (!battleship.verifShip(st, ed) || battleship.getType() != TypeOfShip.battleship) {
                    System.out.println("Réessaye avec des bonnes positions");
                }
            } while (!battleship.verifShip(battleship.getStartCoord(), battleship.getEndCoord())
                    || battleship.getType() != TypeOfShip.battleship);
            battleship = new Ship(battleship.getStartCoord(), battleship.getEndCoord());
            battleship.setTypeOfShip(TypeOfShip.battleship);
            isUsed = positionUsed(battleship);
            if (isUsed) {
                System.out.println("le bateau ne peut pas utiliser une case déjà prise par un autre de vos bateaux");
            }
        } while (isUsed);
        setBattleship(battleship);
        listOfMyShips.addAll(battleship.getMyShip());

        // Cruiser
        System.out.println("Cruiser(3 cases)");
        do {
            do {
                System.out.println("startCoord of your cruiser: ");
                String st = sc.nextLine();
                System.out.println("endCoord of your cruiser: ");
                String ed = sc.nextLine();
                cruiser = new Ship(st, ed);
                if (!cruiser.verifShip(st, ed)) {
                    System.out.println("ce n'est pas un bateau admissible");
                } else if (cruiser.verifShip(st, ed) && cruiser.shipSize(st, ed) == 3) {
                    cruiser.setTypeOfShip(TypeOfShip.cruiser);
                    cruiser.setStartCoord(st);
                    cruiser.setEndCoord(ed);
                }
                if (!cruiser.verifShip(st, ed) || cruiser.getType() != TypeOfShip.cruiser) {
                    System.out.println("Réessaye avec des bonnes positions");
                }
            } while (!cruiser.verifShip(cruiser.getStartCoord(), cruiser.getEndCoord())
                    || cruiser.getType() != TypeOfShip.cruiser);
            cruiser = new Ship(cruiser.getStartCoord(), cruiser.getEndCoord());
            cruiser.setTypeOfShip(TypeOfShip.cruiser);
            isUsed = positionUsed(cruiser);
            if (isUsed) {
                System.out.println("le bateau ne peut pas utiliser une case déjà prise par un autre de vos bateaux");
            }
        } while (isUsed);
        setCruiser(cruiser);
        listOfMyShips.addAll(cruiser.getMyShip());

        // Submarine
        System.out.println("submarine(3 cases)");
        do {
            do {
                System.out.println("startCoord of your cruiser: ");
                String st = sc.nextLine();
                System.out.println("endCoord of your cruiser: ");
                String ed = sc.nextLine();
                submarine = new Ship(st, ed);
                if (!submarine.verifShip(st, ed)) {
                    System.out.println("ce n'est pas un bateau admissible");
                } else if (submarine.verifShip(st, ed) && submarine.shipSize(st, ed) == 3) {
                    submarine.setTypeOfShip(TypeOfShip.submarine);
                    submarine.setStartCoord(st);
                    submarine.setEndCoord(ed);
                }

                if (!submarine.verifShip(st, ed) || submarine.getType() != TypeOfShip.submarine) {
                    System.out.println("Réessaye avec des bonnes positions");
                }
            } while (!submarine.verifShip(submarine.getStartCoord(), submarine.getEndCoord())
                    || submarine.getType() != TypeOfShip.submarine);
            submarine = new Ship(submarine.getStartCoord(), submarine.getEndCoord());
            submarine.setTypeOfShip(TypeOfShip.submarine);
            isUsed = positionUsed(submarine);
            if (isUsed) {
                System.out.println("le bateau ne peut pas utiliser une case déjà prise par un autre de vos bateaux");
            }
        } while (isUsed);
        setSubmarine(submarine);
        listOfMyShips.addAll(submarine.getMyShip());

        // Destroyer
        System.out.println("destroyer(2 cases)");
        do {
            do {
                System.out.println("startCoord of your destroyer: ");
                String st = sc.nextLine();
                System.out.println("endCoord of your destroyer: ");
                String ed = sc.nextLine();
                destroyer = new Ship(st, ed);
                if (!destroyer.verifShip(st, ed)) {
                    System.out.println("ce n'est pas un bateau admissible");
                } else if (destroyer.verifShip(st, ed) && destroyer.shipSize(st, ed) == 2) {

                    destroyer.setTypeOfShip(TypeOfShip.destroyer);
                    destroyer.setStartCoord(st);
                    destroyer.setEndCoord(ed);
                }

                if (!destroyer.verifShip(st, ed) || destroyer.getType() != TypeOfShip.destroyer) {
                    System.out.println("Réessaye avec des bonnes positions");
                }
            } while (!destroyer.verifShip(destroyer.getStartCoord(), destroyer.getEndCoord())
                    || destroyer.getType() != TypeOfShip.destroyer);
            destroyer = new Ship(destroyer.getStartCoord(), destroyer.getEndCoord());
            destroyer.setTypeOfShip(TypeOfShip.destroyer);
            isUsed = positionUsed(destroyer);
            if (isUsed) {
                System.out.println("le bateau ne peut pas utiliser une case déjà prise par un autre de vos bateaux");
            }
        } while (isUsed);
        setDestroyer(destroyer);
        listOfMyShips.addAll(destroyer.getMyShip());
        System.out.println("tous vos bateaux sont prêts");
    }

    public boolean gameOver() {
        return nbShipsAlive == 0;
    }

    public void howManyShipsAlive() {
        nbShipsAlive = 0;
        if (!carrier.isDestroyed()) {
            nbShipsAlive++;
        }
        if (!battleship.isDestroyed()) {
            nbShipsAlive++;
        }
        if (!cruiser.isDestroyed()) {
            nbShipsAlive++;
        }
        if (!submarine.isDestroyed()) {
            nbShipsAlive++;
        }
        if (!destroyer.isDestroyed()) {
            nbShipsAlive++;
        }
    }

    public void addMyShots(String shot) {
        myShots.add(shot);
    }

    public void addEnemyShots(String shot) {
        enemyShots.add(shot);
    }

    public boolean receiveMissile(String missile) {
        if (!enemyShots.contains(missile)) { 
            addEnemyShots(missile);
            if (carrier.isHit(missile)) {
                carrier.damageShip(missile);
                if (carrier.isDestroyed())
                    System.out.println("le carrier a coulé");

                return true;
            } else if (battleship.isHit(missile)) {
                battleship.damageShip(missile);
                if (battleship.isDestroyed())
                    System.out.println("le battleship  a coulé");
                return true;
            } else if (cruiser.isHit(missile)) {
                cruiser.damageShip(missile);
                if (cruiser.isDestroyed())
                    System.out.println("le cruiser  a coulé");
                return true;
            } else if (destroyer.isHit(missile)) {
                destroyer.damageShip(missile);
                if (destroyer.isDestroyed())
                    System.out.println("le destroyer  a coulé");
                return true;
            } else if (submarine.isHit(missile)) {
                submarine.damageShip(missile);
                if (submarine.isDestroyed())
                    System.out.println("le submarine a coulé");
                return true;
            } else {
                return false;
            }
        } else
        {
            System.out.println("tir deja reçu sur cette case, dommage");
            return false;
        }
    }

    public int sendMissile(String missile, Joueur playerReceiving) {

        if (!myShots.contains(missile)) {
            addMyShots(missile);
            if (playerReceiving.receiveMissile(missile)) {
                myGoodShots.add(missile);
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

    //
    //
    //
    // get & set
    //
    //
    //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbShipsAlive() {
        return nbShipsAlive;
    }

    public void setNbShipsLeft(int nbShipsAlive) {
        this.nbShipsAlive = nbShipsAlive;
    }

    public Ship getCarrier() {
        return carrier;
    }

    public void setCarrier(Ship carrier) {
        if (carrier.getType() == TypeOfShip.carrier) {
            this.carrier = carrier;
        }
    }

    public Ship getBattleship() {
        return battleship;
    }

    public void setBattleship(Ship battleship) {
        if (battleship.getType() == TypeOfShip.battleship) {
            this.battleship = battleship;
        }
    }

    public Ship getCruiser() {
        return cruiser;
    }

    public void setCruiser(Ship cruiser) {
        if (cruiser.getType().equals(TypeOfShip.cruiser)) {
            this.cruiser = cruiser;
        }
    }

    public Ship getSubmarine() {
        return submarine;
    }

    public void setSubmarine(Ship submarine) {
        if (submarine.getType().equals(TypeOfShip.submarine)) {
            this.submarine = submarine;
        } 
    }

    public Ship getDestroyer() {
        return destroyer;
    }

    public void setDestroyer(Ship destroyer) {
        if (destroyer.getType().equals(TypeOfShip.destroyer)) {
            this.destroyer = destroyer;
        }
    }

    public ArrayList<Ship> getMyShips() {
        return myShips;
    }

    public void setMyShips(ArrayList<Ship> myShips) {
        this.myShips = myShips;
    }

    public boolean isCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<String> getListOfMyShips() {
        return listOfMyShips;
    }

    public void setListOfMyShips(ArrayList<String> listOfMyShips) {
        this.listOfMyShips = listOfMyShips;
    }
    
    public ArrayList<String> getMyGoodShots() {
        return myGoodShots;
    }

    public void setMyGoodShots(ArrayList<String> myGoodShots) {
        this.myGoodShots = myGoodShots;
    }

    public ArrayList<String> getMyShots() {
        return myShots;
    }

    public void setMyShots(ArrayList<String> myShots) {
        this.myShots = myShots;
    }

    public ArrayList<String> getEnemyShots() {
        return enemyShots;
    }

    public void setEnemyShots(ArrayList<String> enemyShots) {
        this.enemyShots = enemyShots;
    }
}
