package main;

import javax.swing.*;
import game.Hands;
import game.RPSGame;
import game.Result;
import game.RoundResult;
import strategy.CopyPlayerStrategy;
import strategy.RandomStrategy;
import java.awt.*;

public class RPS1Main extends JFrame {
    private JLabel resultLabel;
    private JLabel cpuLabel;
    private RPSGame game = new RPSGame(new RandomStrategy());
    private int playerScore = 0;
    private int cpuScore = 0;
    private JLabel scoreLabel;

    // コンストラクタ
    public RPS1Main() {
        setTitle("じゃんけんゲーム1号");
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

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // CPUの戦略ボタンを配置する。
        JPanel strategyPanel = new JPanel();
        JButton randomButton = new JButton("Random CPU");
        JButton copyButton = new JButton("Copy CPU");
        randomButton.addActionListener(e -> {
            game.setStrategy(new RandomStrategy());
            resultLabel.setText("CPU戦略: Random");
        });
        copyButton.addActionListener(e -> {
            game.setStrategy(new CopyPlayerStrategy());
            resultLabel.setText("CPU戦略: Copy");
        });
        strategyPanel.add(randomButton);
        strategyPanel.add(copyButton);
        topPanel.add(strategyPanel, BorderLayout.NORTH);

        // スコア表示を配置する
        scoreLabel = new JLabel("あなた 0 : 0 CPU", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        topPanel.add(scoreLabel, BorderLayout.CENTER);

        // CPUの手を配置する
        cpuLabel = new JLabel("CPU ?", SwingConstants.CENTER);
        cpuLabel.setFont(new Font("Sansserif", Font.BOLD, 80));
        topPanel.add(cpuLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // 結果パネルを配置する
        resultLabel = new JLabel("ボタンを押してください。", SwingConstants.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
        add(resultLabel, BorderLayout.SOUTH);
    }

    // じゃんけんを実行する
    private void play(Hands playerHand) {
        // いざ対戦！
        RoundResult round = game.play(playerHand);
        // CPUの手を表示する
        cpuLabel.setText(round.getCpu().getSymbol());
        // スコアを更新する
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RPS1Main().setVisible(true);
        });
    }

}
