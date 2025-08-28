package com.zane.TankGame5;

import java.util.Vector;

//自己的坦克
public class Hero extends Tank {
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();
    public Hero(int x, int y, int direction) {
        super(x, y, direction);
    }

    //射击
    public void shotEnemy(){
        //控制我方坦克发射子弹数目
        if(shots.size() == 1000){
            return;
        }

        switch (getDirection()) {
            case 0://上
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1://右
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2://下
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3://左
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }

        //将shot加入集合
        shots.add(shot);
        //启动shot线程
        new Thread(shot).start();
    }
}
