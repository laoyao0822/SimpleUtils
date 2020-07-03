package cn.chen.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class TestUtils {
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

    /***
     * 获取方法执行的时间
     * @param method 执行方法
     * @param args 方法参数
     * @return 方法结果objects[0],执行时间objects[1]
     */
    public static Object[] getMethodtime(Method method,Object...args){
        long start=System.currentTimeMillis();
        Object object=null;
        try {
            object=method.invoke(args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        long end=System.currentTimeMillis();
        return new Object[]{object,end-start};
    }

}
