package strategy;

import game.Hands;

/**
 * プレイヤーの前回の手をコピーするCPU戦略。
 * 
 * 前回のプレイヤーの手を記録し、
 * 次の対戦ではその手をそのまま出す。
 */
public class CopyPlayerStrategy implements CPUStrategy {

    /**
     * 前回のプレイヤーの手。
     */
    private Hands lastPlayer = Hands.ROCK;

    /**
     * プレイヤーの手を記録する。
     * 
     * @param player プレイヤーが出した手
     */
    public void recordPlayer(Hands player) {
        lastPlayer = player;
    }

    /**
     * 記録されたプレイヤーの手を返す。
     * 
     * @return 前回のプレイヤーの手
     */
    @Override
    public Hands nextHand() {
        return lastPlayer;
    }
}
