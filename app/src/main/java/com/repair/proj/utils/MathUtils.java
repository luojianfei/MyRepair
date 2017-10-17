package com.repair.proj.utils;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2016/12/23.
 */

public class MathUtils {

    /**
     * 求求给定双精度数组中值的最小值
     *
     * @param inputData 输入数据数组
     * @return 运算结果, 如果输入值不合法，返回为-1
     */
    public static float getMin(ArrayList<Float> inputData) {
        if (inputData == null || inputData.size() == 0)
            return -1;
        int len = inputData.size();
        float min = inputData.get(0);
        for (int i = 0; i < len; i++) {
            if (min > inputData.get(i))
                min = inputData.get(i);
        }
        return min;
    }

    /**
     * 求给定双精度数组中值的最大值
     *
     * @param inputData 输入数据数组
     * @return 运算结果, 如果输入值不合法，返回为-1
     */
    public static float getMax(ArrayList<Float> inputData) {
        if (inputData == null || inputData.size() == 0)
            return -1;
        int len = inputData.size();
        float max = inputData.get(0);
        for (int i = 0; i < len; i++) {
            if (max < inputData.get(i))
                max = inputData.get(i);
        }
        return max;
    }

}
