import java.awt.*;

public class Goal extends Rectangle{
    int id;
    Goal(int x, int y, int width, int height){
        super(x,y,width,height);
    }
    public void draw(Graphics g){
        if(id==1){
            g.setColor(Color.GRAY);
        }else{
            g.setColor(Color.GRAY);
        }
        g.fillRect(x, y, width, height);
    }

    
}
