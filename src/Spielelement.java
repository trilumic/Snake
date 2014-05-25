import java.awt.*;

/**
 * Die Klasse Spielelement dient als Superklasse für alle Spielelemente (Schlange, Diamant und Spielgrenze). Sie einen Kunstruktor zur Übergabe des Rechteck-Objekts
 * sowie mehrere abstrakte Methoden für die erbenden Klassen.
 *
 * @author Tristan Michel
 */
abstract class Spielelement {

    protected Rectangle position;
    protected boolean isAlive;

    public Spielelement(Rectangle r){
        this.position = r;
    }

    public abstract void draw(Graphics g);

    public abstract Boolean checkCollision(Schlange s);

//    public abstract boolean isAlive();
//
//    public abstract void kill();
}
