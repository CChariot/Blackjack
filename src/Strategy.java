import java.io.IOException;

public interface Strategy {

    static boolean betting(Deck currDeck, int gameValue, Dealer myDealer) {
        return false;
    }

    void testOutput();
}
