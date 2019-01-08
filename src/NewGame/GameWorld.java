package NewGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;


public class GameWorld extends JPanel {


    public static int w, h;
    public int x = 1;
    public static final int WORLD_WIDTH = 1920;
    public static final int WORLD_HEIGHT = 1440;
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 960;

    public static ArrayList<Explosion> explosionArrayList = new ArrayList<>();
    public static ArrayList<Asteroids> asteroidsArrayList = new ArrayList<>();
    public static ArrayList<Station> stationArrayList = new ArrayList<>();

    private static Collision gameCollision = new Collision();


    private Graphics2D buffer;

    private JFrame jf;

    public static Player playerOne;

    public static Asteroids asteroidImg;

    public static Station spaceStationImg;

    public static Bullet bulletMain;
    public static Bullet bullet2;
    //
    //Images
    // private Graphics2D buffer;
    //
    private BufferedImage world;
    private BufferedImage tank;
    private BufferedImage background;
    private BufferedImage asteroid;
    private BufferedImage asteroid2;
    private BufferedImage spaceStation;
    private BufferedImage enemyShip;
    private BufferedImage enemyShip2;
    private BufferedImage enemyShip3;
    private BufferedImage bossShip;
    private BufferedImage powerUpShip_1;

    private BufferedImage gameWall;
    private BufferedImage mapTiles;
    private BufferedImage borderWallLeft;
    private BufferedImage borderWallRight;
    private BufferedImage borderWallTop;
    private BufferedImage borderWallBottom;
    private BufferedImage mainBullet;
    private BufferedImage powerUpImg;
    private BufferedImage healthUp;
    private BufferedImage potionImg;
    private BufferedImage largeExplosion;
    private BufferedImage smallExplosion;
    private BufferedImage blockImg;
    private BufferedImage metalBox;
    private BufferedImage rockWall;
    private BufferedImage startLogo;

    private static boolean gameStart = true;
    private static boolean nextLevel;


    int move = 0;
    int speed = 1;


    public static void main(String[] args) {
        Thread x;
        GameWorld trex = new GameWorld();
        // start();
        trex.init();
        try {

            while (true) {
                if (gameStart == true) {

                    trex.playerOne.update();
                    for (int i = 0; i < asteroidsArrayList.size(); i++) {
                        asteroidsArrayList.get(i).update();
                    }
                    for (int i = 0; i < stationArrayList.size(); i++) {
                        stationArrayList.get(i).update();
                    }

                    trex.repaint();
                    updateCollisions();
                    System.out.println("trex playerOne: " + trex.playerOne);
                    //  System.out.println("trex t2: " + trex.t2);
                    //  System.out.println("trex asteroid: " + trex.asteroidImg);
                    // System.out.println(trex.t2);
                    //   System.out.println("trex enemyPlayer: " + trex.enemyPlayer);
                    Thread.sleep(1000 / 144);

            }else{
                break;
            }
        }

    } catch(Exception e){
    }




    }





    public  static void updateCollisions(){

        gameCollision.playerVSsmallAsteroid(GameWorld.playerOne);
        gameCollision.bulletVSAsteroid(GameWorld.playerOne);


    }

    private void init() {


        this.jf = new JFrame("Java 2D Shooter");
        this.world = new BufferedImage(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage t1img = null;

        try {
            System.out.println(System.getProperty("user.dir"));
            /*
             * note class loaders read files from the out folder (build folder in netbeans) and not the
             * current working directory.
             */
          //  t1img = read(new File("tank1.png"));
            tank = read(new File("Resources/spaceShooter/PNG/Sprites/Ships/spaceShips_002.png"));
            powerUpShip_1 = read(new File("Resources/spaceShooter2/PNG/Power-ups/star_silver.png"));

            enemyShip = read (new File("Resources/spaceShooter/PNG/Sprites/Ships/enemyShips_002.png"));
            enemyShip2 = read (new File("Resources/spaceShooter/PNG/Sprites/Ships/enemyShips_001.png"));
            enemyShip3 = read(new File("Resources/spaceShooter/PNG/Sprites/Ships/enemyShips_004.png"));
            bossShip = read(new File("Resources/spaceShooter/PNG/Sprites/Ships/enemyShips_008.png"));

            background = read(new File("Resources/trippy_space.jpg"));
            asteroid = read(new File("Resources/spaceShooter/PNG/Sprites/Meteors/spaceMeteors_001.png"));
            asteroid2 = read(new File("Resources/spaceShooter2/PNG/Meteors/meteorGrey_big4.png"));
            spaceStation = read(new File("Resources/spaceShooter/PNG/Sprites/Station/spaceStation_021.png"));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        if(stationArrayList.size() == 0){
            playerOne = new Player(tank, 80, 85, 0, 0, 0);
            for(int i = 0; i <= 3; ++i) {
                asteroidsArrayList.add(asteroidImg = new Asteroids(asteroid, 100, 100));
            }
            for(int i = 0; i < 3; i++){
                stationArrayList.add(spaceStationImg = new Station(spaceStation));
            }
        }else{
            playerOne = new Player(tank, 80, 85, 0, 0, 0);
            for(int i = 0; i <= 5; ++i) {
                asteroidsArrayList.add(asteroidImg = new Asteroids(asteroid, 100, 100));
                //asteroidImg = new Asteroids(asteroid, 100, 100);
            }
            for(int i = 0; i < 5; i++){
                stationArrayList.add(spaceStationImg = new Station(spaceStation));
            }
        }

            //
            //TankControl tc1 handles all the control keys
            //UP, DOWN, LEFT, RIGHT, ENTER will handle all the controls for tank 1.
            //W(up), S(down), A(left), D(right), F(shoot) will handle all the controls for tank 2.
            //
            //Another instance of a tank should be created.
            //
            PlayerControls tc1 = new PlayerControls(playerOne, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER, KeyEvent.VK_SPACE);
          //  TankControl tc2 = new TankControl(t2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_F);

            this.jf.setLayout(new BorderLayout());
            this.jf.add(this);


            this.jf.addKeyListener(tc1);
          //  this.jf.addKeyListener(tc2);


            this.jf.setSize(GameWorld.SCREEN_WIDTH + 16, GameWorld.SCREEN_HEIGHT + 30);

            this.jf.setResizable(false);
            jf.setLocationRelativeTo(null);

            this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.jf.setVisible(true);


        }




    /**
     * PaintComponent paints all the objects in the map.
     * It does not paint the background of the map. The background is fixed.
     *
     * */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        buffer = world.createGraphics();

        super.paintComponent(g2);


        int offsetMaxX = (WORLD_WIDTH - (SCREEN_WIDTH) + 100);
        int offsetMaxY = WORLD_HEIGHT - SCREEN_HEIGHT + 100;
        int offsetMinX = 0;
        int offsetMinY = 0;

        int camX = (playerOne.getX() - (SCREEN_WIDTH+ 100) );
        int camY = playerOne.getY() - (SCREEN_HEIGHT + 100) ;

      //  int camX2 = (t2.getX() - SCREEN_WIDTH / 4);
      //  int camY2 = t2.getY() - SCREEN_HEIGHT / 2;

        /////////////////////////////////////////////////////////////////////
        if (camX > offsetMaxX) {
            camX = offsetMaxX;
        } else if (camX < offsetMinX) {
            camX = offsetMinX;
        }
        if (camY > offsetMaxY) {
            camY = offsetMaxY;
        } else if (camY < offsetMinY) {
            camY = offsetMinY;
        }

        /////////////////////////////////////////////////////////////////////
    /**
        if (camX2 > offsetMaxX) {
            camX2 = offsetMaxX;
        } else if (camX2 < offsetMinX) {
            camX2 = offsetMinX;
        }
        if (camY2 > offsetMaxY) {
            camY2 = offsetMaxY;
        } else if (camY2 < offsetMinY) {
            camY2 = offsetMinY;
        }

        /////////////////////////////////////////////////////////////////////////
**/

       BufferedImage bufferImgOne = (world.getSubimage(camX , camY, SCREEN_WIDTH  , SCREEN_HEIGHT));
      //  BufferedImage bufferImgTwo = (world.getSubimage(camX2, camY2, SCREEN_WIDTH / 2, SCREEN_HEIGHT));

         g2.drawImage(bufferImgOne, 0, 0, this);
      //  g2.drawImage(bufferImgTwo, 640, 0, this);




            drawGame();
    }

    public void drawBackGroundImage(){
        buffer.drawImage(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);

    }
    public void endLevel(){
        if(stationArrayList.size() == 0){
            nextLevel = true;
        }else{
            nextLevel = false;
        }
    }



    /**
     * This section draws Background and Bullets
     */
    public void drawGame() {

        drawBackGroundImage();
        drawBullet();
        endLevel();
        this.playerOne.drawImage(buffer);
        for(int i = 0; i < asteroidsArrayList.size(); i++){
            asteroidsArrayList.get(i).drawImage(buffer);
        }
        for(int i = 0; i< stationArrayList.size(); i++){
            stationArrayList.get(i).drawImage(buffer);
        }
    }


    public void drawBullet() {

    /**

         * Bullet For Player 1*/

        for (int i = 0; i < playerOne.getMyBulletList().size(); i++) {
            this.playerOne.getMyBulletList().get(i).drawImage(buffer);


        }

    }
}
