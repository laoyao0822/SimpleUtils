package cn.chen.collection;

public class QueueNode<E>{
    private QueueNode next;
    volatile E value;
    QueueNode(E item){
        setValue(item);
    }
    QueueNode(){
    }
    public synchronized void setNext(QueueNode next) {
        this.next = next;
    }

    public E getValue() {
        return value;
    }

    public QueueNode getNext() {
        return next;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
