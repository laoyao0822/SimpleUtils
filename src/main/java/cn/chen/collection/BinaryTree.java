package cn.chen.collection;

import java.util.prefs.NodeChangeEvent;

/**
 *二向树(线程不安全)
 * @param <Key>
 * @param <Value>
 */
public class BinaryTree<Key ,Value>{
    private class Node{
        private Key key;
        private Value value;
        private int hashcode;
        private Node left,right;
        private int N;//该节点的根节点数;
        Node(){
        }
        Node(Key key,Value value){
            this.key=key;
            this.value=value;
            this.hashcode=key.hashCode();
            N=1;
        }
        public Key getKey() {
            return key;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getN() {
            return N;
        }

        public void setN(int n) {
            N = n;
        }

        public void setKey(Key key) {
            this.key = key;
            hashcode=key.hashCode();
        }
        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }

        public int getHashcode() {
            return hashcode;
        }

        public void setHashcode(int hashcode) {
            this.hashcode = hashcode;
        }
    }
    private Node root;

    /***
     *获取二向树的大小
     * @return
     */
    public int size(){
        return size(root);
    }
    public int size(Node node){
        if(node==null){
            return 0;
        }else {
            return node.getN();
        }
    }
    public void put(Key key,Value value){
       root=put(root,key,value,key.hashCode());
    }
    private Node put(Node putnode,Key key,Value value,int hash){
        if (putnode==null){
            return new Node(key,value);
        }
        int putnodeHashcodehash= putnode.getHashcode();
        if(putnodeHashcodehash<hash){
            putnode.setRight(put(putnode.getRight(), key, value,hash));
        }else if(putnodeHashcodehash>hash){
            putnode.setLeft(put(putnode.getLeft(), key, value,hash));
        }else if (putnodeHashcodehash==hash){
            putnode.setValue(value);
        }
        putnode.setN(size(putnode.getLeft())+size(putnode.getRight())+1);
        return putnode;
    }

    /**
     * 根据key取出某个元素
     * @param key
     * @return
     */
    public Value get(Key key){
        int hash=key.hashCode();
        return get(root,hash);
    }
    public Value get(Node node,int hash){
        if(node==null){return null;}
        int hashcode= node.getHashcode();
        if(hash==hashcode){
            return node.getValue();
        }else if(hash<hashcode){
            return get(node.getLeft(),hash);
        }else {
            return get(node.getRight(),hash);
        }
    }
    public void delete(Key key){
        int hash=key.hashCode();
        root=delete(root,hash);
    }
    private Node delete(Node node,int hash){
        if(node==null){return null;}
        int hah=node.getHashcode();
        if(hash<hah){
            node.setLeft(delete(node.getLeft(),hash));
        }else if(hash>hah){
            node.setRight(delete(node.getRight(),hash));
        } else {
            if(node.getRight()==null){return node.getLeft();}
            if(node.getLeft()==null){return node.getRight();}
            Node t=min(node);
            node=deletemin(node);
            node.setValue(t.getValue());
            node.setKey(t.getKey());
            node.setN(size(node.getLeft())+size(node.getRight())+1);
        }
        return node;
    }
    private Node min(Node node){
        while(node.getLeft()!=null){
            node=node.getLeft();
        }
        return node;
    }
    private Node deletemin(Node node){
        if(node.getLeft()!=null){
            node.setLeft(deletemin(node.getLeft()));
        }
        if (node.getRight()!=null){
            node=node.getRight();
        }
        return node;
    }
}
