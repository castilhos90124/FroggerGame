
package pacoteFrogger;

import jplay.Window;
import static pacoteFrogger.Game.*;


public class SpecialCar extends Vehicle{
    
    public SpecialCar(){
        super("src/resources/car22.png",NUM_VEHICLE_FRAMES);
        this.setSpeed(INITIAL_SPECIALCAR_SPEED);
        this.setTotalDuration(TOTAL_FRAME_DURATION);
    }
    public SpecialCar(int x,int y) {
        super("src/resources/car22.png",NUM_VEHICLE_FRAMES);
        this.setX(x);
        this.setY(y);
        this.setSpeed(INITIAL_SPECIALCAR_SPEED);
        this.setTotalDuration(TOTAL_FRAME_DURATION);
    }

   // @Override
    public void move(double frog_y,int pista_y){
        
        if(((this.getY() + this.height)< ( pista_y + DISTANCE_BETWEEN_ROADS))&& (frog_y >= this.getY())){
           if((frog_y - SURPRISE_DISTANCE)< this.getY()){
                this.setY(this.getY()+CHANGE_ROAD_SPEED) ;
            } 
        }
        else{
            if(frog_y < this.getY())
                if(this.getY() > pista_y)
                    this.setY(this.getY()-CHANGE_ROAD_SPEED);
        }
            
            
        
        
        
       
            
        
        if(this.getX()  + this.speed >(Window.getInstance().getWidth())){
            this.setX(VEHICLE_SPAWN_X);
        }
        else
            this.setX(this.getX()+this.getSpeed());
    }       
    
}


