import java.util.Scanner;

public class GrognakRunner {
    public static void main(String[] args) {
        System.out.println("---------------------------------------\n Welcome to Grognak and the Ruby Ruins\n         Trademark: RobCo 2077");
        Player player = new Player();
        Scanner input = new Scanner(System.in);
        Game game = new Game(player, input);
        player.CreateCharacter(input);
        while (player.getHealth() > 0){
            game.randomEvent();
        }
        input.close();
    }
}