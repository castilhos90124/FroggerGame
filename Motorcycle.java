
package pacoteFrogger;

import static pacoteFrogger.Game.*;

public class Motorcycle extends Vehicle {
    
    
    public Motorcycle(){
        super("src/resources/moto01.png",NUM_VEHICLE_FRAMES);
        this.setSpeed(INITIAL_MOTO_SPEED);
        this.setTotalDuration(TOTAL_FRAME_DURATION);
    }
    
    public Motorcycle(int x,int y) {
        super("src/resources/moto01.png",NUM_VEHICLE_FRAMES);
        this.setX(x);
        this.setY(y);
        this.setSpeed(INITIAL_MOTO_SPEED);
        this.setTotalDuration(TOTAL_FRAME_DURATION);
    }
         
     
    
}
