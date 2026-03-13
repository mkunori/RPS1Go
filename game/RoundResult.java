package game;

public class RoundResult {
    private final Hands player;
    private final Hands cpu;
    private final Result result;

    // コンストラクタ
    public RoundResult(Hands player, Hands cpu, Result result) {
        this.player = player;
        this.cpu = cpu;
        this.result = result;
    }

    public Hands getPlayer() {
        return player;
    }

    public Hands getCpu() {
        return cpu;
    }

    public Result getResult() {
        return result;
    }
}
