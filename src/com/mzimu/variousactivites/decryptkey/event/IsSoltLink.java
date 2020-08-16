package com.mzimu.variousactivites.decryptkey.event;

public class IsSoltLink {
    private boolean[][] is;
    private int a,b;
    /**
     * 用二维数组表示RawSlot
     * i*9+j = RawSlot
     * RawSlot/9 = i
     * RawSlot%9 = j
     *
     *
     * RawSlot
     * 0-4
     * 9-13
     * 18-22
     * 27-31
     * 36-40
     * 0-53 总文件
     *
     * 45-49 奖励目录
     * 5,14,23,32,41
     *
     * 52 为抽奖按钮
     * 53 为指定解密
     *
     * 25 为累计奖励
     *
     */
    public IsSoltLink(boolean[][] is, int a, int b) {
        this.is = is;
        this.a = a;
        this.b = b;
    }

    /**
     *
     * b 输入的是列
     * @return返回int 是用来获取奖励
     * 是否被玩家获取了的 36-40
     */
    public Integer IsB(){
        //运行时判断十字
        for(int i=0;i<5;i++){
            if(!is[i][b]){
                return -1;
            }
        }
        return 5;
//        return b+36;
    }

    /**
     *
     * a 输入的是行
     * @return 返回int 是用来获取奖励
     * 是否被玩家获取了的 5,14,23,32,41
     */
    public Integer IsA(){
        //运行时判断十字
        for(int j=0;j<5;j++){
            if(!is[a][j]){
                return -1;
            }
        }
        return 5;
//        return a*9+5;
    }


}
