package com.zane.TankGame5;

public class Tank {
    //坦克横纵坐标
    private int x;
    private int y;
    private int direction;
    private int speed = 5;
    boolean isLive = true;

    public void moveUp() {
        y -= speed;
    }
    public void moveDown() {
        y += speed;
    }
    public void moveLeft() {
        x -= speed;
    }
    public void moveRight() {
        x += speed;
    }

    public Tank(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {return direction;}

    public void setDirection(int direction) {this.direction = direction;}

    public int getSpeed() {return speed;}

    public void setSpeed(int speed) {this.speed = speed;}
}
