import java.util.Scanner;

public class GameManager
{
    private static int deck_used;
    private static int player_in;

    public static void main(String[] args){

        //initializing the game
        Scanner scan1 = new Scanner(System.in);
        System.out.print("Enter number of deck(Max 3): ");
        deck_used = scan1.nextInt();

        Scanner scan2 = new Scanner(System.in);
        System.out.print("Enter number of player(Max 5): ");
        player_in = scan2.nextInt();

        Deck currDeck = new Deck(deck_used);
        currDeck.shuffle();


        Player [] players_pool = new Player[player_in];
        for(Player a: players_pool){
            a = new Player();
        }

        //start of the game




        //test section:
        Card oneCard = new Card(1,"Spades");
        System.out.println(oneCard.toString());

        Deck myDeck = new Deck(1);
        System.out.println(myDeck.getFirstCard().toString());

        myDeck.shuffle();
        System.out.println(myDeck.getFirstCard().toString());
    }

}