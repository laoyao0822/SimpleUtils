package cn.chen.collection;

public class RedBlackBinarySearchTree<Key,Value>{
    private Node root;
    public static final boolean RED=true;
    public static final boolean BLACK=false;
    private class Node{
        private int key;
        private Value value;
        private int hashcode;
        private Node left,right;
        private boolean color=false;//红为true,黑为false
        private int size;//该节点的根节点数;

        public int getKey() {
            return key;
        }

        public void setKey(Key key) {
            this.key = key.hashCode();
        }
        public void setKey(int key) {
            this.key = key;
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

        public boolean getColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size=size;
        }

        Node(){
        }
        Node(int  key,Value value) {
            this.key=key;
            this.value=value;
        }
        Node(int key,Value value,int size,boolean color) {
            this.key=key;
            this.color=color;
            this.value=value;
            this.size=size;
        }
    }

    /***
     * 判断该节点是否为红色
     * @param node
     * @return
     */
    private boolean isRed(Node node){
        if(node==null){
            return false;
        }
        return node.getColor();
    }
    private Node rotateLeft(Node node){
        Node left=node.getRight();
        left.setColor(node.getColor());
        node.setRight(left.getLeft());
        node.setColor(RED);
        left.setSize(size(node));
        node.setSize(size(node.getLeft())+size(node.getRight())+1);
        left.setLeft(node);
        return left;
    }
    private Node rotateRight(Node node){
        Node right=node.getLeft();
        right.setColor(node.getColor());
        node.setColor(RED);
        node.setLeft(right.getRight());
        right.setSize(size(node));
        node.setSize(size(node.getLeft())+size(node.getRight())+1);
        right.setRight(node);
        return right;
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
    public Value get(Node node, int hash){
        if(node==null){return null;}
        int hashcode= node.key;
        if(hash==hashcode){
            return node.getValue();
        }else if(hash<hashcode){
            return get(node.getLeft(),hash);
        }else {
            return get(node.getRight(),hash);
        }
    }
    private Node flipColors(Node node){
        node.setColor(RED);
        Node right=node.getRight();
        right.setColor(BLACK);
        Node left=node.getLeft();
        left.setColor(BLACK);
        node.setRight(right);
        node.setLeft(left);
        return node;
    }
    public void put(Key key,Value value){
        root=put(root,key.hashCode(),value);
        root.setColor(BLACK);
    }
    private Node put(Node node,int key,Value value) {
        if (node == null) {
            return new Node(key, value, 1, RED);
        }
        int nodekey = node.getKey();
        if (key < nodekey) {
            node.setLeft(put(node.getLeft(), key, value));
        } else if (key > nodekey) {
            node.setRight(put(node.getRight(), key, value));
        } else {
            node.setValue(value);
            return node;
        }
        if (!isRed(node.getLeft())) {
            if (isRed(node.getRight())) {
                node = rotateLeft(node);
            }
        } else if (isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        } else if (isRed(node.getRight())) {
            node=flipColors(node);
        }
        node.setSize(size(node.getLeft())+size(node.getRight())+1);
        return node;
        }
    public int size(){
        return size(root);
    }
    public int size(Node node){
        if(node==null){
            return 0;
        }else {
            return node.getSize();
        }
    }
    public void delete(Key key){
        root=delete(root,key.hashCode());
    }
    public Node delete(Node node,int hash){
        if(node==null){
            return null;
        }
        if(hash<node.getKey()){
            node.setLeft(delete(node.getLeft(),hash));
        }else if(hash> node.getKey()){
            node.setRight(delete(node.getRight(),hash));
        }
        
    }
}
