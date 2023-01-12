package deque;

public class ArrayDeque<T> implements Deque<T>{
    private int size;
    private int Maxsize;
    private int front;
    private T[] items;

    public ArrayDeque(){
        this.Maxsize=8;
        front=0;
        items=(T[]) new Object[Maxsize];
    }
    private void resize(int newsize){
        Maxsize=newsize;
        T[] temp=(T[]) new Object[Maxsize];
        System.arraycopy(items, front, temp, 0, size);
        items=temp;
        front=0;
    }


    @Override
    public void addFirst(T item) {
        if(size==Maxsize){
            resize(Maxsize*2);
        }
        items[front]=item;
        front=(front+Maxsize-1)%Maxsize;
        size++;
    }

    @Override
    public void addLast(T item) {
        if(size==Maxsize){
            resize(Maxsize*2);
        }
        items[(front+size)%Maxsize]=item;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for(int i=0;i<size;i++){
            System.out.println(items[(front+i)%Maxsize]);
        }
    }

    @Override
    public T removeFirst() {
        if(size==0) return null;
        if(Maxsize>=16&&size<=Maxsize*0.25){
            resize((int) (Maxsize*0.5));
        }
        T item = items[(front+1)%Maxsize];
        front=(front+1)%Maxsize;
        size--;
        return item;
    }

    @Override
    public T removeLast() {
        if(size==0) return null;
        T item = items[(front+size)%Maxsize];
        size--;
        return item;
    }

    @Override
    public T get(int index) {
        if(index>=size) return null;
        return items[(front+index)%Maxsize];
    }
}
