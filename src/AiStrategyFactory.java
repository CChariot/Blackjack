public class AiStrategyFactory extends AbstractStrategy {
    @Override
    public Strategy getStrategy(String StrategyType) {
        if (StrategyType.equalsIgnoreCase("Count")) {
            return new CountStrategy();
        } else if (StrategyType.equalsIgnoreCase("Memorize")) {
            return new MemorizeStrategy();
        }
        return null;
    }
}
