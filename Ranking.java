/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacoteFrogger;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import jplay.Window;

public class Ranking {
    private int score[];
    private String name[];
    private static final int NUM_FILE_LINES =22;
    private static final int RANKING_TITLE_X =245;
    private static final int RANKING_TITLE_Y =50;
    private static final int NAME_X =20;
    private static final int NAME_Y =520;
    private static final int SCORE_X =700;
    private static final int SCORE_Y =520;
   
    public void readFile(String path) throws IOException {
      
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String line = "";
       
        for(int i=0;i<NUM_FILE_LINES;i++){
            line=buffRead.readLine();
            if((i%2)==0)
                name[i/2]=line;
            else
                score[i/2]=Integer.parseInt(line);
            
        }
        
        
        buffRead.close();
    }
    
    public void writeFile(String path) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        
        for(int i=0;i<NUM_FILE_LINES;i++){
            if((i%2)==0) 
                buffWrite.append(name[i/2]+"\n");
            else
                buffWrite.append(score[i/2]+"\n");
        }
            
        buffWrite.close();
    }
    
   
    public void initializeNames(){
        name=new String[NUM_FILE_LINES];
        for(int i=0;i<NUM_FILE_LINES;i++)
            name[i]="<defaultName>";
    }
    public void initializeScores(){
        score=new int[NUM_FILE_LINES];
        for(int i=0;i<NUM_FILE_LINES;i++)
            score[i]=0;
    }
    public void scoreSort() 
    {
        for (int i = 0; i < NUM_FILE_LINES/2; i++) 
        {
            int a = score[i];
            String temp = name[i];
            for (int j = i - 1; j >= 0 && score[j] > a; j--)
            {
                score[j + 1] = score[j];
                name[j+1]=name[j];
                score[j] = a;
                name[j]=temp;
            }                       
        }               
   }
    public void show(Window window){
        
        Font fonte = new Font("arial", Font.BOLD, 40);
        Font fonte2 = new Font("arial", Font.BOLD, 60);
        window.drawText("Ranking", RANKING_TITLE_X, RANKING_TITLE_Y, Color.WHITE, fonte2);
        
        for(int i=(NUM_FILE_LINES/2)-1;i>0;i--){
            window.drawText(name[i], NAME_X, NAME_Y - (i * 45), Color.WHITE, fonte);
            window.drawText(""+score[i],SCORE_X, SCORE_Y - (i * 45), Color.WHITE, fonte);
        }
        window.update();
    }
    
    public void setLastPosition(String string,int score){
        this.name[0]=string;
        this.score[0]=score;
    }
    
}
