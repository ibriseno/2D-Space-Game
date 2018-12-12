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
    public int x = 1;
    public static final int WORLD_WIDTH = 1920;
    public static final int WORLD_HEIGHT = 1440;
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 960;
    public static final int TILE_SIZE = 1280 / 32;    //Used for building walls
    private Bullet blt;
    private Image bulletImage;

    private MapWalls mapPowerUp;
    private MapWalls mapWallsRight;
    private MapWalls powerUp;
    private MapWalls health;
    private MapWalls breakWall;


    public static ArrayList<MapWalls> powerUpArrayList = new ArrayList<>();
    public static ArrayList<MapWalls> wallsArrayListRight = new ArrayList<>();
    public static ArrayList<MapWalls> powerUpArrayList2 = new ArrayList<>();
    public static ArrayList<MapWalls> healthArrayList = new ArrayList<>();
    public static ArrayList<Explosion> explosionArrayList = new ArrayList<>();
    public static ArrayList<Asteroids> asteroidsArrayList = new ArrayList<>();
    public static ArrayList<Station> stationArrayList = new ArrayList<>();

    private static Collision gameCollision = new Collision();


    private Graphics2D buffer;

    private JFrame jf;

    public static Player playerOne;

    public static Asteroids asteroidImg;
    public static Asteroids asteroidImg2;
    public static Asteroids asteroidImg3;

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
    private static boolean nextLevel = false;


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
                    if (nextLevel == false) {
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
                        trex.playerOne.update();
                        for (int i = 0; i < asteroidsArrayList.size(); i++) {
                            asteroidsArrayList.get(i).update();
                        }
                        for (int i = 0; i < stationArrayList.size(); i++) {
                            stationArrayList.get(i).update();
                        }

                        trex.repaint();
                        updateCollisions();
                        Thread.sleep(1000 / 144);
                    }
                }else{
                    break;
                }
            }

        } catch (InterruptedException ignored) {

        }


    }


    public  static void updateCollisions(){

        gameCollision.playerVSsmallAsteroid(GameWorld.playerOne);
        gameCollision.bulletVSAsteroid(GameWorld.playerOne);
      //  gameCollision.playerVSstation(GameWorld.playerOne);
//        gameCollision.playerVSsmallAsteroid(GameWorld.playerOne, GameWorld.asteroidImg2);
//        gameCollision.playerVSsmallAsteroid(GameWorld.playerOne, GameWorld.asteroidImg3);


        /**
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

**/


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
          //  mapTiles = read(new File("Resources/topdown-shooter/PNG/Tiles/tile_01.png"));
          //  borderWallLeft = read(new File("Resources/topdown-shooter/PNG/Tiles/tile_400.png"));
          //  borderWallRight = read(new File("Resources/topdown-shooter/PNG/Tiles/tile_402.png"));



        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        playerOne = new Player(tank, 80, 85, 0, 0, 0);
        for(int i = 0; i < 5; ++i) {
            asteroidsArrayList.add(asteroidImg = new Asteroids(asteroid, 100, 100));
            //asteroidImg = new Asteroids(asteroid, 100, 100);
        }
        for(int i = 0; i < 3; i++){
            stationArrayList.add(spaceStationImg = new Station(spaceStation));
        }
     //   enemyPlayer = new Enemy(enemyShip, 700, 700, 0, 0, 0);
    //    enemyPlayer2 = new Enemy(enemyShip2, 700, 500, 0, 0, 0);
    //    enemyPlayer3 = new Enemy(enemyShip3, 700, 300, 0, 0, 0);
    //    bossEnemy = new Enemy(bossShip, 200,800,0,0,0);

     //   asteroidImg = new Asteroids(asteroid, asteroid2, 400 ,0, 0, 200, 400, 0);
     //   asteroidImg2 = new Asteroids(asteroid, asteroid2, 800 ,0, 0, 800, 200, 0);
     //   asteroidImg3 = new Asteroids(asteroid, asteroid2, 1000 ,0, 0, 500, 600, 0);
     //   spaceStationImg = new Asteroids(asteroid, spaceStation, 600 ,0, 0, 1000, 600, 0);



        /**
         * Inside this for loop is where everything gets read.
         * Created array lists to handle the objects inside the game. The loop
         * goes through the map and reads what object is set at that index of the map.
         *
         * */
        /**
        for (int a = 0; a < MapWalls.map.length; a++) {
            for (int b = 0; b < MapWalls.map[a].length; b++) {
                //Draws Border Walls
                if (MapWalls.map[a][b] == 1) {
                    mapPowerUp = new MapWalls(a * 32, b * 32, powerUpShip_1);
                    powerUpArrayList.add(mapPowerUp);
                }
            }
        }
         **/
        /**
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
         **/
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

        if(nextLevel == true){
            drawGameLevel2();
        }else{
            drawGame();
        }


        /**
         * This bottom section draws the minimap.
         * It's important to put it at the end of this class.*/
        /**
        this.playerOne.drawImage(buffer);
        for(int i = 0; i < asteroidsArrayList.size(); i++){
            asteroidsArrayList.get(i).drawImage(buffer);
        }
        for(int i = 0; i< stationArrayList.size(); i++){
            stationArrayList.get(i).drawImage(buffer);
        }
         **/
      //  this.asteroidImg.drawImage(buffer);
    //    this.asteroidImg2.drawImage(buffer);
    //    this.asteroidImg3.drawImage(buffer);
    //    this.spaceStationImg.drawImage(buffer);
/**
        String msg = "Game Score: " + playerOne.getX();
        Font small = new Font("Helvetica", Font.BOLD, 40);
        FontMetrics metr = getFontMetrics(small);
        g2.setColor(Color.white);
        g2.setFont(small);
        g2.drawString(msg, (SCREEN_WIDTH - metr.stringWidth(msg)) - 945 ,SCREEN_HEIGHT - 900);
 **/
 //     this.enemyPlayer.drawImage(buffer);
   //     this.enemyPlayer2.drawImage(buffer);
   //     this.enemyPlayer3.drawImage(buffer);
   //     this.bossEnemy.drawImage(buffer);
     //   this.t2.drawImage(buffer);
     //   Image scaledMap = world.getScaledInstance(200, 200, Image.SCALE_FAST);
     //   g2.drawImage(scaledMap, SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT / 2 - 100, 200, 200, this);
    }




    /**
     * This draws the background for the entire map
     */
    public void drawBackGroundWithTileImage() {
        int TileWidth = background.getWidth(this);
        int TileHeight = background.getHeight(this);

        int NumberX = (int) (WORLD_WIDTH / TileWidth);
        int NumberY = (int) (WORLD_HEIGHT / TileHeight);

        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {

               buffer.drawImage(background, j * TileWidth,
                        i * TileHeight + (move % TileHeight), TileWidth,
                        TileHeight, this);

            }
        }

    }
    public void drawBackGroundImage(){
        buffer.drawImage(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);

    }
    public void endLevel(){
        if(playerOne.getX() > 1200){
            nextLevel = true;
        }else{
            nextLevel = false;
        }
    }
    public void drawGameLevel2(){

            drawBackGroundImage();
            drawBullet();
            this.playerOne.drawImage(buffer);
            for(int i = 0; i < asteroidsArrayList.size(); i++){
                asteroidsArrayList.get(i).drawImage(buffer);
            }
            for(int i = 0; i< stationArrayList.size(); i++){
                stationArrayList.get(i).drawImage(buffer);
            }
            //    drawPowerUp();
        }

    /**
     * This section draws Background and Bullets
     */
    public void drawGame() {

        drawBackGroundImage();
        drawBullet();
        drawPowerUp();
        endLevel();
        this.playerOne.drawImage(buffer);
        for(int i = 0; i < asteroidsArrayList.size(); i++){
            asteroidsArrayList.get(i).drawImage(buffer);
        }
        for(int i = 0; i< stationArrayList.size(); i++){
            stationArrayList.get(i).drawImage(buffer);
        }
    }


    public void drawPowerUp() {

        for (int i = 0; i < powerUpArrayList.size(); i++) {
            powerUpArrayList.get(i).drawImage(buffer);

        }
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
       // for(int i = 0; i < enemyPlayer.getMyBulletList().size();i++){
    //        this.enemyPlayer.getMyBulletList().get(i).drawImage(buffer);
    //    }


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
