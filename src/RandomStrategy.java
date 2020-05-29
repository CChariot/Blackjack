import java.util.Random;

public class RandomStrategy implements Strategy {

    public static boolean betting(Deck currDeck, int gameValue, Dealer myDealer) {
        Random random = new Random();

        if (random.nextBoolean()) {
            return true;
        } else {
            return false;
        }
    }

    public void testOutput() {
        System.out.println("This is Random Strategy.");
    }
}
