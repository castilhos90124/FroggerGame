package pacoteFrogger;
import java.awt.Color;
import java.awt.Font;
import static java.awt.SystemColor.window;
import jplay.*;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class Game {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int WINDOW_DELAY = 1000;
    
    public static final int NUM_MOTOS_BELOW = 2;
    public static final int NUM_MOTOS_ABOVE = 0;
    public static final int NUM_CARS_BELOW = 2;
    public static final int NUM_CARS_ABOVE = 1;
    public static final int NUM_TRUCKS_BELOW = 2;
    public static final int NUM_TRUCKS_ABOVE = 3;
    public static final int NUM_SPECIALCARS = 2;
    
    public static final int INITIAL_FROG_X = 370;
    public static final int INITIAL_FROG_Y = 420;
    public static final int INITIAL_LIFES = 5;
    public static final int INITIAL_LEVEL = 1;
    public static final int TOTAL_FRAME_DURATION =2800;
    public static final int NUM_VEHICLE_FRAMES =1;
    public static final int NUM_FROG_FRAMES=8;
    public static final int SURPRISE_DISTANCE = 60;
    public static final int DISTANCE_BETWEEN_ROADS = 70;
    
    public static final double ACCELERATE_SPEED = 0.2;    
    public static final double INITIAL_MOTO_SPEED = 2.2 ;
    public static final double INITIAL_TRUCK_SPEED = 1.0 ;
    public static final double INITIAL_CAR_SPEED = 1.4 ;
    public static final double INITIAL_SPECIALCAR_SPEED = 1.2;
    public static final double JUMP_X_SPEED = 40;
    public static final double JUMP_Y_SPEED = 30;
    public static final double CHANGE_ROAD_SPEED = 0.8;
    
    public static final int VEHICLE_SPAWN_X = -20;
    
    public static final int PISTA1_Y =345;
    public static final int PISTA2_Y =310;
    public static final int PISTA3_Y = 280;
    public static final int PISTA4_Y = 150;
    public static final int PISTA5_Y = 120;
    public static final int PISTA6_Y = 85;
            
    public static final int SAFE_NUMBER = 2;
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static final int LEFT = 4;
    
    public static final int MENU_Y = 480;
    public static final int MENU_X = 0;
    public static final int OPTION_BALL_X = 7;
    public static final int OPTION_BALL_Y = 486;
    public static final int OPTION_BALL_DISTANCE=40;
    public static final int INSTRUCTIONS_X=160;
    public static final int INSTRUCTIONS_Y=485;
    public static final int PAUSE_X=270;
    public static final int PAUSE_Y=232;
    public static final int PAUSE_MENU_X =6;
    public static final int PAUSE_MENU_Y =484;
    public static final int NEXT_LEVEL_LIMIT_Y= 15;
    public static final int NEXT_LEVEL_X=270;
    public static final int NEXT_LEVEL_Y= 232;
    public static final int LEVEL_X=740;
    public static final int LEVEL_Y= 585;
    
    public static final int GAME_OVER_X=270;
    public static final int GAME_OVER_Y= 232;
    public static final int YOU_DIED_X=270;
    public static final int YOU_DIED_Y= 232;
    public static final int NAME_X=100;
    public static final int NAME_Y= 235;
    public static final int LIFES_DISPLAY_X= 350;
    public static final int LIFES_DISPLAY_Y= 505;
    
    public static final int TIMER_X = 580;
    public static final int TIMER_Y = 530;
    public static final int MINUTES=3;
    public static final int SECONDS=0;
    public static final int TOP_CHOICE = 1;
    public static final int MIDDLE_CHOICE = 0;
    public static final int BOTTOM_CHOICE = -1;
    
    public static final int ASCII_A= 65;
    public static final int ASCII_Z = 90;
    public static final int ASCII_SPACE= 32;
    public static final int ASCII_BACKSPACE = 8;
    
    
    //private Window window;
    public Scene scene;
    private Frog frog;
    private Player player;
    
    private Motorcycle moto_below[];
    private Motorcycle moto_above[];
    private Car car_below[];
    private Car car_above[];
    private Truck truck_below[];
    private Truck truck_above[];
    private SpecialCar special_car[];
    private GameImage menu;
    private GameImage option_ball;
    private GameImage instructions;
    private GameImage pause;
    private GameImage pause_menu;
    private GameImage next_level;
    private GameImage game_over;
    private GameImage you_died;
    private GameImage one,two,three,four,five,name;
    
    private Time timer;
    private Ranking ranking;
    private String rankingPath="C:\\Users\\ANDE\\Desktop\\testeFrogger\\src\\pacoteFrogger\\Ranking.txt"; 
    Font fonte = new Font("Comic Sans MS", Font. TRUETYPE_FONT, 40);
    
    public Game() throws IOException
    {
   //     window = window;
        scene = new Scene();
        scene.loadFromFile("UmMapa.scn");
        frog = new Frog(INITIAL_FROG_X,INITIAL_FROG_Y);
        menu= new GameImage("src/resources/menu06.png");
        menu.setX(MENU_X);
        menu.setY(MENU_Y);
        
        ranking=new Ranking();
        ranking.initializeNames();
        ranking.initializeScores();
        ranking.readFile(rankingPath);
        
        player= new Player();
        
        initializeImages();
        
        timer = new Time(0,MINUTES,SECONDS,TIMER_X,TIMER_Y, fonte ,Color.BLACK, false);
        
        moto_below= new Motorcycle[NUM_MOTOS_BELOW];
        moto_above= new Motorcycle[NUM_MOTOS_ABOVE];
        car_below= new Car[NUM_CARS_BELOW];
        car_above= new Car[NUM_CARS_ABOVE];
        truck_below= new Truck[NUM_TRUCKS_BELOW];
        truck_above= new Truck[NUM_TRUCKS_ABOVE];
        special_car= new SpecialCar[NUM_SPECIALCARS];
        
        
        initializeMotos(moto_below,NUM_MOTOS_BELOW);
        initializeMotos(moto_above,NUM_MOTOS_ABOVE);
        
        initializeCars(car_below,NUM_CARS_BELOW);
        initializeCars(car_above,NUM_CARS_ABOVE);
        
        initializeTrucks(truck_below,NUM_TRUCKS_BELOW);
        initializeTrucks(truck_above,NUM_TRUCKS_ABOVE);
           
        initializeSpecialCars(special_car,NUM_SPECIALCARS);
        
        addVehicles(special_car,PISTA4_Y,NUM_SPECIALCARS);
        addVehicles(car_below,PISTA1_Y, NUM_CARS_BELOW);
        addVehicles(truck_below,PISTA2_Y, NUM_TRUCKS_BELOW);
        addVehicles(moto_below,PISTA3_Y, NUM_MOTOS_BELOW);
        addVehicles(truck_above,PISTA5_Y, NUM_TRUCKS_ABOVE);
        addVehicles(car_above,PISTA6_Y, NUM_CARS_ABOVE);
        
    }
    
    /**
     * 
     * @param keyboard
     * @param player_score
     * @param window
     * @return game_over_boolean
     * true:o jogo acabou
     * false:o jogo não acabou
     * 
     */
    
    
    public boolean play(Keyboard keyboard,int player_score,Window window) throws IOException{     
        
        boolean game_over_boolean=false;
        frog.move(keyboard,window);
        
        //frog.pause();
         scene.draw();
        
        
        frog.avoidWalls(scene);
        menu.draw();
        timer.draw();
        showLifes();
        levelDraw(window);
       
        animateVehicles(car_above, NUM_CARS_ABOVE);
        animateVehicles(truck_above, NUM_TRUCKS_ABOVE);
        animateSpecialCars(special_car,NUM_SPECIALCARS,PISTA4_Y);
        animateVehicles(moto_below, NUM_MOTOS_BELOW);
        animateVehicles(truck_below, NUM_TRUCKS_BELOW);
        animateVehicles(car_below, NUM_CARS_BELOW);
        
        if(death())
            treatDeath(window);
       
        if(nextLevel())
            treatNextLevel(window);
        
        if(gameOver()){
            treatGameOver(window,keyboard);
            
            game_over_boolean = true;
        }
        
       
        frog.draw();
        return game_over_boolean;
    }
    private boolean nextLevel(){
        if(frog.getY() <= NEXT_LEVEL_LIMIT_Y)
            return true;
        else
            return false;
    }
    
    private boolean gameOver(){
        if (timer.timeEnded() || (player.getLifes()==0))
            return true;
        else
            return false;
    }
    
    private boolean death(){
         if(frogVehicleColision(frog,moto_below,NUM_MOTOS_BELOW))
            return true;
        if(frogVehicleColision(frog,moto_above,NUM_MOTOS_ABOVE))
            return true;
        if(frogVehicleColision(frog,truck_below,NUM_TRUCKS_BELOW))
            return true;
        if(frogVehicleColision(frog,truck_above,NUM_TRUCKS_ABOVE))
            return true;
        if(frogVehicleColision(frog,car_below,NUM_CARS_BELOW))
            return true;
        if(frogVehicleColision(frog,car_above,NUM_CARS_ABOVE))
            return true;
        if(frogVehicleColision(frog,special_car,NUM_SPECIALCARS))
            return true;
        return false;
    }
    private void initializeImages(){
        option_ball= new GameImage("src/resources/option_ball.png");
        option_ball.setX(OPTION_BALL_X);
        option_ball.setY(OPTION_BALL_Y);
        
        instructions= new GameImage("src/resources/instructions.png");
        instructions.setX(INSTRUCTIONS_X);
        instructions.setY(INSTRUCTIONS_Y);
        
        pause= new GameImage("src/resources/paused.png");
        pause.setX(PAUSE_X);
        pause.setY(PAUSE_Y);
        
        pause_menu= new GameImage("src/resources/pause_menu.png");
        pause_menu.setX(PAUSE_MENU_X);
        pause_menu.setY(PAUSE_MENU_Y);
        
        next_level= new GameImage("src/resources/next_level.png");
        next_level.setX(NEXT_LEVEL_X);
        next_level.setY(NEXT_LEVEL_Y);
        
        game_over= new GameImage("src/resources/game_over.png");
        game_over.setX(GAME_OVER_X);
        game_over.setY(GAME_OVER_Y);
        
        you_died= new GameImage("src/resources/you_died.png");
        you_died.setX(YOU_DIED_X);
        you_died.setY(YOU_DIED_Y);
        
        one= new GameImage("src/resources/one.png");
        one.setX(LIFES_DISPLAY_X);
        one.setY(LIFES_DISPLAY_Y);
        
        two= new GameImage("src/resources/two.png");
        two.setX(LIFES_DISPLAY_X);
        two.setY(LIFES_DISPLAY_Y);
        
        three= new GameImage("src/resources/three.png");
        three.setX(LIFES_DISPLAY_X);
        three.setY(LIFES_DISPLAY_Y);
        
        four= new GameImage("src/resources/four.png");
        four.setX(LIFES_DISPLAY_X);
        four.setY(LIFES_DISPLAY_Y);
        
        five= new GameImage("src/resources/five.png");
        five.setX(LIFES_DISPLAY_X);
        five.setY(LIFES_DISPLAY_Y);
        
        name= new GameImage("src/resources/nome.png");
        name.setX(NAME_X);
        name.setY(NAME_Y);
    }
    
    private void levelDraw(Window window){
        window.drawText(""+player.getLevel(), LEVEL_X, LEVEL_Y, Color.BLACK, fonte);

    }
    
    private void showLifes(){
        switch(player.getLifes()){
            case 5:five.draw();break;
            case 4:four.draw();break;
            case 3:three.draw();break;
            case 2:two.draw();break;
            default:one.draw();break;
        }
        //window.update();
    }
    private void treatDeath(Window window){
        frog.toStartPosition();
        player.decreaseLifes();
        you_died.draw();
        window.update();
        window.delay(WINDOW_DELAY);
    }
    
    private void treatGameOver(Window window,Keyboard keyboard) throws IOException{
        game_over.draw();
        window.update();
        window.delay(WINDOW_DELAY);
        player.calculateScore();
       
        ranking.readFile(rankingPath);
        ranking.setLastPosition(player.getName(),player.getScore());
       
        ranking.scoreSort();
        ranking.writeFile(rankingPath);
        ranking.show(window);
        waitForEsc(keyboard);
     
    }
    
    private void treatNextLevel(Window window){
        next_level.draw();
        window.update();
        window.delay(WINDOW_DELAY);
        frog.toStartPosition();
        accelerateVehicles(moto_below,NUM_MOTOS_BELOW);
        accelerateVehicles(moto_above,NUM_MOTOS_ABOVE);
        accelerateVehicles(car_below,NUM_CARS_BELOW);
        accelerateVehicles(car_above,NUM_CARS_ABOVE);
        accelerateVehicles(truck_below,NUM_TRUCKS_BELOW);
        accelerateVehicles(truck_above,NUM_TRUCKS_ABOVE);
        accelerateVehicles(special_car,NUM_SPECIALCARS);
        
        player.setLevel(player.getLevel()+1);
       
        
    }
    
    
    /**
     * mostra o menu inicial e permite o jogador escolher sua opçao
     * retorna um boolean que define se o jogo continua rodando ou não
    */ 
    public boolean initialMenu(Keyboard keyboard,Window window){
        
        int choice=TOP_CHOICE;
        int minutes;
        int seconds;
        boolean choosing_menu = true;
        boolean menu_running = true;
        boolean program_executing = true;
        
        scene.draw();
        
        
        while(menu_running){
            while(choosing_menu){
                menu.draw();
                option_ball.draw();
                
                
                if (keyboard.keyDown(Keyboard.UP_KEY))
                    if(choice < TOP_CHOICE){
                        choice ++;
                        option_ball.y -= OPTION_BALL_DISTANCE;
                    }

                if (keyboard.keyDown(Keyboard.DOWN_KEY))
                    if(choice > BOTTOM_CHOICE){
                        choice --;
                        option_ball.y += OPTION_BALL_DISTANCE;
                    }

                if(keyboard.keyDown(Keyboard.ENTER_KEY))
                    choosing_menu = false;

                window.update();
                //System.out.println(choice);
            }
            
            switch(choice){
            case TOP_CHOICE:menu_running=false;
                        program_executing= true;
                        name.draw();
                        window.update();
                        
                        player.insertName(keyboard,window);
                        //waitForEsc(keyboard);
                break;
            case MIDDLE_CHOICE: ranking.show(window);
                window.update();
                menu_running = true;
                choosing_menu=true;
                waitForEsc(keyboard);
                
                //instructions
                break;
            default: menu_running =false;
                    program_executing= false;
            }
            
            
        }
            
        timer.setMinute(MINUTES);
        timer.setSecond(SECONDS);
        return program_executing;
    }
    
    
    public boolean pauseMenu(Keyboard keyboard,Window window){
        
        int choice=TOP_CHOICE;
        int minutes;
        int seconds;
        boolean choosing_menu = true;
        boolean menu_running = true;
        boolean program_executing = true;
        
        minutes=timer.getMinute();
        seconds=timer.getSecond();
        
        pause.draw();
        window.update();
        while(menu_running){
            while(choosing_menu){
                menu.draw();
                pause_menu.draw();
                option_ball.draw();
                timer.draw();
                timer.setMinute(minutes);
                timer.setSecond(seconds);
                showLifes();
                
                if (keyboard.keyDown(Keyboard.UP_KEY))
                    if(choice < TOP_CHOICE){
                        choice ++;
                        option_ball.y -= OPTION_BALL_DISTANCE;
                    }

                if (keyboard.keyDown(Keyboard.DOWN_KEY))
                    if(choice > BOTTOM_CHOICE){
                        choice --;
                        option_ball.y += OPTION_BALL_DISTANCE;
                    }

                if(keyboard.keyDown(Keyboard.ENTER_KEY))
                    choosing_menu = false;

                window.update();
                //System.out.println(choice);
            }
            
            switch(choice){
            case TOP_CHOICE:menu_running=false;
                        program_executing= true;                       //new game ,deve pegar o nome do jogador
                break;
            case MIDDLE_CHOICE: ranking.show(window);
                window.update();
                menu_running = true;
                choosing_menu=true;
                waitForEsc(keyboard);
                
                //instructions
                break;
            default: menu_running =false;
                    program_executing= false;
            }
            
            
        }
            
        
        return program_executing;
    }
    
   
    
    private void waitForEsc(Keyboard keyboard){
        while(!(keyboard.keyDown(Keyboard.ESCAPE_KEY))){
                    
        }
        
    } 
    
    private void addVehicles(Vehicle vehicle[],int y,int numVehicles){
        
        if(numVehicles !=0){
            double randomNum= (Math.random()* Window.getInstance().getWidth());
            vehicle[0].setX(randomNum);
            vehicle[0].setY(y);
            for(int i=1;i<numVehicles;i++){

                if(randomNum + ((Window.getInstance().getWidth() / numVehicles)*i) <= Window.getInstance().getWidth()){
                   vehicle[i].setX(randomNum + ((Window.getInstance().getWidth() / numVehicles)*i));
                }
                else
                    vehicle[i].setX(randomNum + ((Window.getInstance().getWidth() / numVehicles)*i) - Window.getInstance().getWidth());

                vehicle[i].setY(y);

            }
        }    
    }
    private void animateVehicles(Vehicle vehicle[],int numVehicles){
        for(int i=0;i<numVehicles;i++){
                 vehicle[i].draw();
                 vehicle[i].move();
                 vehicle[i].update();
             }
    }
    
    private void animateSpecialCars(SpecialCar special_car[],int numSpecialCars,int pista_y){
        for(int i=0;i<numSpecialCars;i++){
                 special_car[i].draw();
                 special_car[i].move(frog.y,pista_y);
                 special_car[i].update();
             }
    }
    
    
     private void initializeSpecialCars(SpecialCar special_car[],int numSpecialCars){
        for(int i=0;i<numSpecialCars;i++)
            special_car[i]=new SpecialCar();
        
    }
    
    private void accelerateVehicles(Vehicle vehicle[],int numVehicle){
        for(int i=0;i<numVehicle;i++)
            vehicle[i].accelerate();
      
    }
    
    private boolean frogVehicleColision(Frog frog,Vehicle vehicle[],int numVehicles){
        for(int i=0;i<numVehicles;i++)
            if(frog.vehicleColision(vehicle[i]))
                return true;
        
        return false;
    }
    
   
    
    private void initializeMotos(Motorcycle moto[],int numMotos){
        for(int i=0;i<numMotos;i++)
            moto[i]=new Motorcycle();
        
    }
    
     private void initializeTrucks(Truck truck[],int numTrucks){
        for(int i=0;i<numTrucks;i++)
            truck[i]=new Truck();
        
    }
     public void initializeCars(Car car[],int numCars){
        for(int i=0;i<numCars;i++)
            car[i]=new Car();
        
    }
     
    
}
