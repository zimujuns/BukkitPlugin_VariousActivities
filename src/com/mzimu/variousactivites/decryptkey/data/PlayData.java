package com.mzimu.variousactivites.decryptkey.data;

import org.bukkit.entity.Player;

public class PlayData {

    public PlayData(Player player) {
        this.player = player;
    }

    private Player player;
    private int number;
    private boolean guiList[][] = new boolean[6][6];
    //用来判断玩家是否处于指定获得key的模式
    private Boolean editGui =false;
    /**
     * @param i 数据的行
     * @param j 数据的列
     * @param a 数据的值
     */
    public void setGuiList(int i,int j,Boolean a){
        this.guiList[i][j] = a;
    }

    public void setGuiList(boolean[][] guiList) {
        this.guiList = guiList;
    }

    /**
     * 获取数据的值
     * @param i 数据的行
     * @param j 数据的列
     * @return 返回是否被激活
     */
    public boolean getGuiList(int i, int j) {
        return guiList[i][j];
    }

    public boolean[][] getGuiList() {
        return guiList;
    }

    public Boolean getEditGui() {
        return editGui;
    }

    public void setEditGui(Boolean editGui) {
        this.editGui = editGui;
        player.sendMessage("您的指定模式已关闭");
    }

    public int getNumber() {
        return number;
    }

    public void delNumber(int number){
        this.number -=number;
    }

    public void addNumber(int number) {
        this.number +=number;
    }

    public Player getPlayer() {
        return player;
    }
}
