import java.awt.Color;
import processing.core.PApplet;
import processing.core.PImage;

public class NoiseConfig extends PApplet {

    float prvalue = 0.08F;

    float xoff = 0;

    float yoff = 0;

    float speed = 0.3F;

    public static int SCREEN_HEIGHT = 1280;
    public static int SCREEN_WIDTH = 720;

    public static int UNIT_SIZE = 5;

    PImage[] sprites = new PImage[4];
    public void loadImages() {

        sprites[0] = loadImage("grass.png");
        sprites[1] = loadImage("sand.png");
        sprites[2] = loadImage("water.png");
        sprites[3] = loadImage("hilly.png");
    }

    public void settings(){
        size(SCREEN_WIDTH,SCREEN_HEIGHT);
        loadImages();
    }

    public void draw(){
        configuration();
    }

    void configuration(){
        for(int i = 0; i<SCREEN_WIDTH/UNIT_SIZE;i++){
            for(int j = 0;j<SCREEN_HEIGHT/UNIT_SIZE;j++){
                int tileColor = GetTileType(i,j);
                fill(tileColor);
                image(sprites[GetTileType(i, j)], i * UNIT_SIZE, j * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
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
        float t = noise(xoff + x*prvalue,yoff+ y*prvalue);
        if(t<0.3){
            //water
            return 2;
        }
        else if(t>0.3 && t<0.4){
            //sand
            return 1;
        }
        else if(t>0.4 && t<0.6){
            //grass
            return 0;
        }
        else{
            //mountain
            return 3;
        }

    }

    public static void main(String[] args){
        PApplet.main("NoiseConfig");
    }


}




