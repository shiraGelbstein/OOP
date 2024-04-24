public class HumanPlayer implements Player {
    private final static String INVALID_COORDINATE = "Invalid mark position, " +
            "please choose a different position.\nInvalid coordinates, type again: ";

    private final static String OCCUPIED_COORDINATE = "Mark position is already occupied.\n" +
            "Invalid coordinates, type again: ";
    private static String playerRequestInputString(String markSymbol) {
        return "Player " + markSymbol + ", type coordinates: ";
    }

    //constructor:
    public HumanPlayer(){}

    /**
     * A function that asks the human for a coordinate input, if the input is not available
     * it asks again endlessly. when he chose a correct answer it marks it on the board.
     * @param board game board.
     * @param mark the human cur mark to mark on the board.
     */
    public void playTurn(Board board, Mark mark) {

        //requesting the player for an input:
        if(mark == Mark.X){
        System.out.println(playerRequestInputString("X"));
        }
        else{
            System.out.println(playerRequestInputString("O"));
        }

        // checking for validation and marking if valid:
        while (true) {
            //the valid format is for ex:"31" when 3 is thw row and col is 1.

            int playerInput = KeyboardInput.readInt();

            if (playerInput < 10 && board.putMark(mark, 0, playerInput)) break;

            if (playerInput < 100 && board.putMark(mark, playerInput / 10, playerInput % 10)) break;

            if (board.getMark(playerInput / 10, playerInput % 10) != Mark.BLANK) {
                System.out.println(OCCUPIED_COORDINATE); //occupied coordinates message
            } else {
                System.out.println(INVALID_COORDINATE); //invalid coordinates message
            }
        }
    }
}

