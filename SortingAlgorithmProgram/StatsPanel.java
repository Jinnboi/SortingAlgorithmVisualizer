package SortingAlgorithmProgram;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that displays the stats of the current sorting algorithm
 *
 * @author Marcos E. Mancia Jr.
 * @version 1.0
 */
public class StatsPanel extends JPanel {

    // Declare Java Swing variables
    private static final Border bottomRightBorder = BorderFactory.createMatteBorder(0,0, 1, 1,
            new Color(53, 57,53));
    private static final Border bottomBorder = BorderFactory.createMatteBorder(0,0,1,0,
            new Color(53,57,53));
    private static final Border topLeftRightBorder = BorderFactory.createMatteBorder(1, 1, 0, 1,
            new Color(53, 57, 53));
    private static final Border normalLineBorder = BorderFactory.createLineBorder(new Color(53, 57,53));
    private static final Border bottomLeftRightBorder = BorderFactory.createMatteBorder(0, 1, 1, 1,
            new Color(53, 57, 53));
    protected JTextPane dynamicInformation;
    private static JTextPane sortCode;
    private static JScrollPane scrollCode;


    /**
     * Constructor for StatsPanel used for JPanel formatting
     */
    public StatsPanel() {
        this.setLayout(null);
        this.setBorder(bottomRightBorder);
        this.setBounds(500, 0, 300, 500);
        this.setBackground(new Color(200,200,200));
        this.createStatsPanel();
    }

    /**
     * Initializes various JComponents to add to StatsPanel
     */
    private void createStatsPanel() {
        // This JLabel will be the title for the StatsPanel
        JLabel statsSubtitle = new JLabel();
        statsSubtitle.setBounds(0,0,295,45);
        statsSubtitle.setBorder(bottomBorder);
        statsSubtitle.setFont(new Font("Georgia", Font.BOLD, 25));
        statsSubtitle.setForeground(Color.BLACK);
        statsSubtitle.setText("Information Panel");
        statsSubtitle.setHorizontalAlignment(JLabel.CENTER);
        this.add(statsSubtitle);

        // This JLabel will display the word "Stats: " for visual aesthetics
        JLabel stats = new JLabel();
        stats.setBounds(21, 70, 200, 15);
        stats.setFont(new Font("Georgia", Font.ITALIC, 15));
        stats.setForeground(Color.BLACK);
        stats.setText("Stats:");
        this.add(stats);

        // This JLabel will display the words "Choose Array Size (0-100):" for visual aesthetics
        JLabel arrSizer = new JLabel();
        arrSizer.setBounds(21, 358, 248, 15);
        arrSizer.setFont(new Font("Georgia", Font.ITALIC, 15));
        arrSizer.setForeground(Color.BLACK);
        arrSizer.setText("Choose Array Size (0-100):");
        this.add(arrSizer);

        // This JTextField will be one way to edit the array size
        JTextField sliderVal = new JTextField();
        sliderVal.setBounds(226, 356, 42, 19);
        sliderVal.setBorder(topLeftRightBorder);
        sliderVal.setFont(new Font("Georgia", Font.PLAIN, 15));
        sliderVal.setForeground(Color.BLACK);
        sliderVal.setText("50"); // Initial value of array size is 50
        sliderVal.setHorizontalAlignment(JLabel.RIGHT);
        sliderVal.setOpaque(true);
        sliderVal.setBackground(Color.WHITE);
        this.add(sliderVal);

        // This JTextPane will display relevant information regarding the current sorting algorithm
        JTextPane staticInformation = new JTextPane();
        dynamicInformation = new JTextPane();
            staticInformation.setBounds(20,85,180,53);
        dynamicInformation.setBounds(200, 85, 68, 53);
        staticInformation.setBorder(normalLineBorder);
        dynamicInformation.setBorder(normalLineBorder);
        // Displays worst case, best case, and overall time complexities of each sorting algorithm
        staticInformation.setText(" Time Complexity (Worst Case):\n" +
                " Time Complexity (Best Case):\n" +
                " Time Complexity (Overall):");
        dynamicInformation.setText("    Θ(N^2)\n      Θ(N)\n    O(N^2)");
        staticInformation.setFocusable(false);
        dynamicInformation.setFocusable(false);
        this.add(staticInformation);
        this.add(dynamicInformation);

        sortCode = new JTextPane();
        sortCode.setText("""
                public static void bubbleSort(int[] arr, int
                                           arraySize) {
                    int len = arraySize;
                    while(len>0) {
                        int n = 0;
                        for(int i=1;i<arraySize;i++) {
                            if(arr[i-1]>arr[i]) {
                                int temp = arr[i-1];
                                arr[i-1] = arr[i];
                                arr[i] = temp;
                                n = i;
                            }
                        }
                        len = n;
                    }
                }
                """);
        sortCode.setCaretPosition(0);
        sortCode.setFocusable(false);
        scrollCode = new JScrollPane(sortCode);
        scrollCode.setBounds(20, 138, 248, 197);
        scrollCode.getVerticalScrollBar().setPreferredSize(new Dimension(12, 0));
        scrollCode.setBorder(bottomLeftRightBorder);
        scrollCode.setFocusable(false);
        this.add(scrollCode);

        // This JSlider will be one way to edit the array size
        JSlider arraySizer = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        arraySizer.setBounds(20, 375, 248, 50);
        arraySizer.setBorder(normalLineBorder);
        arraySizer.setMajorTickSpacing(25);
        arraySizer.setMinorTickSpacing(5);
        arraySizer.setPaintTicks(true);
        arraySizer.setPaintLabels(true);
        this.add(arraySizer);

        ////////////////////////////// LISTENERS //////////////////////////////////////
        sliderVal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SortAlgos.sorter==null || SortAlgos.sorter.isDone()) { // If there is no sorting algorithm active
                    String temp = sliderVal.getText(); // Get value from input text
                    if(temp.isEmpty()) { // If the field is empty, set text to current array size
                        sliderVal.setText("" + SortAlgos.arraySize);
                    }else if(!isNumeric(temp)) { // If the field is not numeric, set text to current array size
                        sliderVal.setText("" + SortAlgos.arraySize);
                    }else {
                        int val = (int)Double.parseDouble(temp);
                        if(val <= 0) { // If the value is <= 0, set array size, text value, and slider value to 1
                            sliderVal.setText("1");
                            SortAlgos.arraySize = 1;
                            arraySizer.setValue(1);
                        }else if(SortAlgos.arraySize > 100) { // If the value is > 100, set array size, text value, and slider value to 100
                            sliderVal.setText("100");
                            SortAlgos.arraySize = 100;
                            arraySizer.setValue(100);
                        }else {
                            // Set array size and slider value to input value, then repaint the graph to reflect change
                            SortAlgos.arraySize = val;
                            arraySizer.setValue(SortAlgos.arraySize);
                            SortAlgos.heights = SortAlgos.createArray();
                            SortingAlgoVisualizer.graph.repaint();
                        }
                    }
                }
            }
        });

        arraySizer.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(SortAlgos.sorter==null || SortAlgos.sorter.isDone()) { // If there is no sorting algorithm active
                    if(((JSlider)e.getSource()).getValue()==0) { // If the slider value is 0, set array size to 1 to avoid / by 0 error
                        SortAlgos.arraySize = 1;
                    }else {
                        // Set array size to slider value
                        SortAlgos.arraySize = ((JSlider)e.getSource()).getValue();
                    }
                    sliderVal.setText("" + SortAlgos.arraySize); // Set text to new array size to reflect change
                    SortAlgos.heights = SortAlgos.createArray(); // Create new array from array size variable
                    SortingAlgoVisualizer.graph.repaint(); // Repaint graph to reflect change
                }else {
                    // Keep slider value to current array size for visual aesthetics
                    arraySizer.setValue(SortAlgos.arraySize);
                }
            }
        });
    }

    /**
     * Helper function that determines whether an input String object is numeric
     *
     * @param string        The String object to execute the function on
     * @return              Boolean value; True if input is numeric, false if input is non-numeric
     */
    private static boolean isNumeric(String string) {
        try {
            Double.parseDouble(string);
            return true;
        }catch(NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Initializes and displays JTextPane and JScrollPane for different sorting
     * algorithms' code implementations
     *
     * @param choice        The byte variable to display current sorting algorithm code
     */

    protected void createSortCode(byte choice) {
        this.remove(scrollCode); // Remove current JScrollPane to make edits
        if(choice==0) { // If Bubble Sort
            sortCode.setText("""
                public static void bubbleSort(int[] arr, int
                                           arraySize) {
                    int len = arraySize;
                    while(len>0) {
                        int n = 0;
                        for(int i=1;i<arraySize;i++) {
                            if(arr[i-1]>arr[i]) {
                                int temp = arr[i-1];
                                arr[i-1] = arr[i];
                                arr[i] = temp;
                                n = i;
                            }
                        }
                        len = n;
                    }
                }
                """);
        }else if(choice==1) {
            sortCode.setText("""
                    public static void selectionSort(int[] arr,
                                               int arraySize) {
                        for(int i=0;i<arraySize;i++) {
                            int min_index = i, min = arr[i];
                            for(int j=i+1;j<arraySize;j++) {
                                if(arr[j]<min) {
                                    min_index = j;
                                    min = arr[min_index];
                                }
                            }
                            if(min_index!=i) {
                                int temp = arr[i];
                                arr[i] = arr[min_index];
                                arr[min_index] = temp;
                            }
                        }
                    }""");
        }else if(choice==2) {
            sortCode.setText("""
                    public static void insertionSort(int[] arr,
                                               int arraySize) {
                        for(int i=1;i<arraySize;i++) {
                            int k, current = arr[i];
                            for(k=i-1;k>=0 &&
                                 arr[k]>current;k--) {
                                arr[k+1] = arr[k];
                            }
                            arr[k+1] = current;
                        }
                    }""");
        }else if(choice==3) {
            sortCode.setText("""
                    public static void heapSort(int[] arr, int
                                              arraySize) {
                        for(int i=(arraySize/2)-1;i>=0;i--) {
                            percolateDown(arr, i, arraySize);
                        }
                        for(int i=arraySize-1;i>0;i--) {
                            int temp = arr[0];
                            arr[0] = arr[i];
                            arr[i] = temp;
                            percolateDown(arr, 0, i);
                        }
                    }
                    
                    private static void percolateDown(int[]
                                               arr, int i, int n) {
                        int child = 2 * i + 1, temp;
                        for(temp=arr[i];child<n;i=child,
                             child=2*i+1) {
                            if(child!=n-1 && arr[child] <
                               arr[child+1]) {
                                child++;
                            }
                            if(temp<arr[child]) {
                                arr[i] = arr[child];
                            }else {
                                break;
                            }
                        }
                        arr[i] = temp;
                    }
                    """);
        }else if(choice==4) {
            sortCode.setText("""
                    public static void mergeSort(int[] arr, int
                                               arraySize) {
                        int[] scratch = new int[arraySize];
                        for(int i=1;i<arraySize;i*=2) {
                            for(int low=0;low<arraySize-1;
                                 low+=2*i) {
                                int mid = Math.min(low+i-1,
                                                             arraySize-1);
                                int high =  Math.min(low+2*i-1,
                                                             arraySize-1);
                                mergeSortHelper(arr, low, mid,
                                                            high);
                            }
                        }
                    }
                    
                    private static void mergeSortHelper(int[]
                                      arr, int low, int mid, int high) {
                        int left = low;
                        int right = mid+1;
                        int index = low;
                        while(left<=mid && right<=high) {
                            if(arr[left]<=arr[right]) {
                                scratch[index++] = arr[left++];
                            }else {
                                scratch[index++] = arr[right++];
                            }
                        }
                        while(left<=mid) {
                            scratch[index++] = arr[left++];
                        }
                        while(right<=high) {
                            scratch[index++] = arr[right++];
                        }
                        for(int j=low;j<=high;j++) {
                            arr[j] = scratch[j];
                        }
                    }
                    """);
        }else if(choice==5) {
            sortCode.setText("""
                    public static void quickSort(int[] arr, int
                                               arraySize) {
                        quickSortHelper(arr, 0, arraySize-1);
                    }
                    
                    private static void quickSortHelper(int[]
                                                arr, int p, int r) {
                        if(p<r) {
                            int q = lomutoPartition(arr, p, r);
                            quickSortHelper(arr, p, q - 1);
                            quickSortHelper(arr, q + 1, r);
                        }
                    }
                    
                    private static int lomutoPartition(int[] arr,
                                             int left, int right) {
                        int x = arr[left], s = left;
                        for(int j=left+1;j<=right;j++) {
                            if(arr[j]<x) {
                                s++;
                                if(s!=j) {
                                    int temp = arr[s];
                                    arr[s] = arr[j];
                                    arr[j] = temp;
                                }
                            }
                        }
                        if(s!=left) {
                            int temp = arr[s];
                            arr[s] = arr[left];
                            arr[left] = temp;
                        }
                    }
                    """);
        }else if(choice==6) {
            sortCode.setText("""
                    public static void countingSort(int[] arr) {
                        int max = -1;
                        for(int value : arr) {
                            if(max<value) {
                                max = value;
                            }
                        }
                        int[] counts = new int[max+1];
                        for(int value : arr) {
                            counts[value]++;
                        }
                        for(int i=0, k=0;i<counts.length;i++) {
                            for(int j=0, count=counts[i];j<count;
                                 j++) {
                                arr[k++] = i;
                            }
                        }
                    }
                    """);
        }else if(choice==7) {
            sortCode.setText("""
                    public static void bogoSort(int[] arr,
                                      int arraySize, int maxValue) {
                        while(!isSorted(arr, arraySize)) {
                            randomizeArray(arr, arraySize,
                                                     maxValue);
                        }
                    }
                    
                    private static boolean isSorted(int[] arr,
                                                                    int n) {
                        for(int i=1;i<n;i++) {
                            if(arr[i]<arr[i-1]) {
                                return false;
                            }
                        }
                        return true;
                    }
                    
                    private static void randomizeArray(int[]
                                            arr, int n, int maxValue) {
                        for(int i=0;i<n;i++) {
                            arr[i] = Math.random()*maxValue;
                        }
                    }
                    """);
        }
        sortCode.setCaretPosition(0); // Sets JScrollPane to top of text
        scrollCode = new JScrollPane(sortCode); // Initialize JScrollPane with sortCode text
        scrollCode.setBounds(20, 138, 248, 197);
        scrollCode.getVerticalScrollBar().setPreferredSize(new Dimension(12, 0)); // Set ScrollBar width
        scrollCode.setBorder(bottomLeftRightBorder);
        scrollCode.setFocusable(false); // Make JScrollPane uneditable
        this.add(scrollCode); // Display scrollCode in MyFrame
    }
}
