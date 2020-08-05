package cn.chen.collection;

import java.util.Map;

/**
 * 一个带索引的优先队列
 * @param <Key>
 * @param <Value>
 */
public class IndexMaxPriorityQueue<Key extends Comparable<Key>,Value> {
    private Node[]collection;
    private volatile Integer size=0;
    public IndexMaxPriorityQueue(Map<Key,Value>map){
       for(Key key:map.keySet()){

       }
    }

    /***
     * 堆得长度为128
     */
    public IndexMaxPriorityQueue(){
        collection=new Node[512+1] ;
    }

    /***
     * 堆得长度为max
     * @param max
     */
    public IndexMaxPriorityQueue(int max){
        collection=new Node[max+1];
        size=max;
    }

    public int getSize() {
        return size;
    }

    public  void setSize(int size) {
        synchronized ((Integer)size) {
            this.size = size;
        }
    }

    /**
     * 交换下标为i,j的两个元素
     * @param i
     * @param j
     */
    private synchronized void exch(int i,int j){
        Node temp = collection[i];
        synchronized (collection) {
            collection[i] = collection[j];
            collection[j] = temp;
        }
    }
    private boolean less(int i,int j){
        Node nodei=collection[i];
        Node nodej=collection[j];
        if(nodei==null){
            if(nodej==null){
                return false;
            }
            return true;
        }
        if(collection[j].key!=null) {
            if (collection[i].key == null||collection[i].key.compareTo(collection[j].key) < 0) {
                return true;
            }
        }
        return false;
    }
    private void swim(int k){
        while(k>1&&less(k/2,k)){
            exch(k/2,k);
            k/=2;
        }
    }
    private void sink(int k){
        while(2*k<=size){
            int j=2*k;
            if(j<size&&less(j,j+1)){
                j++;
            }
            if(!less(k,j))break;
            exch(k,j);
            k=j;
        }
    }
    public void insert(Key v,Value value){
        int temp=size+1;
        if(collection.length==temp){
            expand();
        }
        collection[temp]=new Node(v,value);
        swim(temp);
        setSize(size+1);
    }

    /**
     * 扩大数组的容量
     */
    private void expand(){
        Node[]a;
        int len=collection.length;
        synchronized (collection) {
            if (len < 1000000) {
                a = new Node[len * 2];
            } else {
                a =  new Node[len / 2 + len];
            }
            for (int i = 1; i < len; i++) {
                a[i] = collection[i];
            }
            collection = a;
        }
    }

    /**
     * 取出并删除最大元素
     * @return
     */
    public Value delMax(){
        Node temp=collection[1];
        synchronized (collection){
            collection[1]=null;
            sink(1);
        }
        setSize(size-1);
        return (Value) temp.getValue();
    }

    class Node<Key extends Comparable<Key>,Value>{
        volatile Key key;
        volatile Value value;
        Node(Key key,Value value){
            this.key=key;
            this.value=value;
        }
        public Key getKey() {
            return key;
        }

        public void setKey(Key key) {
            synchronized (key) {
                this.key = key;
            }
        }

        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            synchronized (value) {
                this.value = value;
            }
        }
    }
}
