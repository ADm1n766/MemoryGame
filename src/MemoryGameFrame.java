import java.awt.BorderLayout;
import java.awt.GridLayout;
// import java.awt.List; -> Warum ist das falsch?
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class MemoryGameFrame extends JFrame{

    private int rows;
    private int cols;
    private List<MemoryCard> cards;

    private int cardStatus = -1;
    private int cardChosen1;
    private int cardChosen2;
    private int maxPlayer;






    public MemoryGameFrame(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.cards = new ArrayList<>();
        initUI();

    }
    private void initUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Memory-Spiel");
        this.setLayout(new BorderLayout());

        //Panel für Karten
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(rows, cols));

        // Erzeuge Kartenobjekte in pairs
        int numPairs = (rows * cols) / 2;
        for (int pid = 0; pid < numPairs; pid++){
            // 2 cards with the same pairId
            MemoryCard c1 = new MemoryCard(pid);
            MemoryCard c2 = new MemoryCard(pid);
            cards.add(c1);
            cards.add(c2);
        }

        // shuffle cards
        Collections.shuffle(cards);

        // add Buttons
        for (MemoryCard card : cards) {
            JButton btn = card.geButton();
            boardPanel.add(btn);
            // here can be placed a ActionListener for later actions
        }


        this.add(boardPanel, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);  // zentrieren
        this.setVisible(true);
    }
















}


/* 


     private static void createAndShowGUI(){
        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel emptyLabel = new JLabel("Label 1");
        emptyLabel.setPreferredSize(new Dimension(175, 100));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        //display the window
        frame.pack();
        frame.setVisible(true);
        
     }











     public static void main(String[] args) {
        // scheduling a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
     }
    
}
*/
