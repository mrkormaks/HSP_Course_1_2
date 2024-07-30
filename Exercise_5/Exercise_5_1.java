// Три простых класса
class Weapon {
  private String name = "Gun";
  private int maxDamage = 10;
  private int weight = 5;
  private int ammo = 8;

  public void setName(String newName) {
    if (!newName.isEmpty()) {
      this.name = newName;
    } else {
      this.name = "Gun"; // Задаём значение по умолчанию
    }
  }

  public String getName() {
    return this.name;
  }

  public void setMaxDamage(int newMaxDamage) {
    this.maxDamage = Math.max(newMaxDamage, 1); // Минимум 1
  }

  public int getWeight() {
    return this.weight;
  }

  public void setWeight(int newWeight) {
    this.weight = Math.max(newWeight, 1); // Минимум 1
  }

  public void setAmmoCount(int newAmmo) {
    this.ammo = Math.max(newAmmo, 0); // Минимум 0
  }

  public int getAmmoCount() {
    return this.ammo;
  }

  public void shoot() {
    if (this.ammo > 0) {
      this.ammo -= 1;
    }
  }

  public void shoot(Person target) {
    if (this.ammo > 0) {
      this.ammo -= 1;
      target.takeDamage(this.maxDamage);
    }
  }
}

class Armor {
  private String name = "Default Armor";
  private int durability = 10;
  private int weight = 5;

  public void setName(String newName) {
    if (!newName.isEmpty()) {
      this.name = newName;
    } else {
      this.name = "Default Armor"; // Задаём значение по умолчанию
    }
  }

  public String getName() {
    return this.name;
  }

  public void setDurability(int newDurability) {
    this.durability = Math.max(newDurability, 1); // Минимум 1
  }

  public int getDurability() {
    return this.durability;
  }

  public int getWeight() {
    return this.weight;
  }

  public void setWeight(int newWeight) {
    this.weight = Math.max(newWeight, 1); // Минимум 1
  }
}

class Person {
  private String name = "Nameless";
  private float health = 100.0f;
  private float stamina = 100.0f;
  private float maxWeight = 50.0f;
  private int level = 1;
  private Weapon weapon = null;
  private Armor armor = null;

  public void setName(String newName) {
    if (!newName.isEmpty()) {
      this.name = newName;
    } else {
      this.name = "Nameless"; // Задаём значение по умолчанию
    }
  }

  public String getName() {
    return this.name;
  }

  public int getLevel() {
    return this.level;
  }

  public float getStamina() { return this.stamina; }

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
    int totalWeight = (this.weapon != null ? this.weapon.getWeight() : 0) +
            (this.armor != null ? this.armor.getWeight() : 0);
    double weightFactor = 1 + totalWeight / this.maxWeight; // Коэффициент зависимости от веса

    if (this.stamina > 0) {
      this.stamina -= weightFactor; // Уменьшаем выносливость с учетом веса
      if (this.stamina < 0) {
        this.stamina = 0;
      }
    }
  }

  public void shoot() {
        if (this.weapon != null) {
      this.weapon.shoot();
    }
  }

  public void shoot(Person target) {
    if (this.weapon != null) {
      this.weapon.shoot(target);
    }
  }

  public boolean IsOverweight() {
    if (this.weapon == null || this.armor == null) {
      return false;
    }

    return this.maxWeight <= (this.weapon.getWeight() + this.armor.getWeight());
  }

  public void takeDamage(int damage) {
    if (this.armor != null) {
      double armorFactor = 1 - (this.armor.getDurability() / 100.0); // Коэффициент снижения урона
      damage = (int)(damage * armorFactor);
    }
    this.health -= damage;
    this.health = Math.max(this.health, 0); // Здоровье не может быть меньше 0
  }

  public float getHealth() {
    return this.health;
  }
}

public class Exercise_5_1 {
  public static void main(String[] args) {
    // Создание экземпляров классов (объектов) и инициализация полей
    Weapon weapon = new Weapon();
    weapon.setName("Shotgun");
    weapon.setMaxDamage(20);
    weapon.setWeight(7);
    weapon.setAmmoCount(10);

    Armor armor = new Armor();
    armor.setName("Leather armor");
    armor.setDurability(17);
    armor.setWeight(12);

    Person hero = new Person();
    hero.setName("Jack");
    hero.setLevel(10);
    hero.setWeapon(weapon);
    hero.setArmor(armor);

    Person enemy = new Person();
    enemy.setName("Enemy");
    enemy.setLevel(5);

    System.out.println("Ammo count: " + weapon.getAmmoCount());
    hero.shoot();
    System.out.println("Ammo count: " + weapon.getAmmoCount());

    hero.shoot(enemy);
    System.out.println(enemy.getName() + "'s health: " + enemy.getHealth());

    String overweightMessage = hero.IsOverweight() ? "You have too many things!" : "You still have enough space.";
    System.out.println(overweightMessage);

    // Пример использования метода run
    System.out.println(hero.getName() + "'s stamina before run: " + hero.getStamina());
    hero.run();
    System.out.println(hero.getName() + "'s stamina after run: " + hero.getStamina());
  }
}
