package cn.chen.sort;

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
        int len=comparables.length;
        for(int i=0;i<len;i++){
            int temp=i;
            for(int j=i+1;j<len;j++){
                if(less(comparables[j],comparables[temp]))temp=j;
            }
            exch(comparables,i,temp);
        }
        return comparables;
    }

    /**
     * 将数组中的lo-hi部分排序
     * @param comparables 排序的数组
     * @param lo
     * @param hi
     * @return
     */
    public static Comparable[] sortBySelect(Comparable[]comparables,int lo,int hi){
        int len=hi-lo;
        for(int i=lo;i<hi;i++){
            int temp=i;
            for(int j=i+1;j<hi+1;j++){
                if(less(comparables[j],comparables[temp]))temp=j;
            }
            exch(comparables,i,temp);
        }
        return comparables;
    }
    public static Comparable[] sortByInsert(Comparable[]comparables){
        int len=comparables.length;
       for(int i=1;i<len;i++){
           for(int j=i;j>0&&less(comparables[j],comparables[j-1]);j--){
               exch(comparables,j,j-1);
           }
       }

        return comparables;
    }
    public static Comparable[] sortByInsert(Comparable[]comparables,int lo,int hi){
        for(int i=lo+1;i<=hi;i++){
            for(int j=i;j>lo&&less(comparables[j],comparables[j-1]);j--){
                exch(comparables,j,j-1);
            }
        }

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
     * 希尔排序(仅次于快速排序)
     * @param comparables 需要排序的数组
     * @return 排序好的数组
     */
    public static Comparable[] sortByShell(Comparable[]comparables){
        int len=comparables.length;
        int h=1;
        while (h<len/3)h=3*h+1;
        while (h>=1){
            for (int i=h;i<len;i++){
                for(int j=i;j>=h&&less(comparables[j],comparables[j-h]);j-=h){
                    exch(comparables,j,j-h);
                }
            }
            h=h/3;
        }
        return comparables;
    }
    public static Object[] getSame(Object[]classes){
        Object[]classes1=new Object[classes.length];
        for (int i = 0; i < classes1.length ; i++) {
            classes1[i]=classes[i];
        }
        return classes1;
    }

    /**
     * 将数组中两端有序部分归并(相连)归并low-middle与middle-hight
     * @param comparables 归并的数组
     * @param low 最小的下标
     * @param middle 中间的下标
     * @param hight 最大的下标
     * @return 归并完成的数组
     */
    public static Comparable[] merge(Comparable[]comparables,int low,int middle,int hight){
        int k=middle+1;
        Comparable[] aux=comparables.clone();
        for(int i=low;i<=hight;i++){
            if(low>middle){
                aux[i]= comparables[k++];
            }else if(k>hight){
                aux[i]= comparables[low++];
            }else if (less(comparables[low],comparables[k])) {
                aux[i]=comparables[low++];
            }else{
                aux[i]=comparables[k++];
            }
        }
        return aux;
    }

    /**
     * 归并排序(不推荐小数组)(与希尔排序不相上下)
     * @param comparables 目标数组
     * @return排序完成的数组
     */
    public static Comparable[] sortByMerger(Comparable[]comparables){
        return sortByMerger(comparables,0, comparables.length-1);
    }
    public static Comparable[] sortByMerger(Comparable[]comparables,int lo,int hi){
        if(hi-lo<=8){
            return sortBySelect(comparables,lo,hi);
        }
        int mid=lo+(hi-lo)/2;
        comparables=sortByMerger(comparables,lo,mid);
        comparables=sortByMerger(comparables,mid+1,hi);
        comparables=merge(comparables,lo,mid,hi);
        return comparables;
    }

    /**
     * 快速排序/切分排序(目前最快的排序算法)
     * It takes me 3hours to fina and fix the bug. Fuck!
     * @param comparables
     * @return
     */
    public static Comparable[] quickSort(Comparable[]comparables){
        comparables=quickSort(comparables,0, comparables.length-1);
        return comparables;
    }
    public static Comparable[] quickSort(Comparable[]comparables,int lo,int hi){
        if(hi-lo<10){return sortByInsert(comparables,lo,hi);}
        if(hi<=lo)return comparables;
        int middle=(lo+hi)/2;
        middle=cut(comparables,lo,hi);
        comparables=quickSort(comparables,lo,middle);
        comparables=quickSort(comparables,middle+1,hi);
        return comparables;
    }
    public static int  cut(Comparable[]comparables,int low,int hight){
        int lo=low;
        int hi=hight;
        int len=hi-lo;
        Comparable standard=comparables[low];
        Comparable[]coms=new Comparable[len+1];
        for (int i = 0; i <len; i++) {
            coms[i]=comparables[lo+i+1];
        }
        for(int i=0;i<len;i++){
            if(less(coms[i],standard)){
                comparables[lo++]=coms[i];
            }else {
                comparables[hi--]=coms[i];
            }
        }
        comparables[lo]=standard;
        return lo;
    }
}
