// Три простых класса
class Weapon {
  private String name = "Gun";
  private int maxDamage = 10;
  private int weight = 5;
  private int ammo = 8;

  public void setName(String newName) {
    if (!newName.isEmpty())
      this.name = newName;
    else
      System.out.println("The specified name cannot be empty. The default name is used.");
  }

  public String getName() {
    return this.name;
  }

  public void setMaxDamage(int newMaxDamage) {
    if (newMaxDamage <= 1)
      this.maxDamage = 1;
    else
      this.maxDamage = newMaxDamage;
  }

  public int getWeight() {
    return this.weight;
  }

  public void setWeight(int newWeight) {
    if (newWeight <= 0) {
      System.out.println("The weight can't be less and is equal to zero.");
      return;
    }
      
    this.weight = newWeight;
  }

  public void setAmmoCount(int newAmmo) {
    if (newAmmo < 0) {
      System.out.println("The amount of ammo can't be less than zero.");
      return;
    }
      
    this.ammo = newAmmo;
  }

  public void shoot() {
    if (this.ammo > 0)
      this.ammo -= 1;
    else
      System.out.println("Ammo ran out!");
  }
}

class Armor {
  private String name = "Default Armor";
  private int durability = 10;
  private int weight = 5;

  public void setName(String newName) {
    if (!newName.isEmpty())
      this.name = newName;
    else
      System.out.println("The specified name cannot be empty. The default name is used.");
  }

  public String getName() {
    return this.name;
  }

  public void setDurability(int newDurability) {
    if (newDurability <= 1)
      this.durability = 1;
    else
      this.durability = newDurability;
  }

  public int getWeight() {
    return this.weight;
  }

  public void setWeight(int newWeight) {
    if (newWeight <= 0) {
      System.out.println("The weight can't be less and is equal to zero.");
      return;
    }

    this.weight = newWeight;
  }
}

class Player {
  private String name = "Nameless";
  private int health = 100;
  private int stamina = 100;
  private int maxWeight = 50;
  private int level = 1;
  private Weapon weapon = null;
  private Armor armor = null;

  public void setName(String newName) {
    if (!newName.isEmpty())
      this.name = newName;
    else
      System.out.println("The specified name cannot be empty. The default name is used.");
  }

  public String getName() {
    return this.name;
  }

  public int getLevel() {
    return this.level;
  }

  public void setLevel(int newLevel) {
    this.level = newLevel;
  }

  public void setWeapon(Weapon newWeapon) {
    this.weapon = newWeapon;
  }

  public void setArmor(Armor newArmor) {
    this.armor = newArmor;
  }

  public void run() {
    if (this.stamina > 0) {
      this.stamina -= 1;
    } else {
      System.out.println("You're tired out!");
    }
  }

  public void shoot() {
    if (this.weapon != null) {
      this.weapon.shoot();
    } else {
      System.out.println("You don't have a weapon!");
    }
  }

  public boolean IsOverweight() {
    if (this.weapon == null || this.armor == null)
      return false;

    return this.maxWeight <= (this.weapon.getWeight() + this.armor.getWeight());
  }
}

public class Exercise_5_1 {
  public static void main(String[] args) {
    // Создание экземпляров классов (объектов) и иницализация полей
    Weapon weapon = new Weapon();
    weapon.setName("Shotgun");
    weapon.setMaxDamage(15);
    weapon.setWeight(3);
    weapon.setAmmoCount(10);

    Armor armor = new Armor();
    armor.setName("Leather armor");
    armor.setDurability(12);
    armor.setWeight(7);

    Player player = new Player();
    player.setName("Jack");
    player.setLevel(10);
    player.setWeapon(weapon);
    player.setArmor(armor);

    String overweightMessage = player.IsOverweight() ? "You have too many things!" : "You still have enough space.";
    System.out.println(overweightMessage);
  }
}
