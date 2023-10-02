class GameField {
    private static final int RECOVERS = 4;
    private static final double PERCENT = 0.3;
    private Player player;
    private Monster monster;
    private int playerRecover;

    public GameField(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
        playerRecover = RECOVERS;
    }

    public void startBattle() {
        System.out.println("Битва началась!");
        while (player.getHealth() > 0 && monster.getHealth() > 0) {
            player.attack(monster);

            System.out.println(player.getName() + " attack: " + monster.getName() + " health " + monster.getHealth());

            if (monster.getHealth() <= 0) {
                System.out.println(player.getName() + " победил!");
                break;
            }

            monster.attack(player);

            System.out.println(monster.getName() + " attack: " + player.getName() + "health       " + player.getHealth());

// Если у игрока меньше 0 процентов жизни и есть ещё попытка восстановления - восстановить здоровье на 30%
            if (player.getHealth() <= 0 && playerRecover >= 1) {
                // player.setHealth( player.getHealth() + (int)(0.3 * player.getMaxHealth()));
                player.heal();
                playerRecover-- ;
                System.out.println("Попыток восстановления осталось " + playerRecover);
            }

            if (player.getHealth() <= 0) {
                System.out.println(monster.getName() + " победил!");
                break;
            }
        }
    }
}