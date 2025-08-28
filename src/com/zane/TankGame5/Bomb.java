package com.zane.TankGame5;

public class Bomb {
    int x;
    int y;
    int life = 9;
    boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lifeDown() { //配合出现图片爆炸效果
        if(life > 0){
            life--;
        }else {
            isLive = false;
        }
    }
}
