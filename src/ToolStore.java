public class ToolStore extends NormalLoc {

    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    @Override
    public boolean onLocation() {
        System.out.println("----- Welcome to tool store ! -----");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1 - Weapons");
            System.out.println("2 - Armors");
            System.out.println("3 - Exit the tool store");
            System.out.print("Your choise: ");
            int selectCase = input.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                System.out.print("You enter invalid value,re-enter valid value");
                selectCase = input.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("See you again !");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("-----Weapons-----");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + " - " + w.getName() + " < Money : " + w.getPrice() + " Damage: " + w.getDamage() + " >");
        }
        System.out.println("0 - Exit ");
    }


    public void buyWeapon() {
        System.out.print("Select weapon: ");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length) {
            System.out.print("You enter invalid value,re-enter valid value: ");
            selectWeaponID = input.nextInt();
        }

        if (selectWeaponID != 0) {

            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("You don't have got enough money !");
                } else {
                    System.out.println(selectedWeapon.getName() + " weapon you bought");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Your balance: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }

        }

    }

    public void printArmor() {
        System.out.println("-----Armors-----");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + " - " + a.getName() + " < Money : " + a.getPrice() + " Block: " + a.getBlock() + " >");
        }
        System.out.println("0 - Exit ");
    }

    public void buyArmor() {
        System.out.print("Select armor: ");
        int selectArmorID = input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length) {
            System.out.print("You enter invalid value,re-enter valid value:  ");
            selectArmorID = input.nextInt();
        }
        if (selectArmorID != 0) {
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);
            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("You don't have got enough money !");
                } else {
                    System.out.println(selectedArmor.getName() + " you bought !");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Your balance: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(selectedArmor);

                }
            }

        }
    }
}