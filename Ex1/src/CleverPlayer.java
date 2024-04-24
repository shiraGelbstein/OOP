import java.util.Random;

public class CleverPlayer implements Player{

    //constructor
    public CleverPlayer() {}

    /**
     * plays like the whatever player 55% of the time and the rest like
     * the genius. puts the chosen mark of the board.
     * @param board game board
     * @param mark current mark of the player
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        Random rand = new Random();
        int randomInt = rand.nextInt(0,100);
        if(randomInt<55) {
            WhateverPlayer wp = new WhateverPlayer();
            wp.playTurn(board,mark);
        }
        else{
            GeniusPlayer gp = new GeniusPlayer();
            gp.playTurn(board,mark);
        }
    }
}
