
public class main {


    public static void main(String[] args){
        
       Card oneCard = new Card(1,"Spades");
       System.out.println(oneCard.toString());

       Deck myDeck = new Deck();
       System.out.println(myDeck.getFirstCard().toString());

       myDeck.shuffle();
       System.out.println(myDeck.getFirstCard().toString());
    }
}
