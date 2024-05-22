// Три простых класса
class Weapon {
  protected  String name = "Gun";
  protected  int maxDamage = 10;
  protected  int weight = 5;
  protected  int ammo = 8;

  Weapon(String name, int maxDamage, int weight, int ammo) {
    this.name = name;
    this.maxDamage = maxDamage;
    this.weight = weight;
    this.ammo = ammo;
  }

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

class Crossbow extends Weapon {
  protected int shotRange = 120;
  protected int criticalHitChance = 20;

  Crossbow(String name, int maxDamage, int weight, int ammo, int shotRange, int criticalHitChance) {
    super(name, maxDamage, weight, ammo);
    this.shotRange = shotRange;
    this.criticalHitChance = criticalHitChance;
  }

  public boolean canHitTarget(int distance) {
    return distance <= shotRange;
  }
}

class Armor {
  protected  String name = "Default Armor";
  protected  int durability = 10;
  protected  int weight = 5;

  Armor(String name, int durability, int weight) {
    this.name = name;
    this.durability = durability;
    this.weight = weight;
  }

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

class Shield extends Armor {
  protected int blockChance = 15;

  Shield(String name, int durability, int weight, int blockChance) {
    super(name, durability, weight);
    this.blockChance = blockChance;
  }

  public int getBlockChance() {
    return this.blockChance;
  }
}

class Player {
  protected  String name = "Nameless";
  protected  int health = 100;
  protected  int stamina = 100;
  protected  int maxWeight = 50;
  protected  int level = 1;
  protected  Weapon weapon = null;
  protected  Armor armor = null;

  Player(String name, int health, int stamina, int maxWeight, int level, Weapon weapon, Armor armor) {
    this.name = name;
    this.health = health;
    this.stamina = stamina;
    this.maxWeight = maxWeight;
    this.level = level;
    this.weapon = weapon;
    this.armor = armor;
  }

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

public class Exercise_5_3 {
  public static void main(String[] args) {
    // Создание экземпляров классов (объектов) и иницализация полей (с помощью конструкторов)
    Weapon weapon = new Weapon("Shotgun", 15, 3, 10);
    Armor armor = new Armor("Leather armor", 12, 7);
    Player player = new Player("Jack", 100, 100, 50, 10, weapon, armor);

    String overweightMessage = player.IsOverweight() ? "You have too many things!" : "You still have enough space.";
    System.out.println(overweightMessage);
  }
}
