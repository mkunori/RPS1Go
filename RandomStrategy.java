// ランダムに手を決める
public class RandomStrategy implements CPUStrategy {
    @Override
    public Hands nextHand() {
        return Hands.randomHand();
    }
}
