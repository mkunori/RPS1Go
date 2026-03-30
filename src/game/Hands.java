package game;

import java.util.Random;

/**
 * じゃんけんの手を表す列挙型。
 * 
 * 各手は表示用の文字を持ち、勝敗判定のロジックを提供する。
 */
public enum Hands {
    ROCK("✊") {
        public boolean beats(Hands other) {
            return other == SCISSORS;
        }
    },
    SCISSORS("✌") {
        public boolean beats(Hands other) {
            return other == PAPER;
        }
    },
    PAPER("✋") {
        public boolean beats(Hands other) {
            return other == ROCK;
        }
    };

    private final String symbol;
    private static final Random random = new Random();

    /**
     * 指定された手に勝てるか判定する。
     * 
     * @param other 相手の手
     * @return 勝てる場合はtrue
     */
    public abstract boolean beats(Hands other);

    /**
     * 手の表示文字を設定する。
     * 
     * @param symbol 手を表す文字
     */
    Hands(String symbol) {
        this.symbol = symbol;
    }

    /**
     * 手に対応する表示文字を取得する。
     * 
     * @return 手を表す文字
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * ランダムに手を選択する。
     * 
     * @return ランダムに選ばれた手
     */
    public static Hands randomHand() {
        return values()[random.nextInt(values().length)];
    }

    /**
     * 相手の手との勝敗を判定する。
     * 
     * @param opponent 相手の手
     * @return 勝敗結果
     */
    public Result fight(Hands opponent) {
        // あいこになった？
        if (this == opponent) {
            return Result.DRAW;
        }
        // プレイヤーが勝利した？
        if (this.beats(opponent)) {
            return Result.WIN;
        }
        // プレイヤーが敗北した
        return Result.LOSE;
    }
}
