/**
 * Array based list.
 *
 * @author Josh Hug
 */

public class AList<T> {

    private int size;
    private T[] item;

    /**
     * Creates an empty list.
     */
    public AList() {
        this.item = (T[]) new Object[100];
        this.size = 0;
    }

    /**
     * Inserts X into the back of the list.
     */
    public void addLast(T x) {
        if (size == item.length) {
            resize(size + 1);
        }
        this.item[size] = x;
        size++;
    }

    private void resize(int capacity) {
        T[] newItem = (T[]) new Object[size + 1];
        System.arraycopy(item, 0, newItem, 0, size);
        item = newItem;
    }

    /**
     * Returns the item from the back of the list.
     */
    public T getLast() {
        return this.item[size - 1];
    }

    /**
     * Gets the ith item in the list (0 is the front).
     */
    public T get(int i) {
        return this.item[i];
    }

    /**
     * Returns the number of items in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Deletes item from back of the list and
     * returns deleted item.
     */
    public T removeLast() {
        T last = getLast();
        item[size - 1] = null;
        size--;
        return last;
    }

    public static void main(String[] args) {
        AList aList = new AList();
        for (int i = 0; i < 1000; i++) {
            aList.addLast(i);
        }
        Object o = aList.removeLast();
        System.out.println(o);
        System.out.println(aList.getLast());
    }
} 