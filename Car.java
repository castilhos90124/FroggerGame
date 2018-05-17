
package pacoteFrogger;

import static pacoteFrogger.Game.*;

public class Car extends Vehicle{
    
  

    
    public Car(){
        super("src/resources/car05.png",NUM_VEHICLE_FRAMES);
        this.setSpeed(INITIAL_CAR_SPEED);
        this.setTotalDuration(TOTAL_FRAME_DURATION);
    }
    public Car(int x,int y) {
        super("src/resources/car05.png",NUM_VEHICLE_FRAMES);
        this.setX(x);
        this.setY(y);
        this.setSpeed(INITIAL_CAR_SPEED);
        this.setTotalDuration(TOTAL_FRAME_DURATION);
    }

           
    
}
