package strategy;

import game.Hands;

/**
 * CPUの手の決め方を定義するインターフェース。
 * 
 * 実装クラスはCPUが次に出す手を決定するアルゴリズムを提供する。
 * Strategyパターンにより、CPUの思考ロジックを交換可能にしている。
 */
public interface CPUStrategy {

    /**
     * CPUが次に出す手を決定する。
     * 
     * @return CPUが出す手
     */
    Hands nextHand();
}
