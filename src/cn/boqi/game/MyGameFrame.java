package cn.boqi.game;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 飞机游戏的主窗口
 * @author gao
 */
public class MyGameFrame extends JFrame {

    Image plane = GameUtil.getImage("images/plane.png");
    Image bg = GameUtil.getImage("images/bg.png");

    int x = 250;
    int y = 250;

    @Override
    public void paint(Graphics g){
        g.drawImage(bg,0,0,null);
        g.drawImage(plane, x, y,null);
        x++;
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
    }

    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }

}
