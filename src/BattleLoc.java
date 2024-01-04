import java.util.Random;

public abstract class BattleLoc extends Location {

    private Obstacle obstacle;
    private String award;
    private int maxObstacle;


    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("You are in the " + this.getName() + " now");
        System.out.println("Be careful ! " + obsNumber + " " + this.getObstacle().getName() + " live here !");
        System.out.print("<F>ight or <R>un : ");
        String selectCase = input.nextLine().toUpperCase();
        if (selectCase.equals("F") && combat(obsNumber)) {
            System.out.println(this.getName() + " beat all the obstacles");
            if(this.award.equals("food") && !this.getPlayer().getInventory().isFood()) {
                System.out.println("You win the " + this.award);
                this.getPlayer().getInventory().setFood(true);
            } else if (this.award.equals("firewood") && !this.getPlayer().getInventory().isFirewood()) {
                System.out.println("You win the " + this.award);
                this.getPlayer().getInventory().setFirewood(true);
            } else if (this.award.equals("water") && !this.getPlayer().getInventory().isWater()) {
                System.out.println("You win the " + this.award);
                this.getPlayer().getInventory().setWater(true);
            }
            return true;

        }

        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("You died !!");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print("<H>it or <R>un : ");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("H")) {
                    whoHit();
                } else {
                    return false;
                }


            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("You defeated the enemy");
                if(getObstacle().getName().equals("Snake")) {
                    snakeAward();
                }
                System.out.println("You won the: " + this.getObstacle().getAward() + " money");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Your current balance: " + this.getPlayer().getMoney() + " money");
            } else {
                return false;
            }

        }
        return true;
    }

    public void afterHit() {
        System.out.println("Player Health: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Health: " + this.getObstacle().getHealth());
        System.out.println("----------");

    }

    public void playerStats() {
        System.out.println("Player Stats");
        System.out.println("----------");
        System.out.println("Health: " + this.getPlayer().getHealth());
        System.out.println("Weapon: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Armor: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Blocking: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Damage: " + this.getPlayer().getTotalDamage());
        System.out.println("Money: " + this.getPlayer().getMoney());
        System.out.println();
    }

    public void obstacleStats(int i) {
        System.out.println(i + "." + this.getObstacle().getName() + " Stats");
        System.out.println("----------");
        System.out.println("Health: " + this.getObstacle().getHealth());
        System.out.println("Damage: " + this.getObstacle().getDamage());
        System.out.println("Award: " + this.getObstacle().getAward());
        System.out.println();
    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;

    }


    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public boolean whoHit() {
        double start = Math.random() * 100;
        if (start < 50) {
            System.out.println(this.getPlayer().getName() + " start");
            playerHit();
            obstacleHit();

        } else {
            System.out.println(this.getObstacle().getName() + " start");
            obstacleHit();
            playerHit();
        }
        return true;
    }

    public boolean playerHit() {
        System.out.println("You hit obstacle !");
        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
        afterHit();
        return true;
    }

    public boolean obstacleHit() {
        if (this.getObstacle().getHealth() > 0) {
            System.out.println();
            System.out.println("Obstacle hit you !");
            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
            if (obstacleDamage < 0) {
                obstacleDamage = 0;
            }
            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
            afterHit();
        }
        return true;
    }

    public void snakeAward() {
        Random random = new Random();
        int randomAward = random.nextInt(101);
        if(randomAward >0 && randomAward <= 15) {
            int randomWeapon = random.nextInt(101);
                if(randomWeapon <= 20) {
                    getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));
                    System.out.println("You won " + getPlayer().getInventory().getWeapon().getName());
            } else if (randomWeapon > 20 && randomWeapon <= 50) {
                    getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
                    System.out.println("You won " + getPlayer().getInventory().getWeapon().getName());
                } else {
                    getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(0));
                    System.out.println("You won " + getPlayer().getInventory().getWeapon().getName());
                }
        }
       else if(randomAward > 15 && randomAward <= 30) {
            int randomArmor = random.nextInt(101);
            if(randomArmor <= 20) {
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));
                System.out.println("You won " + getPlayer().getInventory().getArmor().getName());
            } else if (randomArmor > 20 && randomArmor <= 50) {
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
                System.out.println("You won " + getPlayer().getInventory().getArmor().getName());
            } else {
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(0));
                System.out.println("You won " + getPlayer().getInventory().getArmor().getName());
            }
        } else if (randomAward > 30 && randomAward <= 55) {
           int randomMoney = random.nextInt(101);
           if(randomMoney <= 20) {
               getPlayer().setMoney(getPlayer().getMoney()+ 10);
               System.out.println("You won 10 money");
           } else if (randomMoney > 20 && randomMoney <= 55) {
               getPlayer().setMoney(getPlayer().getMoney() + 5);
               System.out.println("You won 5 money");
           } else {
               getPlayer().setMoney(getPlayer().getMoney() +1);
           }

        } else {
            System.out.println("You won nothing !");
        }


    }


}
