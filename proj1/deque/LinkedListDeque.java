package deque;

public class LinkedListDeque<T> {

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node sentinel;

    /**
     * Creates an empty linked list deque
     */
    public LinkedListDeque() {
        this.size = 0;
        Node head = new Node(null, null, null);
        head.prev = head;
        head.next = head;
        this.sentinel = head;
    }

    /**
     * Creates a deep copy of other
     *
     * @param other
     */
    public LinkedListDeque(LinkedListDeque<T> other) {
        Node head = new Node(null, null, null);
        head.prev = head;
        head.next = head;
        this.sentinel = head;
        this.size = 0;

        for (int i = 0; i < other.size; i++) {
            addLast(other.get(i));
        }

    }

    /**
     * Adds an item of type T to the front of the deque.
     *
     * @param item
     */
    public void addFirst(T item) {
        Node temp = this.sentinel;
        Node node = new Node(item, null, null);
        node.prev = temp;
        node.next = temp.next;
        node.next.prev = node;
        node.prev.next = node;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     *
     * @param item
     */
    public void addLast(T item) {
        Node temp = this.sentinel;
        Node node = new Node(item, null, null);
        node.next = temp;
        node.prev = temp.prev;
        node.prev.next = node;
        node.next.prev = node;
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        //todo
        Node temp = this.sentinel;
        for (int i = 0; i < size; i++) {
            temp = temp.next;
            System.out.print(temp.item + " ");
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     *
     * @return
     */
    public T removeFirst() {
        //todo
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return item;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     *
     * @return
     */
    public T removeLast() {
        //todo
        if (size == 0) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     *
     * @param index
     * @return
     */
    public T get(int index) {
        //todo
        if (index > size) {
            return null;
        }
        Node temp = this.sentinel;
        for (int i = 0; i < size; i++) {
            temp = temp.next;
            if (index == i) {
                return temp.item;
            }
        }
        return null;
    }

    /**
     * Same as get, but uses recursion.
     *
     * @param index
     * @return
     */
    public T getRecursive(int index) {
        return null;
    }

}
