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
 * Class that formats the GraphPanel that holds the SortAlgos JPanel
 *
 * @author Marcos E. Mancia Jr.
 * @version 1.0
 */
public class GraphPanel extends JPanel {

    // Declare Java Swing variables
    private static final Border bottomRightBorder = BorderFactory.createMatteBorder(0,0, 1, 1,
            new Color(53, 57,53));
    protected JLabel sortName;

    // Declare primitive variables
    protected static short buttonClicks = 0;

    /**
     * Constructor for GraphPanel used for JPanel formatting
     */
    public GraphPanel() {
        this.setLayout(null);
        this.setBounds(0, 0, 500, 300);
        this.setBorder(bottomRightBorder);
        this.setBackground(new Color(200,200,200));
        this.createGraphPanel();
    }

    /**
     * Initializes various JComponents to add to GraphPanel
     */
    private void createGraphPanel() {
        // This JLabel will be the title for the GraphPanel
        JLabel graphSubtitle = new JLabel();
        graphSubtitle.setBounds(0, 0, 180, 45);
        graphSubtitle.setBorder(bottomRightBorder);
        graphSubtitle.setFont(new Font("Georgia", Font.BOLD, 25));
        graphSubtitle.setForeground(Color.BLACK);
        graphSubtitle.setText("Graph Panel");
        graphSubtitle.setHorizontalAlignment(JLabel.CENTER);
        this.add(graphSubtitle);

        // This JLabel displays the words "Current Sort: " for visual aesthetics
        JLabel currentSort = new JLabel();
        currentSort.setBounds(7, 75, 95, 15);
        currentSort.setFont(new Font("Georgia", Font.PLAIN, 15));
        currentSort.setForeground(Color.BLACK);
        currentSort.setText("Current Sort: ");
        this.add(currentSort);

        // This JLabel displays the first word of the chosen sort Ex: "Bubble"
        sortName = new JLabel();
        sortName.setBounds(10, 100, 150, 40);
        sortName.setFont(new Font("Georgia", Font.PLAIN, 35));
        sortName.setForeground(Color.BLACK);
        sortName.setText("Bubble");
        this.add(sortName);

        // This JLabel displays the word "Sort" for visual aesthetics
        JLabel sort = new JLabel();
        sort.setBounds(90, 145, 75, 40);
        sort.setFont(new Font("Georgia", Font.PLAIN, 40));
        sort.setForeground(Color.BLACK);
        sort.setText("Sort");
        this.add(sort);

        // This JButton is what invokes the sorting algorithms
        JButton sortButton = new JButton("Sort");
        sortButton.setBounds(7, 200, 175, 75);
        sortButton.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 35));
        this.add(sortButton);

        /////////////////////////////////// LISTENERS ////////////////////////////////////////////

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buttonClicks==0) { // Ensure only one sort is allowed per click
                    if(OptionsPanel.sortingChoice==7) {
                        // Bogo sort uses a Swing Timer, which means it must be differentiated from the Swing Worker
                        // algorithms
                        SortingAlgoVisualizer.graph.bogoSort();
                    }else {
                        SortingAlgoVisualizer.graph.sort(OptionsPanel.sortingChoice);
                    }
                    buttonClicks++;
                }
            }
        });

    }
}
