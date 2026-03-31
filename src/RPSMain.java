import java.awt.*;
import javax.swing.*;

import game.Hands;
import game.RPSGame;
import game.Result;
import game.RoundResult;

import strategy.CopyPlayerStrategy;
import strategy.RandomStrategy;

/**
 * Swingで作成したじゃんけんゲームのメイン画面クラス。
 * 
 * プレイヤーがボタンで手を選択し、CPUと対戦する。
 * また、CPU戦略の切り替え、スコア表示、対戦結果表示を行う。
 */
public class RPSMain extends JFrame {
    /** 対戦結果を表示するラベル。 */
    private JLabel resultLabel;

    /** CPUの手を表示するラベル。 */
    private JLabel cpuLabel;

    /** ゲーム進行を管理するオブジェクト。 */
    private RPSGame game = new RPSGame(new RandomStrategy());

    /** プレイヤーのスコア。 */
    private int playerScore = 0;

    /** CPUのスコア。 */
    private int cpuScore = 0;

    /** スコアを表示するラベル。 */
    private JLabel scoreLabel;

    /**
     * メイン画面を初期化する。
     * 
     * 画面内の各種パネル、ボタン、ラベルを生成し、
     * イベント処理を設定する。
     */
    public RPSMain() {
        setTitle("じゃんけんゲーム");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // じゃんけんの手を配置する
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        Font handFont = new Font("SansSerif", Font.BOLD, 80);
        JButton rockButton = new JButton("✊"); // グー
        JButton scissorsButton = new JButton("✌"); // チョキ
        JButton paperButton = new JButton("✋"); // パー
        rockButton.setFont(handFont);
        scissorsButton.setFont(handFont);
        paperButton.setFont(handFont);
        buttonPanel.add(rockButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(paperButton);
        add(buttonPanel, BorderLayout.CENTER);
        rockButton.addActionListener(e -> play(Hands.ROCK));
        scissorsButton.addActionListener(e -> play(Hands.SCISSORS));
        paperButton.addActionListener(e -> play(Hands.PAPER));

        // 上部パネルを作成する。
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // CPUの戦略を選択するプルダウンを配置する。
        JPanel strategyPanel = new JPanel();
        String[] strategies = { "Random", "Copy" };
        JComboBox<String> strategyBox = new JComboBox<>(strategies);
        strategyBox.addActionListener(e -> {
            String selected = (String) strategyBox.getSelectedItem();
            if ("Random".equals(selected)) {
                game.setStrategy(new RandomStrategy());
                resultLabel.setText("CPU戦略: Random");
            } else if ("Copy".equals(selected)) {
                game.setStrategy(new CopyPlayerStrategy());
                resultLabel.setText("CPU戦略: Copy");
            }
        });
        strategyPanel.add(new JLabel("CPU戦略"));
        strategyPanel.add(strategyBox);
        topPanel.add(strategyPanel, BorderLayout.NORTH);

        // スコア表示ラベルを配置する。
        scoreLabel = new JLabel("あなた 0 : 0 CPU", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        topPanel.add(scoreLabel, BorderLayout.CENTER);

        // CPUの手を表示するラベルを配置する。
        cpuLabel = new JLabel("CPU ?", SwingConstants.CENTER);
        cpuLabel.setFont(new Font("SansSerif", Font.BOLD, 80));
        topPanel.add(cpuLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // 対戦結果表示パネルを配置する。
        resultLabel = new JLabel("ボタンを押してください。", SwingConstants.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
        add(resultLabel, BorderLayout.SOUTH);
    }

    /**
     * じゃんけんを1回実行し、画面表示を更新する。
     * 
     * プレイヤーの手を受け取り、CPUとの対戦結果に応じて
     * CPU表示、スコア表示、結果表示を更新する。
     * 
     * @param playerHand プレイヤーが選択した手
     */
    private void play(Hands playerHand) {
        RoundResult round = game.play(playerHand);

        // CPUの手を表示する
        cpuLabel.setText(round.getCpu().getSymbol());

        // 勝敗に応じてスコアを更新する
        Result result = round.getResult();
        if (result == Result.WIN) {
            playerScore++;
        } else if (result == Result.LOSE) {
            cpuScore++;
        } else {
            // あいこは更新なし
        }
        // スコアを表示する
        scoreLabel.setText("あなた " + playerScore + " : " + cpuScore + " CPU");

        // 結果を表示する
        resultLabel.setText(round.getResult().toString());
    }

    /**
     * アプリケーションを起動する。
     * 
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RPSMain().setVisible(true);
        });
    }

}
