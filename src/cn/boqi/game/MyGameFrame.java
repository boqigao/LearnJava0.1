package cn.boqi.game;

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
public class MyGameFrame extends JFrame {

    Image planeImg = GameUtil.getImage("images/plane.png");
    Image bg = GameUtil.getImage("images/bg.png");
    Plane plane = new Plane(planeImg, 250, 250);
    //重要，在类最外面只能定义元素，不能使用函数

    @Override
    public void paint(Graphics g){
        g.drawImage(bg,0,0,null);
        plane.drawSelf(g);
    }

    //帮助我们反复的重画窗口！
    class PaintThread extends Thread{
        @Override
        public void run(){
            while(true){
                repaint();  //重画

                try {
                    Thread.sleep(20); //1秒刷新50次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void launchFrame(){
        this.setTitle("Presented by Boqi");
        this.setVisible(true);
        this.setSize(500,500);
        this.setLocation(300,300);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e){
                System.exit(0);
            }
        });

        new PaintThread().start();  //启动重画窗口的线程
        addKeyListener(new KeyMonitor()); //给窗口增加键盘的监听
    }

    /**
     * 定义键盘监听的内部类
     */
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println(e.getKeyCode());
        }
    }


    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }




}
