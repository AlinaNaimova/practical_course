package by.internship.practical_course.utils;

public class CustomLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    //constructor
    public CustomLinkedList() {
    }

    //size() - returns the size of the list
    public int size() {
        return size;
    }

    //checking if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    //addFirst(el) - adds the element in the beginning of the list
    public void addFirst(E element) {
        Node<E> newNode = new Node<E>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
        size++;
    }

    //addLast(el) - adds the element in the end of the list
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
    }

    //add(index, el) - adds the element in the list by index
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == size) {
            addLast(element);
        } else if (index == 0) {
            addFirst(element);
        } else {
            Node<E> currentNode = getNode(index);
            Node<E> prevNode = currentNode.getPrev();
            Node<E> newNode = new Node<>(element, prevNode, currentNode);

            prevNode.setNext(newNode);
            currentNode.setPrev(newNode);

            size++;
        }
    }

    //getFirst() - returns the first element of the list
    public E getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return head.getElement();
    }

    //getLast() - returns the last element of the list
    public E getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return tail.getElement();
    }

    //get(index) - returns the element by index
    public E get(int index) {
        return getNode(index).getElement();
    }


    //removeFirst() - retrieve and remove the first element of the list
    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        E removedElement = head.getElement();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }
        size--;
        return removedElement;
    }

    //removeLast() - retrieve and remove the last element of the list
    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        E removedElement = tail.getElement();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
        }
        size--;
        return removedElement;
    }


    //remove(index) - retrieve and remove the element of the list by index
    public E remove(int index) {

        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> nodeToRemove = getNode(index);
            E removedElement = nodeToRemove.getElement();

            nodeToRemove.getPrev().setNext(nodeToRemove.getNext());
            nodeToRemove.getNext().setPrev(nodeToRemove.getPrev());

            size--;
            return removedElement;
        }
    }

    //method to get node by index
    private Node<E> getNode(int index) {
        checkElementIndex(index);

        Node<E> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        }
        return current;
    }


    //Checks that the index exists for get/remove
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // toString() method for easier debugging and display
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
