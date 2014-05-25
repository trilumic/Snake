import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * Die Klasse Game ist die zentrale, ausführbare Klasse des Snake-Projekts. Sie verwaltet alle Objekte und die Laufzeit des Spiels in einem Loop.
 * Zudem enthält sie all wichtigen Konstanten für das Spiel.
 *
 * @author Tristan Michel
 */
public class Game extends KeyAdapter {

    // GUI-Objekt
    private GUI gui;

    //Spielelemente (Schlange und Grenze) und Vector, der alle Spielelemente enthält
    private Spielgrenze grenze;
    private Schlange schlange;
    private Vector<Spielelement> elemente = new Vector<Spielelement>();

    //Spielkonstanten wie Anzahl Diamanten, Zustand des Spiels und Rastergrösse des Spielfelds (UNIT)
    public static final int ANZ_DIAMANTEN = 10;
    public static boolean gameState = true;
    public static final int UNIT = 20;

    /***
     * Konstruktor der Klasse Game. Hier werden die einzelnen Spielelemente erstellt und das Game- und GUI-Objekt verknüpft.
     * Durch das Erstellen des GUI im Konstruktor wird beim Erzeugen des Game-Objekts das Spielfeld gezeichnet,
     */
    public Game() {
        grenze = new Spielgrenze(new Rectangle(20, 20, 740, 500));
        schlange = new Schlange(new Rectangle(400,400,Game.UNIT,Game.UNIT));

        elemente.add(grenze);
        elemente.add(schlange);
        for(int i = 0; i<ANZ_DIAMANTEN;i++) {
            Diamant diamant = new Diamant(new Rectangle(Zufallsgenerator.zufallszahl(80,700),Zufallsgenerator.zufallszahl(80,500),Game.UNIT,Game.UNIT));
            elemente.add(diamant);
            }
        this.gui = new GUI(this);
        }

    /***
     * Die Methode init initialisiert das Spiel. In init() wird ein Game-Objekt (inklusive GUI und Panel) erzeugt
     * und die loop()-Methode aufgerufen, in der das Spiel stattfindet.
     */
    public static void init(){
        Game game = new Game();
        game.loop();
    }

    public static void restart(){
        init();
    }

    /***
     * Die Methode keyPressed des KeyListeners übergibt bei einem Tastenanschlg den KeyEvent an die Methode setRichtung() der Schlange,
     * in der die Bewegungsrichtung der Schlange anhand der gedrückten Taste gesetzt wird.
     * @param e Das ausgelöste KeyEvent.
     */

    public void keyPressed(KeyEvent e) {

        schlange.setRichtung(e.getKeyCode());
    }

    /***
     * In der main-Methode wird das Spiel durch Aufrufen von init() gestartet.
     * @param args Argumente, die beim Ausführen des Programms (von der Kommandozeile) übergeben werden können. Hier nicht verwendet.
     */
    public static void main(String[] args) {
        Game.init();
    }

    /***
     * Die Methode loop() wird in main() gestartet und über die gesamte Dauer des Spiels ausgeführt (while-Schlaufe).
     * Wird eine Regel des Spiels gebrochen oder hat man gewonnen wird der gameState auf false gesetzt und der Loop unterbricht (--> Das Spiel stoppt).
     * Innerhalb des Loops wird die Schlange bewegt (move), neu gezeichnet (repaint) und die Kollisionsüberprüfung (checkSpielelemente) durchgeführt und der
     * Thread anschliessend pausiert.
     */
    private void loop() {
        while (gameState){
            schlange.move();
            gui.repaint();
            checkSpielelemente(schlange);
            try {
                Thread.sleep(60);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    /***
     * Die Methode checkSpielelemente überprüft in einer foreach-Schlaufe, ob die Schlange mit einem Element kollidiert ist (checkCollision).
     * @param s Schlange-Objekt, mit dessen Position die Position der Spielelemente verglichen wird.
     */
    public void checkSpielelemente(Schlange s){
        for(Spielelement e: elemente)
            e.checkCollision(s);
    }

    /**
     * Die draw-Methode delegiert das Zeichnen der einzelnen Spielelemente aus dem Vector elemente an die jeweiligen Spielelemente (Diamant, Spielgrenze, Schlange).
     * @param g Graphics-Objekt, das zum Zeichnen verwendet wird (Siehe spezifische draw-Methoden in den Spielelement-Klassen).
     */
    public void draw(Graphics g) {
        for (Spielelement e : elemente) {
                e.draw(g);
        }
    }

    /***
     * Die Methode gameEnd() wird beim Ende des Spiels aufgerufen. Da das Ende durch verschiedene Ereignisse ausgelöst werden kann,
     * ist diese Methode static. gameEnd wird der String message übergeben, welcher in einem MessageDialog ausgegeben wird.
     * Der Benutzer/Spieler wird dadurch beim Spielende über die Art des Endes (Sieg, Kollision mit Grenze/Schlangenkörper) informiert.
     * @param message String, der im MessageDialog ausgegeben wir. Unterschiedliche Nachricht abhängig vom Auslöser.
     */
    public static void gameEnd(String message){
        JOptionPane.showMessageDialog(null,message);
    }

}

