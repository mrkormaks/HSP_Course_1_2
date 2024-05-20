// Три простых класса
class Weapon {
  String name;
  int maxDamage;
  int weight;
  int level;
  int ammo;

  Weapon(String name, int maxDamage, int weight, int level, int ammo) {
    this.name = name;
    this.maxDamage = maxDamage;
    this.weight = weight;
    this.level = level;
    this.ammo = ammo;
  }

  void setName(String newName) {
    this.name = newName;
  }

  int getMaxDamage() {
    return this.maxDamage;
  }

  int getWeight() {
    return this.weight;
  }

  void setMaxDamage(int newMaxDamage) {
    this.maxDamage = newMaxDamage;
  }

  void setLevel(int newLevel) {
    this.level = newLevel;
  }

  int getAmmoCount() {
    return this.ammo;
  }

  void shoot() {
    this.ammo -= 1;
  }
}

class Armor {
  String name;
  int durability;
  int weight;
  int level;

  Armor(String name, int durability, int weight, int level) {
    this.name = name;
    this.durability = durability;
    this.weight = weight;
    this.level = level;
  }

  void setName(String newName) {
    this.name = newName;
  }

  int getDurability() {
    return this.durability;
  }

  int getWeight() {
    return this.weight;
  }

  int getLevel() {
    return this.level;
  }

  void setLevel(int newLevel) {
    this.level = newLevel;
  }
}

class Player {
  String name;
  int health;
  int stamina;
  int maxWeight;
  int experience;
  Weapon weapon;
  Armor armor;

  Player(String name, int health, int stamina, int maxWeight, int experience, Weapon weapon, Armor armor) {
    this.name = name;
    this.health = health;
    this.stamina = stamina;
    this.maxWeight = maxWeight;
    this.experience = experience;
    this.weapon = weapon;
    this.armor = armor;
  }

  void setName(String newName) {
    this.name = newName;
  }

  void run() {
    this.stamina -= 1;
  }

  void shoot() {
    weapon.shoot();
  }

  boolean IsOverweight() {
    return (this.maxWeight > (this.weapon.weight + this.armor.weight));
  }
}

public class Exercise_2 {
  public static void main(String[] args) {
    // Создание экземпляров классов (объектов) и иницализация полей (теперь с помощью конструкторов)
    Weapon weapon = new Weapon("Shotgun", 15, 3, 1, 10);
    Armor armor = new Armor("Leather armor", 12, 7, 1);
    Player player = new Player("Jack", 100, 100, 50, 10, weapon, armor);

    System.out.println("Player's name is " + player.name);   
    System.out.println("Player's weapon is " + player.weapon.name);
    System.out.println("Player's armor is " + player.armor.name + "\n");

    // Поменяем броню игроку
    Armor heavyArmor = new Armor("Heavy armor", 23, 15, 3);

    player.armor = heavyArmor;
    System.out.println("Player's armor now is " + player.armor.name + "\n");

    // Сменим характеристики оружия
    System.out.println("Weapon's maxDamage before is " + weapon.maxDamage);
    System.out.println("Weapon's level before is " + weapon.level + "\n");

    weapon.setName("Reinforced shotgun");
    weapon.setMaxDamage(20);
    weapon.setLevel(2);

    System.out.println("Weapon's maxDamage after is " + weapon.maxDamage);
    System.out.println("Weapon's level after is " + weapon.level + "\n");

    System.out.println("Player's weapon now is " + player.weapon.name + "\n");

    // Пример побочного эффекта от передачи объектов по ссылке
    Weapon weapon2;
    weapon2 = weapon;
    System.out.println("Weapon's max damage " + weapon.maxDamage);
    weapon2.setMaxDamage(25);
    System.out.println("Weapon's max damage " + weapon.maxDamage + "\n");

    player.run();
    System.out.println("Player's stamina now is " + player.stamina + "\n");

    player.shoot();
    System.out.println("Ammo count now is " + player.weapon.ammo + "\n");

    String message = player.IsOverweight() ? "You still have plenty of inventory space" : "You are overloaded!";
    System.out.println(message);

  }
}
