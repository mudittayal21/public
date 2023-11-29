package CustomArrayList;

import java.util.Arrays;

public class LinearArrayList<T> implements CustomArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int capacity, size;
    private float loadFactor;
    private T[] data;

    // Constructors :: Start
    public LinearArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.data = (T[]) new Object[this.capacity];
    }
    // Constructors :: End

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(T data) {
        this.data[this.size++] = data;

        if (this.size > (this.capacity * this.loadFactor))
            resize();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size)
            return null;

        return this.data[index];
    }

    @Override
    public T delete(int index) {
        if (index < 0 || index >= this.size)
            return null;

        T deleted = this.data[index];

        for (int i = index + 1; i < this.size; i++) {
            this.data[i - 1] = this.data[i];
        }

        this.size--;
        return deleted;
    }

    private void resize() {
        int currentCapacity = this.capacity;
        this.capacity *= 2;

        int index = 0;
        T[] newData = (T[]) new Object[this.capacity];

        for (T val : this.data)
            newData[index++] = val;

        this.data = newData;
    }
}
