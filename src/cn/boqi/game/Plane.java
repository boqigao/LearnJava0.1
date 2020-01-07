package cn.boqi.game;

import java.awt.*;

public class Plane extends GameObject {

    public void drawSelf(Graphics g){
        g.drawImage(img, (int)x, (int)y, null);
        x++;
    }

    public Plane(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }
}
