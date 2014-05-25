import java.awt.*;

/**
 * Created by emictr on 02.05.2014.
 */
public class Diamant extends Spielelement {

    private int wert;
    private Rectangle position;
    private String[] wertArray = {"1","2","3","4","5","6","7","8","9"};

    public Diamant(Rectangle p) {
        super(p);
        this.position = p;
        //position.x = p.x;  // Zufallsgenerator.zufallszahl(50,700);// ()int) p.getX();
        //position.y = p.y; // Zufallsgenerator.zufallszahl(50,450); // ()int) p.getY();
        this.wert = Zufallsgenerator.zufallszahl(0,8);
//        this.isAlive = true;
    }

    @Override

    public void draw(Graphics g) {
//        super.draw(g);
          Graphics2D g2d = (Graphics2D) g.create();
          Rectangle rect = new Rectangle(position.x,position.y,Game.UNIT-4,Game.UNIT-4);
//          g2d.translate(rect.x + (rect.width/2), rect.y+(rect.height/2));
          g2d.drawString(wertArray[wert],position.x-4,position.y+Game.UNIT-3);
          g2d.rotate(Math.toRadians(45),position.x,position.y);

          g2d.draw(rect);

//        g.drawRect((int)position.getX(),(int)position.getY(),20,20);
    }

    public Boolean checkCollision (Schlange s){
        if (position.intersects(s.position)){
            s.grow(wert+1);
            this.kill();
            return true;
        }else {
            return false;
        }
    }

    public void kill(){
        this.isAlive = false;
    }
}
