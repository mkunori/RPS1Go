package game;

/**
 * 1回のじゃんけん対戦結果を保持するクラス。
 *
 * プレイヤーの手、CPUの手、勝敗結果をまとめて管理する。
 */
public class RoundResult {
    private final Hands player;
    private final Hands cpu;
    private final Result result;

    /**
     * 対戦結果を生成する。
     *
     * @param player プレイヤーの手
     * @param cpu CPUの手
     * @param result 勝敗結果
     */
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
