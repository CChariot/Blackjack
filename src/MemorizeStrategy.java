public class MemorizeStrategy implements Strategy {

    public boolean betting(int gameValue, Dealer myDealer) {
        return true;
    }

    public void testOutput() {

        System.out.println("This is Memorize Strategy.");
    }
}
