class DynamicArray<T> extends DynArray<T> {
  private int bank;  // "Банк" амортизационных единиц
  private final int growthFactor;

  public DynamicArray(Class<T> clz, int growthFactor) {
    super(clz);
    this.growthFactor = growthFactor;
    this.bank = 0; // Начальный баланс банка
  }

  @Override
  public void makeArray(int new_capacity) {
    if (new_capacity <= capacity) {
      return; // Уже достаточно памяти
    }

    // БАНКОВСКИЙ МЕТОД: если накоплено достаточно "денег", тратим их на реаллокацию
    if (bank >= capacity) {
      bank -= capacity;  // Списываем накопленный банк
      new_capacity = capacity * 2;  // Удваиваем размер
    } else {
      new_capacity = capacity + growthFactor;  // Иначе обычное расширение
    }

    super.makeArray(new_capacity);
  }

  @Override
  public void append(T itm) {
    if (count == capacity) {
      makeArray(capacity + growthFactor); // Реаллокация при полном заполнении
    }
    array[count] = itm;
    count++;

    // БАНКОВСКИЙ МЕТОД: При добавлении 1 реальный расход + 2 откладываем в банк
    bank += 2;
  }

  @Override
  public void insert(T itm, int index) {
    if (index < 0 || index > count) {
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    if (count == capacity) {
      makeArray(capacity + growthFactor);
    }
    System.arraycopy(array, index, array, index + 1, count - index);
    array[index] = itm;
    count++;

    // БАНКОВСКИЙ МЕТОД: Аналогично append — 1 расход + 2 в банк
    bank += 2;
  }

  @Override
  public void remove(int index) {
    if (index < 0 || index >= count) {
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    System.arraycopy(array, index + 1, array, index, count - index - 1);
    count--;

    // БАНКОВСКИЙ МЕТОД: При удалении списываем 1 + 10% от оставшихся элементов
    bank -= Math.max(1, count / 10);

    // При падении числа элементов ниже половины, уменьшаем размер, но не резко
    if (count < capacity / 2) {
      int newCapacity = (int) (capacity / 1.5);
      if (newCapacity < 16) {
        newCapacity = 16;
      }
      if (newCapacity != capacity) {
        makeArray(newCapacity);
      }
    }
  }
}

class DynamicMultiArray<T> extends DynArray<T> {
  private int[] dimensions;

  public DynamicMultiArray(Class<T> clz, int... dimensions) {
    super(clz);
    this.dimensions = dimensions.clone();
    int totalSize = 1;
    for (int dim : dimensions) {
      totalSize *= dim;
    }
    makeArray(totalSize);
  }

  public void insert(int[] indices, T value) {
    int flatIndex = convertIndicesToFlat(indices);
    insert(value, flatIndex);
  }

  public T retrieve(int[] indices) {
    int flatIndex = convertIndicesToFlat(indices);
    return getItem(flatIndex);
  }

  public void remove(int[] indices) {
    int flatIndex = convertIndicesToFlat(indices);
    remove(flatIndex);
  }

  private int convertIndicesToFlat(int[] indices) {
    if (indices.length != dimensions.length) {
      throw new IllegalArgumentException("Incorrect number of indices");
    }
    int flatIndex = 0;
    int multiplier = 1;
    for (int i = indices.length - 1; i >= 0; i--) {
      if (indices[i] < 0 || indices[i] >= dimensions[i]) {
        throw new IndexOutOfBoundsException("Index out of bounds for dimension " + i);
      }
      flatIndex += indices[i] * multiplier;
      multiplier *= dimensions[i];
    }
    return flatIndex;
  }
}



