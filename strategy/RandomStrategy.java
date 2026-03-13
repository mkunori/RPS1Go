package strategy;

import game.Hands;

public class RandomStrategy implements CPUStrategy {
    @Override
    public Hands nextHand() {
        return Hands.randomHand();
    }
}
