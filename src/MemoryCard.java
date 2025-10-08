import javax.swing.JButton;

public class MemoryCard {

    private final JButton button;
    private final int pairId;
    private boolean faceUp = false;
    private boolean matched = false;

    // text for Icons
    private final String frontText;
    private final String backText = "?";

    public MemoryCard(int pairId){
        this.pairId = pairId;
        this.frontText = Integer.toString(pairId); // front card shows pairId
        this.button = new JButton(backText); // in the beginning backside visible
        // am Anfang sind alle Karten verdeckt, also kein Icon
    }
    public JButton getButton(){
        return button;
    }
    public int getPairId(){
        return pairId;
    }
    public boolean isFaceUp(){
        return faceUp;
    }
    public void setFaceUp(boolean faceUp){
        this.faceUp = faceUp;
        updateView();
    }
    public boolean isMatched(){
        return matched;
    }
    public void setMatched(boolean m){
        this.matched = m;
        updateView();
    }
    private void updateView(){
        if (matched || faceUp) {
            button.setText(frontText);
            // later: button.setIcon(frontIcon)
        } else {
            button.setText(backText);
            // later: button.setIcon(backIcon)
        }
    }
}
