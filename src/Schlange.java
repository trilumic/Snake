import javafx.scene.shape.Ellipse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by TM on 03.05.2014.
 */

public class Schlange extends Spielelement {

    private int[] controlKeys = {37,38,39,40};
    protected Rectangle position;
    private Point richtung = new Point(1,0);
    private Rectangle head;
    private Vector<Rectangle> segmente = new Vector<Rectangle>();
    private int länge = 10;
    private boolean result;

    public Schlange(Rectangle position) {
        super(new Rectangle(position.x,position.y));
        this.position = position;
//        this.head = new Rectangle(200,200, Game.UNIT, Game.UNIT);
//        this.segmente.add(new Rectangle(richtung.x,richtung.y,Game.UNIT,Game.UNIT));
//        this.segmente.add(new Rectangle((richtung.x+Game.UNIT), (richtung.y+Game.UNIT) ,Game.UNIT,Game.UNIT));

    }

    public void setRichtung(int keyCode){
        System.out.println(keyCode);
        switch (keyCode){
            case 38: //nach KeyCode[x] abfragen, für mehrere Schlangen
                //up
                richtung = new Point(0,-1);
                break;
            case 39:
                //right
                richtung = new Point(1,0);
                break;
            case 40:
                //down
                richtung = new Point(0,1);
                break;
            case 37:
                //left
                richtung = new Point(-1,0);
                break;
        }

    }

    public void move(){
        //Bewegt Schlang um game.UNiT weiter
         position.x += richtung.x*Game.UNIT;
         position.y += richtung.y*Game.UNIT;

         segmente.insertElementAt(new Rectangle(position.x, position.y, Game.UNIT, Game.UNIT), 0);
            if(segmente.size() > länge){
                segmente.removeElementAt(länge);
            }
    }

    public void grow(int wachstum) {
        länge += wachstum;
    }

    public int getLänge(){
        return this.länge;
    }

    public Point getRichtung(){
        return this.richtung;
    }

//    public Boolean checkCollision(Schlange s) {
//        for (Rectangle r : s.segmente) {
//            if (position.intersects(r)) {
//                System.out.println("Kollision Schlange");
//                result = true;
//            } else {
//                result = false;
//            }
//        }return result;
//    }



    @Override
    public void draw(Graphics g) {
//        super.draw(g);
        for(Rectangle r: segmente){
            g.drawRect(r.x,r.y,r.width,r.height);
            g.fillRect(r.x,r.y,r.width,r.height);
        }

        g.drawRect(position.x,position.y,Game.UNIT,Game.UNIT);
        g.fillRect(position.x,position.y,Game.UNIT,Game.UNIT);

//            head = new Rectangle(position.x, position.y, Game.UNIT, Game.UNIT);
////        for(Rectangle r: segmente)
//            Graphics2D g2d = (Graphics2D) g.create();
////            Rectangle rect = new Rectangle(position.x, position.y, Game.UNIT, Game.UNIT);
//            g2d.draw(head);
//            g2d.fill(head);


    }
}
