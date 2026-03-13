import java.util.Random;

public enum Hands {
    ROCK("✊"), SCISSORS("✌"), PAPER("✋");

    private final String symbol;
    private static final Random random = new Random();

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
}
