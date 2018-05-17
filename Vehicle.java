
package pacoteFrogger;

import static pacoteFrogger.Game.*;
import jplay.*;

public abstract class Vehicle extends Sprite{
    
    protected double speed;
    
    

    public Vehicle(String fileName,int numFrames) {
        super(fileName,numFrames);
    }
    
    public Vehicle(int x,int y,String fileLocation) {
        super(fileLocation,NUM_VEHICLE_FRAMES);
        this.setX(x);
        this.setY(y);
        this.setTotalDuration(TOTAL_FRAME_DURATION);
    }
     
    
    
    public void move(){
        if(this.x  + this.speed >(Window.getInstance().getWidth())){
            this.setX(VEHICLE_SPAWN_X);
        }
        else
            this.setX(this.getX()+this.getSpeed());
    }
    public void accelerate(){
        this.setSpeed(this.getSpeed()+ACCELERATE_SPEED) ;
    }
    
    
    
    //Getters e Setters
    
    public double getSpeed(){
        return this.speed;
    } 
    public void setSpeed(double speed){
        this.speed=speed;
    }
    
    
    
}