
public class Deck {
    private Card [] currDeck = new Card[52];
    private int cardUsed;

    public Deck(){
        String [] Suits = {"Spade", "Diamond", "Heart", "Club"};
        int count = 0; //keeps track of total cards
        int value = 1; //1-13 as Ace-King
        int suitCount =0; //Pointer for Suits array
        while(count <13){ //Ace - King, Spade
            currDeck[count] = new Card(value, Suits[suitCount]);
            value++;
            count++;
        }suitCount++; value = 1;
        while(count <26){ //Ace - King, Diamond
            currDeck[count] = new Card(value, Suits[suitCount]);
            value++;
            count++;
        }suitCount++; value = 1;
        while(count <39){ //Ace - King, Heart
            currDeck[count] = new Card(value, Suits[suitCount]);
            value++;
            count++;
        }suitCount++; value = 1;
        while(count <52){ //Ace - King, Club
            currDeck[count] = new Card(value, Suits[suitCount]);
            value++;
            count++;
        }

        cardUsed =0;
        }

    public void shuffle() {
        for ( int i = 51; i > 0; i-- ) {
            int random = (int)(Math.random()*(51));
            Card temp = currDeck[i];
            currDeck[i] = currDeck[random];
            currDeck[random] = temp;
        }
        cardUsed = 0;
    }

    public Card getFirstCard(){
        return currDeck[50];
    }

    public int getCardLeft(){
        return 52 - cardUsed;
    }

    public Card deal(){
        if(cardUsed == 52) shuffle();
        cardUsed ++;
        return currDeck[cardUsed-1];
    }
}
