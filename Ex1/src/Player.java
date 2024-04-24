public interface Player {
    /**
     * Each player will know how to play a turn with its own strategy.
     * @param board game board to play on the turn.
     * @param mark a mark to put on the board .
     */
    void playTurn(Board board, Mark mark);
}
