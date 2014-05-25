import java.awt.*;
import java.util.Vector;

/**
 * Die Klasse Schlange erzeugt das Spielerobjekt (die Schlange) und verwaltet deren Grösse, grafische Darstellung und Position.
 * @author Tristan Michel
 *
 */

public class Schlange extends Spielelement {

//    private int[] controlKeys = {37,38,39,40};
    protected Rectangle position;
    private Point richtung = new Point(1,0);
    private Vector<Rectangle> segmente = new Vector<Rectangle>();
    private int länge = 10;
    private boolean result;
    private String checkDirection = "right";

    public Schlange(Rectangle r) {
        super(r);
        this.position = r;
    }

    public void setRichtung(int keyCode){
        System.out.println(keyCode);

        switch (keyCode){
            case 38: //nach KeyCode[x] abfragen, für mehrere Schlangen
                //up
                if(!checkDirection.equals("down")){
                    richtung = new Point(0,-1);}
                checkDirection = "up";
                break;
            case 39:
                //right
                if(!checkDirection.equals("left")){
                    richtung = new Point(1,0);}
                checkDirection = "right";
                break;
            case 40:
                //down
                if(!checkDirection.equals("up")){
                    richtung = new Point(0,1);}
                checkDirection = "down";
                break;
            case 37:
                //left
                if(!checkDirection.equals("right")){
                    richtung = new Point(-1,0);}
                checkDirection = "left";
                break;
        }

    }

    public void move(){

        segmente.insertElementAt(new Rectangle(position.x, position.y, Game.UNIT, Game.UNIT), 0);

        position.x += richtung.x*Game.UNIT;
        position.y += richtung.y*Game.UNIT;

        if(segmente.size() > länge){
            segmente.removeElementAt(länge);
        }
    }

    public void grow(int wachstum) {
        länge += wachstum;
    }

//    public int getLänge(){
//        return this.länge;
//    }
//
//    public Point getRichtung(){
//        return this.richtung;
//    }

    public Boolean checkCollision(Schlange s) {
        for (Rectangle r : s.segmente) {
            if (position.intersects(r)) {
                System.out.println("Kollision Schlange");
                Game.gameState =false;
                Game.gameEnd("Don't try to eat yourself! That hurts..");
                result = true;
            } else {
                result = false;
            }
        }return result;
    }

//    @Override
//    public boolean isAlive() {
//        return false;
//    }
//
//    @Override
//    public void kill() {
//
//    }


    @Override
    public void draw(Graphics g) {
        for(Rectangle r: segmente){
            g.drawRect(r.x,r.y,r.width,r.height);
            g.fillRect(r.x,r.y,r.width,r.height);
        }

        g.drawRect(position.x,position.y,Game.UNIT,Game.UNIT);
        g.fillRect(position.x,position.y,Game.UNIT,Game.UNIT);
    }
}
