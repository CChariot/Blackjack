public class Player implements person {

    int game_value;
    int money_left;
    int bet_amount;
    int first_card;
    int second_card;
    boolean isFinal;
    //variables to take care of split case;
    boolean isSplit;
    int split_amount;
    int split_game_value;

    Player() {
        game_value = 0;
        money_left = 1000;
    }

    public int addCount(int input) {
        if (input < 6) return 1;
        else if (input > 8 && input < 14) return -1;
        else return 0;
    }

    public void firstHit(Deck inputDeck) {
        int value = inputDeck.deal().getValue();
        game_value += value;
        first_card = value;
    }

    public void secondHit(Deck inputDeck) {
        int value = inputDeck.deal().getValue();
        game_value += value;
        first_card = value;
    }

    public void splitHit(Deck inputDeck) {
        int value = inputDeck.deal().getValue();
        split_game_value += value;
    }
    @Override
    public void hit(Deck inputDeck){
        int value = inputDeck.deal().getValue();
        game_value += value;
    }

    public void deposit(int amount){
        money_left += amount;
    }

    boolean shouldDouble() {
        return game_value == 11;
    }

    boolean shouldHit(int game_value, Dealer D) {
        if (game_value == 21) {
            return false;
        } else if (game_value < 11) {
            return true;
        } else return game_value < 12 && D.game_value > 9;
    }

    public int getMoney(){
        return money_left;
    }

    @Override
    public void cleanHand() {
        game_value = 0;
        bet_amount = 0;
    }
}