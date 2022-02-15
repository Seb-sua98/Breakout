import acm.graphics.GCanvas;
import acm.graphics.GOval;

public class Ball extends GOval{

    private double deltaX = 1;
    private double deltaY = -1;
    private GCanvas screen;
    public boolean lost = false;


    public Ball(double x, double y, double size, GCanvas screen){
        super(x, y, size, size);
        setFilled(true);
        this.screen = screen;
    }

    public void handleMove(){



        //move the ball
        move(deltaX, -deltaY);
        //check to see if ball is too high
        if(getY() <= 0){
            //start moving down
            deltaY *= -1;
        }
        //check to see if ball is too low
        if(getY() >= screen.getHeight() - getHeight()){
            //Lose a life



            //set lost flag
            lost = true;
            deltaY = 1;
            deltaX = 1;
        }
        //check to see if ball hits left side
        if(getX() <= 0){
            //start moving right
            deltaX *= -1;
        }
        //check to see if ball hits right side
        if(getX() >= screen.getWidth() - getWidth()){
            //start moving left
            deltaX *= -1;
        }

    }

    public void bounce(){
        deltaY = -deltaY;
    }

    public void bounceLeft(){
        deltaY = -deltaY;
        deltaX = -Math.abs(deltaX);
    }

    public void bounceRight(){
        deltaY = -deltaY;
        deltaX = Math.abs(deltaX);
    }

}
