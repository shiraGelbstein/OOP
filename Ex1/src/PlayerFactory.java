import java.util.Locale;

public class PlayerFactory {
    //constructor
    public PlayerFactory(){}

    /**
     * A function that gets a type string and "builds" = initialise the player
     * @param type string
     * @return a brand-new player
     */
    public Player buildPlayer(String type){
        Player player = null;
        type = type.toLowerCase(Locale.ROOT);
        player = switch (type) {
            case ("human") -> new HumanPlayer();
            case ("genius") -> new GeniusPlayer();
            case ("clever") -> new CleverPlayer();
            case ("whatever") -> new WhateverPlayer();
            default -> player;
        };
        return player;
    }
}
