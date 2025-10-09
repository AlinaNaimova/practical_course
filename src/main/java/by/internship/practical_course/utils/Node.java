package by.internship.practical_course.utils;

public class Node <E> {
	private final E element;
	private Node<E> prev;
	private Node<E> next;

	public Node(E element) {
		this.element = element;
		this.prev = null;
		this.next = null;
	}

	public Node(E element, Node<E> prev, Node<E> next) {
		this.element = element;
		this.prev = prev;
		this.next = next;
	}

    public E getElement() {
        return element;
    }


	public Node<E> getPrev() {
		return prev;
	}

	public Node<E> getNext() {
		return next;
	}

	public void setPrev(Node<E> prev) {
		this.prev = prev;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}
}
