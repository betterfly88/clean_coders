package tds.chapter_3;

import javax.xml.bind.Element;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by betterfly
 * Date : 2019.02.17
 */
public class MyLinkedList<E> implements List<E> {
    private int size;
    private Node head;

    private class Node{
        public E data;
        public Node next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }

        public Node(E data, Node next){
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    public MyLinkedList(){
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if(head == null){
            head = new Node(e);
        }else{
            Node node = head;

            for(; node.next != null; node = node.next){

            }
            node.next = new Node(e);
        }
        size ++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

//    @Override
//    public E get(int index) {
//        E returnValue = null;
//        Node node = head;
//        for (int i=0; i<=index ;i++){
//            if (i == index){
//                returnValue = node.data;
//            }
//            node = head.next;
//        }
//        return returnValue;
//    }

    @Override
    public E get(int index) {
        Node node = getNode(index);
        return node.data;
    }

    @Override
    public E set(int index, E element) {
        Node node = getNode(index);
        E old = node.data;
        node.data = element;
        return old;
    }

//    @Override
//    public void add(int index, E element) {
//        if(head == null){
//            throw new NullPointerException();
//        }
//
//        size++;
//
//        if (index ==0 ){
//            head = new Node(element, head);
//            return;
//        }
//
//        Node node = head;
//        for(int i=0; i <= index; i++){
//            if(i==index){
//                node = new Node(element, node.next);
//            }
//            node = node.next;
//        }
//    }

//    @Override
//    public void add(int index, E element) {
//        if(head == null){
//            throw new NullPointerException();
//        }
//
//        Node node = head;
//        Node tempNode = null;
//
//        if(index == 0){
//            tempNode = new Node(element, head);
//        }else{
//            if(index > size-1){
//                tempNode = head;
//                tempNode.next = new Node(element);
//            }else{
//                for(int i=0; i <= index; i++){
//                    if(i == index-1){
//                        tempNode = node;
//                        tempNode.next = new Node(element, node.next);
//                        break;
//                    }
//
//                    node = head.next;
//                }
//            }
//        }
//
//        head = tempNode;
//        size++;
//    }

    @Override
    public void add(int index, E element) {
        // no need to check bounds; getNode does it.
        if (index == 0) {
            head = new Node(element, head);
        } else {
            Node node = getNode(index-1);
            node.next = new Node(element, node.next);
        }
        size++;
    }

    /** Returns the node at the given index.
     * @param index
     * @return
     */
    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        for (int i=0; i<index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public E remove(int index) {
        return null;
    }


    /*
        Returns the index of the first occurrence of the specified element in this list,
        or -1 if this list does not contain the element. More formally,
        returns the lowest index i such that
        (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
     */
//    @Override
//    public int indexOf(Object o) {
//        Node node = head;
//
//        for (int i=0; node != null; i++){
//            if(node.data.equals(o)){
//                return i;
//            }
//
//            node = node.next;
//        }
//
//        return -1;
//    }

    @Override
    public int indexOf(Object target) {
        Node node = head;
        for (int i=0; i<size; i++) {
            if (equals(target, node.data)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    private boolean equals(Object target, Object element) {
        if (target == null) {
            return element == null;
        } else {
            return target.equals(element);
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
