import java.awt.*;

/**
 * Die Klasse Diamant erzeugt Rechtecke mit variablem Wert, durch deren Verzehr die Schlange grösser wird.
 * Sie entählt Methoden zur Verwaltung des eigenen Lifecycle, zur grafischen Darstellung und überprüft, ob alle Elemente gefressen wurden.
 *
 * @author Tristan Michel
 */
public class Diamant extends Spielelement {

    private int wert;
    private String[] wertArray = {"1","2","3","4","5","6","7","8","9"};
    public static int gefresseneDiamanten;


    public Diamant(Rectangle r) {
        super(r);
        this.wert = Zufallsgenerator.zufallszahl(0,8);
        this.isAlive = true;
    }

    @Override

    public void draw(Graphics g) {
        if(isAlive) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawString(wertArray[wert], position.x, position.y + Game.UNIT - 3);
            g2d.rotate(Math.toRadians(45), position.x, position.y);

            g2d.drawRect(position.x+4, position.y-2, Game.UNIT - 4, Game.UNIT - 4);
        }
    }

    public Boolean checkCollision (Schlange s){
        if (isAlive && position.intersects(s.position)){
            s.grow(wert+1);
            gefresseneDiamanten+=1;
            this.kill();
            if(allEaten()){
                Game.gameEnd("All Diamonds Eaten. Nice job!");
            }
            return true;
        }else {
            return false;
        }
    }

//    @Override
//    public boolean isAlive() {
//        return this.isAlive;
//    }

    public void kill(){
        super.isAlive = false;
    }

    public static boolean allEaten(){
        if(gefresseneDiamanten == Game.ANZ_DIAMANTEN){
            Game.gameState = false;
            return true;
        }else{
            return false;
        }
    }
}
