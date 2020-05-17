public class CountStrategy implements Strategy {
    int count;

    @Override
    public boolean betting(Deck currDeck, int gameValue, Dealer myDealer) {
        return true;
    }

    public void testOutput() {
        System.out.println("This is Count Strategy.");
    }
}
