package NewGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;


public class Collision {

    private Image Explosionimage;

    public void bulletVSAsteroid(Player bullet) {
        for (int i = 0; i < GameWorld.asteroidsArrayList.size(); i++) {
            for (int j = 0; j < bullet.getMyBulletList().size(); j++) {
                Rectangle bulletBox = bullet.getMyBulletList().get(j).grabObject();
                Rectangle asteroidBox = GameWorld.asteroidsArrayList.get(i).grabObject();
            if (bulletBox.intersects(asteroidBox)) {
                GameWorld.asteroidsArrayList.remove(i);
                GameWorld.asteroidsArrayList.add(new Asteroids());
            }
        }

        }

    }

    public void playerVSsmallAsteroid(Player player) {
        Rectangle playerBox = player.grabObject();
        for (int i = 0; i < GameWorld.asteroidsArrayList.size(); i++) {
            Rectangle asteroidBox = GameWorld.asteroidsArrayList.get(i).grabObject();
            if (player.getDocked() == false) {
                if (asteroidBox.intersects(playerBox)) {
                    player.hit(true, 30);
                    GameWorld.asteroidsArrayList.remove(i);
                    GameWorld.asteroidsArrayList.add(new Asteroids());

                }
               // player.hit(false, 0);
            } player.hit(false, 0);
        }
        }

      public void playerVSstation(Player player){
        Rectangle playerBox = player.grabObject();
        for(int i = 0; i < GameWorld.stationArrayList.size(); i++){
            Rectangle stationBox = GameWorld.stationArrayList.get(i).grabObject();
            if(stationBox.intersects(playerBox)){
                player.setDocked(true);
                player.setX(GameWorld.stationArrayList.get(i).getX() );
                player.setY(GameWorld.stationArrayList.get(i).getY());
             //   player.setAngle(GameWorld.stationArrayList.get(i).getAngle());
                if(player.getLaunched()){
                    player.setDocked(false);
                    GameWorld.stationArrayList.remove(i);
                    player.setLaunched(false);
                }
            }

        }
      }



/**
    public void playerVSfloatingAsteroid(Player player, Asteroids asteroids){
        Bullet player2bullet;
        Rectangle tank1Box = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        Rectangle tank2Box = new Rectangle(tank2.getX(), tank2.getY(), tank2.getWidth(), tank2.getHeight());

        for (int i = 0; i < tank2.getMyBulletList().size(); i++){

            player2bullet = tank2.getMyBulletList().get(i);
            Rectangle bulletBox = new Rectangle(player2bullet.getX(), player2bullet.getY(),player2bullet.getWidth(), player2bullet.getHeight());

            if (bulletBox.intersects(tank1Box))
            {

                tank2.getMyBulletList().remove(i);
                try {
                    Explosionimage = ImageIO.read(new File("Resources/Explosion_large.png"));
                    Explosion epl = new Explosion(player2bullet.getX(), player2bullet.getY(),Explosionimage);
                    GameWorld.explosionArrayList.add(epl);
                    GameWorld.t1.hit(true, player2bullet.getDamage());

                }
                catch (IOException e){

                }

            }
        }
    }
 **/
    /**
    public void player2VSbullet(Tank tank1, Tank tank2){
        Bullet player1bullet;


        Rectangle tank2Box = new Rectangle(tank2.getX(), tank2.getY(), tank2.getWidth(), tank2.getHeight());

        //Rectangle bulletBox = new Rectangle(player1bullet.getX(), player1bullet.getY(),player1bullet.getWidth(), player1bullet.getHeight());

        for (int i = 0; i < tank1.getMyBulletList().size(); i++){

            player1bullet = tank1.getMyBulletList().get(i);
            Rectangle bulletBox = new Rectangle(player1bullet.getX(), player1bullet.getY(),player1bullet.getWidth(), player1bullet.getHeight());

            if (bulletBox.intersects(tank2Box))
            {

                tank1.getMyBulletList().remove(i);
                try {
                    Explosionimage = ImageIO.read(new File("Resources/Explosion_large.png"));
                    Explosion epl = new Explosion(player1bullet.getX(), player1bullet.getY(),Explosionimage);
                    GameWorld.explosionArrayList.add(epl);
                    GameWorld.t2.hit(true, player1bullet.getDamage());
                }
                catch (IOException e){

                }

            }
        }
    }

    public void player1VSpowerbullet(Tank tank1, Tank tank2){
        Bullet playerbullet;


        Rectangle tank2Box = new Rectangle(tank2.getX(), tank2.getY(), tank2.getWidth(), tank2.getHeight());

        //Rectangle bulletBox = new Rectangle(player1bullet.getX(), player1bullet.getY(),player1bullet.getWidth(), player1bullet.getHeight());

        for (int i = 0; i < tank1.getPowerList().size(); i++){

            playerbullet = tank1.getPowerList().get(i);
            Rectangle bulletBox = new Rectangle(playerbullet.getX(), playerbullet.getY(),playerbullet.getWidth(), playerbullet.getHeight());

            if (bulletBox.intersects(tank2Box))
            {

                tank1.getPowerList().remove(i);
                try {
                    Explosionimage = ImageIO.read(new File("Resources/Explosion_small.png"));
                    Explosion epl = new Explosion(playerbullet.getX(), playerbullet.getY(),Explosionimage);
                    GameWorld.explosionArrayList.add(epl);
                    GameWorld.t2.hit(true, playerbullet.getDamage());
                }
                catch (IOException e){

                }

            }
        }
    }

    public void player2VSpowerbullet(Tank tank1, Tank tank2){
        Bullet playerbullet;


        Rectangle tank2Box = new Rectangle(tank2.getX(), tank2.getY(), tank2.getWidth(), tank2.getHeight());

        //Rectangle bulletBox = new Rectangle(player1bullet.getX(), player1bullet.getY(),player1bullet.getWidth(), player1bullet.getHeight());

        for (int i = 0; i < tank1.getPowerList().size(); i++){

            playerbullet = tank1.getPowerList().get(i);
            Rectangle bulletBox = new Rectangle(playerbullet.getX(), playerbullet.getY(),playerbullet.getWidth(), playerbullet.getHeight());

            if (bulletBox.intersects(tank2Box))
            {

                tank1.getPowerList().remove(i);
                try {
                    Explosionimage = ImageIO.read(new File("Resources/Explosion_small.png"));
                    Explosion epl = new Explosion(playerbullet.getX(), playerbullet.getY(),Explosionimage);
                    GameWorld.explosionArrayList.add(epl);
                    GameWorld.t1.hit(true, playerbullet.getDamage());
                }
                catch (IOException e){

                }

            }
        }
    }




    public void playerVSborderWall(Tank tank1){
        Rectangle tank1Box = new Rectangle(tank1.getX(), tank1.getY(), tank1.getWidth(), tank1.getHeight());

        for (int i = 0; i < GameWorld.wallsArrayList.size(); i++){
            MapWalls wall = GameWorld.wallsArrayList.get(i);
            int leftwallxpos = ((MapWalls) wall).getX();
            int topwallypos = wall.getY()+1;
            int bottomwallypos = wall.getY()+wall.getHeight()+1;
            int rightwallxpos = wall.getX() + wall.getWidth()+1;
            Rectangle WallBox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
            if (tank1Box.intersects(WallBox)) {
                if (tank1.getX() + tank1.getWidth() == leftwallxpos+2) {
                    tank1.Collision(true, false, false, false, leftwallxpos);
                    //System.out.println("Collision");
                }
                if (tank1.getY() + tank1.getHeight() == topwallypos+2) {
                    tank1.Collision(false, true, false, false, topwallypos);
                    //System.out.println("Collision");
                }
                if (tank1.getY() == bottomwallypos-2) {
                    tank1.Collision(false, false, true, false, bottomwallypos);
                    //System.out.println("Collision");
                }
                if (tank1.getX() == rightwallxpos-2) {
                    tank1.Collision(false, false, false, true, rightwallxpos);
                    //System.out.println("Collision");
                }
                //System.out.println("Collision");
            }
        }
    }

    public void playerVSgameWall(Tank tank1){
        Rectangle tank1Box = new Rectangle(tank1.getX(), tank1.getY(), tank1.getWidth(), tank1.getHeight());

        for (int i = 0; i < GameWorld.wallsArrayList2.size(); i++){
            MapWalls wall = GameWorld.wallsArrayList2.get(i);
            int leftwallxpos = wall.getX();
            int topwallypos = wall.getY()+1;
            int bottomwallypos = wall.getY()+wall.getHeight()+1;
            int rightwallxpos = wall.getX() + wall.getWidth()+1;
            Rectangle WallBox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
            if (tank1Box.intersects(WallBox)) {
                if (tank1.getX() + tank1.getWidth() == leftwallxpos+2) {
                    tank1.Collision(true, false, false, false, leftwallxpos);
                    //System.out.println("Collision");
                }
                if (tank1.getY() + tank1.getHeight() == topwallypos+2) {
                    tank1.Collision(false, true, false, false, topwallypos);
                    //System.out.println("Collision");
                }
                if (tank1.getY() == bottomwallypos-2) {
                    tank1.Collision(false, false, true, false, bottomwallypos);
                    //System.out.println("Collision");
                }
                if (tank1.getX() == rightwallxpos-2) {
                    tank1.Collision(false, false, false, true, rightwallxpos);
                    //System.out.println("Collision");
                }
                //System.out.println("Collision");
            }
        }
    }


    public void bulletVSgamewall(Bullet bullet, Tank tank, int ind){
        Rectangle bulletbox = new Rectangle(bullet.getX()+8,bullet.getY()+8,1,1);

        for (int i = 0; i < GameWorld.wallsArrayList2.size(); i++){
            MapWalls wall = GameWorld.wallsArrayList2.get(i);
            Rectangle WallBox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth()-1, wall.getHeight()-1);
            if (bulletbox.intersects(WallBox)) {
                try {
                    Explosionimage = ImageIO.read(new File("Resources/Explosion_small.png"));
                    Explosion epl = new Explosion( bullet.getX(), bullet.getY(),Explosionimage);
                    GameWorld.explosionArrayList.add(epl);
                    GameWorld.wallsArrayList2.remove(i);
                    tank.getMyBulletList().remove(ind);
                }
                catch (IOException e){

                }
            }
        }
    }

    public void powerbulletVSgamewall(Bullet bullet, Tank tank, int ind){
        Rectangle bulletbox = new Rectangle(bullet.getX()+8,bullet.getY()+8,1,1);

        for (int i = 0; i < GameWorld.wallsArrayList2.size(); i++){
            MapWalls wall = GameWorld.wallsArrayList2.get(i);
            Rectangle WallBox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth()-1, wall.getHeight()-1);
            if (bulletbox.intersects(WallBox)) {
                try {
                    Explosionimage = ImageIO.read(new File("Resources/Explosion_small.png"));
                    Explosion epl = new Explosion( bullet.getX(), bullet.getY(),Explosionimage);
                    GameWorld.explosionArrayList.add(epl);
                    GameWorld.wallsArrayList2.remove(i);
                    tank.getPowerList().remove(ind);
                }
                catch (IOException e){

                }
            }
        }
    }

//    public void bulletVSwall(Bullet bullet, Tank tank, int ind){
//        Rectangle bulletbox = new Rectangle(bullet.getX()+8,bullet.getY()+8,1,1);
//
//        for (int i = 0; i < GameWorld.wallArrayList.size(); i++){
//            Wall wall = GameWorld.wallArrayList.get(i);
//            Rectangle WallBox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
//            if (bulletbox.intersects(WallBox)) {
//                try {
//                    Explosionimage = ImageIO.read(new File("Resources/Explosion_small.png"));
//                    Explosion epl = new Explosion(bullet.getX(), bullet.getY(), Explosionimage);
//                    GameWorld.explosionArrayList.add(epl);
//                    tank.getBulletlist().remove(ind);
//                }
//                catch (IOException e){
//
//                }
//            }
//        }
//    }

    public void bulletVSborderwall(Bullet bullet, Tank tank, int ind ){
        Rectangle bulletbox = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());

        int i = 0;
        while(i < GameWorld.wallsArrayList.size()){
            MapWalls wall = GameWorld.wallsArrayList.get(i);
            Rectangle wallbox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());

            if (bulletbox.intersects(wallbox)){
                try {
                    Explosionimage = ImageIO.read(new File("Resources/Explosion_small.png"));
                    Explosion epl = new Explosion( bullet.getX(), bullet.getY(),Explosionimage);
                    GameWorld.explosionArrayList.add(epl);
                    tank.getMyBulletList().remove(ind);
                }
                catch (IOException e){

                }
                break;
            }
            i++;
        }
    }

//    public void bulletVSwall2(Bullet bullet, Tank tank, int ind ){
//        Rectangle bulletbox = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
//
//        int i = 0;
//        while(i < GameWorld.wallArrayList.size()){
//            Wall wall = GameWorld.wallArrayList.get(i);
//            Rectangle wallbox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
//
//            if (bulletbox.intersects(wallbox)){
//                try {
//                    Explosionimage = ImageIO.read(new File("Resources/Explosion_small.png"));
//                    Explosion epl = new Explosion(bullet.getX(), bullet.getY(), Explosionimage);
//                    GameWorld.explosionArrayList.add(epl);
//                    tank.getBulletlist().remove(ind);
//                }
//                catch (IOException e){
//
//                }
//                break;
//            }
//            i++;
//        }
//    }

//    public void powerUpVSwall(Bullet bullet, Tank tank, int ind){
//        Rectangle bulletbox = new Rectangle(bullet.getX(),bullet.getY(),1,1);
//
//        for (int i = 0; i < GameWorld.wallArrayList.size(); i++){
//            Wall wall = GameWorld.wallArrayList.get(i);
//            Rectangle WallBox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth()-1, wall.getHeight()-1);
//            if (bulletbox.intersects(WallBox)) {
//                try {
//                    Explosionimage = ImageIO.read(new File("Resources/Explosion_large.png"));
//                    Explosion epl = new Explosion( bullet.getX(), bullet.getY(),Explosionimage);
//                    GameWorld.explosionArrayList.add(epl);
//                    tank.getPowerList().remove(ind);
//                }
//                catch (IOException e){
//
//                }
//            }
//        }
//    }

    public void powerUpVSwall2(Bullet bullet, Tank tank, int ind){
        Rectangle bulletbox = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());

        int i = 0;
        while(i < GameWorld.wallsArrayList.size()){
            MapWalls wall = GameWorld.wallsArrayList.get(i);
            Rectangle wallbox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());

            if (bulletbox.intersects(wallbox)){
                try {
                    Explosionimage = ImageIO.read(new File("Resources/Explosion_small.png"));
                    Explosion epl = new Explosion(bullet.getX(), bullet.getY(), Explosionimage);
                    GameWorld.explosionArrayList.add(epl);
                    tank.getPowerList().remove(ind);
                }
                catch (IOException e){

                }
                break;
            }
            i++;
        }
    }


//    public void detectHealth(Tank tank1) {
//        Rectangle tank1Box = new Rectangle(tank1.getX(), tank1.getY(), tank1.getWidth(), tank1.getHeight());
//
//        for (int i = 0; i < GameWorld.healthArrayList.size(); i++) {
//
//            Wall wall = GameWorld.healthArrayList.get(i);
//            int leftwallxpos = wall.getX();
//            int topwallypos = wall.getY() + 2;
//            int bottomwallypos = wall.getY() + wall.getHeight();
//            int rightwallxpos = wall.getX() + wall.getWidth() + 1;
//            Rectangle WallBox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
//
//            if (tank1Box.intersects(WallBox)) {
//
//                if (tank1.getX() + tank1.getWidth() == leftwallxpos ) {
//                    tank1.Collision(true, false, false, false, leftwallxpos);
//                    GameWorld.healthArrayList.remove(i);
//                    //  i++;
//                }
//
//                if (tank1.getY() + tank1.getHeight() == topwallypos ) {
//                    tank1.Collision(false, true, false, false, topwallypos);
//                    GameWorld.healthArrayList.remove(i);
//                    //  i++;
//                }
//
//                if (tank1.getY() == bottomwallypos - 2 ) {
//                    tank1.Collision(false, false, true, false, bottomwallypos);
//                    GameWorld.healthArrayList.remove(i);
//                    //     i++;
//                }
//
//                if (tank1.getX() == rightwallxpos - 2) {
//                    tank1.Collision(false, false, false, true, rightwallxpos);
//                    GameWorld.healthArrayList.remove(i);
//                    //  i++;
//                }
//                //GameWorld.t1.getHealth(true);
//
//            }
//        }
//    }


    public void playerVSpowerUp(Tank tank){

        Rectangle tank1Box = new Rectangle(tank.getX(), tank.getY(), tank.getWidth(), tank.getHeight());

        for (int i = 0; i < GameWorld.powerUpArrayList.size(); i++){
            MapWalls wall = GameWorld.powerUpArrayList.get(i);

            Rectangle WallBox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
            if (tank1Box.intersects(WallBox)) {
                tank.setPower(true);

                GameWorld.powerUpArrayList.remove(i);

            }
        }
    }
    public void detectHealth(Tank tank1, Tank tank2) {
        Rectangle tank1Box = new Rectangle(tank1.getX(), tank1.getY(), tank1.getWidth(), tank1.getHeight());
        Rectangle tank2Box = new Rectangle(tank2.getX(), tank2.getY(), tank2.getWidth(), tank2.getHeight());

        for (int i = 0; i < GameWorld.healthArrayList.size(); i++) {

            MapWalls wall = GameWorld.healthArrayList.get(i);
            int leftwallxpos = wall.getX();
            int topwallypos = wall.getY() + 2;
            int bottomwallypos = wall.getY() + wall.getHeight();
            int rightwallxpos = wall.getX() + wall.getWidth();
            Rectangle WallBox = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());

            if (tank1Box.intersects(WallBox)) {

                if (tank1.getX() + tank1.getWidth() == leftwallxpos ) {
                    tank1.Collision(true, false, false, false, leftwallxpos);
                    GameWorld.healthArrayList.remove(i);
                    //  i++;
                }

                if (tank1.getY() + tank1.getHeight() == topwallypos ) {
                    tank1.Collision(false, true, false, false, topwallypos);
                    GameWorld.healthArrayList.remove(i);
                    //  i++;
                }

                if (tank1.getY() == bottomwallypos - 2 ) {
                    tank1.Collision(false, false, true, false, bottomwallypos);
                    GameWorld.healthArrayList.remove(i);
                    //     i++;
                }

                if (tank1.getX() == rightwallxpos - 2) {
                    tank1.Collision(false, false, false, true, rightwallxpos);
                    GameWorld.healthArrayList.remove(i);
                    //  i++;
                }
                GameWorld.t1.getHealth(true);


            }else if(tank2Box.intersects(WallBox)){
                if (tank2.getX() + tank2.getWidth() == leftwallxpos ) {
                    tank2.Collision(true, false, false, false, leftwallxpos);
                    GameWorld.healthArrayList.remove(i);
                    //  i++;
                }

                if (tank2.getY() + tank2.getHeight() == topwallypos ) {
                    tank2.Collision(false, true, false, false, topwallypos);
                    GameWorld.healthArrayList.remove(i);
                    //  i++;
                }

                if (tank2.getY() == bottomwallypos - 2 ) {
                    tank2.Collision(false, false, true, false, bottomwallypos);
                    GameWorld.healthArrayList.remove(i);
                    //     i++;
                }

                if (tank2.getX() == rightwallxpos - 2) {
                    tank2.Collision(false, false, false, true, rightwallxpos);
                    GameWorld.healthArrayList.remove(i);
                    //  i++;
                }

                GameWorld.t2.getHealth(true);
            }

        }

    }
     **/
}