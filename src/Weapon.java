public class Weapon {
    //Variables
    private final String name;
    private final int damage;
    private final int damageRange;
    private final int cost;
    private final boolean ranged;

    //Parameterized Constructor
    public Weapon(String n, int d, int dr, int c, boolean r) {
        name = n;
        damage = d;
        damageRange = dr;
        cost = c;
        ranged = r;
    }

    //Getter Methods
    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getDamageRange() {
        return damageRange;
    }

    public int getCost() {
        return cost;
    }

    public boolean isRanged() {
        return ranged;
    }
}