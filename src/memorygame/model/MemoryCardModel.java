package memorygame.model;

public class MemoryCardModel {
    private final int pairId;
    private boolean faceUp = false;
    private boolean matched = false;

    public MemoryCardModel(int pairId) { this.pairId = pairId; }
    public int getPairId() { return pairId; }
    public boolean isFaceUp() { return faceUp; }
    public void setFaceUp(boolean faceUp) { this.faceUp = faceUp; }
    public boolean isMatched() { return matched; }
    public void setMatched(boolean matched) { this.matched = matched; }
}