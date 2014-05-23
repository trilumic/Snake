import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * Created by emictr on 02.05.2014.
 */
public class Game extends KeyAdapter {

    private GUI gui;
    private Spielgrenze grenze = new Spielgrenze(new Rectangle(20, 20, 740, 500));
    private Schlange schlange = new Schlange(new Rectangle(200,200));
    private Diamant diamant = new Diamant(new Rectangle(Zufallsgenerator.zufallszahl(100,600),Zufallsgenerator.zufallszahl(100,400)));
    private Diamant diamant1 = new Diamant(new Rectangle(Zufallsgenerator.zufallszahl(100,600),Zufallsgenerator.zufallszahl(100,400)));
    private Diamant diamant2 = new Diamant(new Rectangle(Zufallsgenerator.zufallszahl(100,600),Zufallsgenerator.zufallszahl(100,400)));
    private Vector<Spielelement> elemente = new Vector<Spielelement>();

    public static final int UNIT = 20;

    public Game() {
        this.gui = new GUI(this);
        elemente.add(grenze);
        elemente.add(diamant);
        elemente.add(diamant1);
        elemente.add(diamant2);
        elemente.add(schlange);
    }

    public void keyPressed(KeyEvent e) {

        schlange.setRichtung(e.getKeyCode());
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.loop();
    }

    private void loop() {
        while (true){
            schlange.move();
            checkSpielelemente(schlange);
            gui.repaint();
            try {
                Thread.sleep(60);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void checkSpielelemente(Schlange s){
        for(Spielelement e: elemente)
            e.checkCollision(s);
//        for(Spielelement e: elemente)
//            if (e.position.equals(s.position)) {
//                System.out.println("Kollision");
//            }

    }

    public void draw(Graphics g) {
        for (Spielelement e : elemente) {
            if (e.isAlive) {
                e.draw(g);
            }
        }
    }
}

