package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c){
        cmp=c;
    }
    public T max(){
        if(size()==0) return null;
        T max=get(0);
        for(int i=1;i<size();i++){
            T temp=get(i);
            if(cmp.compare(temp,max)>0)
                max=temp;
        }
        return max;
    }

    public T max(Comparator<T> c){
        if(size()==0) return null;
        T max=get(0);
        for(int i=1;i<size();i++){
            T temp=get(i);
            if(c.compare(temp,max)>0)
                max=temp;
        }
        return max;
    }
}
