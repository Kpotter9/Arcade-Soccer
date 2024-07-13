import java.awt.*;
import java.awt.event.*;

import java.util.*;
import javax.swing.*;
public class GamePanel extends JPanel implements Runnable{
    
    static final int GAME_WIDTH=1000;
    static final int GAME_HEIGHT=(int)(GAME_WIDTH*(0.555));
    static final Dimension SCREEN_SIZE=new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER=20;
    static final int PADDLE_WIDTH=25;
    static final int PADDLE_HEIGHT=100;
    static final int GOAL_WIDTH=50;
    static final int GOAL_HEIGHT=200;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    Goal goal1;
    Goal goal2;


    GamePanel() {
        newPaddles();
        newBall();
        goal1=new Goal(0,GAME_HEIGHT-GOAL_HEIGHT,GOAL_WIDTH,GOAL_HEIGHT);
        goal2=new Goal(GAME_WIDTH-GOAL_WIDTH,GAME_HEIGHT-GOAL_HEIGHT,GOAL_WIDTH,GOAL_HEIGHT);
        
        score=new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread=new Thread(this);
        gameThread.start();
        
    }


    public void newBall(){
        
        ball=new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-BALL_DIAMETER/2,BALL_DIAMETER,BALL_DIAMETER);

    }

    public void newPaddles(){
        paddle1=new Paddle(GAME_WIDTH/4,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2=new Paddle(((GAME_WIDTH/4)*3)+PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);


    }

    public void paint(Graphics g){
        image=createImage(getWidth(),getHeight());
        graphics=image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

        
    }
    public void draw(Graphics g){
        
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        goal1.draw(g);
        goal2.draw(g);

    }

    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();

    }
    public void checkCollision(){
        
        if(ball.y>=(GAME_HEIGHT-BALL_DIAMETER)){
            ball.y=GAME_HEIGHT-BALL_DIAMETER;
            
            ball.setYDirection(-(int)((Math.round(ball.currentGravity/2)+ball.yVelocity/2))+1);
            ball.drag();
            ball.resetGravity();
            
        }else{
            ball.applyGravity();

        }

        if(ball.intersects(paddle1)){
            if(paddle1.xVelocity!=0){

                if((ball.xVelocity<0&&paddle1.xVelocity<0)){
                    ball.setXDirection((-ball.xVelocity)+paddle1.xVelocity);
            }
            else{
            ball.setXDirection((int)(paddle1.xVelocity*2 ));}}
            else {ball.setXDirection(-ball.xVelocity);}
            
            if(paddle1.kicking){
                ball.setYDirection(-5);
            }
            
        }
        if(ball.intersects(paddle2)){
           
            
            if(paddle2.xVelocity!=0){
                if((ball.xVelocity>0&&paddle2.xVelocity>0)){
                    ball.setXDirection((-ball.xVelocity)+paddle2.xVelocity);
                }
                else{
                        
    
                
                ball.setXDirection((int)(paddle2.xVelocity*2 ));}}
                else {ball.setXDirection(-ball.xVelocity);}
            
            if(paddle2.kicking){
                ball.setYDirection(-5);
                
            }
           
        }
        
        
        
        if(paddle1.y<=0){
            paddle1.y=0;
        }
        if(paddle1.y>=(GAME_HEIGHT-PADDLE_HEIGHT)){
            paddle1.onGround();
            paddle1.resetGravity();
            paddle1.y=GAME_HEIGHT-PADDLE_HEIGHT;
        }

        if(paddle2.y<=0){
            paddle2.y=0;
        }
        if(paddle2.y>=(GAME_HEIGHT-PADDLE_HEIGHT)){
            paddle2.onGround();

            paddle2.resetGravity();

            paddle2.y=GAME_HEIGHT-PADDLE_HEIGHT;
            
        }
        if(paddle1.x<=0){
         paddle1.x=0;
        }
        if(paddle2.x>=(GAME_WIDTH-PADDLE_WIDTH)){
            paddle2.x=GAME_WIDTH-PADDLE_WIDTH;

        }
        if(paddle2.x<=0){
            paddle2.x=0;
           }
           if(paddle1.x>=(GAME_WIDTH-PADDLE_WIDTH)){
               paddle1.x=GAME_WIDTH-PADDLE_WIDTH;
   
           }
        if(paddle1.intersects(paddle2)){
            paddle1.setxDirection(0);
            paddle1.x-=5;
            paddle2.x+=5;
            paddle2.setxDirection(0);
            
        }
        
        if(ball.x<=0){
            ball.setXDirection(-ball.xVelocity);

        }
            if(goal2.intersects(ball)){    
            score.player2++;
            newPaddles();
            newBall();
        }

    
        if(ball.x>=(GAME_WIDTH-BALL_DIAMETER)){
            ball.setXDirection(-ball.xVelocity);
        }
        if(goal1.intersects(ball)){
            score.player1++;
            newPaddles();
            newBall();
        }

        
    }

    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks=60.0;
        double ns=1000000000/amountOfTicks;
        double delta=0;
        while(true){
            long now=System.nanoTime();
            delta+= (now-lastTime)/ns;
            lastTime=now;
            
            if(delta>=1){
                
                
                move();
                
                checkCollision();
                repaint();
                delta--;
                


            }
        }

    }

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }


}