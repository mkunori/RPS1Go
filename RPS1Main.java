import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RPS1Main extends JFrame {
    private JLabel resultLabel;
    private JLabel cpuLabel;
    private Random random = new Random();

    // コンストラクタ
    public RPS1Main() {
        setTitle("じゃんけんゲーム1号");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // じゃんけんの手パネルを配置する
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

        // じゃんけんボタンイベントを設定する
        rockButton.addActionListener(e -> play(Hands.ROCK));
        scissorsButton.addActionListener(e -> play(Hands.SCISSORS));
        paperButton.addActionListener(e -> play(Hands.PAPER));

        // CPUのパネルを配置する
        cpuLabel = new JLabel("CPU ?", SwingConstants.CENTER);
        cpuLabel.setFont(new Font("Sanserif", Font.BOLD, 80));
        add(cpuLabel, BorderLayout.NORTH);

        // 結果パネルを配置する
        resultLabel = new JLabel("ボタンを押してください。", SwingConstants.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
        add(resultLabel, BorderLayout.SOUTH);
    }

    // じゃんけんを実行する
    private void play(Hands playerHand) {
        // CPUの手を決めて表示する
        Hands cpuHand = Hands.randomHand();
        // いざ対戦！
        String result = judge(playerHand, cpuHand);
        // CPUの手を表示する
        cpuLabel.setText(cpuHand.getSymbol());
        // プレイヤーに結果を表示する
        resultLabel.setText("結果: " + result);
    }

    // 勝敗を判定する
    private String judge(Hands player, Hands cpu) {
        if (player == cpu) {
            return "あいこ";
        }

        if ((player == Hands.ROCK && cpu == Hands.SCISSORS)
                || (player == Hands.SCISSORS && cpu == Hands.PAPER)
                || (player == Hands.PAPER && cpu == Hands.ROCK)) {
            return "あなたの勝ち";
        }

        return "あなたの負け";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RPS1Main().setVisible(true);
        });
    }

}
