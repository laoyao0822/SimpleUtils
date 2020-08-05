package cn.chen.collection;

/***
 * 优先队列,排序用(下标0不使用)(不出意外的话线程应该是安全的)
 */
public class MaxPriorityQueue<Key extends Comparable<Key>>{
    private volatile Key[]collection;
    private volatile Integer size=0;
    public MaxPriorityQueue(Key[]a){
        for (int i = 1; i <= a.length; i++) {
            collection[i]=a[i-1];
        }
        size=a.length+1;
    }

    /***
     * 堆得长度为128
     */
    public MaxPriorityQueue(){
        collection=(Key[])new Comparable[512+1] ;
    }

    /***
     * 堆得长度为max
     * @param max
     */
    public MaxPriorityQueue(int max){
        collection=(Key[])new Comparable[max+1];
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
            Key temp = collection[i];
        synchronized (collection) {
            collection[i] = collection[j];
            collection[j] = temp;
        }
    }
    private boolean less(int i,int j){
        if(collection[j]!=null) {
            if (collection[i] == null||collection[i].compareTo(collection[j]) < 0) {
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
    public void insert(Key v){
        int temp=size+1;
        if(collection.length==temp){
            expand();
        }
        collection[temp]=v;
        swim(temp);
        setSize(size+1);
    }

    /**
     * 扩大数组的容量
     */
    private void expand(){
        Key[]a;
        int len=collection.length;
        synchronized (collection) {
            if (len < 1000000) {
                a = (Key[]) new Comparable[len * 2];
            } else {
                a = (Key[]) new Comparable[len / 2 + len];
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
    public Key delMax(){
        Key temp=collection[1];
        synchronized (collection){
            collection[1]=null;
            sink(1);
        }
        setSize(size-1);
        return temp;
    }

}
