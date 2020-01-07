package cn.boqi.game;

import java.awt.*;

/**
 * 炮弹类
 * @author g
 */
public class Shell extends GameObject {

    double degree;

    public Shell(){
        x = 200;
        y = 200;
        width = 10;
        height = 10;
        speed = 3;
        degree = Math.random() * Math.PI * 2;

    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.YELLOW);


        g.fillOval((int)x, (int)y, width, height);

        //炮弹沿着任意角度飞
        x += speed * Math.cos(degree);
        y += speed * Math.sin(degree);
        g.setColor(c);
    }
}
