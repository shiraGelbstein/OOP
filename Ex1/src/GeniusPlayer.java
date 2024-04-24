public class GeniusPlayer implements Player {
    public GeniusPlayer(){}

    /**
     * A function that marks the given mark in the most empty row in the board
     * in the most left col.
     * @param board game board.
     * @param mark the players mark.
     * @return "marked" < 0 if didnt mark and an int > 0 if marked.
     */
    private int bestCurMove(Board board, Mark mark) {
        int marked = -1;

        int[] RowList = new int[board.getSize()];
        for (int row = 0; row < board.getSize(); row++) {
            int numOfBlanckInRow = 0;
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getMark(row, col) == Mark.BLANK){ //checking the symbol
                numOfBlanckInRow ++;
                }
            }
            RowList[row] = numOfBlanckInRow;
        }

        int max = 0;
        int maxIndex = -1;

        for (int i = 0; i < RowList.length; i++) {
            if (RowList[i] > max) {
                max = RowList[i];
                maxIndex = i;
            }
        }

        for(int col=0;col< board.getSize();col++){
            if(board.getMark(maxIndex, col) == Mark.BLANK){
                board.putMark(mark,maxIndex,col);
                marked = 1;
            }
        }
        return marked;
    }


    /**
     * A function that plays the genius turn using the strategy of empty lines
     * if possible else plays like the whatever
     * @param board game board
     * @param mark the players mark
     */
    public void playTurn(Board board, Mark mark) {
        int marked = bestCurMove(board, mark);
        if (marked < 0) {
            Player cp = new WhateverPlayer();
            cp.playTurn(board, mark);
        }
    }
}



