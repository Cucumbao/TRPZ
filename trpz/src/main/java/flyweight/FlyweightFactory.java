package flyweight;
import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
    private final Map<String, Flyweight> flyweightPool = new HashMap<>();

    public Flyweight getFlyweight(String key) {
        if (!flyweightPool.containsKey(key)) {
            switch (key) {
                case "KEYWORD":
                    flyweightPool.put(key, new ConcreteFlyweight("blue", true, false));
                    break;
                case "NUMBER":
                    flyweightPool.put(key, new ConcreteFlyweight("orange", false, false));
                    break;
                case "STRING":
                    flyweightPool.put(key, new ConcreteFlyweight("green", false, true));
                    break;
                case "COMMENT":
                    flyweightPool.put(key, new ConcreteFlyweight("gray", false, true));
                    break;
                default:
                    flyweightPool.put(key, new ConcreteFlyweight("black", false, false));
                    break;
            }
        }
        return flyweightPool.get(key);
    }
}
