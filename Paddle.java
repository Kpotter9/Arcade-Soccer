import java.awt.*;
import java.awt.event.*;
public class Paddle extends Rectangle{

    int id;
    int yVelocity;
    int xVelocity;
    int  speed=4;
    final double gravity=0.05;
    double currentGravity=0;
    boolean isGrounded=false;
    boolean kicking=false;
    Paddle(int x,int y,int GAME_WIDTH,int GAME_HEIGHT, int id){
        super(x,y,GAME_WIDTH,GAME_HEIGHT);
        this.id=id;

    }

    public void keyPressed(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    if(isGrounded){
                    setYDirection(-speed);
                    move();
                isGrounded=false;}
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    
                    setYDirection(speed);
                    kicking=true;
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_D){
                    
                    setxDirection(speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_A){
                    
                    setxDirection(-speed);
                    move();
                }
                break;
                case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    if(isGrounded){
                    setYDirection(-speed);
                    move();
                    isGrounded=false;}
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(speed);
                    kicking=true;
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    
                    setxDirection(speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    
                    setxDirection(-speed);
                    move();
                }
        }

    }
    public void onGround(){
        isGrounded=true;
        resetGravity();
    }
    public void keyReleased(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(0);
                    kicking=false;
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_A){
                    setxDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_D){
                    setxDirection(0);
                    move();
                }
                break;
                case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(0);
                    kicking=false;
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    setxDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    setxDirection(0);
                    move();
                }
        }
        
    }
    public void setYDirection(int yDirection){
        yVelocity=yDirection;

    }
    public void setxDirection(int xDirection){
        xVelocity=xDirection;

    }
    public void move(){
        currentGravity+=gravity;
        y=(int)(y+yVelocity+currentGravity);
        x+=xVelocity;
    }
    public void resetGravity(){
        currentGravity=0;
    }
    public void draw(Graphics g){
        if(id==1){
            g.setColor(Color.blue);
        }else{
            g.setColor(Color.red);
        }
        g.fillRect(x, y, width, height);
    }
}
