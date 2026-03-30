package strategy;

import game.Hands;

/**
 * ランダムに手を出すCPU戦略。
 *
 * 毎回ランダムにグー・チョキ・パーのいずれかを選択する。
 */
public class RandomStrategy implements CPUStrategy {

    /**
     * ランダムに手を選択する。
     *
     * @return ランダムに選ばれた手
     */
    @Override
    public Hands nextHand() {
        return Hands.randomHand();
    }
}
