import javax.swing.JButton;

public class MemoryCard {

    private JButton button;
    private int pairId;
    private boolean faceUp = false;
    private boolean matched = false;

    public MemoryCard(int pairId){
        this.pairId = pairId;
        this.button = new JButton();
        // am Anfang sind alle Karten verdeckt, also kein Icon
    }
    public JButton geButton(){
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
    }
    public boolean isMatched(){
        return matched;
    }
    public void setMatched(boolean m){
        this.matched = m;
    }


    
}
