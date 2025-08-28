package com.zane.TankGame5;

import java.io.*;
import java.util.Vector;

public class Recorder {
    //记录我方击毁敌人坦克数目
    private static int destroyEnemyNum = 0;
    //定义IO对象 写数据到文件中
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "src\\recordData.txt";
    //指向MyPanel 的 enemyTanks
    private static Vector<EnemyTank> enemyTanks = null;
    //定义一个Node 的 vector
    private static Vector<Node> nodes = new Vector<>();

    public static String getRecordFile() {
        return recordFile;
    }

    public static Vector<Node> getNodesAndEnemyNum() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            destroyEnemyNum = Integer.parseInt(br.readLine());
            String line;
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                int x = Integer.parseInt(xyd[0]);
                int y = Integer.parseInt(xyd[1]);
                int dir = Integer.parseInt(xyd[2]);
                nodes.add(new Node(x, y, dir));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nodes;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static void keepRecord() {
        try {
            //记录已经击毁的敌方坦克数目
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(destroyEnemyNum + "");
            bw.newLine();
            //记录剩余敌人坦克坐标、方向
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirection();
                bw.write(record);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static int getDestroyEnemyNum() {
        return destroyEnemyNum;
    }

    public static void setDestroyEnemyNum(int destroyEnemyNum) {
        Recorder.destroyEnemyNum = destroyEnemyNum;
    }

    public static void addDestroyEnemyNum(){
        Recorder.destroyEnemyNum++;
    }
}
