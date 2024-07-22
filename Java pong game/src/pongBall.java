import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class pongBall extends  Rectangle{
    Random random;
    int xVelocity;
    int yVelocity;
    int initalSpeed = 2;

    pongBall(int x, int y, int width, int height){

            super(x,y,width,height);
            random = new Random();
            int randomXdIRECTION = random.nextInt(2);
            if(randomXdIRECTION == 0)
                randomXdIRECTION--;
            setXDirection(randomXdIRECTION*initalSpeed);

        int randomYdIRECTION = random.nextInt(2);
        if(randomYdIRECTION == 0)
            randomYdIRECTION--;
        setYDirection(randomYdIRECTION*initalSpeed);

    }

    public void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;

    }

    public void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;
    }

    public void move(){
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x,y,height,width);
    }
}
