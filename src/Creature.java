import java.util.Random;

class Creature {
    private String name;
    private int attack;
    private int defense;
    private int health;
    private int maxHealth;
    private int[] damageRange;
    //Урон, берум у атакующего и из здоровья защищающегося вычитаем, будем брать произвольно из диапазона

    public Creature(String name, int attack, int defense, int health, int[] damageRange) {
        if (attack < 1 || attack > 30 || defense < 1 || defense > 30 || health < 0 || damageRange.length != 2 || damageRange[0] < 1 || damageRange[1] < damageRange[0]) {
            throw new IllegalArgumentException("Некорректные аргументы при создании существа.");
        }

        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.maxHealth = health;
        this.damageRange = damageRange;
    }

    // Кидаем кубик
    private int rollDice(int n) {
        Random random = new Random();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += random.nextInt(6) + 1;
        }
        return sum;
    }

    public void attack(Creature target) {
        int attackModifier = Math.max(this.attack - target.defense + 1, 1);
        int[] attackRolls = new int[attackModifier];
        for (int i = 0; i < attackModifier; i++) {
            attackRolls[i] = rollDice(1);
        }

        boolean successfulAttack = false;
        for (int roll : attackRolls) {
            if (roll >= 5) {
                successfulAttack = true;
                break;
            }
        }

        if (successfulAttack) {
            Random random = new Random();
            int damage = random.nextInt(this.damageRange[1] - this.damageRange[0] + 1) + this.damageRange[0];
            target.takeDamage(damage);
        }
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Урон не может быть отрицательным.");
        }
        health = Math.max(health - damage, 0);
        if (health == 0) {
            System.out.println(name + " умер.");
        }
    }

    // heal - увеличение на 0.3 от макс здоровья
    public void heal() {
        int healing = (int) (maxHealth * 0.3);
        if (health < 0 ) health = 0;
        health = Math.min(health + healing, maxHealth);
        System.out.println(name + " исцелился на " + healing + " здоровья. Текущее здоровье: " + health + "/" + maxHealth);
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public String getName() {
        return name;
    }

/*    public void setHealth(int new_health) {
        health = new_health;
    }

 */
}
