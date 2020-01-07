package cn.boqi.game;

import sun.awt.image.OffScreenImage;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 飞机游戏的主窗口
 * @author gao
 */
public class MyGameFrame extends Frame {

    private Image offScreenImage = null;

    Image planeImg = GameUtil.getImage("images/plane.png");
    Image bg = GameUtil.getImage("images/bg.png");
    Plane plane = new Plane(planeImg, 250, 250);
    Shell[] shells = new Shell[50];
    Explode bao;
    //重要，在类最外面只能定义元素，不能使用函数

    @Override
    public void paint(Graphics g){
        g.drawImage(bg,0,0,null);
        plane.drawSelf(g);
        //画出所有的炮弹
        for(int i = 0; i< shells.length; i++){
            shells[i].draw(g);

            boolean peng = shells[i].getRect().intersects(plane.getRect());

            if (peng){

                plane.live = false;

                if(bao == null){
                bao  = new Explode(plane.x, plane.y);}
                bao.draw(g);
            }
        }
    }

    //帮助我们反复的重画窗口！
    class PaintThread extends Thread{
        @Override
        public void run(){
            while(true){
                repaint();  //重画

                try {
                    Thread.sleep(40); //1秒刷新50次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void launchFrame(){
        this.setTitle("Presented by Boqi");
        this.setVisible(true);
        this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        this.setLocation(300,300);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e){
                System.exit(0);
            }
        });

        new PaintThread().start();  //启动重画窗口的线程
        addKeyListener(new KeyMonitor()); //给窗口增加键盘的监听

        //初始化50个炮弹
        for(int i = 0; i <shells.length; i++){
            shells[i] = new Shell();
        }
    }

    /**
     * 定义键盘监听的内部类
     */
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
        }
    }



    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        }

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0,0,null);
    }


    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }




}
