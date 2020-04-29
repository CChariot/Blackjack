public class Player implements person {

    int game_value;
    int money_left;
    int bet_amount;
    boolean isFinal;

    Player() {
        game_value = 0;
        money_left = 1000;
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

    boolean shouldHit(Player P, Dealer D) {
        if(P.game_value == 21){
            return false;
        }
        else if(P.game_value < 11){
            return true;
        } else return P.game_value < 12 && D.game_value > 9;
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