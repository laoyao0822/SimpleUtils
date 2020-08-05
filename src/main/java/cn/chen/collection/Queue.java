package cn.chen.collection;

/**
 * 队列,不敢保证线程安全
 * @param <E>
 */
public class Queue <E>{
    private volatile QueueNode<E>last;
    private volatile QueueNode<E>first;
    private volatile int size;
    public Queue(){
        size=0;
    }
    public  E dequeue(){
        E result=first.getValue();
            first = first.getNext();
            setSize(--size);
            if(isEmpty()){
              last=null;
            }
        return result;
    }
    public void  enqueue(E item){
        QueueNode queueNode=new QueueNode(item);
        if(isEmpty()){
            first=last=queueNode;
        }
        last.setNext(queueNode);
        last = queueNode;
        last.setNext(null);
        setSize(++size);

    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    private  void  setSize(int size){
        size=size;
    }
}
