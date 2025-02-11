class DynamicArray<T> extends DynArray<T> {
  private final int growthFactor;

  public DynamicArray(Class<T> clz, int growthFactor) {
    super(clz);
    this.growthFactor = growthFactor;
  }

  @Override
  public void makeArray(int new_capacity) {
    if (new_capacity < 16) {
      new_capacity = 16;
    }

    if (new_capacity <= capacity) {
      return;
    }
     
    new_capacity = Math.max(new_capacity, capacity + growthFactor);
    
    super.makeArray(new_capacity);
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



