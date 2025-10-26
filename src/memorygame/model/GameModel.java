package memorygame.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Timer;

public class GameModel {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private List<MemoryCardModel> cards = new ArrayList<>();
    private int rows, cols;
    private int firstIndex = -1;
    private int secondIndex = -1;
    private boolean boardLocked = false;

    public void initGame(int rows, int cols) {
        if ((rows * cols) % 2 != 0) throw new IllegalArgumentException("Even number of cards required");
        this.rows = rows; this.cols = cols;
        cards.clear();
        int pairs = (rows * cols) / 2;
        for (int i = 0; i < pairs; i++) {
            // for each pair 2 objects are created from MemoryCardModel
            // with the same pairId = i
            cards.add(new MemoryCardModel(i));
            cards.add(new MemoryCardModel(i));
        }
        Collections.shuffle(cards);
        pcs.firePropertyChange("gameReset", null, null); // all listeners get info about new game for new UI-Rendering
    }

    public List<MemoryCardModel> getCards() { return Collections.unmodifiableList(cards); }

    public void addListener(PropertyChangeListener l) { pcs.addPropertyChangeListener(l); }
    public void removeListener(PropertyChangeListener l) { pcs.removePropertyChangeListener(l); }

    public void flipCard(int index) {
        if (boardLocked) return;
        MemoryCardModel clicked = cards.get(index);
        if (clicked.isFaceUp() || clicked.isMatched()) return;

        clicked.setFaceUp(true);
        pcs.firePropertyChange("cardUpdated", -1, index);

        if (firstIndex == -1) {
            firstIndex = index;
            return;
        }
        if (secondIndex == -1 && index != firstIndex) {
            secondIndex = index;
            // compare
            MemoryCardModel a = cards.get(firstIndex);
            MemoryCardModel b = cards.get(secondIndex);
            if (a.getPairId() == b.getPairId()) {
                a.setMatched(true); b.setMatched(true);
                pcs.firePropertyChange("match", null, new int[]{firstIndex, secondIndex});
                firstIndex = secondIndex = -1;
            } else {
                boardLocked = true;
                // Timer to flip back after delay
                Timer t = new Timer(700, e -> {
                    a.setFaceUp(false);
                    b.setFaceUp(false);
                    pcs.firePropertyChange("cardUpdated", -1, firstIndex);
                    pcs.firePropertyChange("cardUpdated", -1, secondIndex);
                    firstIndex = secondIndex = -1;
                    boardLocked = false;
                });
                t.setRepeats(false);
                t.start();
            }
        }
    }
}
