import java.awt.*;

/**
 * Created by emictr on 02.05.2014.
 */
abstract class Spielelement {
    protected Rectangle position;
    protected boolean isAlive;

    public Spielelement(Rectangle r){
        this.position = r;
    }
    public void draw(Graphics g){
    }

    public Boolean checkCollision(Schlange s) {
        return true;
    }

    public void kill(){}
}
