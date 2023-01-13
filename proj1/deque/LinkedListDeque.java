package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T>{
    private final Node<T> sentinel=new Node<>(null,null,null);
    private Node<T> last;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return new LinkedLisDequeIterator();
    }

    class Node<Item>{
        public Item item;
        public Node<Item> next;
        public Node<Item> before;
        public Node(Item item, Node<Item> next, Node<Item> before){
            this.item=item;
            this.before=before;
            this.next=next;
        }
    }

    public LinkedListDeque(){
        size=0;
        last=sentinel;
    }
    @Override
    public void addFirst(T item) {
        if(size==0){
            Node<T> addNode= new Node<>(item, null, sentinel);
            sentinel.next=addNode;
            last=addNode;
        }
        else{
            Node<T> temp=sentinel.next;
            Node<T> addNode= new Node<>(item, temp, sentinel);
            sentinel.next=addNode;
            temp.before=addNode;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> addNode = new Node<>(item,null,last);
        last.next=addNode;
        last=addNode;
        size++;
    }
    

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node<T> ptr = sentinel.next;
        while(ptr!=null){
            System.out.println(ptr.item);
            ptr=ptr.next;
        }
    }

    @Override
    public T removeFirst() {
        Node<T> fir = sentinel.next;
        if(size==0) return null;
        else if(size==1){
            sentinel.next=null;
            last=sentinel;
            size--;
            return fir.item;
        }
        else{
            Node<T> sec=fir.next;
            sentinel.next=sec;
            sec.before=sentinel;
            size--;
            return fir.item;
        }
    }

    @Override
    public T removeLast() {
        if(size==0) return null;
        else{
            Node<T> seclast = last.before;
            T item = last.item;
            last=seclast;
            seclast.next=null;
            size--;
            return item;
        }
    }

    @Override
    public T get(int index) {
        if(index>=size) return null;
        Node<T> iter = sentinel;
        for(int i=0;i<=index;i++){
            iter=iter.next;
        }
        return iter.item;
    }

    public T getRecursive(int index){
        if(index>size) return null;
        return Recursive(sentinel.next,index);
    }

    private T Recursive(Node<T> node,int count){
        if(count==0) return node.item;
        return Recursive(node.next,count-1);
    }

    private class LinkedLisDequeIterator implements Iterator<T>{
        Node<T> iter;

        @Override
        public boolean hasNext() {
            return iter.next!=null;
        }

        @Override
        public T next() {
            T item = iter.next.item;
            iter=iter.next;
            return item;
        }

        public LinkedLisDequeIterator(){
            iter=sentinel;
        }
    }

    public boolean equals(Object o){
        if(o instanceof Deque){
            Deque<T> temp = (Deque<T>) o;
            if(temp.size()==size()){
                for(int i=0;i<size();i++){
                    if(!get(i).equals(temp.get(i)))
                        return false;
                }
                return true;
            }
        }
        return false;
    }
}
