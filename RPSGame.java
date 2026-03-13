public class RPSGame {
    public RoundResult play(Hands player) {
        Hands cpu = Hands.randomHand();
        Result result = player.fight(cpu);
        return new RoundResult(player, cpu, result);
    }
}
