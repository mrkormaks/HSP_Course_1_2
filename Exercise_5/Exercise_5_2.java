// Три простых класса
class Weapon {
  private String name;
  private int maxDamage;
  private int weight;
  private int level;
  private int ammo;

  Weapon(String name, int maxDamage, int weight, int level, int ammo) {
    this.name = name;
    this.maxDamage = maxDamage;
    this.weight = weight;
    this.level = level;
    this.ammo = ammo;
  }

  public void setName(String newName) {
    this.name = newName;
  }

  public int getMaxDamage() {
    return this.maxDamage;
  }

  public int getWeight() {
    return this.weight;
  }

  public void setMaxDamage(int newMaxDamage) {
    this.maxDamage = newMaxDamage;
  }

  public void setLevel(int newLevel) {
    this.level = newLevel;
  }

  public int getAmmoCount() {
    return this.ammo;
  }

  public void shoot() {
    this.ammo -= 1;
  }
}

class Armor {
  private String name;
  private int durability;
  private int weight;
  private int level;

  Armor(String name, int durability, int weight, int level) {
    this.name = name;
    this.durability = durability;
    this.weight = weight;
    this.level = level;
  }

  public void setName(String newName) {
    this.name = newName;
  }

  public int getDurability() {
    return this.durability;
  }

  public int getWeight() {
    return this.weight;
  }

  public int getLevel() {
    return this.level;
  }

  public void setLevel(int newLevel) {
    this.level = newLevel;
  }
}

class Player {
  private String name;
  private int health;
  private int stamina;
  private int maxWeight;
  private int experience;
  private Weapon weapon;
  private Armor armor;

  Player(String name, int health, int stamina, int maxWeight, int experience, Weapon weapon, Armor armor) {
    this.name = name;
    this.health = health;
    this.stamina = stamina;
    this.maxWeight = maxWeight;
    this.experience = experience;
    this.weapon = weapon;
    this.armor = armor;
  }

  public void setName(String newName) {
    this.name = newName;
  }

  public void run() {
    this.stamina -= 1;
  }

  public void shoot() {
    weapon.shoot();
  }

  public boolean IsOverweight() {
    return (this.maxWeight <= (this.weapon.getWeight() + this.armor.getWeight()));
  }
}

public class Exercise_5_2 {
  public static void main(String[] args) {
    // Создание экземпляров классов (объектов) и иницализация полей (с помощью конструкторов)
    Weapon weapon = new Weapon("Shotgun", 15, 3, 1, 10);
    Armor armor = new Armor("Leather armor", 12, 7, 1);
    Player player = new Player("Jack", 100, 100, 50, 10, weapon, armor);

    String overweightMessage = player.IsOverweight() ? "You have too many things!" : "You still have enough space.";
    System.out.println(overweightMessage);
  }
}
