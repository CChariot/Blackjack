import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

//        MemorizeGameManager MemorizeStrategy = MemorizeGameManager.getInstance();
//        System.out.println("Game started.");
//        MemorizeStrategy.playGame();

        Deck testDeck = new Deck(3);
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < 1000; i++) {
            int value = testDeck.deal().getValue();
            map.put(value, map.getOrDefault(value, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            float percentage = (entry.getValue() * 100 / 1000);
            System.out.println("There are " + entry.getValue() + " of " + entry.getKey() + ". " + percentage + "%");
        }
    }
}
