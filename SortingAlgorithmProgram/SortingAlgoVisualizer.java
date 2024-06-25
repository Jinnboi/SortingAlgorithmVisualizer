package SortingAlgorithmProgram;

import javax.swing.SwingUtilities;

/**
 *  Class that visually represents various sorting algorithms. Inspired by my
 *  Data Structures course, where I learned about a variety of sorting algorithms.
 *
 *  @author Marcos E. Mancia Jr.
 *  @version 1.0
 */
public class SortingAlgoVisualizer {

    // Declare all JComponents
    private static MyFrame frame;
    protected static GraphPanel graphPanel;
    protected static SortAlgos graph;
    protected static StatsPanel statsPanel;
    protected static OptionsPanel optionsPanel;

    /**
     * Initializes and creates the Java Swing frame
     *
     * @param args      The String[] object that takes in input from terminal
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Initialize frame
                initFrame();

                // Initialize panels
                initGraphPanel();
                initStatsPanel();
                initOptionsPanel();

                // Add all components to frame
                finalizeFrame();

                // Show final frame for output
                frame.setVisible(true);
            }
        });
    }

    /**
     * Initializes the JFrame using MyFrame class
     */
    private static void initFrame() {
        frame = new MyFrame();
    }

    /**
     * Initializes two JPanels using GraphPanel class and SortAlgos class
     */
    private static void initGraphPanel() {
        // This panel will hold the Graph and display information
        graphPanel = new GraphPanel();
        // This panel will contain the histogram used to show the sorting algorithms
        graph = new SortAlgos();
    }

    /**
     * Initializes a JPanel using StatsPanel class
     */
    private static void initStatsPanel() {
        // This panel will show relevant information about the sorting algorithms
        statsPanel = new StatsPanel();
    }

    /**
     * Initializes a JPanel using OptionsPanel class
     */
    private static void initOptionsPanel() {
        // This panel will allow the user to pick between sorting algorithms
        optionsPanel = new OptionsPanel();
    }

    /**
     * Finalize JFrame by combining all JComponents
     */
    private static void finalizeFrame() {
        // Add all JPanels to their respective JComponents
        graphPanel.add(graph);
        frame.add(graphPanel);
        frame.add(statsPanel);
        frame.add(optionsPanel);
    }
}
