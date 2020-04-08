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
        while(deck_used > 3){
            System.out.print("Max number of deck is 3, please try again: ");
            deck_used = scan1.nextInt();
        }

        Scanner scan2 = new Scanner(System.in);
        System.out.print("Enter number of player(Max 5): ");
        player_in = scan2.nextInt();
        while(player_in > 5){
            System.out.print("Max number of player is 5, please try again: ");
            player_in = scan2.nextInt();
        }


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

        while(!needsRefill(players_pool)) {
            //taking bets
            for (int i = 0; i < player_in; i++) {
                Scanner scan3 = new Scanner(System.in);
                System.out.print("Enter your betting amount for player " + (i + 1) + "\t");
                players_pool[i].bet_amount = scan3.nextInt();
                while (players_pool[i].bet_amount > players_pool[i].money_left) {
                    System.out.print("Please enter amount less or equal to what you have");
                    players_pool[i].bet_amount = scan3.nextInt();
                }
            }

            //deal cards for players
            //players get two cards at the beginning
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < player_in; j++) {
                    players_pool[j].hit(currDeck);
                }
            }
            //deal first card for dealer
            myDealer.hit(currDeck);

            //hitting for all players
            for (int i = 0; i < player_in; i++) {
                while (players_pool[i].shouldHit(players_pool[i], myDealer)) {
                    players_pool[i].hit(currDeck);
                }
            }

            //second card for dealer;
            myDealer.hit(currDeck);
            System.out.println("Dealer has " + myDealer.game_value);
            //hitting for dealer
            while (myDealer.shouldHit(players_pool, myDealer)) {
                myDealer.hit(currDeck);
            }

            countMoney(players_pool,myDealer);

            System.out.println("Dealer has " + myDealer.game_value);

            for (int i = 0; i < player_in; i++) {
                System.out.println("Player " + i + " has " + players_pool[i].game_value);
                System.out.println("Player " + i + " now has " + players_pool[i].getMoney());
            }

            cleanTable(players_pool,myDealer);

        }
    }

    public static boolean needsRefill(Player[] players_pool){

        for(Player x : players_pool){
            if(x.money_left == 0){
                return true;
            }
        }
        return false;
    }

    public static void countMoney(Player[] players_pool, Dealer myDealer){
        //Determine winning
        for (int i = 0; i < player_in; i++) {
            if (players_pool[i].game_value == myDealer.game_value) {
                //player got pushed, do nothing
            } else if (myDealer.game_value == 21) {
                players_pool[i].money_left -= players_pool[i].bet_amount;
                myDealer.money_left += players_pool[i].bet_amount;
            } else if (players_pool[i].game_value == 21) {
                players_pool[i].money_left += players_pool[i].bet_amount;
                myDealer.money_left -= players_pool[i].bet_amount;
            } else if (players_pool[i].game_value > 21) {
                players_pool[i].money_left -= players_pool[i].bet_amount;
                myDealer.money_left += players_pool[i].bet_amount;
            } else if (myDealer.game_value > 21) {
                players_pool[i].money_left += players_pool[i].bet_amount;
                myDealer.money_left -= players_pool[i].bet_amount;
            } else if (players_pool[i].game_value > myDealer.game_value && players_pool[i].game_value <= 21) {
                players_pool[i].money_left += players_pool[i].bet_amount;
                myDealer.money_left -= players_pool[i].bet_amount;
            } else if (players_pool[i].game_value < myDealer.game_value && myDealer.game_value <= 21) {
                players_pool[i].money_left -= players_pool[i].bet_amount;
                myDealer.money_left += players_pool[i].bet_amount;
            }
        }
    }
    public static void cleanTable(Player[] players_pool, Dealer myDealer){
        for(Player x: players_pool){
            x.cleanHand();
        }
        myDealer.cleanHand();
    }

}