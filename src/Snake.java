import java.util.Random;


public class Snake extends Obstacle {
    Random random = new Random();


    public Snake() {
        super("Snake", 4, 0, 12, 0);
        this.setDamage(getRandomDamage());

    }

    int randomDamage = random.nextInt(4) + 3;

    public int getRandomDamage() {
        return randomDamage;
    }


}










