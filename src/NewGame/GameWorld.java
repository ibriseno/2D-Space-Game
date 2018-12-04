package NewGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;


public class GameWorld extends JPanel{


    public static int w, h;
    public static final int WORLD_WIDTH = 1920;
    public static final int WORLD_HEIGHT = 1440;
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 960;
    public static final int TILE_SIZE = 1280 / 32;    //Used for building walls
    private Bullet blt;
    private Image bulletImage;

    private MapWalls mapWalls;
    private MapWalls mapWallsRight;
    private MapWalls powerUp;
    private MapWalls health;
    private MapWalls breakWall;


    public static ArrayList<MapWalls> wallsArrayListLeft = new ArrayList<>();
    public static ArrayList<MapWalls> wallsArrayListRight = new ArrayList<>();
    public static ArrayList<MapWalls> powerUpArrayList = new ArrayList<>();
    public static ArrayList<MapWalls> powerUpArrayList2 = new ArrayList<>();
    public static ArrayList<MapWalls> healthArrayList = new ArrayList<>();
    public static ArrayList<Explosion> explosionArrayList = new ArrayList<>();

    private static Collision tankcollision = new Collision();


    private Graphics2D buffer;

    private JFrame jf;

    public static Player playerOne;

    public static Bullet bulletMain;
    public static Bullet bullet2;

    // private Graphics2D buffer;
    private BufferedImage world;
    private BufferedImage tank;
    private BufferedImage gameWall;
    private BufferedImage background;
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

                  //  trex.t2.update();

                    trex.repaint();
                  //  updateCollisions();
                    //  System.out.println("trex t1: " + trex.t1);
                    //  System.out.println("trex t2: " + trex.t2);
                    // System.out.println(trex.t2);
                    Thread.sleep(1000 / 144);
                }else{
                    break;
                }
            }

        } catch (InterruptedException ignored) {

        }


    }

    /**
    public  static void updateCollisions(){

        tankcollision.playerVSplayer(GameWorld.t1, GameWorld.t2);

        //  tankcollision.playerVSbullet(GameWorld.t1, GameWorld.t2);
        //  tankcollision.playerVSbullet(GameWorld.t2, GameWorld.t1);

        tankcollision.player1VSbullet(GameWorld.t1, GameWorld.t2);
        tankcollision.player2VSbullet(GameWorld.t1, GameWorld.t2);
        //tankcollision.playerVSbullet(GameWorld.t2, GameWorld.t1);

        tankcollision.player2VSbullet(GameWorld.t2, GameWorld.t1);
        tankcollision.playerVSgameWall(GameWorld.t1);
        tankcollision.playerVSgameWall(GameWorld.t2);
        tankcollision.player1VSpowerbullet(GameWorld.t1, GameWorld.t2);
        tankcollision.player2VSpowerbullet(GameWorld.t2, GameWorld.t1);
        tankcollision.playerVSborderWall(GameWorld.t1);
        tankcollision.playerVSborderWall(GameWorld.t2);
        tankcollision.playerVSpowerUp(GameWorld.t1);
        tankcollision.playerVSpowerUp(GameWorld.t2);
        tankcollision.detectHealth(GameWorld.t1, GameWorld.t2);




    }
     **/
    private void init() {


        this.jf = new JFrame("Java 2D Shooter");
        this.world = new BufferedImage(GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage t1img = null;

        try {
            System.out.println(System.getProperty("user.dir"));
            /*
             * note class loaders read files from the out folder (build folder in netbeans) and not the
             * current working directory.
             */
          //  t1img = read(new File("tank1.png"));
            tank = read(new File("Resources/topdown-shooter/PNG/Hitman 1/hitman1_gun.png"));
            background = read(new File("Resources/background_2.jpg"));
            mapTiles = read(new File("Resources/topdown-shooter/PNG/Tiles/tile_01.png"));
            borderWallLeft = read(new File("Resources/topdown-shooter/PNG/Tiles/tile_400.png"));
            borderWallRight = read(new File("Resources/topdown-shooter/PNG/Tiles/tile_402.png"));



        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        playerOne = new Player(tank, 80, 85, 0, 0, 0);
       // t2 = new Tank(t1img, 1758, 1289, 0, 0,180);


        /**
         * Inside this for loop is where everything gets read.
         * Created array lists to handle the objects inside the game. The loop
         * goes through the map and reads what object is set at that index of the map.
         *
         * */


        for (int a = 0; a < MapWalls.map.length; a++) {
            for (int b = 0; b < MapWalls.map[a].length; b++) {
                //Draws Border Walls
                if (MapWalls.map[a][b] == 1) {
                    mapWalls = new MapWalls(a * 32, b * 32, borderWallLeft);
                    wallsArrayListLeft.add(mapWalls);
                }
                //Draws Border Walls
                if (MapWalls.map[a][b] == 2) {
                    mapWallsRight = new MapWalls(a * 32, b * 32, borderWallRight);
                    wallsArrayListRight.add(mapWallsRight);
                }
                /**
                //Draws Power Up
                if (MapWalls.map[a][b] == 2) {
                    powerUp = new MapWalls(a * 32, b * 32, powerUpImg);
                    powerUpArrayList.add(powerUp);
                }
                //Draws Health icons on the map
                if (MapWalls.map[a][b] == 3) {
                    health = new MapWalls(a * 32, b * 32, potionImg);
                    healthArrayList.add(health);
                }
                //Draws Breakable Walls on the map
                if (MapWalls.map[a][b] == 4) {
                    breakWall = new MapWalls(a * 32, b * 32, gameWall);
                    wallsArrayList2.add(breakWall);
                }
            }
**/
    }}

            //
            //TankControl tc1 handles all the control keys
            //UP, DOWN, LEFT, RIGHT, ENTER will handle all the controls for tank 1.
            //W(up), S(down), A(left), D(right), F(shoot) will handle all the controls for tank 2.
            //
            //Another instance of a tank should be created.
            //
            PlayerControls tc1 = new PlayerControls(playerOne, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);
          //  TankControl tc2 = new TankControl(t2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_F);

            this.jf.setLayout(new BorderLayout());
            this.jf.add(this);


            this.jf.addKeyListener(tc1);
          //  this.jf.addKeyListener(tc2);


            this.jf.setSize(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT + 30);

            this.jf.setResizable(false);
            jf.setLocationRelativeTo(null);

            this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.jf.setVisible(true);


        }


    public void startGame(boolean isStart){
        //  gameStart = true;
        if(isStart == false){
            gameStart = false;
        }else{
            gameStart = true;
        }
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


        int offsetMaxX = (WORLD_WIDTH - (SCREEN_WIDTH / 2));
        int offsetMaxY = WORLD_HEIGHT - SCREEN_HEIGHT;
        int offsetMinX = 0;
        int offsetMinY = 0;

        int camX = (playerOne.getX() - SCREEN_WIDTH / 4);
        int camY = playerOne.getY() - SCREEN_HEIGHT / 2;

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
    **/
        /////////////////////////////////////////////////////////////////////////


        BufferedImage bufferImgOne = (world.getSubimage(camX, camY, SCREEN_WIDTH / 2, SCREEN_HEIGHT));
      //  BufferedImage bufferImgTwo = (world.getSubimage(camX2, camY2, SCREEN_WIDTH / 2, SCREEN_HEIGHT));

        g2.drawImage(bufferImgOne, 0, 0, this);
      //  g2.drawImage(bufferImgTwo, 640, 0, this);

        drawGame();

        /**
         * This bottom section draws the minimap.
         * It's important to put it at the end of this class.*/

        this.playerOne.drawImage(buffer);
     //   this.t2.drawImage(buffer);
        Image scaledMap = world.getScaledInstance(200, 200, Image.SCALE_FAST);
        g2.drawImage(scaledMap, SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT / 2 - 100, 200, 200, this);
    }




    /**
     * This draws the background for the entire map
     */
    public void drawBackGroundWithTileImage() {
        int TileWidth = mapTiles.getWidth(this);
        int TileHeight = mapTiles.getHeight(this);

        int NumberX = (int) (WORLD_WIDTH / TileWidth);
        int NumberY = (int) (WORLD_HEIGHT / TileHeight);

        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {

                buffer.drawImage(mapTiles, j * TileWidth,
                        i * TileHeight + (move % TileHeight), TileWidth,
                        TileHeight, this);

            }
        }

    }
    /**
     * This section draws Background and Bullets
     */
    public void drawGame() {

        drawBackGroundWithTileImage();
   //     drawBackGround();
        drawBullet();
    //    drawPowerUp();            //Draws the power ups on the map
    //    drawHealth();
        drawWallsLeftSide();
        drawWallsRightSide();
    //    drawExplosion();
    //    powerUp();                //Draws Power up bullet fot tank 1 only
    //    powerUp2();
    //    drawBreakWalls();
    }

    /**
    public void drawExplosion(){
        for (int i = 0; i < explosionArrayList.size(); i++){
            explosionArrayList.get(i).drawImage(buffer);
            explosionArrayList.remove(i);
        }

    }

    public void powerUp(){
        if(t1.getPowerList().size() != 0) {
            for (int i = 0; i < t1.getPowerList().size(); i++) {
                this.t1.getPowerList().get(i).drawImage(buffer);
            }
        }
    }

    public void powerUp2(){
        if(t2.getPowerList2().size() != 0){
            for(int i = 0; i < t2.getPowerList2().size();i++){
                this.t2.getPowerList2().get(i).drawImage(buffer);
            }
        }
    }
**/
    public void drawWallsLeftSide() {

        for (int i = 0; i < wallsArrayListLeft.size(); i++) {
            wallsArrayListLeft.get(i).drawImage(buffer);

        }
    }
    public void drawWallsRightSide() {

        for (int i = 0; i < wallsArrayListRight.size(); i++) {
            wallsArrayListRight.get(i).drawImage(buffer);

        }
    }
/**
    public void drawPowerUp() {

        for (int i = 0; i < powerUpArrayList.size(); i++) {
            powerUpArrayList.get(i).drawImage(buffer);

        }
    }

    public void drawPowerUp2() {

        for (int i = 0; i < powerUpArrayList2.size(); i++) {
            powerUpArrayList2.get(i).drawImage(buffer);

        }
    }

    public void drawHealth() {

        for (int i = 0; i < healthArrayList.size(); i++) {
            healthArrayList.get(i).drawImage(buffer);

        }
    }
    public void drawBreakWalls(){
        for (int i = 0; i < wallsArrayList2.size(); i++){
            wallsArrayList2.get(i).drawImage(buffer);
        }
    }
**/
    public void drawBullet() {

    /**

         * Bullet For Tank 1*/

        for (int i = 0; i < playerOne.getMyBulletList().size(); i++) {
            this.playerOne.getMyBulletList().get(i).drawImage(buffer);
          //  tankcollision.bulletVSgamewall(this.t1.getMyBulletList().get(i), this.t1, i);
         //   tankcollision.bulletVSborderwall(this.t1.getMyBulletList().get(i),this.t1, i);

        }


        /**
         * Bullet For Tank 2*/
        /**
        for (int i = 0; i < t2.getMyBulletList().size(); i++) {
            this.t2.getMyBulletList().get(i).drawImage(buffer);
            tankcollision.bulletVSgamewall(this.t2.getMyBulletList().get(i), this.t2, i);
            tankcollision.bulletVSborderwall(this.t2.getMyBulletList().get(i),this.t2, i);

        }
        **/
    }
}
