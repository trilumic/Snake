import java.awt.*;

/**
 * Die Klasse Spielgrenze erzeugt die Spielgrenze in Form eines Rechtecks. Sie enthält Methoden zum Prüfen auf Kollision mit der Schlange und
 * zur Erzeugung der grafischen Darstellung (draw-Methode).
 *
 * @author Tristan Michel
 */
public class Spielgrenze extends Spielelement {

    private Rectangle grenze;

    public Spielgrenze(Rectangle r) {
        super(r);
        this.grenze = r;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(grenze);
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

    public Boolean checkCollision(Schlange s) {
            if (!position.contains(s.position))
            {
                System.out.println("Kollision Grenze");
                Game.gameState = false;
                Game.gameEnd("You crashed into the border! Massive headaches are your reward.");
                return true;
            }else {
                return false;
            }
        }

}
