package NewGame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Station {

    private int x, y, vx, vy, angle, width, height;
    private boolean visible;

    private final int R = 0;

    private boolean isStation = true;

    private BufferedImage stationImg;

    private ArrayList<Bullet> stationArrayList = new ArrayList<>();

    private int ROTATIONSPEED = 0;

    Station(BufferedImage stationImg){
        this.x = getRandomNumberInRange(50, 1150);
        this.y = getRandomNumberInRange(50, 900);
        this.angle = 0;
        this.stationImg = stationImg;
        this.vx = getRandomNumberInRange(-2,2);
        this.vy = getRandomNumberInRange(-2,2);

    }

    public void update(){

        this.rotate();

    }

    public ArrayList<Bullet> getAsteroidArrayList(){
        return this.stationArrayList;
    }


    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return this.stationImg.getWidth();
    }

    public int getHeight(){
        return this.stationImg.getHeight();
    }

    private void rotate() {
        this.angle += this.ROTATIONSPEED;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public Rectangle grabObject() {
        return new Rectangle(x, y, stationImg.getWidth(), stationImg.getHeight());
    }




    void drawImage(Graphics2D g) {
        AffineTransform asteroid = AffineTransform.getTranslateInstance(x, y);
        asteroid.rotate(Math.toRadians(angle), this.stationImg.getWidth() / 2, this.stationImg.getHeight() / 2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.stationImg, asteroid, null);

        g2d.setColor(Color.blue);
        g2d.drawRect(this.x, this.y, stationImg.getWidth(), stationImg.getHeight());
    }

}
