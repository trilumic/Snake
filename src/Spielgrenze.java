import java.awt.*;
import java.util.Vector;

/**
 * Created by TM on 03.05.2014.
 */
public class Spielgrenze extends Spielelement {

    private Rectangle grenze;
    private boolean result;

    public Spielgrenze(Rectangle r) {
        super(r);
        this.grenze = r;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(grenze);
    }

    public Boolean checkCollision(Schlange s) {
//        for (Rectangle r : s.position) {
            if (!grenze.contains(s.position))
            {
                System.out.println("Kollision Grenze");
                result = false;
            }
            return result;
        }

}
