import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
// import java.awt.List; <- Why is it not right?
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class MemoryGameFrame extends JFrame{

    private final int rows;
    private final int cols;
    private final List<MemoryCard> cards;
    private int cardStatus = -1;
    private int cardChosen1;
    private int cardChosen2;
    private int maxPlayer;

    // practical intern conditions for simple matching
    private MemoryCard firstPick = null;
    private MemoryCard secondPick = null;
    private boolean boardLocked = false;


    public MemoryGameFrame(int rows, int cols){
        if ((rows * cols) % 2 != 0){
            throw new IllegalArgumentException("Anzahl der Felder muss gerade sein (Paare).");
        }
        this.rows = rows;
        this.cols = cols;
        this.cards = new ArrayList<>();
        initUI();

    }
    private void initUI(){
        this.setTitle("Memory-Spiel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Panel for cards
        JPanel boardPanel = new JPanel(new GridLayout(rows, cols, 5, 5));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // create card-objects in pairs
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

        // add Buttons, for-each-loop
        for (MemoryCard card : cards) {
            JButton btn = card.getButton();

            // optical changes
            btn.setFocusable(false); // no keyboard focus, tab not any longer available 
            btn.setPreferredSize(new Dimension(80,80));

            btn.addActionListener(e -> {
                if (!boardLocked) {
                    handleCardClick(card);
                }
            });

            boardPanel.add(btn);
        }
        /*
         * for-loop
         * for (int i = 0; i < cards.size(); i++) {
                MemoryCard card = cards.get(i);
                ...
            }
         */

        add(boardPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);  // ?
        setVisible(true);
    }
    /**
     * handleCardClick: Called when a card is clicked.
     * - Checks whether a card may be opened/compared
     * - Handles match/no match with a short delay (timer)
     * @param clicked
     */
    private void handleCardClick(MemoryCard clicked){
        // ignore clicks on cards that have already been revealed or matched
        if (clicked.isFaceUp() || clicked.isMatched()) {
            return;
        }

        // pick first card
        if (firstPick == null) {
            firstPick = clicked;
            firstPick.setFaceUp(true);
            return;
        }

        // if first card already exists, then set second card (if it doesn't exist)
        if (secondPick == null && clicked != firstPick) {
            secondPick = clicked;
            secondPick.setFaceUp(true);

            // now 2 cards are open -> compare
            if (firstPick.getPairId() == secondPick.getPairId()) {
                // match: mark both as found
                firstPick.setMatched(true);
                secondPick.setMatched(true);

                // update counters 
                firstPick = null;
                secondPick = null;
            } else {
                // no match: show for short, then hide, lock inputs
                boardLocked = true;
                Timer t = new Timer(800, ev -> {
                    firstPick.setFaceUp(false);
                    secondPick.setFaceUp(false);
                    firstPick = null;
                    secondPick = null;
                    boardLocked = false;

                });
                t.setRepeats(false);
                t.start();
            }
        }
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
