public class Main {
    public static void main(String[] args) {
        Player player = new Player("Игрок", 10, 15, 100, new int[]{5, 10});
        Monster monster = new Monster("Монстр", 20, 10, 80, new int[]{6, 12});

        GameField gameField = new GameField(player, monster);
        gameField.startBattle();
    }
}