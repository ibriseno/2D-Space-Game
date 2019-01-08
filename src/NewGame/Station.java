package NewGame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class Station extends GameWorld {

    private int x, y, vx, vy, angle, width, height;

    private final int R = 0;

    private BufferedImage stationImg;

    private ArrayList<Bullet> stationArrayList = new ArrayList<>();

    private int ROTATIONSPEED = 1;

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

    public ArrayList<Bullet> getStationArrayList(){
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
        for(int i = 0; i < stationArrayList.size(); i++){
            if (GameWorld.stationArrayList.size() == 0) {
                String msg = "You Win";
                Font small = new Font("Helvetica", Font.BOLD, 25);
                FontMetrics metr = getFontMetrics(small);
                g.setColor(Color.white);
                g.setFont(small);
                g.drawString(msg, (SCREEN_WIDTH - metr.stringWidth(msg)) - 850, SCREEN_HEIGHT - 910);
            }
        }
        AffineTransform asteroid = AffineTransform.getTranslateInstance(x, y);
        asteroid.rotate(Math.toRadians(angle), this.stationImg.getWidth() / 2, this.stationImg.getHeight() / 2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.stationImg, asteroid, null);



        g2d.setColor(Color.blue);
        g2d.drawRect(this.x, this.y, stationImg.getWidth(), stationImg.getHeight());
    }

}
