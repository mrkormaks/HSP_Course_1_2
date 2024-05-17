// Три простых класса
class Weapon {
  String name;
  int maxDamage;
  int weight;
  int level;
}

class Armor {
  String name;
  int durability;
  int weight;
  int level;
}

class Player {
  String name;
  int health;
  int stamina;
  int maxWeight;
  int experience;
  Weapon weapon;
  Armor armor;
}

public class Exercise_1 {
  public static void main(String[] args) {
    // Создание экземпляров классов (объектов) и иницализация полей
    Weapon weapon = new Weapon();
    weapon.name = "Shotgun";
    weapon.maxDamage = 15;
    weapon.weight = 3;
    weapon.level = 1;

    Armor armor = new Armor();
    armor.name = "Leather armor";
    armor.durability = 12;
    armor.weight = 7;
    armor.level = 1;

    Player player = new Player();
    player.name = "Jack";
    player.health = 100;
    player.stamina = 100;
    player.maxWeight = 50;
    player.experience = 10;
    player.weapon = weapon;
    player.armor = armor;

    System.out.println("Player's name is " + player.name);   
    System.out.println("Player's weapon is " + player.weapon.name);
    System.out.println("Player's armor is " + player.armor.name + "\n");

    // Поменяем броню игроку
    Armor heavyArmor = new Armor();
    heavyArmor.name = "Heavy armor";
    heavyArmor.durability = 23;
    heavyArmor.weight = 15;
    heavyArmor.level = 3;

    player.armor = heavyArmor;
    System.out.println("Player's armor now is " + player.armor.name + "\n");

    // Сменим характеристики оружия
    System.out.println("Weapon's maxDamage before is " + weapon.maxDamage);
    System.out.println("Weapon's level before is " + weapon.level + "\n");

    weapon.name = "Reinforced shotgun";
    weapon.maxDamage = 20;
    weapon.level = 2;

    System.out.println("Weapon's maxDamage after is " + weapon.maxDamage);
    System.out.println("Weapon's level after is " + weapon.level + "\n");

    System.out.println("Player's weapon now is " + player.weapon.name);

    // Пример побочного эффекта от передачи объектов по ссылке
    Weapon weapon2;
    weapon2 = weapon;
    System.out.println("Weapon's max damage " + weapon.maxDamage);
    weapon2.maxDamage = 25;
    System.out.println("Weapon's max damage " + weapon.maxDamage + "\n");
  }
}
