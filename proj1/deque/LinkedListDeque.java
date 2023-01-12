package deque;

import java.sql.ResultSet;
import java.util.Iterator;

public class LinkedListDeque<Item> implements Deque<Item>,Iterable<Item>{
    private final Node<Item> sentinel=new Node<>(null,null,null);
    private Node<Item> last;
    private int size;

    @Override
    public Iterator<Item> iterator() {
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
    public void addFirst(Item item) {
        if(size==0){
            Node<Item> addNode= new Node<>(item, null, sentinel);
            sentinel.next=addNode;
            last=addNode;
        }
        else{
            Node<Item> temp=sentinel.next;
            Node<Item> addNode= new Node<>(item, temp, sentinel);
            sentinel.next=addNode;
            temp.before=addNode;
        }
        size++;
    }

    @Override
    public void addLast(Item item) {
        Node<Item> addNode = new Node<>(item,null,last);
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
        Node<Item> ptr = sentinel.next;
        while(ptr!=null){
            System.out.println(ptr.item);
            ptr=ptr.next;
        }
    }

    @Override
    public Item removeFirst() {
        Node<Item> fir = sentinel.next;
        if(size==0) return null;
        else if(size==1){
            sentinel.next=null;
            last=sentinel;
            size--;
            return fir.item;
        }
        else{
            Node<Item> sec=fir.next;
            sentinel.next=sec;
            sec.before=sentinel;
            size--;
            return fir.item;
        }
    }

    @Override
    public Item removeLast() {
        if(size==0) return null;
        else{
            Node<Item> seclast = last.before;
            Item item = last.item;
            last=seclast;
            seclast.next=null;
            size--;
            return item;
        }
    }

    @Override
    public Item get(int index) {
        if(index>=size) return null;
        Node<Item> iter = sentinel;
        for(int i=0;i<=index;i++){
            iter=iter.next;
        }
        return iter.item;
    }

    public Item getRecursive(int index){
        if(index>size) return null;
        return Recursive(sentinel.next,index);
    }

    private Item Recursive(Node<Item> node,int count){
        if(count==0) return node.item;
        return Recursive(node.next,count-1);
    }

    private class LinkedLisDequeIterator implements Iterator<Item>{
        Node<Item> iter;

        @Override
        public boolean hasNext() {
            return iter.next!=null;
        }

        @Override
        public Item next() {
            Item item = iter.item;
            iter=iter.next;
            return item;
        }

        public LinkedLisDequeIterator(){
            iter=sentinel;
        }
    }

    public boolean equals(Object o){
        if(o instanceof LinkedListDeque){
            LinkedListDeque<Item> temp = (LinkedListDeque<Item>) o;
            if(temp.size()==size()){
                for(int i=0;i<size();i++){
                    if(get(i)!=temp.get(i))
                        return false;
                }
                return true;
            }
        }
        return false;
    }
}
