package SortingAlgorithmProgram;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Timer;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Stack;

/**
 *  Class that contains all sorting algorithms for the application
 *
 * @author Marcos E. Mancia Jr.
 * @version 1.0
 */
public class SortAlgos extends JPanel{
    // Declare Java Swing variables
    private static final Border normalLineBorder = BorderFactory.createLineBorder(new Color(53, 57,53));
    protected static SortWorker sorter; // Used for most of the sorting algorithms
    protected static Timer bogoSort; // Used specifically for Bogo Sort

    // Declare primitive variables
    protected static int arraySize = 50;
    protected static int[] heights = createArray(); // Used to display the bars of the histogram
    private static int t = -1, v = -1; // Used for Selection Sort and Quick Sort bar coloring

    /**
     * Constructor for SortAlgos class
     */
    public SortAlgos() {
        // Set up the Graph formatting
        this.setBounds(189, 10, 300, 280);
        this.setBorder(normalLineBorder);
        this.setBackground(Color.WHITE);
    }

    /**
     * Initializes SortWorker to run sorting algorithms
     *
     * @param choice    The byte variable that selects the sorting algorithm
     */
    protected void sort(byte choice) {
        sorter = new SortWorker(heights, choice);
        sorter.execute();
    }

    /**
     * Initializes Swing Timer to run Bogo Sort
     */
    protected void bogoSort() {
        bogoSort = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isSorted()) {
                    heights = createArray();
                    repaint();
                }else {
                    bogoSort.stop();
                    GraphPanel.buttonClicks = 0;
                }
            }
        });
        bogoSort.start();
    }

    /**
     * Create SwingWorker class that executes sorting algorithms
     */
    class SortWorker extends SwingWorker<Void, int[]> {
        // Declare private variables to hold input arguments
        private int[] values;
        private final byte typeOfSort;

        /**
         * Constructor for SortWorker class that executes sorting algorithms
         *
         * @param unsortedArray     The int[] array to be sorted
         * @param sortChoice        The byte variable that chooses sorting algorithm
         */
        public SortWorker(int[] unsortedArray, byte sortChoice) {
            values = unsortedArray;
            typeOfSort = sortChoice;
        }

        @Override
        protected Void doInBackground() {
            if(typeOfSort==0) { // Start Bubble Sort
                int len = arraySize;
                while(len>0) {
                    int n = 0;
                    for(int i=1;i<arraySize;i++) {
                        if(values[i-1]>values[i]) {
                            swap(values, i-1, i);
                            n = i;
                            publish(values);
                            try { Thread.sleep(50); } catch(InterruptedException ie) { ie.printStackTrace(); }
                        }
                    }
                    len = n;
                }
                GraphPanel.buttonClicks = 0;
            }
            else if(typeOfSort==1) { // Start Selection Sort
                for(int i=0;i<arraySize;i++) {
                    int min_index = i, min = values[i];
                    for(int j=i+1;j<arraySize;j++) {
                        if(values[j]<min) {
                            min_index = j;
                            min = values[min_index];
                        }
                    }
                    if(min_index!=i) {
                        t = i;
                        v = min_index;
                        swap(values, i, min_index);
                        publish(values);
                        try { Thread.sleep(175); } catch(InterruptedException ie) { ie.printStackTrace(); }
                    }
                }
                GraphPanel.buttonClicks = 0;
                repaint();
            }
            else if(typeOfSort==2) { // Start Insertion Sort
                for(int i=1;i<arraySize;i++) {
                    int k, current = values[i];
                    for(k=i-1;k>=0 && values[k]>current;k--) {
                        values[k+1] = values[k];
                        publish(values);
                        try { Thread.sleep(25); } catch(InterruptedException ie) { ie.printStackTrace(); }
                    }
                    values[k+1] = current;
                    publish(values);
                    try { Thread.sleep(25); } catch(InterruptedException ie) { ie.printStackTrace(); }
                }
                GraphPanel.buttonClicks = 0;
            }
            else if(typeOfSort==3) { // Start Heap Sort
                for(int i=(arraySize/2)-1;i>=0;i--) {
                    percolateDown(values, i, arraySize);
                }
                for(int i=arraySize-1;i>0;i--) {
                    int temp = values[0];
                    values[0] = values[i];
                    values[i] = temp;
                    publish(values);
                    try { Thread.sleep(100); } catch(InterruptedException ie) { ie.printStackTrace(); }
                    percolateDown(values, 0, i);
                }
                GraphPanel.buttonClicks = 0;
            }
            else if(typeOfSort==4) { // Start Iterative Merge Sort
                int[] scratch = new int[arraySize];
                for(int i=1;i<arraySize;i*=2) {
                    for(int low=0;low<arraySize-1;low+=2*i) {
                        int mid = Math.min(low+i-1, arraySize-1);
                        int high =  Math.min(low+2*i-1, arraySize-1);

                        int left = low;
                        int right = mid+1;
                        int index = low;
                        while(left<=mid && right<=high) {
                            if(values[left]<=values[right]) {
                                scratch[index++] = values[left++];
                            }else {
                                scratch[index++] = values[right++];
                            }
                        }
                        while(left<=mid) {
                            scratch[index++] = values[left++];
                        }
                        while(right<=high) {
                            scratch[index++] = values[right++];
                        }
                        for(int j=low;j<=high;j++) {
                            values[j] = scratch[j];
                            publish(values);
                            try { Thread.sleep(25); } catch(InterruptedException ie) { ie.printStackTrace(); }
                        }
                    }
                }
                GraphPanel.buttonClicks = 0;
            }
            else if(typeOfSort==5) { // Start Iterative Quick Sort
                Stack<Integer> stack = new Stack<>();
                stack.push(0);
                stack.push(arraySize);
                while(!stack.isEmpty()) {
                    int end = stack.pop();
                    int start = stack.pop();
                    if(end-start<2) { continue; }
                    int p = start + ((end-start)/2);

                    int l = start;
                    int h = end - 2;
                    int piv = values[p];
                    t = p;
                    v = end - 1;
                    swap(values, p, end-1);
                    publish(values);
                    try { Thread.sleep(150); } catch(InterruptedException ie) { ie.printStackTrace(); }

                    while (l < h) {
                        if (values[l] < piv) {
                            l++;
                        } else if (values[h] >= piv) {
                            h--;
                        } else {
                            t = l;
                            v = h;
                            swap(values,l,h);
                            publish(values);
                            try { Thread.sleep(150); } catch(InterruptedException ie) { ie.printStackTrace(); }
                        }
                    }
                    int idx = h;
                    if (values[h] < piv) { idx++; }
                    t = end - 1;
                    v = idx;
                    swap(values,end-1,idx);
                    publish(values);
                    try { Thread.sleep(150); } catch(InterruptedException ie) { ie.printStackTrace(); }
                    /////////////////////////////////
                    p = idx;

                    stack.push(p+1);
                    stack.push(end);
                    stack.push(start);
                    stack.push(p);
                }
                GraphPanel.buttonClicks = 0;
                repaint();
            }
            else if(typeOfSort==6) { // Start Counting Sort
                int max = -1;
                for(int value : values) {
                    if(max < value) {
                        max = value;
                    }
                }
                int[] counts = new int[max+1];
                for(int value : values) {
                    counts[value]++;
                }
                for(int i=0, k=0;i < counts.length;i++) {
                    for(int j=0, count=counts[i];j<count;j++) {
                        values[k++] = i;
                        publish(values);
                        try { Thread.sleep(75); } catch(InterruptedException ie) { ie.printStackTrace(); }
                    }
                }
                GraphPanel.buttonClicks = 0;
            }

            return null;
        }

        @Override
        protected void process(List<int[]> arr) {
            // Assign heights variable to newly sorted values variable
            heights = arr.get(arr.size()-1);
            repaint();
        }

        @Override
        protected void done() { }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(SortAlgos.arraySize==0) { // Ensure / by 0 error doesn't occur
            return;
        }
        super.paintComponent(g);

        int barWidth = 300/SortAlgos.arraySize; // Set bar width using array size and width of panel (300 px)
        int spaceFromBorder = (300%SortAlgos.arraySize)/2; // Set padding to center bars in center of JPanel
        byte sortingChoice = OptionsPanel.sortingChoice; // Used to determine which algorithm is selected
        if(sortingChoice==1 || sortingChoice==5) { // If Selection Sort or Quick Sort
            for(int i=0;i<SortAlgos.arraySize;i++) {
                int height = heights[i];

                if(i==t) {
                    g.setColor(new Color(150, 0, 0));
                    t = -1;
                }else if(i==v) {
                    g.setColor(new Color(0, 0, 210));
                    v = -1;
                }else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect((i * barWidth)+spaceFromBorder, 300-height, barWidth, height);
            }
        }else { // Used for every other sorting algorithm
            g.setColor(Color.BLACK);
            for(int i=0;i<SortAlgos.arraySize;i++) {
                int height = heights[i];
                g.fillRect((i * barWidth)+spaceFromBorder, 300-height, barWidth, height);
            }
        }

    }

    /**
     * Determines whether the heights array is sorted
     *
     * @return      Boolean value; True for sorted, False for unsorted
     */
    protected static boolean isSorted() {
        for(int i=1;i<arraySize;i++) {
            if(heights[i-1]>heights[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates a new, randomized array with min value of 25, and max value of 280
     *
     * @return      New int[] array containing randomized values
     */
    protected static int[] createArray() {
        int[] temp = new int[arraySize];
        for(int i=0;i<arraySize;i++) {
            temp[i] = (int)(Math.random() * 255)+25;
        }

        return temp;
    }

    /**
     * Helper function that swaps an array's values with given indices
     *
     * @param arr       The int[] array to execute the function on
     * @param i         The int variable assigned to first index
     * @param j         The int variable assigned to second index
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Helper function for heap sort. Ensures that the min-heap property is maintained throughout
     * the sort
     *
     * @param arr       The int[] array to execute the function on
     * @param i         The int variable assigned to the input array index
     * @param n         The int variable assigned to the size of the array
     */
    private static void percolateDown(int[] arr, int i, int n) {
        int child = 2 * i + 1, temp;
        for(temp=arr[i];child<n;i=child, child=2*i+1) {
            if(child!=n-1 && arr[child] < arr[child+1]) {
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
}