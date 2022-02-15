import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import svu.csc213.Dialog;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {

    /*
    1) we don't have lives

    Add lives through

    2)All of the bricks only take 1 hit

    make each brick take 2 hits to break by adjusting handleCollisons method

    3) what happens when i run out of lives

    a message pops up saying you have lost and resets the game

    4)How do i know how many lives i have left

    make text displaying lives on top left of screen

    5) how do i know how many bricks i have broken

    add a counter counting how many bricks have been broken

    6)How could i make some bricks contain powerups
    7) How could i make this game have more than one level
     */

    private Ball ball;
    private Paddle paddle;
    private int numBricksInRow;
    private Color[] rowColors = {Color.black, Color.BLACK, Color.magenta, Color.MAGENTA, Color.blue, Color.BLUE, Color.cyan, Color.CYAN, Color.white, Color.WHITE};
    private int lives;
    private GLabel livesLabel;
    @Override
    public void init(){

        numBricksInRow = (int) (getWidth()/ (Brick.WIDTH + 5.0));

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < numBricksInRow; col++) {
                Brick brick = new Brick(10 + col * (Brick.WIDTH + 5), 4 * Brick.HEIGHT + row * (Brick.HEIGHT + 5), rowColors[row], row);
                add(brick);
            }
        }

        ball = new Ball(getWidth()/2, 350, 10, this.getGCanvas());
        paddle = new Paddle(230, 430, 50, 10);
        add(ball);
        add(paddle);

        lives = 3;

        livesLabel = new GLabel("lives:" +  lives);
        add(livesLabel, getWidth()/4 , getHeight()/9);


    }

    @Override
    public void run(){

        addMouseListeners();
        waitForClick();
        gameLoop();

    }

    @Override
    public void mouseMoved(MouseEvent me){
        //Make sure that the paddle doesn't go offscreen
        if((me.getX() < getWidth() - paddle.getWidth())&&(me.getX() > paddle.getWidth()/2)){
            paddle.setLocation(me.getX() - paddle.getWidth()/2, paddle.getY());
        }

    }

    private void gameLoop(){

        while (true){
            //move the ball
            ball.handleMove();

            if (ball.lost){
                handleLoss();
            }

            //handle collisions
            handleCollisions();

            pause(5);
        }

    }



    private void handleCollisions(){
        //obj can store what we hit
        GObject obj = null;

        //check to see if the ball is about to his something
        if(obj == null){
            //check he top right corner
            obj = this.getElementAt(ball.getX() + ball.getWidth(), ball.getY());
        }

        if(obj == null) {
            //check the top left corner
            obj = this.getElementAt(ball.getX(), ball.getY());
        }

        if(obj == null){
            //check bottom left
            obj = this.getElementAt(ball.getX(), ball.getY() + ball.getHeight());
        }

        if(obj == null){
            //check bottom right
            obj = this.getElementAt(ball.getX() + ball.getWidth(),  ball.getY() + ball.getHeight());
        }

        //lets see if we hit something
        if(obj != null){
            //lets see what we hit
            if(obj instanceof Paddle){
                if(ball.getX() < (paddle.getX() + (paddle.getWidth() * .2))){
                    // did i hit the left side of paddle
                    ball.bounceLeft();
                } else if(ball.getX() > (paddle.getX() + (paddle.getWidth() * .8))){
                    // did i hit the righ side of paddle
                    ball.bounceRight();
                }else{
                    // did i hit the middle of paddle
                    ball.bounce();
                }
            }

            if (obj instanceof Brick){

                //bounce the ball
                ball.bounce();
                //destroy brick
                this.remove(obj);

            }

        }



        //if by the nd of the method obj is still null, we hit nothing

    }

    private void handleLoss(){



        ball.lost = false;

        if(ball.lost == false){

         lives -= 1;
         livesLabel.setLabel(String.valueOf("lives:" + lives));

            reset();
        }

        if(lives == 0){

            Dialog.showMessage("You Lost");

        }
        
    }
    private void reset(){

        ball.setLocation(getWidth()/2, 350);
        paddle.setLocation(230,430);
        waitForClick();

    }

    public static void main(String[] args) {
        new Breakout().start();
    }

}
