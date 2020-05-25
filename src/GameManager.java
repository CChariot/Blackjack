import java.io.IOException;
import java.util.Scanner;

//Using Builder Design Pattern
public class GameManager implements gameProcess {

    private static GameManager obj;
    private int deck_used;
    private int player_in;

    //constructor
    private GameManager() {
    }

    //Singleton Design Pattern
    static synchronized GameManager getInstance() {
        if (obj == null) obj = new GameManager();

        return obj;
    }

    void playGame() throws IOException {
        //initialize Strategies
        AbstractStrategy StrategyFactory = FactoryProducer.getFactory(false);
        Strategy RandomStrategy = StrategyFactory.getStrategy("Random");
        Strategy TableStrategy = StrategyFactory.getStrategy("Table");

        AbstractStrategy StrategyFactory1 = FactoryProducer.getFactory(true);
        Strategy CountStrategy = StrategyFactory1.getStrategy("Count");
        Strategy MemorizeStrategy = StrategyFactory1.getStrategy("Memorize");

        RandomStrategy.testOutput();
        TableStrategy.testOutput();
        CountStrategy.testOutput();
        MemorizeStrategy.testOutput();

        //end of initializing Strategies.

        //initializing the game
        initializeDeck();

        //initialize player
        initializePlayer();

        //start of the game
        //initialize deck
        Deck currDeck = new Deck(deck_used);
        currDeck.shuffle();

        //initialize players pool
        Player [] players_pool = new Player[player_in];
        //initialize every players using Factory Design Pattern
        for(int i = 0; i < player_in; i++){
            players_pool[i] = (Player) PlayerFactory.getPlayer("Player");
        }

        //initialize dealer
        Dealer myDealer = (Dealer) PlayerFactory.getPlayer("Dealer");

        while(!needsRefill(players_pool)) {
            //taking bets
            takeBetting(players_pool);

            //deals first two cards for players
            dealFirstTwo(players_pool, currDeck);

            //deal first card for dealer
            myDealer.hit(currDeck);

            //hitting for all players
            playersHit(players_pool, myDealer, currDeck);

            //second card for dealer
            myDealer.hit(currDeck);

            //hitting for dealer
            while (myDealer.shouldHit(players_pool, myDealer)) {
                myDealer.hit(currDeck);
            }

            //pay or take money from player
            countMoney(players_pool,myDealer);
            //display money for each player
            displayMoney(players_pool, myDealer);
            //clean players and dealer hand
            cleanTable(players_pool,myDealer);

        }
    }

    @Override
    public void initializeDeck() { //initialize deck of cards based on user inputs
        Scanner scan1 = new Scanner(System.in);
        System.out.print("Enter number of deck(Max 3): ");
        deck_used = scan1.nextInt();
        while (deck_used > 3) {
            System.out.print("Max number of deck is 3, please try again: ");
            deck_used = scan1.nextInt();
        }
    }

    @Override
    public void initializePlayer() { //initialize players pool based on user inputs
        Scanner scan2 = new Scanner(System.in);
        System.out.print("Enter number of player(Max 5): ");
        player_in = scan2.nextInt();
        while (player_in > 5) {
            System.out.print("Max number of player is 5, please try again: ");
            player_in = scan2.nextInt();
        }
    }

    @Override
    public boolean needsRefill(Player[] players_pool) {

        for(Player x : players_pool){
            if(x.money_left == 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public void takeBetting(Player[] players_pool) {
//        for (int i = 0; i < player_in; i++) {
//            Scanner scan3 = new Scanner(System.in);
//            System.out.print("Enter your betting amount for player " + (i + 1) + "\t");
//            players_pool[i].bet_amount = scan3.nextInt();
//            while (players_pool[i].bet_amount > players_pool[i].money_left) {
//                System.out.print("Please enter amount less or equal to what you have");
//                players_pool[i].bet_amount = scan3.nextInt();
//            }
//        }

        for (int i = 0; i < player_in; i++) {
            players_pool[i].bet_amount = 1;
        }

    }

    private void dealFirstTwo(Player[] players_pool, Deck currDeck) {
        //deal cards for players
        //players get two cards at the beginning
        //using two for loops in order to keep track first card value and second card value
        for (int j = 0; j < player_in; j++) {
            players_pool[j].firstHit(currDeck);

        }
        for (int j = 0; j < player_in; j++) {
            players_pool[j].secondHit(currDeck);
        }
    }

    boolean shouldSplit(Player player) {
        return player.first_card == player.second_card;
    }

    @Override
    public void playersHit(Player[] players_pool, Dealer myDealer, Deck currDeck) {

        for (int i = 0; i < player_in; i++) {

            //takes care of 11-game-value case:
            if (players_pool[i].shouldDouble()) {
                players_pool[i].bet_amount *= 2;
                players_pool[i].isFinal = true;
                players_pool[i].hit(currDeck);
            }

            //takes care of split case
            if (shouldSplit(players_pool[i])) {
                players_pool[i].split_amount = players_pool[i].bet_amount;
                players_pool[i].isSplit = true;
                players_pool[i].split_game_value = players_pool[i].game_value / 2;
                players_pool[i].game_value /= 2;
                while (!players_pool[i].isFinal && players_pool[i].shouldHit(players_pool[i].split_game_value, myDealer)) {
                    players_pool[i].splitHit(currDeck);
                }
            }
            while (!players_pool[i].isFinal && players_pool[i].shouldHit(players_pool[i].game_value, myDealer)) {
                players_pool[i].hit(currDeck);
            }
        }
    }

    public void countMoneyAction(Player player, Dealer myDealer) {
        //player got pushed, do nothing
        if (player.game_value == myDealer.game_value) {
            //Dealer blackjack case
        } else if (myDealer.game_value == 21) {
            player.money_left -= player.bet_amount;
            myDealer.money_left += player.bet_amount;
            //Player blackjack case
        } else if (player.game_value == 21) {
            player.money_left += player.bet_amount;
            myDealer.money_left -= player.bet_amount;
            //player busted case
        } else if (player.game_value > 21) {
            player.money_left -= player.bet_amount;
            myDealer.money_left += player.bet_amount;
            //dealer busted case
        } else if (myDealer.game_value > 21) {
            player.money_left += player.bet_amount;
            myDealer.money_left -= player.bet_amount;
        } else if (player.game_value > myDealer.game_value && player.game_value <= 21) {
            player.money_left += player.bet_amount;
            myDealer.money_left -= player.bet_amount;
        } else if (player.game_value < myDealer.game_value) {
            player.money_left -= player.bet_amount;
            myDealer.money_left += player.bet_amount;
        }
    }

    //count money for split case
    public void countSplitMoney(Player player, Dealer myDealer) {
        //player got pushed, do nothing
        if (player.split_game_value == myDealer.game_value) {
            //Dealer blackjack case
        } else if (myDealer.game_value == 21) {
            player.money_left -= player.bet_amount;
            myDealer.money_left += player.bet_amount;
            //Player blackjack case
        } else if (player.split_game_value == 21) {
            player.money_left += player.bet_amount;
            myDealer.money_left -= player.bet_amount;
            //player busted case
        } else if (player.split_game_value > 21) {
            player.money_left -= player.bet_amount;
            myDealer.money_left += player.bet_amount;
            //dealer busted case
        } else if (myDealer.game_value > 21) {
            player.money_left += player.bet_amount;
            myDealer.money_left -= player.bet_amount;
        } else if (player.split_game_value > myDealer.game_value && player.split_game_value <= 21) {
            player.money_left += player.bet_amount;
            myDealer.money_left -= player.bet_amount;
        } else if (player.split_game_value < myDealer.game_value) {
            player.money_left -= player.bet_amount;
            myDealer.money_left += player.bet_amount;
        }
    }


    @Override
    public void countMoney(Player[] players_pool, Dealer myDealer) {//pay or take money from player
        //Determine winning
        for (int i = 0; i < player_in; i++) {
            countMoneyAction(players_pool[i], myDealer);
            //takes care of split case
            if (players_pool[i].isSplit) {
                countSplitMoney(players_pool[i], myDealer);
            }
        }
    }

    @Override
    public void cleanTable(Player[] players_pool, Dealer myDealer) {
        for(Player x: players_pool){
            x.cleanHand();
        }
        myDealer.cleanHand();
    }

    @Override
    public void displayMoney(Player[] players_pool, Dealer myDealer) {
        System.out.println("Dealer has " + myDealer.game_value);

        for (int i = 0; i < player_in; i++) {
            if (players_pool[i].split_amount != 0) {
                System.out.println("Player " + i + "'s split hand is " + players_pool[i].split_game_value);
            }
            System.out.println("Player " + i + " has " + players_pool[i].game_value);
            System.out.println("Player " + i + " now has " + players_pool[i].getMoney());
        }
    }

}