import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Deck {

    private int totalDeck;
    private Card[] currDeck;
    private int cardUsed;
    List<Integer> DealtCard = new ArrayList<>();
    int count;
    int totalCard;

    //constructor for multiple decks of card
    Deck(int input) {
        totalDeck = input;
        totalCard = 52 * input;
        currDeck= new Card[52 * input];
        String [] Suits = {"Spade", "Diamond", "Heart", "Club"};
        int countCard = 0;
        for(int decks = 0; decks < input; decks++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < 14; j++) {
                    currDeck[countCard] = new Card(j, Suits[i]);
                    countCard++;
                }
            }
        }
        cardUsed =0;
    }

    void shuffle() {
        for (int i = 0; i < DealtCard.size(); i++) {
            DealtCard.set(i, 0);
        }

        Random randInt = new Random();
        int totalCards = totalDeck * 52;
        for (int i = 0; i < totalCards; i++ ) {
            int random = i + randInt.nextInt((totalCards) - i);
            //Swap position
            Card temp = currDeck[i];
            currDeck[i] = currDeck[random];
            currDeck[random] = temp;
        }
        count = 0;
        cardUsed = 0;
    }

    Card deal() {
        if(cardUsed == totalDeck * 52) {
            shuffle();
        }

        cardUsed ++;

        DealtCard.add(currDeck[cardUsed - 1].getValue());
        if (currDeck[cardUsed - 1].getValue() < 6) count++;
        else if (currDeck[cardUsed - 1].getValue() > 8) count--;
        return currDeck[cardUsed-1];
    }
}
