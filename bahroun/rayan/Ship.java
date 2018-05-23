package bahroun.rayan;

import java.util.ArrayList;

public class Ship {

    private String startCoord;
    private String endCoord;
    private int size;
    private int numberHits;
    private TypeOfShip type;
    private ArrayList<String> myShip;
    private ArrayList<String> touchedParts;

    //
    //
    //
    // methods
    //
    //
    //

    public void damageShip(String missileCoord) {
        numberHits++;
        touchedParts.add(missileCoord);
    }

    public boolean isDestroyed() {
        return numberHits == type.getSize();
    }

    public boolean isHit(String shot) {
        return (myShip.contains(shot));
    }

    public boolean verifShip(String startCoord, String endCoord) {
        this.startCoord = startCoord;
        this.endCoord = endCoord;
        this.size = shipSize(startCoord, endCoord);
        Coordonnee firstCoord = new Coordonnee(startCoord);
        Coordonnee lastCoord = new Coordonnee(endCoord);

        if (firstCoord.getColumn() == lastCoord.getColumn() && firstCoord.getLine() == lastCoord.getLine()) {
            // System.out.println("bateau non conforme : startCoord = endCoord ");
            return false;
        } else if (firstCoord.getColumn() != lastCoord.getColumn() && firstCoord.getLine() != lastCoord.getLine()) {
            // System.out.println("...ON NE PEUT PAS FAIRE DE BATEAU EN DIAGONALE !");
            return false;
        } else if (firstCoord.getColumn() > 9 || firstCoord.getColumn() < 0 || firstCoord.getLine() > 10
                || firstCoord.getLine() < 1) {
            return false;
        } else if (lastCoord.getColumn() > 9 || lastCoord.getColumn() < 0 || lastCoord.getLine() > 10
                || lastCoord.getLine() < 1) {
            return false;
        } else if (firstCoord.getColumn() == lastCoord.getColumn() && firstCoord.getLine() != lastCoord.getLine()) {
            return true;
        } else if (firstCoord.getColumn() != lastCoord.getColumn() && firstCoord.getLine() == lastCoord.getLine()) {
            return true;
        } else {
            return false;
        }
    }

    public Ship(String startCoord, String endCoord) {
        touchedParts = new ArrayList<String>();
        numberHits = 0;
        this.startCoord = startCoord;
        this.endCoord = endCoord;
        doMyShip(startCoord, endCoord);
    }

    public ArrayList<String> doMyShip(String startcoord, String endcoord) {
        Coordonnee firstCoord = new Coordonnee(startCoord);
        Coordonnee lastCoord = new Coordonnee(endCoord);
        ArrayList<String> a1 = new ArrayList<String>();

        // MEME COLONNE A1 A2 A3
        if (firstCoord.getColumn() == lastCoord.getColumn() && firstCoord.getLine() != lastCoord.getLine()) {
            int x = firstCoord.getLine();
            int y = lastCoord.getLine();
            int size = Math.abs(x - y);
            if (x < y) {
                for (int i = x; i <= x + size; i++) {
                    String Mark = firstCoord.toStringColumn() + i;
                    a1.add(Mark);
                }
            }
        }
        // MEME LIGNE A1 B1 C1
        else if (firstCoord.getColumn() != lastCoord.getColumn() && firstCoord.getLine() == lastCoord.getLine()) {
            int x = firstCoord.getColumn();
            int y = lastCoord.getColumn();
            int size = Math.abs(x - y);
            if (x < y) {
                for (int i = x; i <= x + size; i++) {
                    String coord = new String();
                    coord = coord + (char) (i + 'A');
                    String Mark = coord + firstCoord.getLine();
                    a1.add(Mark);
                }
            }
        }
        return myShip = a1;

    }

    public int shipSize(String startCoord, String endCoord) {
        int size = 0;
        this.startCoord = startCoord;
        this.endCoord = endCoord;
        Coordonnee firstCoord = new Coordonnee(startCoord);
        Coordonnee lastCoord = new Coordonnee(endCoord);

        if (firstCoord.getColumn() == lastCoord.getColumn() && firstCoord.getLine() == lastCoord.getLine()) {
            return size;
        } else if (firstCoord.getColumn() != lastCoord.getColumn() && firstCoord.getLine() != lastCoord.getLine()) {
            return size;
        } else if (firstCoord.getColumn() == lastCoord.getColumn() && firstCoord.getLine() != lastCoord.getLine()) {
            int x = firstCoord.getLine();
            int y = lastCoord.getLine();
            size = Math.abs(x - y) + 1;
        } else if (firstCoord.getColumn() != lastCoord.getColumn() && firstCoord.getLine() == lastCoord.getLine()) {
            int x = firstCoord.getColumn();
            int y = lastCoord.getColumn();
            size = Math.abs(x - y) + 1;
        }
        return size;
    }
    
    //
    // get & set
    //
    
    public TypeOfShip getType() {
        return type;
    }

    public void setTypeOfShip(TypeOfShip type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberHits() {
        return numberHits;
    }

    public void setNumberHits(int numberHits) {
        this.numberHits = numberHits;
    }

    public String getStartCoord() {
        return startCoord;
    }

    public void setStartCoord(String startCoord) {
        this.startCoord = startCoord;
    }

    public String getEndCoord() {
        return endCoord;
    }

    public void setEndCoord(String endCoord) {
        this.endCoord = endCoord;
    }

    public void setMyShip(ArrayList<String> myShip) {
        this.myShip = myShip;
    }

    public ArrayList<String> getMyShip() {
        return myShip;
    }
}