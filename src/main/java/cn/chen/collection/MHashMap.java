package cn.chen.collection;

import java.security.Key;

public class MHashMap<E,V> {
 	Node []table;


	public MHashMap() {
		table = new Node[16];
	}
	public int myHash(int v) {
	return v&(16-1);
}
	public Object get(V key) {
		Node a=new Node();
		a.setHash(myHash(key.hashCode()));
		a=table[a.getHash()];
		while(a!=null) {
			if(key.equals(a.getKey())) {
			break;
		}else {
			a=a.getNext();
		}
	}
		return a.getValue();
}
	public String toString() {
	StringBuilder a=new StringBuilder();
	a.append("[");
	for(int i=0;i<table.length;i++) {
		Node n=table[i];
		while(n!=null) {
			a.append(n.getKey());
			a.append("-");
			a.append(n.getValue());
			a.append(",");
			n=n.getNext();
		}
	}
	a.append("]");
	return a.toString();
}
public void put(E key,V value) {
      Node newnode=new Node();
      newnode.setKey(key);
      newnode.setHash(myHash(key.hashCode()));
      System.out.println(newnode.getHash()+"-"+newnode.getHash());
      newnode.setValue(value);
      Node temp=table[newnode.getHash()];
      if(temp==null) {
       table[newnode.getHash()]=newnode;
      }else {
    	 while(temp!=null) {
    		 if(newnode.getKey()!=temp.getKey()) {
    			 if(temp.getNext()==null) {
    				 temp.setNext(newnode);
    				 break;
    			 }
    			 temp=temp.getNext();
    		 }else{
    			temp.setValue(newnode.getValue());
    			break;
    		 }
    	 }
      }
}

public static   class Node {
	private Node next;
	private Object value;
	private Object key;
	private int hash;

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public int getHash() {
		return hash;
	}

	public void setHash(int hash) {
		this.hash = hash;
	}

	public Node() {

	}
}
}

