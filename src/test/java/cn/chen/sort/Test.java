package cn.chen.sort;

public class Test {
    @org.junit.Test
    public static void testexach(int i,int j){
        System.out.println(ArrayUtils.isSorted(ArrayUtils.sortBySelect(ArrayUtils.intsToIntegers(ArrayUtils.getRadomArray(i,j)))));
    }
    @org.junit.Test
    public static void testgetRadom(){
        int[]a=ArrayUtils.getRadomArray(10,10);
        ArrayUtils.show(ArrayUtils.intsToIntegers(a));
    }
    @org.junit.Test
    public static void testselectByInsert(int i,int j){
        System.out.println(ArrayUtils.isSorted( ArrayUtils.sortByInsert(ArrayUtils.intsToIntegers(ArrayUtils.getRadomArray(i,j)))));
    }
    @org.junit.Test
    public static void teseShell(int i,int j){
        System.out.println(ArrayUtils.isSorted(ArrayUtils.sortByShell(ArrayUtils.intsToIntegers(ArrayUtils.getRadomArray(i,j)))));

    }

}
