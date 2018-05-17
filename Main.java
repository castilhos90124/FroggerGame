package pacoteFrogger;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import jplay.*;
import static pacoteFrogger.Game.*;
import static jplay.Collision.*;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean running_program = true;
        boolean running_game = true;
        boolean game_over = false;
        Keyboard keyboard = new Keyboard();
        Window window = new Window(WINDOW_WIDTH, WINDOW_HEIGHT);

        while (running_program) {

            Game game = new Game();
            Player player = new Player();

            keyboard = window.getKeyboard();
            running_game=true;
            game_over=false;
            

            window.update();
            running_program = game.initialMenu(keyboard, window);
              if (!running_program) 
                    running_game= false;
                
            //window.update();
            while (running_game) {
              
                game_over = game.play(keyboard, player.getScore(), window);
                window.update();

                if (keyboard.keyDown(KeyEvent.VK_ESCAPE)) {

                    running_game = game.pauseMenu(keyboard, window);

                }
                if (game_over) {
                    running_game = false;
                }

                window.delay(10);

            }
        }
        window.exit();

    }

}
