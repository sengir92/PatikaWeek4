public class SafeHouse  extends  NormalLoc{

    public SafeHouse(Player player) {
        super(player,"Safe House");
    }

    @Override
    public boolean onLocation() {
        System.out.println("You are in the safe house !");
        System.out.println("Your health is renewed !");
        this.getPlayer().setHealth(this.getPlayer().getOrjinalHealth());
        return true;
    }

}
