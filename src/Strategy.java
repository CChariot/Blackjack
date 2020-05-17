import java.io.IOException;

public interface Strategy {
    boolean betting(Deck currDeck, int gameValue, Dealer myDealer) throws IOException;

    void testOutput();
}
