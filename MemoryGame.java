import javax.swing.SwingUtilities;

public class MemoryGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MemoryGameFrame(4, 4);
        });
    }
    
}
