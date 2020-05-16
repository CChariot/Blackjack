import java.io.IOException;

public interface Strategy {
    boolean betting(int gameValue, Dealer myDealer) throws IOException;

    void testOutput();
}
