package pacoteFrogger;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Vector;
import jplay.*;
import static pacoteFrogger.Game.*;


public class Frog extends Sprite {
   
    
    private int direction; 
    private boolean moving = false;

    public Frog(double x, double y) {
        super("src/resources/frog.png",NUM_FROG_FRAMES);
        this.setX(x);
        this.setY(y);
        this.setTotalDuration(TOTAL_FRAME_DURATION);
        
    }
    
    
    public void avoidWalls(Scene scene){
        Point min = new Point((int)this.getX(),(int)this.getY());
        Point max = new Point((int)this.getX()+ this.width,(int)this.getY()+ this.height);
        
        Vector <?>tiles = scene.getTilesFromRect(min, max);
        
        for(int i=0;i<tiles.size();i++){
            TileInfo tile = (TileInfo) tiles.elementAt(i);
        
            if(this.tileColision(this, tile)){  //trata colisao com tiles "especiais"
                if(this.verticalColision(this, tile)){
                    if(this.direction==UP){
                        this.setY(tile.getY() + tile.height);
                        
                    }
                    else if(this.direction==DOWN){
                        this.setY( tile.getY() - this.height);
                    }
                }
                if(this.horizontalColision(this, tile)){
                    if(this.direction==RIGHT){
                        this.setX(tile.getX() - this.width);
                    }
                    else{
                        this.setX(tile.getX() - this.width);
                    }
                }
            }
        }
                  //trata colisao com a janela
                if(this.direction==UP && (this.getY() + this.height - JUMP_Y_SPEED) < 0){
                    this.setY(0);
                }
                    
                if(this.direction==LEFT && (this.getX() + this.height - JUMP_X_SPEED) < 0){
                    this.setX(0);
                }
                    
                if(this.direction==RIGHT && (this.x  + JUMP_X_SPEED >(Window.getInstance().getWidth()))){
                    this.setX((Window.getInstance().getWidth()) - this.width);
                }
                if((this.direction==DOWN) &&(this.y + JUMP_Y_SPEED >(Window.getInstance().getHeight()))){
                    this.setY((Window.getInstance().getHeight()) - this.height);
                }
        this.direction=0;
                    
    }
     public boolean vehicleColision(Vehicle vehicle){
        Point minFrog = new Point((int)this.getX(),(int)((this.getY() + this.getY() + this.height)/2));
        Point maxFrog = new Point((int)this.getX()+ this.width,(int)this.getY()+ this.height);
        
        Point minVehicle = new Point((int) vehicle.getX(), (int) ((vehicle.getY() + vehicle.getY() + vehicle.height)/2));
        Point maxVehicle = new Point((int) (vehicle.getX() + vehicle.width), (int) (vehicle.getY() + vehicle.height));

        return (Collision.collided(minFrog, maxFrog, minVehicle, maxVehicle));
        
       
            
        
    }    
        
    
    
    
    public void move(Keyboard keyboard,Window window){
        
        if (keyboard.keyDown(Keyboard.LEFT_KEY))
        {
            this.setX(this.getX()-JUMP_X_SPEED);
            this.setSequence(2,3);  //sequencia de frames
            this.direction=LEFT;
            moving=true;               
        }
        else if (keyboard.keyDown(Keyboard.RIGHT_KEY))
            {
                this.setX(this.getX()+JUMP_X_SPEED);
                this.setSequence(4,5);
                this.direction=RIGHT;
                
                moving=true;
            }
            else    if (keyboard.keyDown(Keyboard.UP_KEY))
                    {
                        this.setY(this.getY()-JUMP_Y_SPEED);;
                        this.setSequence(6,7);
                        this.direction=UP;
                        moving=true;
                    }

                    else    if (keyboard.keyDown(Keyboard.DOWN_KEY))
                            {
                              this.setY(this.getY()+JUMP_Y_SPEED);;
                               this.setSequence(0,1);
                               this.direction=DOWN;
                               moving=true;
                            }
        if(moving){
            //window.update();
            update();
            //window.update();
            moving = false;
           
        }    
       
    }
    
    private boolean tileColision(GameObject obj,TileInfo tile){
        if((tile.id == 0)&& obj.collided(tile)){
            return true;
        }
        else
            return false;
    }
    private boolean verticalColision(GameObject obj,GameObject vehicle){
        if(vehicle.getX() + vehicle.width <= obj.getX())
            return false;
        if(obj.getX() + obj.width <= vehicle.getX())
            return false;
        return true;
    }
    
    private boolean horizontalColision(GameObject obj,GameObject vehicle){
        if(vehicle.getY() + vehicle.height <= obj.getY())
            return false;
        if(obj.getY() + obj.height <= vehicle.getY())
            return false;
        return true;
    }
    public void toStartPosition(){
        this.setX(INITIAL_FROG_X);
        this.setY(INITIAL_FROG_Y);
    }
   
    
    
   
   

    
    
    
}
