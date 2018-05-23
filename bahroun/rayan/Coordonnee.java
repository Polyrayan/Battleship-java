package bahroun.rayan;

public class Coordonnee {
    private int column;
    private int line;
    private char firstChar = 'A';

    public Coordonnee(String coord) {
        if (coord.length() < 3) {
            column = coord.charAt(0) - firstChar;
            String line = coord.substring(1, 2);
            this.line = Integer.parseInt(line);
        } else {
            column = coord.charAt(0) - firstChar;
            String line = coord.substring(1, 3);
            this.line = Integer.parseInt(line);
        }
    }

    public void coord(int column, int line) {
        this.column = column;
        this.line = line;
    }

    public String toStringColumn() {
        String coord = new String();
        coord = coord + (char) (column + firstChar);
        return coord;
    }

    public String coordHaut(int nb) {
        coord(column, line - nb);
        return this.toString();
    }

    public String coordBas(int nb) {
        coord(column,line + nb);
        return this.toString();
    }

    public String coordGauche(int nb) {
        coord(column - nb, line);
        return this.toString();
    }

    public String coordDroite(int nb) { 
        coord((column + nb), line);
        return this.toString();
    }

    public String toString() {
        String coord = new String();
        coord = coord + (char) (column + firstChar) + line;
        return coord;
    }
    
    //
    // get & set
    //
    
    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }
}