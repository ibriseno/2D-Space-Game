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

    private final int R = 0;

    private BufferedImage asteroidImg;

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

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveForwards() {
        if(vx == 0 || vy == 0){
            this.vx = getRandomNumberInRange(-2,2);
            this.vy = getRandomNumberInRange(-2,2);
        }
        x -= (vx);
        y += (vy);

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

    public Rectangle grabObject(){
        return new Rectangle(x,y, asteroidImg.getWidth(), asteroidImg.getHeight());
    }

    void drawImage(Graphics2D g) {
        AffineTransform asteroid = AffineTransform.getTranslateInstance(x, y);
        asteroid.rotate(Math.toRadians(angle), this.asteroidImg.getWidth() / 2, this.asteroidImg.getHeight() / 2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.asteroidImg, asteroid, null);

        g.setColor(Color.blue);
        g.drawRect(this.x, this.y, asteroidImg.getWidth(), asteroidImg.getHeight());

    }

}