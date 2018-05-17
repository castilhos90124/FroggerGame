
package pacoteFrogger;
import java.awt.Color;
import java.awt.Font;
import static pacoteFrogger.Game.*;
import jplay.*;

public class Player {
    private static final int MAX_CHAR = 30;
    private String name;
    private int score;
    private int level;
    private int lifes;

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }
    
    public Player(){
        this.setScore(0);
        this.setLevel(INITIAL_LEVEL);
        this.setLifes(INITIAL_LIFES);
    
    }
    
    public Player(String name){
        this.setScore(0);
        this.setName(name);
        this.setLevel(INITIAL_LEVEL);
        this.setLifes(INITIAL_LIFES);
    }

    
    public void insertName(Keyboard keyboard,Window window){
        char name_aux[]=new char[MAX_CHAR];
        int i = 0;
        while(!keyboard.keyDown(Keyboard.ENTER_KEY)){
            
            name_aux[i]=getLetter(keyboard);
            if (validLetter(name_aux[i]) && i < MAX_CHAR - 1) 
                 i++;
               
              
            this.setName(echoName(name_aux,window));
            
            window.update();
            
            
        }
    }
    
    public void decreaseLifes(){
        this.lifes --; 
    }
    
    public void calculateScore(){
        int score;
        score= (getLifes()+ 10)*getLevel();
         this.setScore(score);
    }
    
    private char getLetter(Keyboard keyboard) {
		for (int i= ASCII_A; i <= ASCII_Z; i++)
                    if (keyboard.keyDown(i))
                        //return  (char) (i + ASCII_SPACE);
                        return (char) i;
               
                
		return 0;
	}
    private boolean validLetter(char name_aux) {
		return (((name_aux >= ASCII_A) && (name_aux <=ASCII_Z))||(name_aux >= 97 && name_aux <= 122));
	}
    private String echoName(char name[],Window window) {
	String string;	
        Font fonte = new Font("Comic Sans MS", Font. TRUETYPE_FONT, 50);

        string = "";
       // window.drawText("", NAME_X + 20,NAME_Y, Color.BLACK, fonte);
        for (int i = 0; i < MAX_CHAR; i++) {
            if(name[i]!=0)    
                string = string + name[i];
        }
        window.drawText("" + string, NAME_X + 150, NAME_Y + 40, Color.BLACK, fonte);
        return string;
    }
    //GETTERS E SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
