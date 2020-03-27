public class Player implements person {

    int game_value = 0;

    int money_let = 1000;

    @Override
    public void hit(Deck inputDeck){

        game_value += inputDeck.deal().getValue();

    }

    public void initial_game(){

    }

}