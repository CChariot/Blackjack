public class Dealer implements person {

    int game_value = 0;
    int money_left = 100000;

    public Dealer(){}
    @Override
    public void hit(Deck inputDeck){
        game_value += inputDeck.deal().getValue();
    }

    @Override
    public boolean shouldHit(Player P, Dealer D) {
        if(D.game_value == 21){
            return false;
        }
        else if(D.game_value < 17){
            return true;
        }
        else {
            return false;
        }
    }

    public int getMoney(){
        return money_left;
    }
}