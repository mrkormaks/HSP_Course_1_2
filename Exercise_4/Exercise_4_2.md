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

  public void attack() {
    System.out.println("Weapon attack!");
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

  public void attack() {
    System.out.println("Crossbow attack!");
  }
}

class Bow extends Weapon {
  protected int drawTime = 2; // время натяжения лука

  Bow(String name, int maxDamage, int weight, int ammo, int drawTime) {
    super(name, maxDamage, weight, ammo);
    this.drawTime = drawTime;
  }

  public void attack() {
    System.out.println("Bow attack!");
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
      armorWeightImpact = this.armor.getWeight() / 5.0f;
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

public class Exercise_4_2 {
  public static void main(String[] args) {
    Weapon[] weapons = new Weapon[500];

    // Заполнение массива чередующимися объектами Crossbow и Bow
    for (int i = 0; i < weapons.length; i++) {
      if (i % 3 == 0) {
        weapons[i] = new Crossbow("Hunter's Crossbow", 20, 7, 5, 150, 25);
      } else {
        weapons[i] = new Bow("Elven Bow", 15, 3, 10, 3);
      }
    }

    // Вызов метода attack для каждого объекта
    for (Weapon weapon : weapons) {
      weapon.attack();
    }
  }
}
