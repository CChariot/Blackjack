public class Dealer implements person {
    private volatile static Dealer obj;
    int game_value = 0;
    int money_left = 100000;

    private Dealer() {
    }

    //singleton design pattern
    static synchronized Dealer getInstance() {
        if (obj == null) {
            //make sure it's thread safe
            synchronized (Dealer.class) {
                if (obj == null) {
                    obj = new Dealer();
                }
            }
        }
        return obj;
    }
    @Override
    public void hit(Deck inputDeck){
        game_value += inputDeck.deal().getValue();
    }

    boolean shouldHit(Player[] P, Dealer D) {
        int winning_count = 0;
        for(Player x : P) {
            if(x.game_value < D.game_value){
                winning_count++;
            }
        }
        if(winning_count <= (P.length/2)){
            return true;
        }
        else if(D.game_value < 15){
            return true;
        }
        else{
            return false;
        }
    }

    public int getMoney(){
        return money_left;
    }

    @Override
    public void cleanHand() {
        game_value = 0;
    }
}