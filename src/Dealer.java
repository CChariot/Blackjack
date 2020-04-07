public class Dealer implements person {

    int game_value = 0;
    int money_left = 100000;

    public Dealer(){}
    @Override
    public void hit(Deck inputDeck){
        game_value += inputDeck.deal().getValue();
    }

    public boolean shouldHit(Player [] P, Dealer D) {
        int winning_count = 0;
        for(Player x : P) {
            if(x.game_value < D.game_value){
                winning_count++;
            }
        }
        if(winning_count >= (P.length/2) || D.game_value > 21){
            return false;
        }

        return true;
    }

    public int getMoney(){
        return money_left;
    }
}