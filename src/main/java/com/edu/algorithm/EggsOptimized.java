package com.edu.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangzhe
 * @date 2019/3/24 17:59
 */
public class EggsOptimized {

    public static Logger log = LoggerFactory.getLogger(EggsOptimized.class);

    public static void main(String[] args) {
        EggsOptimized e = new EggsOptimized();
        int n = 5;
        int m = 500;
        log.info(n + "个鸡蛋和" + m + "楼层的最优解为:" + e.getMinSteps(n, m));
    }

    public int getMinSteps(int eggNum, int floorNum) {
        if (eggNum < 1 || floorNum < 1)
            return 0;

        //存储鸡蛋数量减1的floorNum楼层下的最优尝试次数
        int[] preCache = new int[floorNum + 1];
        //当前层
        int[] curCache = new int[floorNum + 1];

        for (int i = 1; i <= floorNum; i++) {
            curCache[i] = i;
        }
        for (int n = 2; n <= eggNum; n++) {

            preCache = curCache.clone();
            for (int i = 1; i <= floorNum; i++) {
                curCache[i] = i;
            }

            for (int j = 1; j <= floorNum; j++) {
                for (int k = 1; k <= j; k++) {
                    curCache[j] = Math.min(curCache[j], 1 + Math.max(preCache[k - 1], curCache[j - k]));
                }
            }
        }


        return curCache[floorNum];
    }
}
