import java.util.Random;

public class WhateverPlayer implements Player{
    public WhateverPlayer(){}

    public void playTurn(Board board, Mark mark) {
        Random rand = new Random();
        int randomRow = rand.nextInt(0,board.getSize());
        int randomCol = rand.nextInt(0,board.getSize());
        while(!board.putMark(mark, randomRow, randomCol)){
            randomRow = rand.nextInt(0,board.getSize());
            randomCol = rand.nextInt(0,board.getSize());
        }
    }
}
