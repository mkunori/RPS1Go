public class RPSGame {
    private CPUStrategy cpuStrategy; // CPUの戦略

    // コンストラクタ
    public RPSGame(CPUStrategy cpuStrategy) {
        this.cpuStrategy = cpuStrategy;
    }

    // 戦略を決める
    public void setStrategy(CPUStrategy cpuStrategy) {
        this.cpuStrategy = cpuStrategy;
    }

    // 対戦1回の結果を持つインスタンスを生成する
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
