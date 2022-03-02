import acm.graphics.GRect;
import java.awt.Color;
public class Brick extends GRect {

    public static final int WIDTH = 44;
    public static final int HEIGHT = 20;

    // give the brick hitpoints
    public int hitPoint;

    public Brick(double x, double y, Color color, int row){
        super(x,y,WIDTH,HEIGHT);
        this.setFillColor(color);
        this.setFilled(true);


        // how many hitpoints?
        if(row == 1 || row == 2){

            hitPoint = 5;

        }
        if(row == 3 || row == 4){

            hitPoint = 4;

        }
        if(row == 5 || row == 6){

            hitPoint = 3;

        }
        if(row == 7 || row == 8){

            hitPoint = 2;

        }
        if(row == 9 || row == 10){

            hitPoint = 1;

        }
    }

    // make a method for hitting the brick
    public void getHit(){

        hitPoint--;

    }

}
