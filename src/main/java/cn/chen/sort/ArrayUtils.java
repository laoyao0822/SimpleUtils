package cn.chen.sort;

import com.sun.jmx.remote.internal.ClientCommunicatorAdmin;

import javax.sound.midi.SoundbankResource;
import java.util.Collection;
import java.util.Objects;
import java.util.Random;

public class ArrayUtils {
    /**
     * 比较ｖ，ｗ　ｖ＜ｗ则返回ｔｒｕｅ
     * @param v
     * @param w
     * @returnｖ＜ｗ的布尔值
     */
    public  static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }
    public static void exch(Comparable[]a,int i,int j){
        Comparable temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    /**
     * 打印数组中的元素
     * @param a
     */
    public static void show(Object[] a){
        System.out.println("");
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+",");
        }
        System.out.println("");
    }

    /**
     * 判断数组是否已经排序
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[]a){
        for(int i=1;i<a.length;i++){
            if(less(a[i],a[i-1]))return false;
        }
        return true;
    }

    /**
     * 选择排序(
     * @param comparables
     * @return
     */
    public static Comparable[] sortBySelect(Comparable[]comparables){
        int count=0;
        int len=comparables.length;
        for(int i=0;i<len;i++){
            int temp=i;
            for(int j=i+1;j<len;j++){
                if(less(comparables[j],comparables[temp]))temp=j;
                count+=2;
            }
            exch(comparables,i,temp);
            count+=2;
        }
        System.out.println(count);
        return comparables;
    }
    public static Comparable[] sortByInsert(Comparable[]comparables){
        int count=0;
        int len=comparables.length;
       for(int i=1;i<len;i++){
           for(int j=i;j>0&&less(comparables[j],comparables[j-1]);j--){
               exch(comparables,j,j-1);
               count+=4;
           }
       }
        System.out.println(count);

        return comparables;
    }

    /**
     * 获取一个随机生成的数组
     * @param length　数组长度
     * @param max　数组最大值
     * @return
     */
    public static int[] getRadomArray(int length,int max){
        int []radomArray=new int[length];
        Random random=new Random();
        for(int i=0;i<length;i++){
            radomArray[i]=random.nextInt(length);
        }
        return radomArray;
    }

    /**
     * 将int数组转为Integer数组
     * @param ints
     * @return
     */
    public static Integer[] intsToIntegers(int[]ints){
        Integer[]integers=new Integer[ints.length];
        for (int i=0;i<ints.length;i++) {
            integers[i]=ints[i];
        }
        return integers;
    }

    /**
     * 将一个元素挪到相应位置
     * @param index　元素位置
     * @param toindex　要挪的地方
     * @param objects　改变的数组
     */
    public static void change (int index,int toindex,Object[]objects){
        Object temp= objects[index];
        if(toindex<index) {
            objects[toindex]=index;
            for (int i =index; i>toindex; i--) {
                objects[i] =objects[i-1];
            }
            objects[index]=temp;
        }else{
            for(int i=index;i<toindex;i++){
                objects[i]=objects[i+1];
            }
            objects[toindex]=temp;
        }
    }

    /**
     * 希尔排序
     * @param comparables 需要排序的数组
     * @return 排序好的数组
     */
    public static Comparable[] sortByShell(Comparable[]comparables){
        int count=0;
        int len=comparables.length;
        int h=1;
        while (h<len/3)h=3*h+1;
        while (h>=1){
            for (int i=h;i<len;i++){
                for(int j=i;j>=h&&less(comparables[j],comparables[j-h]);j-=h){
                    exch(comparables,j,j-h);
                    count+=4;
                }
            }
            h=h/3;
        }
        System.out.println(count);
        return comparables;
    }
}
