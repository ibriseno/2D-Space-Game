package NewGame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet {
    private int Xspeed;
    private int damage;
    private int angle;
    private int x;
    private int  y;
    private int vx;
    private int vy;
    private final int R = 2;
    private int Yspeed;
    private BufferedImage img;

    Bullet(BufferedImage img, int x, int y, int angle, int damage, int Xspeed, int Yspeed){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.damage = damage;
        this.Xspeed = Xspeed;
        this.Yspeed = Yspeed;
        this.angle = angle;

    }

    public int getDamage(){return damage;}
    public int getX(){return x;}
    public int getY(){return y;}
    public int getWidth(){return img.getWidth();}
    public int getHeight(){return img.getHeight();}

    public void update(){
        vx = (int) Math.round((R * Math.cos(Math.toRadians(angle))));
        vy = (int) Math.round((R * Math.sin(Math.toRadians(angle))));
        x += vx;
        y += vy;
    }

    public Rectangle grabObject(){
        return new Rectangle(x,y,img.getWidth(), img.getHeight());
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        AffineTransform shoot = AffineTransform.getTranslateInstance(x, y);

        shoot.rotate(Math.toRadians(angle), (int)this.img.getWidth()/2.0, (int)this.img.getHeight()/2.0);

        Graphics2D b2d = (Graphics2D) g;
        b2d.drawImage(this.img, shoot, null);
    }
}
