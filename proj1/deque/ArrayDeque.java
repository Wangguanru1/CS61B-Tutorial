package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private int maxsize;
    private int front;
    private T[] items;

    public ArrayDeque() {
        this.maxsize = 8;
        front = 0;
        items = (T[]) new Object[maxsize];
    }

    private void resize(int newsize) {
        T[] temp = (T[]) new Object[newsize];
        for (int i = 0; i < size; i++) {
            temp[i] = get(i);
        }
        maxsize = newsize;
        items = temp;
        front = newsize - 1;
    }


    @Override
    public void addFirst(T item) {
        if (size == maxsize) {
            resize(maxsize * 2);
        }
        items[front] = item;
        front = (front + maxsize - 1) % maxsize;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == maxsize) {
            resize(maxsize * 2);
        }
        size++;
        items[(front + size) % maxsize] = item;

    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.println(items[(front + i) % maxsize]);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (maxsize >= 16 && size <= maxsize * 0.25) {
            resize((int) (maxsize * 0.5));
        }
        T item = items[(front + 1) % maxsize];
        front = (front + 1) % maxsize;
        size--;
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (maxsize >= 16 && size <= maxsize * 0.25) {
            resize((int) (maxsize * 0.5));
        }
        T item = items[(front + size) % maxsize];
        size--;
        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(front + index + 1) % maxsize];
    }

    public boolean equals(Object o) {
        if (o instanceof Deque) {
            Deque<T> temp = (Deque<T>) o;
            if (temp.size() == size()) {
                for (int i = 0; i < size; i++) {
                    if (!temp.get(i).equals(get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int index = 0;

        public ArrayDequeIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T returnItem = items[index];
            index += 1;
            return returnItem;
        }
    }


}
