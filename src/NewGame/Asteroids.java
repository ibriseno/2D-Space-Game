package NewGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Random;

import static javax.imageio.ImageIO.read;

public class Asteroids {

    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;

    private int x2;
    private int y2;
    private int vx2;
    private int vy2;
    private int angle2;

    private int count = 0;

    private final int R = 0;

    private boolean isStation = true;

    private BufferedImage img2;
    private BufferedImage asteroidImg;
    private BufferedImage spaceStationImg;
    private BufferedImage Explosionimage;

    private ArrayList<Bullet> asteroidArrayList = new ArrayList<>();

    private final int ROTATIONSPEED = 1;


    Asteroids(BufferedImage asteroidImg, int x, int y){
       this.x = getRandomNumberInRange(50, 1150);
       this.y = getRandomNumberInRange(50, 900);
       this.vx = getRandomNumberInRange(-5,5);
       this.vy = getRandomNumberInRange(-5,5);
       this.angle = angle;
       this.asteroidImg = asteroidImg;
    }
    Asteroids(){
        x = getRandomNumberInRange(50, 1150);
        y = getRandomNumberInRange(50, 900);
        vx = getRandomNumberInRange(-2,2);
        vy = getRandomNumberInRange(-2,2);
        angle = 0;
        try {
            asteroidImg = read(new File("Resources/spaceShooter/PNG/Sprites/Meteors/spaceMeteors_001.png"));
        } catch (IOException e) {

        }
    }


    public void update(){

        this.moveForwards();
        this.rotateRight();

    }

    public ArrayList<Bullet> getAsteroidArrayList(){
        return this.asteroidArrayList;
    }


public int getX(){

    return x;
}

public int getY(){
        return y;
}

public int getWidth(){
        return this.asteroidImg.getWidth();
}
public int getHeight(){
        return this.asteroidImg.getHeight();
}

private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
      //  this.angle2 += this.ROTATIONSPEED;
    }


    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= (1);
        y -= (vy);
    //    checkWorldBorder();
    }

    private void moveForwards() {
        //vx = 1;//(int) Math.round(R * Math.cos(Math.toRadians(angle)));
        //vy = 1;//(int) Math.round(R * Math.sin(Math.toRadians(angle)));
        if(vx == 0 || vy == 0){
            this.vx = getRandomNumberInRange(-2,2);
            this.vy = getRandomNumberInRange(-2,2);
        }
        x -= (vx);
        y += (vy);


    //    x2 += (vx);
    //    y2 += (vy);
       checkWorldBorder();
    }
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }



    private void checkWorldBorder(){
        if (x < -100 ) {
            x = getRandomNumberInRange(0,1200);
            y = 0;
            vx = getRandomNumberInRange(-2,2);
           // count += 1;
        }
        if (x >= GameWorld.SCREEN_WIDTH + 100) {
            x = getRandomNumberInRange(0,1200);
            y =0;
            vx = getRandomNumberInRange(-2,2);
            //count += 1;
        }
        if (y < -80) {
            x = getRandomNumberInRange(0,1200);
            y = 0;
            vy = 1;
            vx = getRandomNumberInRange(-2,2);
        }
        if (y >= GameWorld.SCREEN_HEIGHT + 100) {
            x = getRandomNumberInRange(0,1200);
            y = 0;
            vx = getRandomNumberInRange(-2,2);
            //x = 200;
        }
    }

    public void Collision( boolean isXCollidable, boolean isYCollidable, boolean rightCollidable, boolean leftCollidable, int pos){

        if (isXCollidable){
            x = pos - getWidth();
        //    x2 = pos - getWidth2();
        }
        if (isYCollidable){
            y = pos - getHeight();
         //   y2 = pos - getHeight2();
        }
        if (rightCollidable){
            y = pos;
          //  y2 = pos;
        }
        if (leftCollidable){
            x = pos;
          //  x2 = pos;
        }
    }

    public Rectangle grabObject(){
        return new Rectangle(x,y, asteroidImg.getWidth(), asteroidImg.getHeight());
    }
    /**
    public void Collision2( boolean isXCollidable, boolean isYCollidable, boolean rightCollidable, boolean leftCollidable, int pos){

        if (isXCollidable){
            System.out.println("You hit X2!");
          //  x = pos - getWidth();
        //    x2 = x2;
        }
        if (isYCollidable){
            System.out.println("You hit Y2!");
           // y = pos - getHeight();
        //       y2 = pos - getHeight2();
        }
        if (rightCollidable){
            System.out.println("You hit right side 2!");
          //  y = pos;
          //    y2 = pos;
        }
        if (leftCollidable){
            System.out.println("You hit left side 2!");
         //   x = pos;
          //    x2 = pos;
        }
    }
     **/
    void drawImage(Graphics2D g) {
        AffineTransform asteroid = AffineTransform.getTranslateInstance(x, y);
        asteroid.rotate(Math.toRadians(angle), this.asteroidImg.getWidth() / 2, this.asteroidImg.getHeight() / 2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.asteroidImg, asteroid, null);
/**
        AffineTransform spaceStation = AffineTransform.getTranslateInstance(x2, y2);
        spaceStation.rotate(Math.toRadians(angle), this.img2.getWidth() / 2, this.img2.getHeight() / 2);
      //  Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img2, spaceStation, null);
**/
        g.setColor(Color.blue);
        g.drawRect(this.x, this.y, asteroidImg.getWidth(), asteroidImg.getHeight());
//        g.drawRect(this.x2,this.y2, img2.getWidth(), img2.getHeight() );
    }

}