package pkgUtil;

import java.io.Serializable;

public class Node<T> implements Serializable {
    private Node<T> next;
    private T item;

    public Node(T item) {
        this.item = item;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public T getValue() {
        return this.item;
    }
}