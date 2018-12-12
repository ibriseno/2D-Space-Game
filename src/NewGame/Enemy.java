package NewGame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

public class Enemy  {

    private int x;
    private int y;
    private int tankHealth = 60;
    private int damage = 6;

    private int vx;
    private int vy;
    private int angle;
    private int i = 0;
    private int counter = 0;

    private BufferedImage img;
    private BufferedImage enemyBullet;
    private BufferedImage enemyPlane_1;
    private BufferedImage enemyPlane_2;
    private BufferedImage enemyPlane_3;
    private BufferedImage enemyPlane_4;

    private BufferedImage rocketImg;

    private ArrayList<Bullet> myBulletList = new ArrayList<>();
    private Bullet bullet;

    private MapWalls mapWalls;
    private Collision tankCollision = new Collision();

    private final int R = 2;
    private final int ROTATIONSPEED = 4;
    private final int fireRate = 50;

    Enemy(BufferedImage img, int x, int y, int vx, int vy, int angle) {

        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;
        this.img = img;
        try{
            enemyPlane_1 = read(new File("Resources/spaceShooter/PNG/Sprites/Ships/enemyShips_002.png"));
            enemyPlane_2 = read(new File("Resources/spaceShooter/PNG/Sprites/Ships/enemyShips_001.png"));
            enemyPlane_3 = read(new File("Resources/spaceShooter/PNG/Sprites/Ships/enemyShips_004.png"));
            enemyPlane_4 = read(new File("Resources/spaceShooter/PNG/Sprites/Ships/enemyShips_008.png"));
            enemyBullet = read(new File("Resources/spaceShooter/PNG/Sprites/Missiles/spaceMissiles_015.png"));
        }catch(Exception e){

        }

    }

    public int getDamage(){
        return this.damage;
    }


    public void update() {
       moveBackwards();
       // moveForwards();
        for (int i = 0; i < getMyBulletList().size(); i++) {
            getMyBulletList().get(i).update();
         //   tankCollison.bulletVSgamewall(t2.getBulletlist().get(i), t2, i);
//            tankCollison.bulletVSgamewall(t1.getBulletlist().get(i), t1, i);
        }

    //    if(enemyPlayer.getX() + 300 < playerOne.getX() || enemyPlayer.getX() - 300 > playerOne.getX()){
    //        shoot();
        }



        /**
         for (int i = 0; i < getPowerList().size(); ++i){
         getPowerList().get(i).update();
         //            tankCollison.bulletVSgamewall(t2.getPowerList().get(i), t2, i);
         //            tankCollison.bulletVSgamewall(t1.getPowerList().get(i), t1, i);
         }

         for (int i = 0; i < getPowerList().size(); ++i){
         getPowerList().get(i).update();
         //            tankCollison.bulletVSgamewall(t2.getPowerList().get(i), t2, i);
         //            tankCollison.bulletVSgamewall(t1.getPowerList().get(i), t1, i);
         }
         **/

 //   }

    public void hit(boolean isHit, int damage){
        if (isHit){
            try {
                int i = 0;

                while(i < 50){
                    //  img = read(new File("Resources/Explosion_small.png"));
                    i++;
                }
                img =  read(new File("Resources/spaceShooter/PNG/Sprites/Ships/enemyShips_002.png"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * This section has all the actions of the tank
     * @return
     */
    public ArrayList<Bullet> getMyBulletList(){
        return this.myBulletList;
    }

    public void shoot() {
     //   followPlayer();
           Bullet newbullet;
           newbullet = new Bullet(enemyBullet,x+20,y+20, angle,6, 0, 0);
           myBulletList.add(newbullet);


        }

        public int getX(){
        return x;
    }


    public int getY(){
        return y;
    }

    public int getWidth(){
        return this.img.getWidth();
    }
    public int getHeight(){
        return this.img.getHeight();
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= 1;
        y -= 1;
//        checkWorldBorder();
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkWorldBorder();
    }

    private void followPlayer(){
       if(x < Player.playerOne.getX()){
           x +=1;
       }else if(x > Player.playerOne.getX()){
           x -= 1;
       }
    }

    private void checkWorldBorder(){
        if (x < 0) {
            x = 0;
        }
        if (x >= GameWorld.SCREEN_WIDTH - 100) {
            x = GameWorld.SCREEN_WIDTH - 100;
        }
        if (y < 0) {
            y = 0;
        }
        if (y >= GameWorld.SCREEN_HEIGHT - 95) {
            y = GameWorld.SCREEN_HEIGHT - 95 ;
        }
    }

    public void Collision( boolean isXCollidable, boolean isYCollidable, boolean rightCollidable, boolean leftCollidable, int pos){

        if (isXCollidable){
            x = pos - getWidth();
        }
        if (isYCollidable){
            y = pos - getHeight();
        }
        if (rightCollidable){
            y = pos;
        }
        if (leftCollidable){
            x = pos;
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    /**
     * This should be used to load up the health bar for the tanks.
     * @param g
     */
    void drawImage(Graphics2D g) {
        AffineTransform enemy = AffineTransform.getTranslateInstance(x, y);
        enemy.rotate(Math.toRadians(angle), this.img.getWidth()/2 , this.img.getHeight()/2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, enemy, null);

      //  g.setColor(Color.blue);
      //  g.drawRect(this.x, this.y, img.getWidth(),img.getHeight());
    }


}
