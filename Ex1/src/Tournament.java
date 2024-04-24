//import java.util.Locale;
//
//public class Tournament {
//    public final static String UNKNOWN_PLAYER_NAME =
//            "Choose a player, and start again.\nThe players: [human, clever, whatever, genius]";
//
//    public final static String UNKNOWN_RENDERER_NAME =
//            "Choose a renderer, and start again. \nPlease choose one of the following [console, none]";
//
//    static String[] PLAYERS_TYPES =  {"human","clever","whatever","genius"};
//    static String[] RENDERS_TYPES =  {"none","console"};
//
//
//    private final int rounds;
//    private final Renderer renderer;
//    private final Player player1;
//    private final Player player2;
//
//    /**
//     * A normal constructor.
//     * @param rounds
//     * @param renderer
//     * @param player1
//     * @param player2
//     */
//    public Tournament(int rounds, Renderer renderer, Player player1, Player player2) {
//        this.rounds = rounds;
//        this.renderer = renderer;
//        this.player1 = player1;
//        this.player2 = player2;
//    }
//
//
//    /**
//     * A function that runs the tournament == initialising games due to
//     * the number of rounds and counting the wins loose and ties of each player and prints it
//     * in the end.
//     * @param size the size of the board
//     * @param winStreak int
//     * @param playerName1 string of players 1 name
//     * @param playerName2 string of players 2 name
//     */
//    public void playTournament(int size, int winStreak, String playerName1, String playerName2){
//        int player1Wins=0;
//        int player2Wins=0;
//        int numOfTies=0;
//        for(int i=0;i<rounds;i++){
//            Game game ;
//            if(i%2==0){ // in the even rounds the first player marks X
//            game = new Game(player1,player2,size,winStreak,renderer);
//            }
//            else{ // in the uneven rounds the second player marks X
//                game = new Game(player2,player1,size,winStreak,renderer);
//            }
//            Mark cur_winner_mark = game.run();
//            if(i%2==0 && cur_winner_mark==Mark.X) { //if the round is an even number player 1 is an X.
//                player1Wins++;
//                continue;
//            }
//            if(i%2==0 && cur_winner_mark==Mark.O){ //if the round is even players 1 mark is O.
//                player2Wins++;
//                continue;
//            }
//            if(cur_winner_mark==Mark.O) { //if round is uneven player 1 is an O mark
//                player1Wins++;
//                continue;
//            }
//            if(cur_winner_mark==Mark.X){
//                player2Wins++;
//            }
//            else{
//                numOfTies++;
//            }
//        }
//        System.out.printf("######### Results #########\n Player 1, %s" +
//                " won: %d rounds\n Player 2, %s won: %d rounds\n Ties: %d\n"
//                ,playerName1,player1Wins,playerName2,player2Wins,numOfTies);
//    }
//
//
//    /**
//     * A function that check if there is a typo in the players name.
//     * @param arg a string
//     * @return true if there is a type false else.
//     */
//    private static boolean playerNameTypo(String arg){
//        for(int i=0;i<PLAYERS_TYPES.length;i++)
//        {
//            if(arg.equalsIgnoreCase(PLAYERS_TYPES[i])){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * A function that check if there is a typo in the renders name.
//     * @param arg a string
//     * @return true if there is a type false else.
//     */
//    private static boolean renderNameTypo(String arg)
//    {
//        for(int i=0;i< RENDERS_TYPES.length;i++)
//        {
//            if(arg.equalsIgnoreCase(RENDERS_TYPES[i])){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * A function thar check if there is a typo if so prints a specific error message.
//     * @param args3 string
//     * @param args4 string
//     * @param args5 sting
//     * @return true if there was a type false else.
//     */
//    static private boolean illegalInput(String args3,String args4,String args5)
//    {if (renderNameTypo(args3)) //checking render
//        {
//            System.out.println(UNKNOWN_RENDERER_NAME);
//            return true;
//        }
//        if (playerNameTypo(args4)) //checking first player
//        {
//            System.out.println(UNKNOWN_PLAYER_NAME);
//            return true;
//        }
//        if (playerNameTypo(args5)) //checking second player
//        {
//            System.out.println(UNKNOWN_PLAYER_NAME);
//            return true;
//        }
//        return false;
//    }
//
//
//    /**
//     * A function that runs the tournament with the args given in the command line.
//     * @param args command line strings
//     */
//    public static void main(String[] args){
//        //checking if args 3,4,5 is valid:
//        if(illegalInput(args[3],args[4],args[5])) {
//            return;
//        }
//        //converting args strings to int:
//        int numOfRounds = Integer.parseInt(args[0]);
//        int boardSize = Integer.parseInt(args[1]);
//        int winStreak = Integer.parseInt(args[2]);
//
//        // assuming the players giving args3,args4 and args5 are totally valid
//        String chosenRenderLowerCase = args[3].toLowerCase();
//        String player1LowerCase = args[4].toLowerCase();
//        String player2LowerCase = args[5].toLowerCase();
//
//        //building the players and the render using the factories:
//        RendererFactory rendererFactory = new RendererFactory();
//        Renderer chosenRender = rendererFactory.buildRenderer(chosenRenderLowerCase,boardSize);
//
//        PlayerFactory playerFactory = new PlayerFactory();
//        Player player1 = playerFactory.buildPlayer(player1LowerCase);
//        Player player2 = playerFactory.buildPlayer(player2LowerCase);
//
//        //finally running the tournament:
//        Tournament tournament = new
//                Tournament(numOfRounds,chosenRender,player1,player2);
//        tournament.playTournament(boardSize,winStreak,player1LowerCase,player2LowerCase);
//    }
//}

/*DO NOT CHANGE THIS CLASS*/
class Item {
    private String description;
    private float cost;

    public Item(String description, float cost) {
        this.description = description;
        this.cost = cost;
    }

    public float getCost(){
        return cost;
    }
    public String getDescription(){
        return description;
    }
}

/*ADD YOUR CODE HERE*/
class Phone extends Accessory{
    int manufactureYear;

    public Phone(String description, float cost,String model, int manufactureYear) {
        super(description, cost,model);
        this.manufactureYear = manufactureYear;
    }
}

//2. RefurbishedPhone – represents a phone that has been refurbished. In addition to the regular phone properties and methods, each // refurbished phone has:
//int discountPercent - discount in percentage over the original price of the phone.
//String getDescription() - returns the phone description with the prefix "Refurbished ".
//float getCost() - return the cost of the phone after the discount.

class RefurbishedPhone extends Accessory {
    int discountPercent;

    public RefurbishedPhone(String description, float cost,String model ,int discountPercent) {
        super(description, cost, model);
        this.discountPercent = discountPercent;
    }

    @Override
    public float getCost() {
        return super.getCost()*discountPercent;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+"Refurbished ";
    }
}

//Accessory – represents an accessory for sale in the shop.  It has all the properties and methods of an Item and in addition:
//String model - the model of the phone that it is compatible with.
//String getModel() - returns the model.
// String getDescription() - returns the accessory description with the model as a prefix.

class Accessory extends Item {
   private final String model;

    public Accessory(String description, float cost, String model) {
        super(description, cost);
        this.model = model;
    }

    public String getModel()
    {
        return model;
    }
    public String getDescription()
    {
        return super.getDescription() +model;
    }
}



//4. Cart – represents a shopping cart that holds an array of items. The API of the class:
//constructor - initialize the array of items. You can assume that there will be maximum 100 items in the cart.
//float getCost() – return the total cost of all items in the cart.
//float getShippingFee() – calculates the shipping fee of the cart. Each item in the cart adds 1$ to the fee, but if the total cost of the cart is more than 1000$, the cart gets free shipping.
//void addItem(Item item) – adds an item to the cart.
//Item[] getItems() – return a copy of the array of items in the cart (No need to deep copy the items).
// Class for Cart
class Cart {
    private static final int MAX_ITEMS = 100;
    private final Item[] items;
    private int itemCount;

    public Cart() {
        this.items = new Item[MAX_ITEMS];
        this.itemCount = 0;
    }

    public float getCost() {
        float totalCost = 0;
        for (int i = 0; i < itemCount; i++) {
            totalCost += items[i].getCost();
        }
        return totalCost;
    }

    public float getShippingFee() {
        if (getCost() > 1000) {
            return 0; // Free shipping
        } else {
            return itemCount; // $1 shipping fee per item
        }
    }

    public void addItem(Item item) {
        if (itemCount < MAX_ITEMS) {
            items[itemCount++] = item;
        } else {
            System.out.println("Cart is full. Cannot add more items.");
        }
    }

    public Item[] getItems() {
        return items.clone(); // Return a copy of the array
    }
}