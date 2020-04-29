public class actualGame {

    public static void main(String[] str) {
        GameManager Manager = GameManager.getInstance();
        System.out.println("Game started.");
        Manager.playGame();
    }
}
