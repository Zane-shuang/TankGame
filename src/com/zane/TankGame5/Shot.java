package com.zane.TankGame5;

public class Shot implements Runnable{
    int x;
    int y;
    int direction;
    int speed = 8;
    int shotLength = 4;
    boolean isLive = true;

    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            switch (direction) {
                case 0://向上
                    y -= speed;
                    break;
                case 1://向右
                    x += speed;
                    break;
                case 2://向下
                    y += speed;
                    break;
                case 3://向左
                    x -= speed;
                    break;
                default:
                    break;
            }
            //System.out.println("x=" + x + " y=" + y);
            if(!(x >=0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                System.out.println("子弹线程退出...");
                isLive = false;
                break;
            }
        }
    }
}
