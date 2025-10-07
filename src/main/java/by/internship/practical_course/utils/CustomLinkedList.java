package by.internship.practical_course.utils;

import java.util.LinkedList;
/*
size() - returns the size of the list
addFirst(el) - adds the element in the beginning of the list
addLast(el) - adds the element in the end of the list
add(index, el) - adds the element in the list by index
getFirst() - returns the first element of the list
getLast() - returns the last element of the list
get(index) - returns the element by index
removeFirst() - retrieve and remove the first element of the list
removeLast() - retrieve and remove the last element of the list
remove(index) - retrieve and remove the element of the list by index
*/

public class CustomLinkedList<E> {
    private Node head;
    private Node tail;
    private int size;

    //constructor
    public CustomLinkedList() {
        head = new Node(null);
        tail = new Node(null);
        size = 0;
    }

    //size() - returns the size of the list
    public int size() {
        return size;
    }


    //addFirst(el) - adds the element in the beginning of the list
    public void addFirst(E value) {
        Node newNode = new Node(value);
    }

    //addLast(el) - adds the element in the end of the list


    //add(index, el) - adds the element in the list by index


    //getFirst() - returns the first element of the list


    //getLast() - returns the last element of the list


    //get(index) - returns the element by index


    //removeFirst() - retrieve and remove the first element of the list


    //removeLast() - retrieve and remove the last element of the list


    //remove(index) - retrieve and remove the element of the list by index


}
