public class CountStrategy implements Strategy {
    int count;

    public static boolean betting(Deck currDeck, int gameValue, Dealer myDealer) {
        if (currDeck.count > currDeck.totalCard / 2) return true;

        else {
            return false;
        }
    }

    public void testOutput() {
        System.out.println("This is Count Strategy.");
    }
}
