package SortingAlgorithmProgram;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Class that initializes and formats overarching JFrame
 *
 * @author Marcos E. Mancia Jr.
 * @version 1.0
 */
public class MyFrame extends JFrame {

    /**
     * Constructor for MyFrame class that formats JFrame
     */
    public MyFrame() {
        this.setTitle("Sorting Algorithm Visualizer");
        this.setResizable(false); // To ensure the histogram is always shown properly
        this.setSize(800,500);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set different UI style
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException |
               UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
