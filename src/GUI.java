import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Die Klasse GUI erzeugt JFrame und JPanel zur Darstellung der grafischen Elemente.
 *
 * @author Tristan Michel
 */
public class GUI extends JFrame{

    private Game game;
    private JMenuBar mnuBar = new JMenuBar();
    private JMenu mnuSettings = new JMenu("Options");
    private JMenuItem mnuRestart = new JMenuItem("Restart");

    public GUI(Game game) {
        this.game = game;
        this.addKeyListener(game);
        this.mnuRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.restart();
            }
        });
        this.mnuSettings.add(mnuRestart);
        this.mnuBar.add(mnuSettings);
        this.setContentPane(new Panel());

        this.setTitle("Snake");
        this.setPreferredSize(new Dimension(800,600));
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        this.setJMenuBar(mnuBar);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    class Panel extends JPanel{
        @Override
        public void paintComponent(Graphics g) {
            game.draw(g);
        }
    }
}
