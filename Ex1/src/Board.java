public class Board {
    int DEFAULT_NUM_OF_ROWS = 4;
    int DEFAULT_NUM_OF_COLS = 4;
    int DEFAULT_SIZE = 4;


    //for a readable cood I initialised rows and cols
    private final int numOfRows;
    private final int numOfCols;

    private final int size;

    private final Mark[][] board;

    //default constructor
    Board() {
        this.numOfRows = DEFAULT_NUM_OF_ROWS;
        this.numOfCols = DEFAULT_NUM_OF_COLS;
        this.size = DEFAULT_SIZE;
        this.board = new Mark[numOfRows][numOfCols];
        for(int row=0;row<numOfRows;row++)
        {
            for(int col=0;col<numOfCols;col++)
            {
                board[row][col] = Mark.BLANK;
            }
        }
    }

    //constructor

    /**
     * initialise a board size*size
     * and puts a Blank mark all over the board
     * @param size an int
     */
    Board(int size) {
        this.numOfRows = size;
        this.numOfCols = size;
        this.size = size;
        this.board = new Mark[numOfCols][numOfRows];
        for(int row=0;row<numOfRows;row++)
        {
            for(int col=0;col<numOfCols;col++)
            {
                board[row][col] = Mark.BLANK;
            }
        }
    }

    //getters:

    /**
     * Getter
     * @return the size of the board.
     */
    public int getSize() {
        return size;
    }

    //methods:
    /**
     * A method that returns the mark in the given row col on the
     * board if they are legal, otherwise returns a BLANK mark.
     *
     * @param row
     * @param col
     * @return A Blank if illegal otherwise board[row][col].
     */
    public Mark getMark(int row, int col) {
        if (0<= row && row < numOfRows && 0<= col && col < numOfCols) {
            return board[row][col];
        }
        return Mark.BLANK;
    }

    /**
     * A function that checks if marking is possible. ( possible== place and mark)
     * @param row
     * @param col
     * @return true if legal else false.
     */
    private boolean is_legal_to_mark(int row, int col)
    {
        return 0<=row && row < numOfRows&& 0<=col && col < numOfCols && (board[row][col] == Mark.BLANK);
    }

    /**
     * A function that puts the mark on the board if legal.
     * @param mark
     * @param row
     * @param col
     * @return true if marked false if it was not legal to mark.
     */
    public boolean putMark(Mark mark, int row, int col){
    if (is_legal_to_mark(row,col)) {
        board[row][col] = mark;
        return true;
    }
        return false;
}

}
