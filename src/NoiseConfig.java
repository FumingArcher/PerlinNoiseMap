import java.awt.Color;
import processing.core.PApplet;
public class NoiseConfig extends PApplet {

    float prvalue = 0.1F;

    float xoff = 0, yoff = 0;
    float speed = 0.1F;

    public static int SCREEN_HEIGHT = 1280;
    public static int SCREEN_WIDTH = 720;

    public static int UNIT_SIZE = 5;

    public void settings(){
        size(SCREEN_WIDTH,SCREEN_HEIGHT);
    }

    public void draw(){
        configuration();
    }

    void configuration(){
        for(int i = 0; i<SCREEN_WIDTH/UNIT_SIZE;i++){
            for(int j = 0;j<SCREEN_HEIGHT/UNIT_SIZE;j++){
                int tileColor = GetTileType(i,j);
                fill(tileColor);
                rect(i*UNIT_SIZE, j*UNIT_SIZE, UNIT_SIZE,UNIT_SIZE);
            }
        }
    }

    public void keyPressed(){
        if(key == ' '){
            noiseSeed(millis());
            configuration();


        }
        if(key == 'w'){
            yoff -= speed;
        }
        if(key =='s'){
            yoff += speed;
        }
        if(key == 'a'){
            xoff -= speed;
        }
        if(key == 'd'){
            xoff += speed;
        }
    }

    int GetTileType(int x,int y){
        float t = noise(xoff + x*prvalue,yoff + y*prvalue);
        if(t<0.3){
            //water
            return color(0,0,255);
        }
        else if(t>0.3 && t<0.4){
            //sand
            return color(255, 255, 0);
        }
        else if(t>0.4 && t<0.6){
            //grass
            return color(0,245,0);
        }
        else{
            //mountain
            return color(128,128,128);
        }

    }

    public static void main(String[] args){
        PApplet.main("NoiseConfig");
    }



}




