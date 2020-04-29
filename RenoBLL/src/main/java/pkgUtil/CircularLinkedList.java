package pkgUtil;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import pkgCore.Player;

public class CircularLinkedList<E> implements API<E>, Serializable {

	private Node<E> head;
	private E current;
	private int size = 0;
	private int rounds = 1;

	/**
	 * Create an empty CircularLinkedList
	 */
	public CircularLinkedList() {
		super();
		head = null;
		current = null;
	}

	/**
	 * Create a CircularLinkedList, seed it with list contents
	 * 
	 * @param list - given list of items to add at construction
	 */
	public CircularLinkedList(List<E> list) {
		super();
		for (E e : list) {
			add(e);
		}
	}

	public void add(E element) {
		Node<E> newNode = new Node<E>(element);
		if (head == null) {
			head = newNode;
			current = newNode.getValue();
		} else {
			Node<E> temp = head;
			while (temp.getNext() != head) {
				temp = temp.getNext();
			}
			temp.setNext(newNode);
		}

		newNode.setNext(head);
		size++;
	}

	/**
	 * Adds a list of items to existing Circular list, resets head
	 */
	public void addAll(List<E> list) {
		head = null;
		current = null;
		for (E e : list) {
			add(e);
		}
	}

	/**
	 * advanceCurrent. Set the current to next
	 */
	public E advanceCurrent() {

		Node<E> temp = head;
		while (temp.getValue() != current) {
			temp = temp.getNext();
		}
		temp = temp.getNext();
		setCurrent(temp.getValue());
		
		if (head == temp)
			this.rounds++;
		
		return getCurrent();
	}

	public void clear() {
		head = null;
		current = null;
		size = 0;
	}

	private boolean contains(Object Element)
	{
		boolean bContains = false;
		
		Node<E> temp = head;
		while (temp.getNext() != head) {
			if (temp.getValue() == Element)
				return true;
			temp = temp.getNext();
		}
		

		if (temp.getValue().equals(Element))
			return true;
	 
		return bContains;
	}

	public void delete(E element) {

		if (!contains(element))
		{
			return;
		}
		Node<E> temp = head;
		while (true)
		{
			if (temp.getNext().getValue().equals(element))
				break;
			temp = temp.getNext();
		}
		temp.setNext(temp.getNext().getNext());
		
		if (this.current.equals(element)) {
			this.current = temp.getNext().getValue();
		}
		
		if (this.head.getValue() == element)
		{
			this.head = temp.getNext();
		}

		size--;
	}
	public void deleteFromBeginning() {
		Node<E> temp = head;
		while (temp.getNext() != head) {
			temp = temp.getNext();
		}
		temp.setNext(head.getNext());
		head = head.getNext();
		size--;
	}

	public void deleteFromEnd() {
		Node<E> temp = head;
		while (temp.getNext().getNext() != head) {
			temp = temp.getNext();
		}
		temp.setNext(head);
		size--;
	}

	@Override
	public E getCurrent() {
		if (size > 0)
			return (E) this.current;
		return null;
	}

	public List<E> getItemsInOrder() {
		List<E> list = new LinkedList<E>();
		if (head == null)
			return null;
		
		Node<E> temp = head;
		while (temp.getNext() != head) {
			list.add((E) temp.getValue());
			temp = temp.getNext();
		}
		list.add((E) temp.getValue());
		return list;
	}

	public int getRounds() {
		return this.rounds;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public void newHead(E element) {
		Node<E> temp = head;
		while (temp.getNext() != head) {
			if (temp.getNext() == element)
				break;
			temp = temp.getNext();
		}
		head = temp;
	}

	public void placeAtBeginning(E element) {
		Node<E> newNode = new Node<E>(element);
		if (head == null) {
			head = newNode;
			head.setNext(head);
		} else {
			Node<E> last = head;
			while (last.getNext() != head)
				last = last.getNext();
			newNode.setNext(head);
			head = newNode;
			last.setNext(head);
		}
		size++;
	}

	@Override
	public void setCurrent(E element) {
		Node<E> newNode = new Node<E>(element);
		Node<E> temp = null;
		if (head == null) {
			head = newNode;
		} else {
			temp = head;
			while (temp.getNext().getValue() != newNode.getValue()) {
				temp = temp.getNext();
			}
		}
		newNode.setNext(temp.getNext().getNext());
		this.current = newNode.getValue();
	}

}