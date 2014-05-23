import javax.swing.*;
import java.awt.*;
/**
 * Created by emictr on 02.05.2014.
 */
public class GUI extends JFrame{

    private Game game;
    private JMenuBar mnuBar = new JMenuBar();
    private JMenu mnuSettings = new JMenu("Settings");

    public GUI(Game game) {
        this.game = game;
        this.addKeyListener(game);
        this.mnuBar.add(mnuSettings);

        this.setPreferredSize(new Dimension(800,600));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(new Panel());
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
