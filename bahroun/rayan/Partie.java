package bahroun.rayan;

public class Partie {
    private Joueur player1, player2;
    private int round;
    private int game;
    private String firstPlayer;

    

    public Partie(Joueur player1, Joueur player2) {
        round = 0;
        this.player1 = player1;
        this.player2 = player2;
    }
    
    //
    //
    //
    // methods
    //
    //
    //
    public boolean changeRound() {
        boolean newRound = false;
        if (player2.isCurrentPlayer()) {
            round = round + 1;
            newRound = true;
        }
        player1.howManyShipsAlive();
        player2.howManyShipsAlive();
        changePlayer();
        return newRound;
    }
    
    public void newGame() {
        game++;
        System.out.println(player1.getName() + " place ses bateaux :");
        player1.placement();
        System.out.println(player2.getName() + " place ses bateaux :");
        player2.placement();
        if (!(game % 2 == 0)) {
            System.out.println(player2.getName() + " joue");
            player2.setCurrentPlayer(true);
            firstPlayer = player2.getName();
            player1.setCurrentPlayer(false);
            
        } else {
            System.out.println(player1.getName() + " joue");
            player2.setCurrentPlayer(false);
            player1.setCurrentPlayer(true);
            firstPlayer =player1.getName();
        }
    }

    public void newGamePvsIA() {
        game++;
        System.out.println(player2.getName() + " place ses bateaux :");
        player2.placement();
        if (!(game % 2 == 0)) {
            System.out.println(player2.getName() + " joue");
            player2.setCurrentPlayer(true);
            firstPlayer =player2.getName();
            player1.setCurrentPlayer(false);
            
        } else {
            System.out.println(player1.getName() + " joue");
            player2.setCurrentPlayer(false);
            player1.setCurrentPlayer(true);
            firstPlayer =player1.getName();
        }
    }

    public void newGameAuto(IAlvl ia, IAlvl ia2) {
        game++;
        if (!(game % 2 == 0)) {
            System.out.println(player2.getName() + " commence la partie");
            player2.setCurrentPlayer(true);
            firstPlayer =player2.getName();
            player1.setCurrentPlayer(false);
        } else {
            System.out.println(player1.getName() + " commence la partie");
            player2.setCurrentPlayer(false);
            player1.setCurrentPlayer(true);
            firstPlayer =player1.getName();
        }
    }

    public boolean isFinished() {
        return player1.gameOver() || player2.gameOver();
    }

    public Joueur winner() {
        if (player1.gameOver())
            return player2;
        else
            return player1;
    }

    public void changePlayer() {
        if (player1.isCurrentPlayer()) {

            player2.setCurrentPlayer(true);
            player1.setCurrentPlayer(false);
        } else {
            player1.setCurrentPlayer(true);
            player2.setCurrentPlayer(false);
        }
    }
    
    

    //
    //
    //
    // get & set
    //
    //
    //


    public Joueur getCurrentPlayer() {
        if (player1.isCurrentPlayer()) {
            return player1;
        } else {
            return player2;
        }
    }

    public Joueur getPassivePlayer() {
        if (player1.isCurrentPlayer()) {
            return player2;
        } else {
            return player1;
        }
    }
    public Joueur getPlayer1() {
        return player1;
    }

    public void setPlayer1(Joueur player1) {
        this.player1 = player1;
    }

    public Joueur getPlayer2() {
        return player2;
    }

    public void setPlayer2(Joueur player2) {
        this.player2 = player2;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(String firstPlayer) {
        this.firstPlayer = firstPlayer;
    }
    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }
}
