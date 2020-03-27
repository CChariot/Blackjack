public class Dealer implements person {

    int game_value = 0;

    int money_let = 10000;

    @Override
    public void hit(Deck inputDeck){

        game_value += inputDeck.deal().getValue();

    }
}