package cn.chen.colleaction;

import cn.chen.collection.Stack;

public class Test {
    @org.junit.Test
    public static void testStacj(){
        Stack<Integer> temp=new Stack<>();
        for (int i=0;i<1000000;i++){
            temp.push(i);
        }
        for (int i=1000000;i!=0;i--){
            temp.pop();
        }
    }
    @org.junit.Test
    public static void testStac3(){
        java.util.Stack<Integer> temp=new java.util.Stack<>();
        for (int i=0;i<1000000;i++){
            temp.push(i);
        }
        for (int i=1000000;i!=0;i--){
            temp.pop();
        }
    }
}
