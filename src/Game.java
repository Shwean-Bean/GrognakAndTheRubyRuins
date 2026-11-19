import java.util.Scanner;

public class Game {
    //Variable
    private final Player player;
    private final Scanner input;

    //Constructor
    public Game(Player p, Scanner i) {
        player = p;
        input = i;
    }

    //Generators//

    //Creates a Random Weapon
    public Weapon randomWeapon() {
        int rand = (int)(Math.random() * 100) + 1 + player.getLuck();
        if (rand >= 80) {
            return new Weapon("Grognak's Axe", 10, 5, 20 - (player.getCharisma()/2), false);
        } else  if (rand >= 60){
            return new Weapon("Fat Man", 15, 5, 20 - (player.getCharisma()/2),true);
        } else if (rand >= 40) {
            return new Weapon("Gauss Rifle", 8, 2, 10 - (player.getCharisma()/2), true);
        } else {
            return new Weapon("Baseball Bat", 3, 2, 6 - (player.getCharisma()/2), false);
        }

    }

    //Randomly picks out the next event/room
    public void randomEvent() {
        int rand = (int)(Math.random() * 100) + 1 + player.getLuck();
        if (rand >= 95) {
            healRoom();
        } else if (rand >= 71) {
            chestRoom();
        } else if (rand >= 46) {
            merchantRoom();
        } else if (rand >= 1) {
            enemyRoom();
        }
    }

    //Events
    //Gives you a random item
    public void chestRoom() {
        int answer = -1;
        System.out.println("---------------------------------------\nYou see a room with a chest in the center\n\n 1. Open \n 2. Next Room\n---------------------------------------\n");
        while (answer < 1 || answer > 2) {
            answer = input.nextInt();
            if (answer < 1 || answer > 2) {
                System.out.println("Try Again");
            }
        }
        input.nextLine();
        //Creates a new weapon and prompts the player to equip it
        if (answer == 1) {
            answer = -1;
            Weapon tempWep = randomWeapon();
            int maxDam = tempWep.getDamage()+tempWep.getDamageRange();
            System.out.println("---------------------------------------\nYou open the chest and find a " + tempWep.getName() + "(" + (tempWep.getDamage()) + "-" +maxDam + ")" +"\n\n 1. Equip \n 2. Leave\n---------------------------------------\n");
            while (answer < 1 || answer > 2) {
                answer = input.nextInt();
                if (answer < 1 || answer > 2) {
                    System.out.println("Try Again");
                }
            }
            if (answer == 1) {
                player.equip(tempWep);
            }
        }
        player.addRoom();
    }

    //A Random enemy is created and you fight them
    public void enemyRoom() {
        Enemy enemy = new Enemy();
        boolean done = false;
        System.out.println("---------------------------------------\nYou see a room with a " + enemy.getName() + "(" + enemy.getHealth() + ")\n");
        int answer;
        while (enemy.getHealth() > 0 && !done && player.getHealth() > 0) {
            answer = -1;
            System.out.println(" 1. Fight \n 2. Flee\n---------------------------------------\n");
            while (answer < 1 || answer > 2) { //Prompting the Player to Run or Fight
                answer = input.nextInt();
                if (answer < 1 || answer > 2) {
                    System.out.println("Try Again"); //Only 1 or 2 allowed
                }
            }

            if (answer == 1) { //Attempt to Attack
                if (enemy.takeDamage(player.getDamage())) {
                    System.out.println("\n---------------------------------------\n         You kill the " + enemy.getName() + "\n---------------------------------------\n");
                    player.levelUp();
                    int caps = (int)(Math.random() * 25) + 1;
                    player.setCaps(player.getCaps() + caps);
                    System.out.println("\n---------------------------------------\n You get " + caps + " caps\n---------------------------------------\n");
                    done = true;
                }
            } else  { //Attempt to Flee
                if (((int)(Math.random()*4) + 1 + (player.getAgility()/2) >= 4)) {
                    System.out.println("\n---------------------------------------\n You Managed to Flee from the " + enemy.getName() + "\n---------------------------------------\n");
                    done = true;
                } else {
                    System.out.println("\n---------------------------------------\n You Fail to Flee from the " + enemy.getName());
                }
            }
            if (!done) {
                player.takeDamage(enemy.getDamage(),enemy.getName());
            }
        }
        player.addRoom();
    }

    //A Healing fountain that has a chance to instead deal 2 damage
    public void healRoom() {
        int answer = -1;
        System.out.println("---------------------------------------\nYou see a room with a fountain in the center\n\n 1. Drink \n 2. Next Room\n---------------------------------------\n");
        while (answer < 1 || answer > 2) {
            answer = input.nextInt();
            if (answer < 1 || answer > 2) {
                System.out.println("Try Again");
            }
        }
        input.nextLine();
        if (answer == 1) {
            if ((int)(Math.random()*10) + 1  + player.getLuck()/2 >= 6) {
                player.setHealth();
            } else {
                player.takeDamage(2);
            }
        }
        player.addRoom();
    }

    public void merchantRoom() {
        String answer = "";
        Weapon weapon1 = randomWeapon();
        Weapon weapon2 = randomWeapon();
        while (!answer.equals("Leave")) {
            System.out.println("---------------------------------------\nYou see a Merchant Selling items \n \"What do you wish to buy?\"(" + player.getCaps()+ ")\n\n " + weapon1.getName() + "(" + weapon1.getCost() + ") \n " + weapon2.getName() + "(" + weapon2.getCost() + ")\n Heal(15) \n Leave \n---------------------------------------\n");
            while (!answer.equals(weapon1.getName()) && !answer.equals(weapon2.getName()) && !answer.equals("Heal") && !answer.equals("Leave")) {
                answer = input.nextLine();
                if (!answer.equals(weapon1.getName()) && !answer.equals(weapon2.getName()) && !answer.equals("Heal") && !answer.equals("Leave")) {
                    System.out.println("Try Again");
                }
            }
            if (answer.equals(weapon1.getName())) {
                if (weapon1.getCost() <= player.getCaps()) {
                    player.equip(weapon1);
                    player.setCaps(player.getCaps()-weapon1.getCost());
                } else {
                    System.out.println("Try Again");
                }
                answer = "";
            } else if (answer.equals(weapon2.getName())) {
                if (weapon2.getCost() <= player.getCaps()) {
                    player.equip(weapon2);
                    player.setCaps(player.getCaps()-weapon2.getCost());
                } else {
                    System.out.println("Try Again");
                }
                answer = "";
            } else if (answer.equals("Heal")) {
                if (player.getCaps() >= 15) {
                    player.setCaps(player.getCaps()-15);
                    player.setHealth();
                } else {
                    System.out.println("Try Again");
                }
                answer = "";
            }
        }
        player.addRoom();

    }

}