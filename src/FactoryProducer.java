public class FactoryProducer {
    public static AbstractStrategy getFactory(boolean isAI) {
        if (isAI) {
            return new AiStrategyFactory();
        } else {
            return new StrategyFactory();
        }
    }
}
