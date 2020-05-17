import java.io.IOException;
import java.util.Random;

public class TableStrategy implements Strategy {
    boolean shouldBet = false;
    int hitPercentage = 0;

    public boolean betting(Deck currDeck, int gameValue, Dealer myDealer) throws IOException {
        GetPropertyValues properties = new GetPropertyValues();
        String stringValue = Integer.toString(gameValue);

        hitPercentage = Integer.parseInt(properties.getPropValues(stringValue));
        shouldBet = percent(hitPercentage);
        return shouldBet;
    }

    public void testOutput() {
        System.out.println("This is Table Strategy.");
    }

    public boolean percent(int percentage) {
        Random rand = new Random();
        return (rand.nextInt(100) < percentage);
    }
}
