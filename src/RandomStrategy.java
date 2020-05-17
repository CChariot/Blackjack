import java.util.Random;

public class RandomStrategy implements Strategy {

    @Override
    public boolean betting(Deck currDeck, int gameValue, Dealer myDealer) {
        Random randInt = new Random();

        int random = 0 + randInt.nextInt(1);
        return random == 0 ? true : false;
    }

    public void testOutput() {
        System.out.println("This is Random Strategy.");
    }
}
