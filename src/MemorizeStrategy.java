public class MemorizeStrategy implements Strategy {

    public boolean betting(Deck currDeck, int gameValue, Dealer myDealer) {
        int countBig = 0, countSmall = 0;
        for (int i : currDeck.DealtCard) {
            if (i < 7) countSmall++;
            else countBig++;
        }
        if (gameValue == 17) {
            return false;
        }
        if (gameValue < 12) {
            return true;
        } else if (gameValue == 16 && countSmall / currDeck.totalCard < 0.5 && myDealer.game_value > 6) {
            return true;
        } else if (gameValue > 12 && gameValue < 16
                && countBig / currDeck.totalCard > 0.6 && myDealer.game_value > 6) {
            return true;
        } else if (gameValue == 12 && countBig / currDeck.totalCard > 0.4) {
            return true;
        }
        return false;
    }

    public void testOutput() {

        System.out.println("This is Memorize Strategy.");
    }
}
