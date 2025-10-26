package memorygame;

import memorygame.controller.GameController;
import memorygame.model.GameModel;
import memorygame.view.MemoryGameView;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int rows = 4, cols = 4;
            GameModel model = new GameModel();
            MemoryGameView view = new MemoryGameView(rows, cols);
            GameController controller = new GameController(model, view);
            controller.start(rows, cols);
        });
    }
}
