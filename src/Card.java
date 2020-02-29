public class Card {

    private String suit;
    private int value;

    public Card(int cardValue, String cardSuit){
           this.value = cardValue;
           this.suit = cardSuit;
    }

    public String getSuit(){
        return suit;
    }

    public int getValue(){
        return value;
    }

    public String toString(){
        return getValue() + " , " + getSuit();
    }

}
