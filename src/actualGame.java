import java.io.IOException;

public class actualGame {

    public static void main(String[] str) throws IOException {
//        GameManager Manager = GameManager.getInstance();
//        System.out.println("Game started.");
//        Manager.playGame();

//        RandomGameManager RandomStrategy = RandomGameManager.getInstance();
//        System.out.println("Game started.");
//        RandomStrategy.playGame();
//
//        TableGameManager TableStrategy = TableGameManager.getInstance();
//        System.out.println("Game started.");
//        TableStrategy.playGame();

//        CountGameManager CountStrategy = CountGameManager.getInstance();
//        System.out.println("Game started.");
//        CountStrategy.playGame();

        MemorizeGameManager MemorizeStrategy = MemorizeGameManager.getInstance();
        System.out.println("Game started.");
        MemorizeStrategy.playGame();
    }
}
