package com.mzimu.variousactivites.decryptkey.event;

import java.util.Random;

public class RandomSlot {
    /**
     * 通过随机0-4来获取 i 和 j
     * 从而获取到玩家数据中的二维数组
     * 是否为true。
     *
     * @return 返回的是 数组下标
     */
    public int getRandomInt(){
        int random = -1;
        Random r = new Random();
        //这里获取的是0~4 没有5
        random = r.nextInt(5);
        return random;
    }
}
