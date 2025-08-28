package com.zane.TankGame5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

//绘图区
public class MyPanel extends JPanel implements KeyListener,Runnable {
    //定义我的坦克
    Hero hero;
    //定义敌人坦克
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 8;
    //定义炸弹
    Vector<Bomb> bombs = new Vector<>();
    //定义三张爆炸图片
    Image image1, image2, image3;
    //定义Nodes的Vector
    Vector<Node> nodes;

    public MyPanel(String key){
        File file = new File(Recorder.getRecordFile());
        //判断记录文件是否存在
        if(file.exists()){
            nodes = Recorder.getNodesAndEnemyNum();
        }else{
            System.out.println("上一局游戏不存在！必须开启新游戏");
            key = "1";
        }

        switch(key){
            case "1"://新游戏
                Recorder.setDestroyEnemyNum(0);
                //初始化敌方坦克
                for (int i = 0; i < enemyTankSize; i++) {
                    //创建敌人坦克
                    EnemyTank enemyTank = new EnemyTank(100 * (i+1),0,2);
                    //将enemyTanks 传递给 对象里的 enemyTanks
                    enemyTank.setEnemyTanks(enemyTanks);
                    //启动敌人坦克线程
                    new Thread(enemyTank).start();
                    //给敌人坦克加入子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
                    //加入enemyTank 的 vector成员
                    enemyTank.shots.add(shot);
                    //启动 shot 对象
                    new Thread(shot).start();
                    //加入
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2"://上一局游戏
                //初始化敌方坦克
                for (int i = 0; i < nodes.size(); i++) {
                    //获取上一局敌方坦克node
                    Node node = nodes.get(i);
                    //创建敌人坦克
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY(), node.getDirection());
                    //将enemyTanks 传递给 对象里的 enemyTanks
                    enemyTank.setEnemyTanks(enemyTanks);
                    //启动敌人坦克线程
                    new Thread(enemyTank).start();
//                    //给敌人坦克加入子弹
//                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
//                    //加入enemyTank 的 vector成员
//                    enemyTank.shots.add(shot);
//                    //启动 shot 对象
//                    new Thread(shot).start();
                    //加入
                    enemyTanks.add(enemyTank);
                }
                break;
        }

        //初始化自己坦克
        hero = new Hero(100,600,0);


        //初始化爆炸图片
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb3.png"));

        //不加这一句代码 第一次子弹命中敌方坦克时候 无爆炸效果 (...?)
        bombs.add(new Bomb(500,500));

        //将enemyTanks 传递给 Recorder类 里的 enemyTanks
        Recorder.setEnemyTanks(enemyTanks);

        //添加音乐
        new AePlayWave("src\\music.wav").start();
    }

    public void showInfo(Graphics g){
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 30);
        g.setFont(font);

        g.drawString("您击毁的坦克数量",1030,30);
        drawTank(1050,70,g,0,1);
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(Recorder.getDestroyEnemyNum() + "",1170,110);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充黑色矩形
        showInfo(g);//显示计分信息
        //画我方坦克
        if(hero.isLive){
            drawTank(hero.getX(), hero.getY(), g,
                    hero.getDirection(),0);
        }
        //画敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if(enemyTank.isLive){//判断敌方坦克是否还存活着
                drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirection(),1);
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if(shot.isLive){
                        drawShot(shot.x, shot.y, shot.shotLength, g, shot.direction, 1);
                    }else{
                        enemyTank.shots.remove(shot);
                    }
                }
            }else{
                //如果挂了就从vector里去掉
                enemyTanks.remove(enemyTank);
                //更新数据
                Recorder.addDestroyEnemyNum();
            }
        }

        //画子弹
//        if(hero.shot != null && hero.shot.isLive){
//            drawShot(hero.shot.x, hero.shot.y, hero.shot.shotLength,g, hero.getDirection(), 0);
//        }
        //将hero的子弹集合 shots 遍历画出
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if(shot != null && shot.isLive){
                drawShot(shot.x, shot.y, shot.shotLength, g, hero.getDirection(), 0);
            }else{//如果该shot已经挂了，则被销毁
                hero.shots.remove(shot);
            }
        }

        //如果Vector<> bombs 里有对象，就画出爆炸
        for (int i = 0; i < bombs.size(); i++) {
            //爆炸音乐
            //new AePlayWave("src\\bomb.wav").start();

            Bomb bomb = bombs.get(i);
            if(bomb.life > 6){
                g.drawImage(image1, bomb.x, bomb.y, 60,60,this);
            }else if(bomb.life > 3){
                g.drawImage(image2, bomb.x, bomb.y, 60,60,this);
            }else{
                g.drawImage(image3, bomb.x, bomb.y, 60,60,this);
            }
            bomb.lifeDown();
            if(bomb.life == 0){
                bombs.remove(bomb);
            }
        }
    }

    /**
     *
     * @param x 坦克左上角x坐标
     * @param y 坦克左上角y坐标
     * @param g 画笔
     * @param direction 坦克方向
     * @param type 坦克类型
     */
    public void drawTank(int x,int y,Graphics g,int direction,int type){
        switch (type){
            case 0: //我方坦克
                g.setColor(Color.cyan);
                break;
            case 1: //敌方坦克
                g.setColor(Color.yellow);
                break;
        }

        //绘制不同方向坦克
        //0上 1右 2下 3左
        switch (direction){
            case 0://向上
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y,x+20,y+30);
                break;
            case 1://向右
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+60,y+20,x+30,y+20);
                break;
            case 2://向下
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+60,x+20,y+30);
                break;
            case 3://向左
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x,y+20,x+30,y+20);
                break;
            default:
                System.out.println("Nothing happen.");
        }
    }

    public void drawShot(int x,int y,int shotLength,Graphics g,int direction,int type){

        switch (type){
            case 0: //我方坦克子弹
                g.setColor(Color.cyan);
                break;
            case 1: //敌方坦克子弹
                g.setColor(Color.yellow);
                break;
        }

        switch (direction){
            case 0://向上
                g.fillRect(x - shotLength / 2,y - shotLength, shotLength, shotLength);
                break;
            case 1://向右
                g.fillRect(x,y - shotLength / 2, shotLength, shotLength);
                break;
            case 2://向下
                g.fillRect(x - shotLength / 2,y , shotLength, shotLength);
                break;
            case 3://向左
                g.fillRect(x - shotLength,y - shotLength / 2, shotLength, shotLength);
                break;
        }
    }

    //编写方法 判断子弹是否打中我方坦克
    public void hitHero(){
        if(hero.isLive){
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                //判断是否击中我方坦克
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    hitTank(shot, hero);
                }
            }
        }
    }

    //编写方法 判断子弹是否打中敌方坦克
    public void hitEnemyTank(){
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            //判断是否击中敌方坦克
            for (int j = 0; j < hero.shots.size(); j++) {
                Shot shot = hero.shots.get(j);
                hitTank(shot, enemyTank);
            }
        }
    }

    //编写方法 判断子弹是否打中坦克
    public void hitTank(Shot shot, Tank enemyTank){
        switch (enemyTank.getDirection()){
            case 0://坦克向上
            case 2://坦克向下
                if(shot.x > enemyTank.getX() &&
                   shot.x < enemyTank.getX() + 40 &&
                    shot.y > enemyTank.getY() &&
                    shot.y < enemyTank.getY() + 60){
                    shot.isLive = false;
                    enemyTank.isLive = false;
                    //创建Bomb对象 加入bombs中
                    Bomb bomb = new Bomb(enemyTank.getX(),enemyTank.getY());
                    bombs.add(bomb);
                }
                break;

            case 1://坦克向右
            case 3://坦克向左
                if(shot.x > enemyTank.getX() &&
                    shot.x < enemyTank.getX() + 60 &&
                    shot.y > enemyTank.getY() &&
                    shot.y < enemyTank.getY() + 40){
                    shot.isLive = false;
                    enemyTank.isLive = false;
                    //创建Bomb对象 加入bombs中
                    Bomb bomb = new Bomb(enemyTank.getX(),enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                hero.setDirection(0);
                if(hero.getY() > 0) {
                    hero.moveUp();
                }else {
                    hero.setY(0);
                }
                break;
            case KeyEvent.VK_D:
                hero.setDirection(1);
                if(hero.getX() + 60 < 1000) {
                    hero.moveRight();
                }else{
                    hero.setX(1000 - 60);
                }
                break;
            case KeyEvent.VK_S:
                hero.setDirection(2);
                if(hero.getY() + 60 < 750) {
                    hero.moveDown();
                }else{
                    hero.setY(750 - 60);
                }
                break;
            case KeyEvent.VK_A:
                hero.setDirection(3);
                if(hero.getX() > 0){
                    hero.moveLeft();
                }else{
                    hero.setX(0);
                }
                break;
        }
        if(e.getKeyCode() == KeyEvent.VK_J){
            hero.shotEnemy();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            hitEnemyTank();

            hitHero();

            //重画
            this.repaint();
        }

    }
}
