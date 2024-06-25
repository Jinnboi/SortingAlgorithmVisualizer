package SortingAlgorithmProgram;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that holds the different sorting algorithm options
 */
public class OptionsPanel extends JPanel {

    // Declare Java Swing variables
    private static final Border bottomRightBorder = BorderFactory.createMatteBorder(0,0, 1, 1,
            new Color(53, 57,53));

    // Declare Java AWT variables
    private static final Font buttonFont = new Font("Georgia", Font.BOLD | Font.ITALIC, 15);

    // Declare primitive variables
    protected static byte sortingChoice = 0;

    /**
     * Constructor for OptionsPanel used for JPanel formatting
     */
    public OptionsPanel() {
        this.setLayout(null);
        this.setBorder(bottomRightBorder);
        this.setBounds(0, 300, 500, 200);
        this.setBackground(new Color(200,200,200));
        this.createOptionsPanel();
    }

    /**
     * Initializes various JComponents to add to OptionsPanel
     */
    private void createOptionsPanel() {
        // This JLabel will be the title for the OptionsPanel
        JLabel optionsSubtitle = new JLabel();
        optionsSubtitle.setBounds(0,0,187,45);
        optionsSubtitle.setBorder(bottomRightBorder);
        optionsSubtitle.setFont(new Font("Georgia", Font.BOLD, 25));
        optionsSubtitle.setForeground(Color.BLACK);
        optionsSubtitle.setText("Option Panel");
        optionsSubtitle.setHorizontalAlignment(JLabel.CENTER);
        this.add(optionsSubtitle);

        // This JButton will be used to select the option for Bubble Sort
        JButton bubbleSort = new JButton();
        bubbleSort.setBounds(195, 8, 145, 30);
        bubbleSort.setFont(buttonFont);
        bubbleSort.setForeground(Color.BLACK);
        bubbleSort.setText("Bubble Sort");
        bubbleSort.setHorizontalAlignment(JButton.CENTER);
        this.add(bubbleSort);

        // This JButton will be used to select the option for Selection Sort
        JButton selectionSort = new JButton();
        selectionSort.setBounds(348, 8, 145, 30);
        selectionSort.setFont(buttonFont);
        selectionSort.setForeground(Color.BLACK);
        selectionSort.setText("Selection Sort");
        selectionSort.setHorizontalAlignment(JButton.CENTER);
        this.add(selectionSort);

        // This JButton will be used to select the option for Insertion Sort
        JButton insertionSort = new JButton();
        insertionSort.setBounds(15, 60, 145, 30);
        insertionSort.setFont(buttonFont);
        insertionSort.setForeground(Color.BLACK);
        insertionSort.setText("Insertion Sort");
        insertionSort.setHorizontalAlignment(JButton.CENTER);
        this.add(insertionSort);

        // This JButton will be used to select the option for Heap Sort
        JButton heapSort = new JButton();
        heapSort.setBounds(175, 60, 145, 30);
        heapSort.setFont(buttonFont);
        heapSort.setForeground(Color.BLACK);
        heapSort.setText("Heap Sort");
        heapSort.setHorizontalAlignment(JButton.CENTER);
        this.add(heapSort);

        // This JButton will be used to select the option for Merge Sort
        JButton mergeSort = new JButton();
        mergeSort.setBounds(335, 60, 145, 30 );
        mergeSort.setFont(buttonFont);
        mergeSort.setForeground(Color.BLACK);
        mergeSort.setText("Merge Sort");
        mergeSort.setHorizontalAlignment(JButton.CENTER);
        this.add(mergeSort);

        // This JButton will be used to select the option for Quick Sort
        JButton quickSort = new JButton();
        quickSort.setBounds(15, 112, 145, 30);
        quickSort.setFont(buttonFont);
        quickSort.setForeground(Color.BLACK);
        quickSort.setText("Quick Sort");
        quickSort.setHorizontalAlignment(JButton.CENTER);
        this.add(quickSort);

        // This JButton will be used to select the option for Counting Sort
        JButton countingSort = new JButton();
        countingSort.setBounds(175, 112, 145, 30);
        countingSort.setFont(buttonFont);
        countingSort.setForeground(Color.BLACK);
        countingSort.setText("Counting Sort");
        countingSort.setHorizontalAlignment(JButton.CENTER);
        this.add(countingSort);

        // This JButton will be used to select the option for Bogo Sort
        JButton bogoSort = new JButton();
        bogoSort.setBounds(335, 112, 145, 30);
        bogoSort.setFont(buttonFont);
        bogoSort.setForeground(Color.BLACK);
        bogoSort.setText("Bogo Sort");
        bogoSort.setHorizontalAlignment(JButton.CENTER);
        this.add(bogoSort);

        ///////////////////////////// LISTENERS //////////////////////////////
        bubbleSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SortAlgos.sorter==null || SortAlgos.sorter.isDone()) { // If there is no sorting algorithm active
                    SortingAlgoVisualizer.graphPanel.sortName.setText("Bubble"); // Set GraphPanel sort name to "Bubble"
                    SortingAlgoVisualizer.statsPanel.createSortCode((byte)0);
                    // Change time complexities to match Bubble Sort
                    SortingAlgoVisualizer.statsPanel.dynamicInformation.setText("    Θ(N^2)\n      Θ(N)\n    O(N^2)");
                    if(SortingAlgoVisualizer.graph.bogoSort!=null) { // If Bogo Sort is not null, stop the Swing Timer
                        SortingAlgoVisualizer.graph.bogoSort.stop();
                        GraphPanel.buttonClicks = 0; // Set buttonClicks back to 0
                    }
                    SortAlgos.heights = SortAlgos.createArray(); // Create a new randomized heights array
                    SortingAlgoVisualizer.graph.repaint(); // Repaint graph to reflect changes
                    sortingChoice = 0;
                }
            }
        });

        selectionSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SortAlgos.sorter==null || SortAlgos.sorter.isDone()) { // If there is no sorting algorithm active
                    SortingAlgoVisualizer.graphPanel.sortName.setText("Selection"); // Set GraphPanel sort name to "Selection"
                    SortingAlgoVisualizer.statsPanel.createSortCode((byte)1);
                    // Change time complexities to match Selection Sort
                    SortingAlgoVisualizer.statsPanel.dynamicInformation.setText("    Θ(N^2)\n    Θ(N^2)\n    Θ(N^2)");
                    if(SortingAlgoVisualizer.graph.bogoSort!=null) { // If Bogo Sort is not null, stop the Swing Timer
                        SortingAlgoVisualizer.graph.bogoSort.stop();
                        GraphPanel.buttonClicks = 0; // Set buttonClicks back to 0
                    }
                    SortAlgos.heights = SortAlgos.createArray(); // Create a new randomized heights array
                    SortingAlgoVisualizer.graph.repaint(); // Repaint graph to reflect changes
                    sortingChoice = 1;
                }
            }
        });

        insertionSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SortAlgos.sorter==null || SortAlgos.sorter.isDone()) { // If there is no sorting algorithm active
                    SortingAlgoVisualizer.graphPanel.sortName.setText("Insertion"); // Set GraphPanel sort name to "Insertion"
                    SortingAlgoVisualizer.statsPanel.createSortCode((byte)2);
                    // Change time complexities to match Insertion Sort
                    SortingAlgoVisualizer.statsPanel.dynamicInformation.setText("    Θ(N^2)\n      Θ(N)\n    O(N^2)");
                    if(SortingAlgoVisualizer.graph.bogoSort!=null) { // If Bogo Sort is not null, stop the Swing Timer
                        SortingAlgoVisualizer.graph.bogoSort.stop();
                        GraphPanel.buttonClicks = 0; // Set buttonClicks back to 0
                    }
                    SortAlgos.heights = SortAlgos.createArray(); // Create a new randomized heights array
                    SortingAlgoVisualizer.graph.repaint(); // Repaint graph to reflect changes
                    sortingChoice = 2;
                }
            }
        });

        heapSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SortAlgos.sorter==null || SortAlgos.sorter.isDone()) { // If there is no sorting algorithm active
                    SortingAlgoVisualizer.graphPanel.sortName.setText("Heap"); // Set GraphPanel sort name to "Heap"
                    SortingAlgoVisualizer.statsPanel.createSortCode((byte)3);
                    // Change time complexities to match Heap Sort
                    SortingAlgoVisualizer.statsPanel.dynamicInformation.setText(" Θ(N*lg(N))\n Θ(N*lg(N))\n O(N*lg(N))");
                    if(SortingAlgoVisualizer.graph.bogoSort!=null) { // If Bogo Sort is not null, stop the Swing Timer
                        SortingAlgoVisualizer.graph.bogoSort.stop();
                        GraphPanel.buttonClicks = 0; // Set buttonClicks back to 0
                    }
                    SortAlgos.heights = SortAlgos.createArray(); // Create a new randomized heights array
                    SortingAlgoVisualizer.graph.repaint(); // Repaint graph to reflect changes
                    sortingChoice = 3;
                }
            }
        });

        mergeSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SortAlgos.sorter==null || SortAlgos.sorter.isDone()) { // If there is no sorting algorithm active
                    SortingAlgoVisualizer.graphPanel.sortName.setText("Merge"); // Set GraphPanel sort name to "Merge"
                    SortingAlgoVisualizer.statsPanel.createSortCode((byte)4);
                    // Change time complexities to match Merge Sort
                    SortingAlgoVisualizer.statsPanel.dynamicInformation.setText(" Θ(N*lg(N))\n Θ(N*lg(N))\n Θ(N*lg(N))");
                    if(SortingAlgoVisualizer.graph.bogoSort!=null) { // If Bogo Sort is not null, stop the Swing Timer
                        SortingAlgoVisualizer.graph.bogoSort.stop();
                        GraphPanel.buttonClicks = 0; // Set buttonClicks back to 0
                    }
                    SortAlgos.heights = SortAlgos.createArray(); // Create a new randomized heights array
                    SortingAlgoVisualizer.graph.repaint(); // Repaint graph to reflect changes
                    sortingChoice = 4;
                }
            }
        });

        quickSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SortAlgos.sorter==null || SortAlgos.sorter.isDone()) { // If there is no sorting algorithm active
                    SortingAlgoVisualizer.graphPanel.sortName.setText("Quick"); // Set GraphPanel sort name to "Quick"
                    SortingAlgoVisualizer.statsPanel.createSortCode((byte)5);
                    // Change time complexities to match Quick Sort
                    SortingAlgoVisualizer.statsPanel.dynamicInformation.setText("    Θ(N^2)\n Θ(N*lg(N))\n    O(N^2)");
                    if(SortingAlgoVisualizer.graph.bogoSort!=null) { // If Bogo Sort is not null, stop the Swing Timer
                        SortingAlgoVisualizer.graph.bogoSort.stop();
                        GraphPanel.buttonClicks = 0; // Set buttonClicks back to 0
                    }
                    SortAlgos.heights = SortAlgos.createArray(); // Create a new randomized heights array
                    SortingAlgoVisualizer.graph.repaint(); // Repaint graph to reflect changes
                    sortingChoice = 5;
                }
            }
        });

        countingSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SortAlgos.sorter==null || SortAlgos.sorter.isDone()) { // If there is no sorting algorithm active
                    SortingAlgoVisualizer.graphPanel.sortName.setText("Counting"); // Set GraphPanel sort name to "Counting"
                    SortingAlgoVisualizer.statsPanel.createSortCode((byte)6);
                    // Change time complexities to match Counting Sort
                    SortingAlgoVisualizer.statsPanel.dynamicInformation.setText("   Θ(N+M)\n   Θ(N+M)\n   Θ(N+M)");
                    if(SortingAlgoVisualizer.graph.bogoSort!=null) { // If Bogo Sort is not null, stop the Swing Timer
                        SortingAlgoVisualizer.graph.bogoSort.stop();
                        GraphPanel.buttonClicks = 0; // Set buttonClicks back to 0
                    }
                    SortAlgos.heights = SortAlgos.createArray(); // Create a new randomized heights array
                    SortingAlgoVisualizer.graph.repaint(); // Repaint graph to reflect changes
                    sortingChoice = 6;
                }
            }
        });

        bogoSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SortAlgos.sorter==null || SortAlgos.sorter.isDone()) { // If there is no sorting algorithm active
                    SortingAlgoVisualizer.graphPanel.sortName.setText("Bogo"); // Set GraphPanel sort name to "Bogo"
                    SortingAlgoVisualizer.statsPanel.createSortCode((byte)7);
                    // Change time complexities to match Bogo Sort
                    SortingAlgoVisualizer.statsPanel.dynamicInformation.setText("      Θ(∞)\n      Θ(N)\n      O(∞)\n");
                    SortAlgos.heights = SortAlgos.createArray(); // Create a new randomized heights array
                    SortingAlgoVisualizer.graph.repaint(); // Repaint graph to reflect changes
                    sortingChoice = 7;
                }
            }
        });
    }
}
