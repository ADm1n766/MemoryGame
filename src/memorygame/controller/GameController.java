package memorygame.controller;

import memorygame.model.GameModel;
import memorygame.model.MemoryCardModel;
import memorygame.view.MemoryGameView;

import java.beans.PropertyChangeEvent;

public class GameController {
    private final GameModel model;
    private final MemoryGameView view;

    public GameController(GameModel model, MemoryGameView view) {
        this.model = model;
        this.view = view;

        model.addListener(evt -> handleModelEvent(evt));
    }

    public void start(int rows, int cols) {
        model.initGame(rows, cols);
        view.buildBoard(rows * cols);
        // register button listeners
        for (int i = 0; i < model.getCards().size(); i++) {
            final int idx = i;
            view.setCardListener(i, e -> model.flipCard(idx));
        }
        // initial render
        for (int i = 0; i < model.getCards().size(); i++) {
            view.updateCardView(i, model.getCards().get(i));
        }
    }

    private void handleModelEvent(PropertyChangeEvent evt) {
        String name = evt.getPropertyName();
        if ("cardUpdated".equals(name)) {
            int idx = (int) evt.getNewValue();
            MemoryCardModel m = model.getCards().get(idx);
            view.updateCardView(idx, m);
        } else if ("match".equals(name)) {
            int[] pair = (int[]) evt.getNewValue();
            view.updateCardView(pair[0], model.getCards().get(pair[0]));
            view.updateCardView(pair[1], model.getCards().get(pair[1]));
        } else if ("gameReset".equals(name)) {
            // rebuild UI if necessary
        }
    }
}
