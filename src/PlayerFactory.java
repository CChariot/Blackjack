//Factory Design Pattern
class PlayerFactory {

    //use getShape method to get object of type shape
    static person getPlayer(String playerType) {
        if (playerType == null) {
            return null;
        }
        if (playerType.equalsIgnoreCase("Dealer")) {
            return Dealer.getInstance();

        } else if (playerType.equalsIgnoreCase("Player")) {
            return new Player();

        }
        return null;
    }
}