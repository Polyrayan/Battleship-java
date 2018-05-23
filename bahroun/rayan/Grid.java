package bahroun.rayan;

public class Grid {
    private int nbLine;
    private int nbCol;
    private String[][] grid;
    public Grid(int nbl, int nbc) {
        nbLine = nbl;
        nbCol = nbc;
        grid = new String[nbLine][nbCol];
        for (int i = 0; i < nbLine; i++) {
            for (int j = 0; j < nbCol; j++) {
                grid[i][j] = " ";
            }
        }

    }

    public void affichage() {
        System.out.println("     A   B   C   D   E   F   G   H   I   J ");
        for (int i = 0; i < nbLine; i++) {
            if (i == 9) {
                System.out.print(i + 1);
            } else {
                System.out.print(1 + i + " ");
            }
            for (int j = 0; j < nbCol; j++) {

                System.out.print(" | " + grid[i][j]);
            }
            System.out.println(" | ");
        }
        System.out.println();
    }

    public void placerMyShots(int letter, int number) {
        if (grid[letter - 1][number] == " ") {
            grid[letter - 1][number] = "¤";
        }
    }

    public void placerMyGoodShots(int letter, int number) {
        if (grid[letter - 1][number] == " ") {
            grid[letter - 1][number] = "!";
        } else if (grid[letter - 1][number] == "¤") {
            grid[letter - 1][number] = "!";
        }
    }

    public void placerBateau(int letter, int number) {
        if (grid[letter - 1][number] == " ") {
            grid[letter - 1][number] = "#";
        }
    }

    public void placerEnemyShots(int letter, int number) {
        if (grid[letter - 1][number] == " ") {
            grid[letter - 1][number] = "O";
        } else if (grid[letter - 1][number] == "#") {
            grid[letter - 1][number] = "!";
        }

    }
}