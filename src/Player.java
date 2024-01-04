import java.util.Scanner;

public class Player {

    private int damage;
    private int health;
    private int orjinalHealth;
    private int money;
    private String charName;
    private String name;
    private Scanner inp = new Scanner(System.in);
    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health < 0) {
            health = 0;
        }
        this.health = health;
    }
    public int getOrjinalHealth() {
        return orjinalHealth;
    }
    public void setOrjinalHealth(int orjinalHealth) {
        this.orjinalHealth = orjinalHealth;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


    public void selectChar() {
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println("Characters");
        System.out.println("----------");
        for (GameChar gameChar : charList) {
            System.out.println("ID : " + gameChar.getId() +
                    "\tCharacter: " + gameChar.getName() +
                    "\tDamage: " + gameChar.getDamage() +
                    "\t Health: " + gameChar.getHealth() +
                    "\t Money: " + gameChar.getMoney());
        }
        System.out.println("----------");
        System.out.print("Please select character: ");
        int selectChar = inp.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());

        }
        System.out.println("Your character: "+ this.getCharName() +
                " Damage: " +this.getDamage() +
                " Health: " +this.getHealth() +
                " Money: " +this.getMoney());

    }

    public void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOrjinalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }

    public void printInfo(){
        System.out.println("Your gun: " + this.getInventory().getWeapon().getName() +
                ", Your armor: " + this.getInventory().getArmor().getName() +
                ", Your blocking: " + this.getInventory().getArmor().getBlock() +
                ", Your Damage: " +this.getTotalDamage() +
                ", Your Health: " +this.getHealth() +
                ", Your Money: " +this.getMoney());
    }

}
