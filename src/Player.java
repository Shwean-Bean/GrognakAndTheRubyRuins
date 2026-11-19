import java.util.Scanner;

public class Player {
    //Variables
    private int health;
    private int maxHealth;
    private int caps = 5;
    private int level;
    private int rooms;
    private String name;
    private Weapon weapon;

    //S.P.E.C.I.A.L. Stats
    private int strength;
    private int perception;
    private int endurance;
    private int charisma;
    private int intelligence;
    private int agility;
    private int luck;

    //Constructor
    public Player() {
        health = 1;
        name = "";
        level = 1;
    }

    //Prompts the User for Character Stats
    public void CreateCharacter(Scanner input) {
        int points = 42;
        //Name Input
        System.out.println("           What is Your Name:\n---------------------------------------");
        name = input.nextLine();
        //S.P.E.C.I.A.L. Overview
        System.out.println("---------------------------------------\n      Now it is time to pick your\n  S.P.E.C.I.A.L. Stats, they are what\nmakes you special. They range from 1-10\n and you get a max of 42 total points.\n---------------------------------------\n");
        System.out.println("---------------------------------------\n First up Strength, it affects how much\ndamage you do and how much you can carry\n Pick a number 1-10. Remaining Pts:" + points + "\n---------------------------------------");
        while (!(10 >= strength && strength >= 1)) {
            strength = input.nextInt();
            if (strength > 10 || strength <= 0) {
                System.out.println("Try Again");
            }
        }
        //Strength Assignment
        input.nextLine();
        points -= strength;
        System.out.println("---------------------------------------\n  Next is Perception, it affects your\n senses, what you see is what is there.\n Pick a number 1-10. Remaining Pts:" + points + "\n---------------------------------------");
        while (!(10 >= perception && perception >= 1 && points >= perception)) {
            perception = input.nextInt();
            if (perception > 10 || perception <= 0 || points < perception) {
                System.out.println("Try Again");
            }
        }
        input.nextLine();
        points -= perception;
        System.out.println("---------------------------------------\n Next is Endurance, it affects defense\n and how much radiation you can take in.\n Pick a number 1-10. Remaining Pts:" + points + "\n---------------------------------------");
        while (!(10 >= endurance && endurance >= 1 && points >= endurance)) {
            endurance = input.nextInt();
            if (endurance > 10 || endurance <= 0 || points < endurance) {
                System.out.println("Try Again");
            }
        }
        input.nextLine();
        points -= endurance;
        System.out.println("---------------------------------------\nNext is Charisma, it affects your charm\nhow well you can convince people of things\n Pick a number 1-10. Remaining Pts:" + points + "\n---------------------------------------");
        while (!(10 >= charisma && charisma >= 1 && points >= charisma)) {
            charisma = input.nextInt();
            if (charisma > 10 || charisma <= 0 || points < charisma) {
                System.out.println("Try Again");
            }
        }
        input.nextLine();
        points -= charisma;
        System.out.println("---------------------------------------\n  Next is Intelligence, it affects your\n      ability to level up and learn.\n Pick a number 1-10. Remaining Pts:" + points + "\n---------------------------------------");
        while (!(10 >= intelligence && intelligence >= 1 && points >= intelligence)) {
            intelligence = input.nextInt();
            if (intelligence > 10 || intelligence <= 0 || points < intelligence) {
                System.out.println("Try Again");
            }
        }
        input.nextLine();
        points -= intelligence;
        System.out.println("---------------------------------------\n    Next is Agility, it affects your\n   speed and how fast you can attack.\n Pick a number 1-10. Remaining Pts:" + points + "\n---------------------------------------");
        while (!(10 >= agility && agility >= 1 && points >= agility)) {
            agility = input.nextInt();
            if (agility > 10 || agility <= 0 || points < agility) {
                System.out.println("Try Again");
            }
        }
        input.nextLine();
        points -= agility;
        System.out.println("---------------------------------------\n   Finally is Luck, it is exactly what\n       what it sounds like.\n Pick a number 1-10. Remaining Pts:" + points + "\n---------------------------------------");
        while (!(10 >= luck && luck >= 1 && points >= luck)) {
            luck = input.nextInt();
            if (luck > 10 || luck <= 0 || points < luck) {
                System.out.println("Try Again");
            }
        }
        health = 5 + getEndurance() * 2;
        maxHealth = health;
        checkStats();
    }

    //One in ten(but also influenced by intelligence) chance to level up; it increases health
    public void levelUp() {
        if (Math.random() * 10 + (double)intelligence/2 + 1 >= 10) {
            level ++;
            health += (int)(Math.pow(level,2)/2);
            maxHealth += (int)(Math.pow(level,2)/2);
            System.out.println("---------------------------------------\n                LEVEL UP: " + level + "\n          Health increased: +" + ((Math.pow(level,2))/2)+ "\n---------------------------------------\n");
        }
    }

    //Equips the player by setting the weapon
    public void equip(Weapon w) {
        weapon = w;
    }

    //Prints out all the player stats
    public void checkStats() {
        System.out.println("---------------------------------------\n          Here are your Stats:\nName: " + name + "\nHealth: " + health + "\nStrength: " + strength + "\nPerception: " + perception + "\nEndurance: " + endurance + "\nCharisma: " + charisma + "\nIntelligence: " + intelligence + "\nAgility: " + agility + "\nLuck: " + luck + "\n---------------------------------------\n");
    }

    //75% chance to take damage if the attack misses enemy is named
    public void takeDamage(int d, String n) {
        int rand = (int) (Math.random()*4) + 1;
        if (rand <= 3) {
            health-=d;
            System.out.println(" You take " + d + " damage.(" + health + ")");
            if (health <= 0) {
                System.out.println("\n---------------------------------------\n            You have Died\nYou Cleared " + rooms + " Rooms and Reached Level " + level + "\n---------------------------------------\n");
            }
        } else {
            System.out.println(" The " + n + "'s attack misses.");
        }
    }

    //Guaranteed removes set Damage
    public void takeDamage(int d) {
        health -= d;
        System.out.println("---------------------------------------\n You take 2 Damage\n---------------------------------------\n");
        if (health <= 0) {
            System.out.println("\n---------------------------------------\n            You have Died\nYou Cleared " + rooms + " Rooms and Reached Level " + level + "\n---------------------------------------\n");
        }
    }

    //Adds rooms(only used in the death text)
    public void addRoom() {
        rooms ++;
    }

    //Getter Methods
    public String getName() {
        return name;
    }

    //Gives a random int value based off of player strength with a range of 2 or by the weapon damage/damage range
    public int getDamage() {
        if (weapon == null) {
            return (int)(Math.random()*2) + strength / 2;
        } else if (weapon.isRanged()) {
            return (int)(Math.random() * weapon.getDamageRange()) + weapon.getDamage() + perception/2;
        }
        return (int)(Math.random() * weapon.getDamageRange()) + weapon.getDamage() + strength/2;
    }

    public int getHealth() {
        return health;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getAgility() {
        return agility;
    }

    public int getLuck() {
        return luck;
    }

    public int getCaps() {
        return caps;
    }

    //Setter Methods
    public void setHealth() {
        setHealth(maxHealth);
        System.out.println("---------------------------------------\n You are Fully Healed\n---------------------------------------\n");
    }

    public void setHealth(int h) {
        health = h;
    }

    public void setCaps(int c) {
        caps = c;
    }
}