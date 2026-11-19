public class Enemy {
    //Variables
    private String name;
    private int damage;
    private int damageRange;
    private int health;

    //Constructor
    public Enemy(String n, int d, int dR, int h) {
        name = n;
        damage = d;
        damageRange = dR;
        health = h;
    }

    //Creates a random Enemy
    public Enemy() {
        int rand = (int) (Math.random() * 100) + 1;
        if (rand >= 80) {
            name = "Irradiated Squirrel";
            damage = 15;
            damageRange = 3;
            health = 100;
        } else if (rand >= 60) {
            name = "Raider";
            damage = 5;
            damageRange = 3;
            health = 10;
        } else if (rand >= 20) {
            name = "Ghoul";
            damage = 8;
            damageRange = 4;
            health = 16;
        } else if (rand >= 1) {
            name = "Deathclaw";
            damage = 12;
            damageRange = 5;
            health = 25;
        }
    }

    //Methods
    //Removes Health from the enemy 75% of the time and returns true if the enemy is dead
    public boolean takeDamage(int d) {
        int rand = (int) (Math.random()*4) + 1;
        if (rand <= 3) {
            health-=d;
            System.out.println("\n---------------------------------------\n The " + name + " gets hit for " + d + " damage.(" + health + ")");
            return health <= 0;
        } else {
            System.out.println("\n---------------------------------------\n Your Attack Misses");
        }

        return false;
    }

    //Getter Methods
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return (int) (Math.random()*damageRange) + damage;
    }
}