public class RandomStrategy implements Strategy {
    @Override
    public boolean betting() {
        return true;
    }

    public void testOutput() {
        System.out.println("This is Random Strategy.");
    }
}
