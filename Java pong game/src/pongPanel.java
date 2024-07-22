import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class pongPanel extends JPanel implements Runnable{

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    pongPaddle paddle1;
    pongPaddle paddle2;
    pongBall ball;
    pongScore score;


    pongPanel() {
        newPaddle();
        newBall();
        score = new pongScore(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();


    }

    public void newBall(){
        //random = new Random();
        //Corrodiants of ball
        ball = new pongBall((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-(BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER);
    }

    public void newPaddle(){
        paddle1 = new pongPaddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new pongPaddle(GAME_WIDTH - PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }

    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0, this);


    }
//extends from paddle class to give me my red and blue panels
    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.drawScore(g);



    }
//Allows my paddels to move nice and smooth and ball also
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision(){


        //tHIS SECTION WILLL BOUNCE THE BALL FROM THE EDGES
        if(ball.y<=0){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER){
            ball.setYDirection(-ball.yVelocity);
        }

        //This code bounces the ball of the panels
        if(ball.intersects(paddle1)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // this is optional for more difficulty
            if(ball.yVelocity >0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if(ball.intersects(paddle2)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // this is optional for more difficulty
            if(ball.yVelocity >0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //Stopping paddles from moving out the screen
        if(paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y >=(GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y >=(GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;

        //This will give a player a point and create new paddles
        if(ball.x <=0){
            score.player2++;
            newPaddle();
            newBall();

        }
        if(ball.x >=GAME_WIDTH - BALL_DIAMETER){
            score.player1++;
            newPaddle();
            newBall();

        }



    }

    public void run(){
        //Game Loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;

            }
        }

    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
