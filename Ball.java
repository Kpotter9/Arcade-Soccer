import java.awt.*;

import java.util.*;
public class Ball extends Rectangle{
    Random random;
    int xVelocity;
    int yVelocity;
    final double gravity=0.05;
    double drag=0;
    double currentGravity=0;
    

    
    Ball(int x,int y,int width,int height){
        super(x,y,width,height);
        
        
    }
    public void resetGravity(){
        currentGravity=0;
    }
    public void applyGravity(){
        currentGravity+=gravity;
    }
    public void drag(){
       drag+=.02;
       if(drag>1){
            drag=0;
            if(xVelocity<0){
                xVelocity++;
            }else if(xVelocity>0){
            xVelocity--;}
       }
        
    }
    public void move(){
        
        x+=xVelocity;
        y+=yVelocity+currentGravity;
    }
    public void setXDirection(int randomXDirection){
        xVelocity=randomXDirection;
    }

    public void setYDirection(int randomYDirection){
        yVelocity=randomYDirection;

    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
        
    }
}

