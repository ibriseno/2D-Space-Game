package NewGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class PlayerControls implements KeyListener{
    private Player playerOne;
   // private Tank t2;

    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;

    /**
    private final int w; //up
    private final int s; //down
    private final int a; //left
    private final int d; //right
    private final int f; //f
    **/





    public PlayerControls(Player player, int up, int down, int left, int right, int shoot) {
        //Controls for tank 1
        this.playerOne = player;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;

/**
        //Controls for tank 2
       // this.t2 = player;
        this.w = up;
        this.s = down;
        this.a = left;
        this.d = right;
        this.f = shoot;
**/
    }


    @Override
    public void keyTyped(KeyEvent ke) {


    }

    @Override
    public void keyPressed(KeyEvent ke) {

        //Player 1 control toggle
        int keyPressed = ke.getKeyCode();
        if (keyPressed == up) {
            this.playerOne.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.playerOne.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.playerOne.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.playerOne.toggleRightPressed();
        }
        if(keyPressed == shoot){
            this.playerOne.toogleEnterPressed();
        }


/**

        //Player 2 control toggle
        if( keyPressed == w){
            this.t2.toggleUpPressed();
        }
        if (keyPressed == s) {
            this.t2.toggleDownPressed();
        }
        if (keyPressed == a) {
            this.t2.toggleLeftPressed();
        }
        if (keyPressed == d) {
            this.t2.toggleRightPressed();
        }
        if(keyPressed == f){
            this.t2.toogleEnterPressed();
        }
**/
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();

        //Player 1 control untoggle
        if (keyReleased  == up) {
            this.playerOne.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.playerOne.unToggleDownPressed();
        }
        if (keyReleased  == left) {
            this.playerOne.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.playerOne.unToggleRightPressed();
        }
        if(keyReleased == shoot){
            this.playerOne.unToggleEnterPressed();
        }



    /**

        //Player 2 control untoggle
        if (keyReleased  == w) {
            this.t2.unToggleUpPressed();
        }
        if (keyReleased == s) {
            this.t2.unToggleDownPressed();
        }
        if (keyReleased  == a) {
            this.t2.unToggleLeftPressed();
        }
        if (keyReleased  == d) {
            this.t2.unToggleRightPressed();
        }
        if(keyReleased == f){
            this.t2.unToggleEnterPressed();
        }
**/
    }
}
