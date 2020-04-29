public class Card {

    private String suit;
    private int value;

    Card(int cardValue, String cardSuit) {
        this.value = cardValue;
        this.suit = cardSuit;
    }

    private String getSuit() {
        return suit;
    }

    int getValue() {
        return value;
    }

    public String toString(){
        return getValue() + " , " + getSuit();
    }

}