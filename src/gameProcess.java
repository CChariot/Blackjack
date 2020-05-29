import java.io.IOException;

//Builder Design Pattern
public interface gameProcess {
    void initializeDeck();

    void initializePlayer();

    boolean needsRefill(Player[] players_pool);

    void takeBetting(Player[] players_pool);

    void playersHit(Player[] players_pool, Dealer myDealer, Deck currDeck) throws IOException;

    void countMoney(Player[] players_pool, Dealer myDealer);

    void cleanTable(Player[] players_pool, Dealer myDealer);

    void displayMoney(Player[] players_pool, Dealer myDealer);

}
