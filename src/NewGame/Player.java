package NewGame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

public class Player extends GameWorld {

    private int x;
    private int y;
    private int tankHealth = 60;
    private int addHealth = 0;
    private int damage = 6;
    private int tankLives = 3;

    private int vx;
    private int vy;
    private int angle;
    private int i = 0;
    private int counter = 0;

    private BufferedImage img;
    private BufferedImage mainBullet;
    private BufferedImage rocketImg;

    private ArrayList<Bullet> myBulletList = new ArrayList<>();
    private ArrayList<Bullet> powerList = new ArrayList<>();
    private ArrayList<Bullet> powerList2 = new ArrayList<>();
    private Bullet bullet;

    private MapWalls mapWalls;
    private Collision tankCollision = new Collision();

    private final int R = 2;
    private final int ROTATIONSPEED = 4;
    private final int fireRate = 50;
    private static boolean power;
    private static boolean power2;

    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean EnterPressed;
    private boolean Ypressed;

    Player(BufferedImage img, int x, int y, int vx, int vy, int angle) {

        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;
        this.img = img;
        try{
            mainBullet = read(new File("Resources/kenney_spaceshooterextension/PNG/Sprites/Missiles/spaceMissiles_017.png"));
        }catch(Exception e){

        }

    }

    public int getDamage(){
        return this.damage;
    }

    /*Toggles*/
    void toggleUpPressed() {
        this.UpPressed = true;

    }

    void toggleDownPressed() {
        this.DownPressed = true;

    }

    void toggleRightPressed() {
        this.RightPressed = true;

    }

    void toggleLeftPressed() {
        this.LeftPressed = true;

    }

    void toogleEnterPressed(){
        this.EnterPressed = true;
    }

    void toggleYPressed(){
        this.Ypressed = true;
    }


    /*Untoggles*/
    void unToggleUpPressed() {
        this.UpPressed = false;

    }

    void unToggleDownPressed() {
        this.DownPressed = false;

    }

    void unToggleRightPressed() {
        this.RightPressed = false;

    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;

    }

    void unToggleEnterPressed(){this.EnterPressed = false;}
    void unToggleYPressed(){this.Ypressed = false;}

    public void update() {


        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if(this.EnterPressed) {
            //i++;
            if (i % fireRate == 0) {
                this.shoot();
            }
            i++;


        }

        for (int i = 0; i < getMyBulletList().size(); i++){
            getMyBulletList().get(i).update();
//            tankCollison.bulletVSgamewall(t2.getBulletlist().get(i), t2, i);
//            tankCollison.bulletVSgamewall(t1.getBulletlist().get(i), t1, i);
        }

        for (int i = 0; i < getMyBulletList().size(); i++){
            getMyBulletList().get(i).update();
//            tankCollison.bulletVSgamewall(t2.getBulletlist().get(i), t2, i);
//            tankCollison.bulletVSgamewall(t1.getBulletlist().get(i), t1, i);
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

    }

    public void hit(boolean isHit, int damage){
        if (isHit){
            try {
                int i = 0;

                while(i < 50){
                    //  img = read(new File("Resources/Explosion_small.png"));
                    i++;
                }
                tankHealth -= damage;
                if(tankLives == 3){
                    if(tankHealth == 0 ){
                        tankHealth = 60;
                        tankLives -= 1;
                    }
                }
                if(tankLives == 2){
                    if(tankHealth == 0 ){
                        tankHealth = 60;
                        tankLives -= 1;
                    }
                }
                if(tankLives == 1){
                    if(tankHealth == 0 ){
                        tankHealth = 60;
                        tankLives -= 1;
                    }
                }
                if(tankLives == 0){
                    if(tankHealth == 0 ){
                        tankHealth = 60;
                    }
                }
                img =  read(new File("Resources/topdown-shooter/PNG/Hitman 1/hitman1_gun.png"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getHealth(boolean isHealth) {
        if (isHealth) {
            // int i = 0;
            if (((tankHealth-damage) | (tankHealth + addHealth)) >= 60) {
                addHealth = 0;
                tankHealth -=damage;
            }
            if ((tankHealth-damage) <= 54) {
                addHealth += 6;
                tankHealth += addHealth;
                System.out.println("Added +6 Health! " + tankHealth);
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

    public void setPower(boolean isPower){
        power = isPower;
    }


    public void shoot() {
        if (power && counter < 5){
            Bullet powerBullet;
            powerBullet = new Bullet(rocketImg,x,y, angle,20, 0, 0);
            powerList.add(powerBullet);
       //     System.out.println("Power Bullet Count Tank 1: " + GameWorld.t1.powerList.size());

         //   powerList2.add(powerBullet);
        //    System.out.println("Power Bullet Count Tank 2: " + GameWorld.t2.powerList2.size());

            counter++;
        }
        else {
            power = false;
            Bullet newbullet;
            newbullet = new Bullet(mainBullet,x+8,y+8, angle,6, 0, 0);
            myBulletList.add(newbullet);
        }

    }

    public ArrayList<Bullet> getPowerList(){return powerList;}
    public ArrayList<Bullet> getPowerList2(){return powerList2;}

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
        x -= vx;
        y -= vy;
        checkWorldBorder();
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkWorldBorder();
    }

    private void checkWorldBorder(){
        if (x < 0) {
            //   x = 30;
        }
        if (x >= GameWorld.WORLD_WIDTH - 88) {
            x = GameWorld.WORLD_WIDTH - 88 ;
        }
        if (y < 0) {
            //    y = 40;
        }
        if (y >= GameWorld.WORLD_HEIGHT - 80) {
            y = GameWorld.WORLD_HEIGHT - 80 ;
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
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth()/2 , this.img.getHeight()/2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);

        g.setColor(Color.blue);
        g.drawRect(this.x, this. y, img.getWidth(),img.getHeight());
/**
 *Life counter for Tanks
 */

        if (tankLives == 3) {
            g.setColor(Color.blue);
            g.fill(new Rectangle2D.Double(x + 30, y + 70, 10, 10));
            g.setColor(Color.blue);
            g.fill(new Rectangle2D.Double(x + 50, y + 70, 10, 10));
            g.setColor(Color.blue);
            g.fill(new Rectangle2D.Double(x + 70, y + 70, 10, 10));
        }
        if(tankLives == 2) {
            g.setColor(Color.blue);
            g.fill(new Rectangle2D.Double(x + 30, y + 70, 10, 10));
            g.setColor(Color.blue);
            g.fill(new Rectangle2D.Double(x + 50, y + 70, 10, 10));
        }
        if(tankLives == 1) {
            g.setColor(Color.blue);
            g.fill(new Rectangle2D.Double(x + 30, y + 70, 10, 10));
        }
        if(tankLives == 0) {
            try {
                mainBullet = read(new File("Resources/kenney_spaceshooterextension/PNG/Sprites/Effects/spaceEffects_014.png"));
                if(GameWorld.playerOne.tankLives == 0 && tankHealth != 0){
                    String msg = "Game Over! Player 2 Wins!";
                    Font small = new Font("Helvetica", Font.BOLD, 100);
                    FontMetrics metr = getFontMetrics(small);
                    g.setColor(Color.white);
                    g.setFont(small);
                    g.drawString(msg, (SCREEN_WIDTH - metr.stringWidth(msg))+ 300 ,SCREEN_HEIGHT/2);
                    GameWorld.playerOne.startGame(false);
                }
                /**
                else if(GameWorld.t2.tankLives == 0 && tankHealth != 0){
                    String msg = "Game Over! Player 1 Wins!";
                    Font small = new Font("Helvetica", Font.BOLD, 100);
                    FontMetrics metr = getFontMetrics(small);
                    g.setColor(Color.white);
                    g.setFont(small);
                    g.drawString(msg, (SCREEN_WIDTH - metr.stringWidth(msg)) + 300 ,SCREEN_HEIGHT/2);
                    GameWorld.t2.startGame(false);

                }
                 **/


                //    GameWorld.t1.restartGame(g);
                //    GameWorld.t2.restartGame(g);
            }catch(IOException e){

                g.setColor(Color.green);
                g.fill(new Rectangle2D.Double(x +30,y + 50, tankHealth ,10));
            }

        }else {
            g.setColor(Color.green);
            g.fill(new Rectangle2D.Double(x + 30, y + 50, tankHealth, 10));
        }

    }


}
