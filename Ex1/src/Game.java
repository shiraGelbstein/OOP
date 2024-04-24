import java.awt.image.renderable.ContextualRenderedImageFactory;

public class Game {
    static int DEFAULT_WIN_STREAK = 3;
    Player playerX;
    Player playerO;
    Renderer renderer;
    int winStreak;
    Board gameBoard;

    /**
     * A default constructor the inisialise a game board and
     * the winstreak with the default values.
     * @param playerX
     * @param playerO
     * @param renderer
     */
    Game(Player playerX, Player playerO, Renderer renderer){
        gameBoard = new Board();
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer=renderer;
        this.winStreak = DEFAULT_WIN_STREAK;
    }

    /**
     * Non default constructor that initialise a game with the values
     * of the givan arguments.
     * @param playerX
     * @param playerO
     * @param size
     * @param winStreak
     * @param renderer
     */
    Game(Player playerX,Player playerO, int size, int winStreak,Renderer renderer){
        gameBoard = new Board(size);
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer=renderer;
        if(winStreak>=2 && winStreak<= gameBoard.getSize()){
            this.winStreak = winStreak;
        }
        else{
            this.winStreak = gameBoard.getSize();
        }
    }

    //getters

    /**
     * a getter
     * @return games win streak
     */
    public int getWinStreak(){
        return winStreak;
    }

    /**
     * a getter
     * @return game boards size
     */
    public int  getBoardSize(){
        return gameBoard.getSize();
    }

    //methods

    /**
     * A method that checks if there is a win =
     * marked places on board the length of the streak in the right
     * direction.
     * @return true if there is a win false there isnt.
     */
    private boolean isWin()
    {
       for(int row = 0; row< gameBoard.getSize();row++)
       {
           for(int col=0; col<gameBoard.getSize();col++)
           {
               Mark curMark = gameBoard.getMark(row,col); //checking the symbol
               if(curMark==Mark.BLANK){continue;} //if symbol empty it is surely not a win
               int leftCounter = 1;
               int downCounter = 1;
               int diagonLeftCounter =1;
               int diagonRightCounter =1;

               for(int i=1;i<winStreak;i++)
               {
                   if(gameBoard.getMark(row,col+i)==curMark) {
                       leftCounter++;
                   }
                   if(gameBoard.getMark(row+i,col)==curMark) {
                       downCounter++;
                   }
                   if(gameBoard.getMark(row+i,col+i)==curMark) {
                       diagonLeftCounter++;
                   }
                   if(gameBoard.getMark(row-i,col-i)==curMark) {
                       diagonRightCounter++;
                   }
               }
               if(leftCounter == winStreak || downCounter == winStreak ||
                       diagonLeftCounter==winStreak || diagonRightCounter==winStreak)
               {
                   return true;
               }

           }
       }
       return false;
    }


    /**
     * A method that checks if the game is over == if all
     * places on the board are marked.(helps if its a tie).
     * @return
     */
    private boolean isGameOver() {
        for (int row = 0; row < gameBoard.getSize(); row++) {
            for (int col = 0; col < gameBoard.getSize(); col++) {
                if (gameBoard.getMark(row, col) == Mark.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * A function that runs the game - play the players turns
     * with the right marks (due to even and eneven) and render the game board.
     * @return the winners mark if there is a winner if its a tie
     * returns the blank mark.
     */
    public Mark run() {
        int counter = 0;
        Mark curPlayerMark = Mark.BLANK;
        Player cur_player;
        while(!isWin() && !isGameOver())
        {
            if(counter%2 == 0) {
                curPlayerMark = Mark.X;
                cur_player = playerX;
            }
            else{
                curPlayerMark = Mark.O;
                cur_player = playerO;
            }
            counter++;
            cur_player.playTurn(gameBoard,curPlayerMark);

        renderer.renderBoard(gameBoard);
        }
        if(isWin()) {

            return curPlayerMark;
        }
    return Mark.BLANK;
    }
}

