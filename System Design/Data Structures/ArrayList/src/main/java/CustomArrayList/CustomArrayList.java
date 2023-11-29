package CustomArrayList;

public interface CustomArrayList<T> {
    // Returns the Size of the ArrayList
    int size();

    // Adds a New Element to the ArrayList
    void add(T data);

    // Returns the Object at the specified index
    T get(int index);

    // Deletes the Object at the specified index
    T delete(int index);
}
