public class Player implements person {

    int game_value;
    int money_left;
    int bet_amount;

    public Player(){
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

    public boolean shouldHit(Player P, Dealer D){
        if(P.game_value ==21){
            return false;
        }
        else if(P.game_value < 12 && D.game_value > 5){
            return true;
        }
        else if(P.game_value <15 && D.game_value > 8){
            return true;
        }
        else{
            return false;
        }

    }

    public int getMoney(){
        return money_left;
    }

}