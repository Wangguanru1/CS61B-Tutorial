package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{

    private BSTNode root;
    private int size;

    private class BSTNode{
        K key;
        V value;
        BSTNode left;
        BSTNode right;
        BSTNode(K key,V value){
            this.key=key;
            this.value=value;
        }
    }
    @Override
    public void clear() {
        root=null;
        size=0;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root,key);
    }
    private boolean containsKey(BSTNode bstNode,K key){
        if(bstNode==null)
            return false;
        int temp = key.compareTo(bstNode.key);
        if(temp>0)
            return containsKey(bstNode.right,key);
        else if(temp<0)
            return containsKey(bstNode.left,key);
        else
            return true;
    }

    @Override
    public V get(K key) {
        return get(root,key);
    }
    private V get(BSTNode node,K key){
        if(node==null)
            return null;
        int temp = key.compareTo(node.key);
        if(temp>0)
            return get(node.right,key);
        else if(temp<0)
            return get(node.left,key);
        else
            return node.value;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = put(root,key,value);
    }

    private BSTNode put(BSTNode node,K key,V value){
        if(node==null){
            size++;
            return new BSTNode(key,value);
        }
        int temp = key.compareTo(node.key);
        if(temp>0)
            node.right=put(node.right,key,value);
        else if(temp<0)
            node.left=put(node.left,key,value);
        return node;
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }
}
