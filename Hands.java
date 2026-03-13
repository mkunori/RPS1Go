import java.util.Random;

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

    public abstract boolean beats(Hands other);

    // コンストラクタ
    Hands(String symbol) {
        this.symbol = symbol;
    }

    // 手を取得する
    public String getSymbol() {
        return symbol;
    }

    // 手をランダムに決める
    public static Hands randomHand() {
        return values()[random.nextInt(values().length)];
    }

    // 勝敗を決める
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
