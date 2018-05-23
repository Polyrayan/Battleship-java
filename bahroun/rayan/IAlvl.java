package bahroun.rayan;

import java.util.ArrayList;
import java.util.Random;

public class IAlvl extends Joueur {
    private boolean lastMissileOk;
    private int level;
    private String st;
    private String ed;
    private int nbDroite;
    private int nbGauche;
    private int nbHaut;
    private int nbBas;
    private boolean droite;
    private boolean gauche;
    private boolean haut;
    private boolean bas;
    private String missileSaved;
    private ArrayList<String> possibilities;

    public IAlvl(String name, int level) {
        super(name);
        placementAuto();
        this.setLevel(level);
        lastMissileOk = false;
        possibilities = halfGrid();
    }
    
    //
    //
    //
    // methods
    //
    //
    //
    
    public String randomPoslvl() {
        if (level == 2)
            return randomPos2();
        else
            return randomPos();
    }

    public String randomPos() {
        String randomPos = "";
        Random r = new Random();
        String Col = "ABCDEFGHIJ";
        char randColumn = Col.charAt(r.nextInt(Col.length()));
        int randLine = r.nextInt(10) + 1;
        randomPos = randomPos + randColumn + randLine;
        return randomPos;

    }

    public String findAPossibleEndShip(String randomPos, int size) { // but trouver un endCoord : ed
        size = size - 1; // car st est deja une coord qui prend une position
        Coordonnee rPos = new Coordonnee(randomPos);
        Coordonnee eF = new Coordonnee(randomPos);
        Ship ship = new Ship(randomPos, randomPos);
        Random r = new Random();
        int choice = r.nextInt(2) + 1; // choix aleatoire entre placement horizontal/vertical
        int changement = 0;
        boolean find = false;
        while (changement < 2 && find == false) {
            // on test vers la droite
            if (choice == 1 && find == false) {
                eF.coord(rPos.getColumn() + size, rPos.getLine());
                find = ship.verifShip(rPos.toString(), eF.toString());
                if (find == true) {
                    st = randomPos;
                    ed = eF.toString();
                    return ed;
                }
                // on test vers la gauche
                else if (choice == 1 && find == false) {
                    eF.coord(rPos.getColumn() - size, rPos.getLine());
                    find = ship.verifShip(rPos.toString(), eF.toString());
                    if (find == true) {
                        st = eF.toString();
                        ed = randomPos;
                        return ed;
                    }
                    choice = 2;
                    changement++;
                }
            }
            // on test vers le bas
            else if (choice == 2 && find == false) {
                eF.coord(rPos.getColumn(), rPos.getLine() + size);
                find = ship.verifShip(rPos.toString(), eF.toString());
                if (find == true) {
                    st = randomPos;
                    ed = eF.toString();
                    return ed;
                }
                // on test vers le haut
                else if (choice == 2 && find == false) {
                    eF.coord(rPos.getColumn(), rPos.getLine() - size);
                    find = ship.verifShip(rPos.toString(), eF.toString());
                    if (find == true) {
                        st = eF.toString();
                        ed = randomPos;
                        return ed;
                    }
                    choice = 1;
                    changement++;

                }
            }
        }
        return ed;
    }

    public void placementAuto() {
        Boolean isUsed = true;
        Ship carrier = null, battleship = null, cruiser = null, submarine = null, destroyer = null;
        // carrier
        while (isUsed) {
            while (carrier == null || !carrier.verifShip(carrier.getStartCoord(), carrier.getEndCoord())
                    || carrier.getType() != TypeOfShip.carrier) {
                String st = randomPos();
                String ed = findAPossibleEndShip(st, TypeOfShip.carrier.getSize());
                carrier = new Ship(st, ed);
                if (carrier.verifShip(st, ed) && carrier.shipSize(st, ed) == 5) {
                    carrier.setTypeOfShip(TypeOfShip.carrier);
                    carrier.setStartCoord(st);
                    carrier.setEndCoord(ed);
                }
            }
            carrier = new Ship(carrier.getStartCoord(), carrier.getEndCoord());
            carrier.setTypeOfShip(TypeOfShip.carrier);
            carrier.setSize(carrier.shipSize(st, ed));
            isUsed = positionUsed(carrier);
            if (isUsed)
                carrier = null;

        }
        this.setCarrier(carrier);
        getListOfMyShips().addAll(carrier.getMyShip());
        isUsed = true;

        // battleship
        while (isUsed) {
            while (battleship == null || !battleship.verifShip(battleship.getStartCoord(), battleship.getEndCoord())
                    || battleship.getType() != TypeOfShip.battleship) {
                String st = randomPos();
                String ed = findAPossibleEndShip(st, TypeOfShip.battleship.getSize());
                battleship = new Ship(st, ed);
                if (battleship.verifShip(st, ed) && battleship.shipSize(st, ed) == 4) {
                    battleship.setTypeOfShip(TypeOfShip.battleship);
                    battleship.setStartCoord(st);
                    battleship.setEndCoord(ed);
                }
            }

            battleship = new Ship(battleship.getStartCoord(), battleship.getEndCoord());
            battleship.setTypeOfShip(TypeOfShip.battleship);
            isUsed = positionUsed(battleship);
            if (isUsed)
                battleship = null;
        }
        this.setBattleship(battleship);
        getListOfMyShips().addAll(battleship.getMyShip());
        isUsed = true;

        // cruiser
        while (isUsed) {
            while (cruiser == null || !cruiser.verifShip(cruiser.getStartCoord(), cruiser.getEndCoord()) || cruiser.getType() != TypeOfShip.cruiser) {
                String st = randomPos();
                String ed = findAPossibleEndShip(st, TypeOfShip.cruiser.getSize());
                cruiser = new Ship(st, ed);
                if (cruiser.verifShip(st, ed) && cruiser.shipSize(st, ed) == 3) {
                    cruiser.setTypeOfShip(TypeOfShip.cruiser);
                    cruiser.setStartCoord(st);
                    cruiser.setEndCoord(ed);
                }
            }

            cruiser = new Ship(cruiser.getStartCoord(), cruiser.getEndCoord());
            cruiser.setTypeOfShip(TypeOfShip.cruiser);
            isUsed = positionUsed(cruiser);
            if (isUsed)
                cruiser = null;
        }
        this.setCruiser(cruiser);
        getListOfMyShips().addAll(cruiser.getMyShip());
        isUsed = true;

        // submarine
        while (isUsed) {
            while (submarine == null || !submarine.verifShip(submarine.getStartCoord(), submarine.getEndCoord())
                    || submarine.getType() != TypeOfShip.submarine) {
                String st = randomPos();
                String ed = findAPossibleEndShip(st, TypeOfShip.submarine.getSize());
                submarine = new Ship(st, ed);
                if (submarine.verifShip(st, ed) && submarine.shipSize(st, ed) == 3) {
                    submarine.setTypeOfShip(TypeOfShip.submarine);
                    submarine.setStartCoord(st);
                    submarine.setEndCoord(ed);
                }
            }

            submarine = new Ship(submarine.getStartCoord(), submarine.getEndCoord());
            submarine.setTypeOfShip(TypeOfShip.submarine);
            isUsed = positionUsed(submarine);
            if (isUsed)
                submarine = null;
        }
        setSubmarine(submarine);
        getListOfMyShips().addAll(submarine.getMyShip());
        isUsed = true;

        // destroyer
        while (isUsed) {
            while (destroyer == null || !destroyer.verifShip(destroyer.getStartCoord(), destroyer.getEndCoord()) || destroyer.getType() != TypeOfShip.destroyer) {
                String st = randomPos();
                String ed = findAPossibleEndShip(st, TypeOfShip.destroyer.getSize());
                destroyer = new Ship(st, ed);
                if (destroyer.verifShip(st, ed) && destroyer.shipSize(st, ed) == 2) {
                    destroyer.setTypeOfShip(TypeOfShip.destroyer);
                    destroyer.setStartCoord(st);
                    destroyer.setEndCoord(ed);
                }
            }

            destroyer = new Ship(destroyer.getStartCoord(), destroyer.getEndCoord());
            destroyer.setTypeOfShip(TypeOfShip.destroyer);
            isUsed = positionUsed(destroyer);
            if (isUsed)
                destroyer = null;
        }
        setDestroyer(destroyer);
        getListOfMyShips().addAll(destroyer.getMyShip());
        // fin du placement
        System.out.println(getName() + " a placé ses bateaux : "+ getListOfMyShips());
    }

    public int attaquelvl(String missile, Joueur playerReceiving) {
        if (level == 0)
            return super.sendMissile(missile, playerReceiving);

        if (level == 1)
            return sendMissileV2(missile, playerReceiving);

        if (level == 2)
            return sendMissileV3(missile, playerReceiving);

        else
            return super.sendMissile(missile, playerReceiving);
    }

    public int sendMissile(String missile, Joueur playerReceiving) {
        return super.sendMissile(missile, playerReceiving);
    }

    public int sendMissileV2(String missile, Joueur playerReceiving) {

        if (!getMyShots().contains(missile)) {
            addMyShots(missile);
            if (playerReceiving.receiveMissile(missile)) {
                getMyGoodShots().add(missile);
                return 1;
            } else
                return 0;
        } else
            return sendMissileV2(randomPos(), playerReceiving);
    }

    public int sendMissileV3(String missile, Joueur playerReceiving) {
        if (!lastMissileOk && getMyShots().contains(missile)) {
            return sendMissileV3(randomPos2(), playerReceiving);
        }

        if (!lastMissileOk || getMyShots().isEmpty()) {
            if (!getMyShots().contains(missile)) {
                addMyShots(missile);
                possibilities.remove(missile);
                if (playerReceiving.receiveMissile(missile)) {
                    missileSaved = missile;
                    changeValues();
                    getMyGoodShots().add(missile);
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        if (lastMissileOk && !getMyShots().isEmpty()) {
            Coordonnee cSaved = new Coordonnee(missileSaved);
            Coordonnee cHaut = new Coordonnee(missileSaved);
            String missileHaut = cHaut.coordHaut(nbHaut);
            Coordonnee cBas = new Coordonnee(missileSaved);
            String missileBas = cBas.coordBas(nbBas);
            Coordonnee cGauche = new Coordonnee(missileSaved);
            String missileGauche = cGauche.coordGauche(nbGauche);
            Coordonnee cDroite = new Coordonnee(missileSaved);
            String missileDroite = cDroite.coordDroite(nbDroite);
            // on tir en haut
            if (haut) {
                // si on peut aller en haut et s'il y a une case dispo en haut sinon faux
                if ((cSaved.getLine() - nbHaut < 1) || getMyShots().contains(missileHaut)) {
                    haut = false;
                } else if (haut && !getMyShots().contains(missileHaut)) {
                    addMyShots(missileHaut);
                    if (playerReceiving.receiveMissile(missileHaut)) {
                        if (possibilities.contains(missileHaut)) {
                            possibilities.remove(missileHaut);
                        }
                        nbHaut++;
                        getMyGoodShots().add(missileHaut);
                        return 1;
                    } else {
                        haut = false;
                        return 0;
                    }
                }
            } else if (bas) {
                if ((cSaved.getLine() + nbBas > 10) || getMyShots().contains(missileBas)) {
                    bas = false;
                } else if (bas && !getMyShots().contains(missileBas)) {
                    addMyShots(missileBas);
                    if (playerReceiving.receiveMissile(missileBas)) {
                        if (possibilities.contains(missileBas)) {
                            possibilities.remove(missileBas);
                        }
                        nbBas++;
                        getMyGoodShots().add(missileBas);
                        return 1;
                    } else {
                        bas = false;
                        return 0;
                    }

                }
            } else if (gauche) {
                if ((cSaved.getColumn() - nbGauche < 0) || getMyShots().contains(missileGauche)) {
                    gauche = false;
                } else if (gauche && !getMyShots().contains(missileGauche)) {
                    addMyShots(missileGauche);
                    if (playerReceiving.receiveMissile(missileGauche)) {
                        if (possibilities.contains(missileGauche)) {
                            possibilities.remove(missileGauche);
                        }
                        nbGauche++;
                        getMyGoodShots().add(missileGauche);
                        return 1;
                    } else {
                        gauche = false;
                        return 0;
                    }

                }
            }

            else if (droite) {
             // si on peut  aller a Droite et s'il  y a une case dispo a Droite
                if ((cSaved.getColumn() + nbDroite >= 10) || getMyShots().contains(missileDroite)) { 
                    droite = false;
                } else if (droite && !getMyShots().contains(missileDroite)) {
                    addMyShots(missileDroite);
                    if (playerReceiving.receiveMissile(missileDroite)) {
                        if (possibilities.contains(missileDroite)) {
                            possibilities.remove(missileDroite);
                        }
                        nbDroite++;
                        getMyGoodShots().add(missileDroite);
                        return 1;
                    } else {
                        droite = false;
                        return 0;
                    }
                }
            } else if (!haut && !bas && !gauche && !droite) {
                missileSaved = null;
                lastMissileOk = false;
                if (!getMyShots().contains(missile)) {
                    addMyShots(missile);
                    if (possibilities.contains(missile)) {
                        possibilities.remove(missile);
                    }
                    if (playerReceiving.receiveMissile(missile)) {

                        missileSaved = missile;
                        changeValues();
                        getMyGoodShots().add(missile);
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        }
        return sendMissileV3(randomPos2(), playerReceiving);
    }

    public void changeValues() {
        lastMissileOk = true;
        haut = true;
        bas = true;
        gauche = true;
        droite = true;
        nbHaut = 1;
        nbBas = 1;
        nbGauche = 1;
        nbDroite = 1;
    }

    public ArrayList<String> halfGrid() {
        ArrayList<String> half = new ArrayList<String>();
        for (int i = 1; i < 10; i = i + 2) {
            String a1 = "A" + i;
            String b2 = "B" + (1 + i);
            String c1 = "C" + i;
            String d2 = "D" + (1 + i);
            String e1 = "E" + i;
            String f2 = "F" + (1 + i);
            String g1 = "G" + i;
            String h2 = "H" + (1 + i);
            String i1 = "I" + i;
            String j2 = "J" + (1 + i);
            half.add(a1);
            half.add(b2);
            half.add(c1);
            half.add(d2);
            half.add(e1);
            half.add(f2);
            half.add(g1);
            half.add(h2);
            half.add(i1);
            half.add(j2);
        }
        return half;
    }

    public String randomPos2() {
        if (possibilities.size() != 0) {
            Random r = new Random();
            int randomi = r.nextInt(possibilities.size());
            if (getMyShots().contains(possibilities.get(randomi))) {
                possibilities.remove(possibilities.get(randomi));
                return randomPos2();
            }
            return possibilities.get(randomi);

        } else
            return randomPos();
    }

    /// get & set
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

}
