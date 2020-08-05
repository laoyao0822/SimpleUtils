package cn.chen.collection;

public class Stack <E>{
    private Object[]items;
    private int size=0;

    /***
     * 取出并删除最后存入的元素
     * @return
     */
    public  E pop(){
        if (size==0)throw new RuntimeException("there is no item");
        E item= (E)items[size];
        deletelast();
        return item;
    }
    private synchronized void deletelast(){
        items[size]=null;
        size--;
    }

    /***
     * 填加一个元素
     * @param item
     */
    public void push(E item){
        int len=items.length;
        size++;
        if(size==len) {
            if(len>100000) {items=kuo(len/2+len);}
            else {items=kuo(len*2);}
        }
        items[size]=item;
    }

    /***
     * 查看该stack是否为空
     * @return
     */
    public boolean isEmpty(){
        if (size!=0){
            return false;
        }
        return true;
    }
   public Stack(int leangth){
        items=new Object[leangth];
    }
    public Stack(){
        items=new Object[10];
    }
    private synchronized Object[]kuo(int leangth){
        Object[] item=new Object[leangth];
        for (int i = 0; i <items.length ; i++) {
            item[i]=items[i];
        }
        return item;
    }
}
