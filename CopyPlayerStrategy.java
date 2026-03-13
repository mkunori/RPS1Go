public class CopyPlayerStrategy implements CPUStrategy {
    private Hands lastPlayer = Hands.ROCK;

    public void recordPlayer(Hands player) {
        lastPlayer = player;
    }

    @Override
    public Hands nextHand() {
        return lastPlayer;
    }
}
