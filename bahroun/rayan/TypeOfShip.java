package bahroun.rayan;

public enum TypeOfShip {
    carrier("carrier", 5), battleship("battleship", 4), cruiser("cruiser", 3), submarine("submarine", 3), destroyer("destroyer", 2);
    private int size;
    private String name;

    TypeOfShip(String name, int size) {
        this.name = name;
        this.size = size;
    }
    //
    // get & set
    //

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
