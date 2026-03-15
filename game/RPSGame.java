package game;

import strategy.CPUStrategy;
import strategy.CopyPlayerStrategy;

/**
 * じゃんけんゲームの進行を管理するクラス。
 *
 * CPU戦略を利用してCPUの手を決定し、プレイヤーとの対戦結果を生成する。
 */
public class RPSGame {
    private CPUStrategy cpuStrategy; // CPUの戦略

    /**
     * CPU戦略を指定してゲームを初期化する。
     *
     * CPUの手の決定方法はStrategyパターンで切り替え可能であり、
     * このコンストラクタで初期戦略を設定する。
     *
     * @param cpuStrategy CPUの手を決定する戦略
     */
    public RPSGame(CPUStrategy cpuStrategy) {
        this.cpuStrategy = cpuStrategy;
    }

    /**
     * CPUの戦略を変更する。
     *
     * @param cpuStrategy 新しいCPU戦略
     */
    public void setStrategy(CPUStrategy cpuStrategy) {
        this.cpuStrategy = cpuStrategy;
    }

    /**
     * じゃんけんを1回実行する。
     *
     * プレイヤーの手を受け取り、CPUの手を決定し、
     * 勝敗結果を生成する。
     *
     * @param player プレイヤーの手
     * @return 対戦結果
     */
    public RoundResult play(Hands player) {
        Hands cpu = cpuStrategy.nextHand();
        Result result = player.fight(cpu);

        // CPU戦略がコピーのとき
        if (cpuStrategy instanceof CopyPlayerStrategy) {
            ((CopyPlayerStrategy) cpuStrategy).recordPlayer(player);
        }

        return new RoundResult(player, cpu, result);
    }
}
