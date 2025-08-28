package com.zane.TankGame5;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
    Shot shot;
    Vector<Shot> shots = new Vector<>();
    Vector<EnemyTank> enemyTanks =  new Vector<>();
    public EnemyTank(int x, int y, int direction) {
        super(x, y, direction);
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks){
        this.enemyTanks = enemyTanks;
    }

    public boolean isTouchEnemyTank(){
        //判断当前敌人坦克(this)方向
        switch (this.getDirection()){
            case 0:
                //当前坦克 左上角 坐标: (this.x,this.y)
                //当前坦克 右上角 坐标: (this.x + 40,this.y)
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if(enemyTank != this){
                        if(enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2){
                            //敌人坦克 x范围 [eT.x , eT.x + 40]
                            //        y范围 [eT.y , eT.y + 60]
                            if(this.getX() >= enemyTank.getX() &&
                            this.getX() <= enemyTank.getX() + 40 &&
                            this.getY()>= enemyTank.getY() &&
                            this.getY() <= enemyTank.getY() + 60){
                                return true;
                            }

                            if(this.getX() + 40 >= enemyTank.getX() &&
                                this.getX() + 40 <= enemyTank.getX() + 40 &&
                                this.getY() >= enemyTank.getY() &&
                                this.getY() <= enemyTank.getY() + 60){
                                return true;
                            }
                        }

                        if(enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3){
                            //敌人坦克 x范围 [eT.x , eT.x + 60]
                            //        y范围 [eT.y , eT.y + 40]

                            if(this.getX() >= enemyTank.getX() &&
                                this.getX() <= enemyTank.getX() + 60 &&
                                this.getY()>= enemyTank.getY() &&
                                this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }

                            if(this.getX() + 40 >= enemyTank.getX() &&
                                this.getX() + 40 <= enemyTank.getX() + 60 &&
                                this.getY() >= enemyTank.getY() &&
                                this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;

            case 1:
                //当前坦克 右上角 坐标: (this.x + 60 , this.y)
                //当前坦克 右下角 坐标: (this.x + 60 , this.y + 40)
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if(enemyTank != this){
                        if(enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2){
                            //敌人坦克 x范围 [eT.x , eT.x + 40]
                            //        y范围 [eT.y , eT.y + 60]
                            if(this.getX() + 60 >= enemyTank.getX() &&
                                this.getX() + 60 <= enemyTank.getX() + 40 &&
                                this.getY() >= enemyTank.getY() &&
                                this.getY() <= enemyTank.getY() + 60){
                                return true;
                            }

                            if(this.getX() + 60 >= enemyTank.getX() &&
                                this.getX() + 60 <= enemyTank.getX() + 40 &&
                                this.getY() + 40 >= enemyTank.getY() &&
                                this.getY() + 40 <= enemyTank.getY() + 60){
                                return true;
                            }
                        }

                        if(enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3){
                            //敌人坦克 x范围 [eT.x , eT.x + 60]
                            //        y范围 [eT.y , eT.y + 40]

                            if(this.getX() + 60 >= enemyTank.getX() &&
                                this.getX() + 60 <= enemyTank.getX() + 60 &&
                                this.getY() >= enemyTank.getY() &&
                                this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }

                            if(this.getX() + 60 >= enemyTank.getX() &&
                                this.getX() + 60 <= enemyTank.getX() + 60 &&
                                this.getY() + 40>= enemyTank.getY() &&
                                this.getY() + 40 <= enemyTank.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;

            case 2:
                //当前坦克 左下角 坐标: (this.x , this.y + 60)
                //当前坦克 右下角 坐标: (this.x + 40 , this.y + 60)
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if(enemyTank != this){
                        if(enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2){
                            //敌人坦克 x范围 [eT.x , eT.x + 40]
                            //        y范围 [eT.y , eT.y + 60]
                            if(this.getX() >= enemyTank.getX() &&
                                this.getX() <= enemyTank.getX() + 40 &&
                                this.getY() + 60 >= enemyTank.getY() &&
                                this.getY() + 60 <= enemyTank.getY() + 60){
                                return true;
                            }

                            if(this.getX() + 40 >= enemyTank.getX() &&
                                this.getX() + 40 <= enemyTank.getX() + 40 &&
                                this.getY() + 60 >= enemyTank.getY() &&
                                this.getY() + 60 <= enemyTank.getY() + 60){
                                return true;
                            }
                        }

                        if(enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3){
                            //敌人坦克 x范围 [eT.x , eT.x + 60]
                            //        y范围 [eT.y , eT.y + 40]

                            if(this.getX() >= enemyTank.getX() &&
                                this.getX() <= enemyTank.getX() + 60 &&
                                this.getY() + 60 >= enemyTank.getY() &&
                                this.getY() + 60 <= enemyTank.getY() + 40){
                                return true;
                            }

                            if(this.getX() + 40 >= enemyTank.getX() &&
                                this.getX() + 40 <= enemyTank.getX() + 60 &&
                                this.getY() + 60>= enemyTank.getY() &&
                                this.getY() + 60 <= enemyTank.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;

            case 3:
                //当前坦克 左上角 坐标: (this.x , this.y)
                //当前坦克 左下角 坐标: (this.x , this.y + 40)
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if(enemyTank != this){
                        if(enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2){
                            //敌人坦克 x范围 [eT.x , eT.x + 40]
                            //        y范围 [eT.y , eT.y + 60]
                            if(this.getX() >= enemyTank.getX() &&
                                this.getX() <= enemyTank.getX() + 40 &&
                                this.getY()  >= enemyTank.getY() &&
                                this.getY()  <= enemyTank.getY() + 60){
                                return true;
                            }

                            if(this.getX() >= enemyTank.getX() &&
                                this.getX() <= enemyTank.getX() + 40 &&
                                this.getY() + 40 >= enemyTank.getY() &&
                                this.getY() + 40 <= enemyTank.getY() + 60){
                                return true;
                            }
                        }

                        if(enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3){
                            //敌人坦克 x范围 [eT.x , eT.x + 60]
                            //        y范围 [eT.y , eT.y + 40]

                            if(this.getX() >= enemyTank.getX() &&
                                this.getX() <= enemyTank.getX() + 60 &&
                                this.getY() >= enemyTank.getY() &&
                                this.getY() <= enemyTank.getY() + 40){
                                return true;
                            }

                            if(this.getX() >= enemyTank.getX() &&
                                this.getX() <= enemyTank.getX() + 60 &&
                                this.getY() + 40>= enemyTank.getY() &&
                                this.getY() + 40 <= enemyTank.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            //敌方坦克子弹数目为0时 新创建一颗子弹并扔进集合中
            if(shots.size() < 100){
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
                shots.add(shot);
                new Thread(shot).start();
            }

            switch (getDirection()){
                case 0:
                    for (int i = 0; i < 29; i++) {
                        if(getY() > 0 && !isTouchEnemyTank()) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 29; i++) {
                        if(getX() + 60 < 1000 && !isTouchEnemyTank()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 29; i++) {
                        if(getY() + 60 < 750 && !isTouchEnemyTank()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 29; i++) {
                        if(getX() > 0 && !isTouchEnemyTank()){
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }

            //敌人坦克设置随机方向
            setDirection((int)(Math.random() * 4));

            if(isLive == false){
                break;
            }
        }
    }
}
