package cn.chen.math;

import java.util.Random;

public class MathUtil {
    public int getRadom(int max){
        return new Random().nextInt(max);
    }
    public int[] getRadomArray(int length,int max){
        int []radomArray=new int[length];
        Random random=new Random(length);
        for(int i=0;i<length;i++){
            radomArray[i]=random.nextInt();
        }
        return radomArray;
    }
}
