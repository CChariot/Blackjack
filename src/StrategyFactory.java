public class StrategyFactory extends AbstractStrategy {
    @Override
    public Strategy getStrategy(String StrategyType) {
        if (StrategyType.equalsIgnoreCase("Random")) {
            return new RandomStrategy();
        } else if (StrategyType.equalsIgnoreCase("Table")) {
            return new TableStrategy();
        }
        return null;
    }
}
