public class CountStrategy implements Strategy {
    @Override
    public boolean betting(int gameValue, Dealer myDealer) {
        return true;
    }

    public void testOutput() {
        System.out.println("This is Count Strategy.");
    }
}
