public class Player implements person {

    int game_value;
    int money_left;
    int bet_amount;
    int first_card;
    int second_card;
    boolean isFinal;
    //variables to take care of split case;
    boolean isSplitted;
    int split_amount;
    int split_game_value;


    Player() {
        game_value = 0;
        money_left = 1000;
    }

    public void firstHit(Deck inputDeck) {
        game_value += inputDeck.deal().getValue();
        first_card = inputDeck.deal().getValue();
    }

    public void secondHit(Deck inputDeck) {
        game_value += inputDeck.deal().getValue();
        first_card = inputDeck.deal().getValue();
    }

    public void splitHit(Deck inputDeck) {
        split_game_value += inputDeck.deal().getValue();
    }
    @Override
    public void hit(Deck inputDeck){
        game_value += inputDeck.deal().getValue();
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