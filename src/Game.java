import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome the Adventure Game ");
        System.out.print("Enter your player name: ");

        String playerName = input.nextLine();

        Player player = new Player(playerName);
        System.out.println("Dear " + player.getName() + " welcome to the dark and foggy island ! ");
        System.out.println("Please choose game character : ");
        System.out.println("----------");
        player.selectChar();
        Location location = null;
        while (true) {
            player.printInfo();
            System.out.println();
            System.out.println("##### Locations #####");
            System.out.println();
            System.out.println("1 - Safe House --> This is a safe house for you.There are no enemies");
            System.out.println("2 - Tool Store --> You can get weapons or armor ");
            System.out.println("3 - Cave --> You can fight zombies and earn food ");
            System.out.println("4 - Forest --> You can fight vampires and earn firewood ");
            System.out.println("5 - River --> You can fight bears and earn water ");
            System.out.println("6 - Mine --> You can fight snakes and earn weapon,armor or money ");
            System.out.println("0 - Exit --> Exit the game ");
            System.out.print("Please select location: ");
            int selectLoc = input.nextInt();
            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if(player.getInventory().isFood()) {
                        System.out.println("You can not enter river because you won food award");
                        continue;
                    } else {
                        location = new Cave(player);
                        break;
                    }

                case 4:
                    if(player.getInventory().isFirewood()) {
                        System.out.println("You can not enter forest because you won firewood award");
                        continue;
                    } else {
                        location = new Forest(player);
                        break;
                    }

                case 5:
                    if (player.getInventory().isWater()) {
                        System.out.println("You can not enter river because you won water award");
                        continue;
                    } else {
                        location = new River(player);
                        break;
                    }
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("Please enter valid location ");
            }

            if (location == null) {
                System.out.println("You quickly gave up on this dark and foggy island !");
                break;
            }
            if (location.getClass().getName().equals("SafeHouse")) {
                if (player.getInventory().isFood() && player.getInventory().isFirewood() && player.getInventory().isWater()) {
                    System.out.println("Congrats !! You are win the game !!");
                    break;
                }
            }

            if (!location.onLocation()) {
                System.out.println("Game Over !! You are lost the game");
                break;
            }

        }

    }
}