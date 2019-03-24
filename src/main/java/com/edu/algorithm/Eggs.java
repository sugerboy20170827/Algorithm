package com.edu.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangzhe
 * @date 2019/3/23 10:54
 */

public class Eggs {

    public static Logger log = LoggerFactory.getLogger(Eggs.class);

    public static void main(String[] args) {
        Eggs e = new Eggs();
        int n = 5;
        int m = 500;
        log.info(n + "个鸡蛋和" + m + "楼层的最优解为:" + e.getMinSteps(n, m));
    }

    public int getMinSteps(int eggNums, int floorNum) {
        if (eggNums < 1 || floorNum < 1)
            return 0;
        //存储eggNums个鸡蛋和floorNum楼层的最优尝试次数
        int[][] cache = new int[eggNums + 1][floorNum + 1];
        //首先将每个位置初始化为  最大尝试次数 也就是无论  多少个鸡蛋  尝试的次数都是楼层数
        for (int i = 1; i <= eggNums; i++) {
            for (int j = 1; j <= floorNum; j++) {
                cache[i][j] = j;
            }
        }
        //n个鸡蛋   m楼层的问题
        for (int n = 2; n <= eggNums; n++) {
            for (int m = 1; m <= floorNum; m++) {
                for (int k = 1; k <= m; k++) {
                    //扔鸡蛋的楼层从1到m枚举一遍，如果尝试次数小于上一次的尝试次数，则取代上一次的尝试次数
                    cache[n][m] = Math.min(cache[n][m], 1 + Math.max(cache[n][m - k], cache[n - 1][k - 1]));
                }
            }
        }
        return cache[eggNums][floorNum];
    }

}