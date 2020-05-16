import java.io.IOException;

public class actualGame {

    public static void main(String[] str) throws IOException {
        GameManager Manager = GameManager.getInstance();
        System.out.println("Game started.");
        Manager.playGame();
    }
}
