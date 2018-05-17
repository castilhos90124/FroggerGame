
package pacoteFrogger;


import static pacoteFrogger.Game.*;
public class Truck extends Vehicle {
    public Truck(){
        super("src/resources/truck08.png",NUM_VEHICLE_FRAMES);
        this.setSpeed(INITIAL_TRUCK_SPEED);
        this.setTotalDuration(TOTAL_FRAME_DURATION);
    }
    public Truck(int x,int y) {
        super("src/resources/truck08.png",NUM_VEHICLE_FRAMES);
        this.setX(x);
        this.setY(y);
        this.setSpeed(INITIAL_TRUCK_SPEED);
        this.setTotalDuration(TOTAL_FRAME_DURATION);
    }

}    
   

