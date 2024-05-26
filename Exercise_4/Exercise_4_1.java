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
  protected String name = "Nameless";
  protected int health = 100;
  protected float stamina = 100.0f; // Изменил на float
  protected int maxWeight = 50;
  protected int level = 1;
  protected Weapon weapon = null;
  protected Armor armor = null;

  Player(String name, int health, float stamina, int maxWeight, int level, Weapon weapon, Armor armor) {
    this.name = name;
    this.health = health;
    this.stamina = stamina;
    this.maxWeight = maxWeight;
    this.level = level;
    this.weapon = weapon;
    this.armor = armor;
  }

  public void setName(String newName) {
    if (!newName.isEmpty()) {
      this.name = newName;
    } else {
      System.out.println("The specified name cannot be empty. The default name is used.");
    }
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
    float staminaCost = 1.0f; // стандартная трата выносливости
    float armorWeightImpact = 0.0f; // коэффициент, зависящий от веса брони

    if (this.armor != null) {
      armorWeightImpact = this.armor.getWeight() / 5.0f; // Пример коэффициента
    }

    staminaCost += armorWeightImpact;

    if (this.stamina >= staminaCost) {
      this.stamina -= staminaCost;
    } else {
      System.out.println("You're too tired to run!");
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
    if (this.weapon == null || this.armor == null) {
      return false;
    }
    return this.maxWeight <= (this.weapon.getWeight() + this.armor.getWeight());
  }
}

public class Exercise_4_1 {
  public static void main(String[] args) {
    // Создаем объекты оружия
    Weapon gun = new Weapon("Pistol", 15, 3, 10);
    Crossbow crossbow = new Crossbow("Hunter's Crossbow", 20, 7, 5, 150, 25);

    // Создаем объекты брони
    Armor lightArmor = new Armor("Light Armor", 25, 10);
    Shield woodenShield = new Shield("Wooden Shield", 30, 8, 20);

    // Создаем объект игрока и присваиваем ему оружие и броню
    Player player = new Player("Hero", 100, 100.0f, 50, 1, gun, lightArmor);

    // Выводим начальное состояние игрока
    System.out.println("Player Name: " + player.getName());
    System.out.println("Player Health: " + player.health);
    System.out.println("Player Stamina: " + player.stamina);
    System.out.println("Player Weapon: " + player.weapon.getName());
    System.out.println("Player Armor: " + player.armor.getName());
    System.out.println("Is player overweight? " + player.IsOverweight());

    // Игрок стреляет из оружия
    player.shoot();
    System.out.println("Ammo left after shooting: " + player.weapon.ammo);

    // Игрок бегает в броне
    player.run();
    System.out.println("Player Stamina after running: " + player.stamina);

    // Игрок меняет оружие и броню
    player.setWeapon(crossbow);
    player.setArmor(woodenShield);

    // Выводим состояние игрока после изменения оружия и брони
    System.out.println("\nAfter changing equipment:");
    System.out.println("Player Weapon: " + player.weapon.getName());
    System.out.println("Player Armor: " + player.armor.getName());
    System.out.println("Is player overweight? " + player.IsOverweight());

    // Игрок стреляет из арбалета
    player.shoot();
    System.out.println("Ammo left in crossbow after shooting: " + player.weapon.ammo);

    // Игрок бегает с щитом
    player.run();
    System.out.println("Player Stamina after running: " + player.stamina);
  }
}
