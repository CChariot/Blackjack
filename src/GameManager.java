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

        //start of the game
        //initialize deck
        Deck currDeck = new Deck(deck_used);
        currDeck.shuffle();

        //initialize players
        Player [] players_pool = new Player[player_in];
        for(int i = 0; i < player_in; i++){
            players_pool[i] = new Player();
        }

        //initialize dealer
        Dealer myDealer = new Dealer();
        System.out.println(myDealer.game_value);

        //deal cards for players
        //players get two cards at the beginning
        for(int i = 0; i < 2; i++) {
            for (int j = 0; j < player_in; j++) {
                players_pool[j].hit(currDeck);
            }
        }
        //deal first cards for dealer
        myDealer.hit(currDeck);

        //hitting for all players
        for(int i = 0; i< player_in; i++){
            while(players_pool[i].shouldHit(players_pool[i],myDealer)){
                players_pool[i].hit(currDeck);
            }
        }

        //second card for dealer;
        myDealer.hit(currDeck);
        //hitting for dealer
        for(int i = 0; i< player_in; i++){
            while(myDealer.shouldHit(players_pool[i],myDealer)){
                myDealer.hit(currDeck);
            }
        }

        //Determine winning
        for(int i = 0; i< player_in; i++){
            if(players_pool[i].game_value == myDealer.game_value){
                //player got pushed, do nothing
            }
            else if(players_pool[i].game_value > myDealer.game_value){
                players_pool[i].money_left += 10;
                myDealer.money_left -= 10;
            }
            else{
                players_pool[i].money_left -= 10;
                myDealer.money_left += 10;
            }
        }

        for(int i = 0; i< player_in; i++){
            System.out.println(players_pool[i].getMoney());
        }

        //test section:
        Card oneCard = new Card(1,"Spades");
        System.out.println(oneCard.toString());

        Deck myDeck = new Deck(3);
        System.out.println(myDeck.getFirstCard().toString());

        myDeck.shuffle();
        System.out.println(myDeck.getFirstCard().toString());
    }

    boolean won(Dealer D, Player P){
        return (D.game_value < P.game_value);
    }

}