package memorygame.view;

import memorygame.model.MemoryCardModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MemoryGameView {
    private final JFrame frame;
    private final JPanel boardPanel;
    private final List<JButton> buttons = new ArrayList<>();

    public MemoryGameView(int rows, int cols) {
        frame = new JFrame("Memory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        boardPanel = new JPanel(new GridLayout(rows, cols, 6, 6));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void buildBoard(int size) {
        boardPanel.removeAll();
        buttons.clear();
        for (int i = 0; i < size; i++) {
            JButton b = new JButton("?");
            b.setFocusable(false);
            b.setPreferredSize(new Dimension(80,80));
            buttons.add(b);
            boardPanel.add(b);
        }
        frame.pack();
        frame.setVisible(true);
    }

    public void setCardListener(int index, ActionListener listener) {
        buttons.get(index).addActionListener(listener);
    }

    public void updateCardView(int index, MemoryCardModel m) {
        JButton b = buttons.get(index);
        if (m.isMatched()) {
            b.setText(Integer.toString(m.getPairId()));
            b.setEnabled(false);
        } else if (m.isFaceUp()) {
            b.setText(Integer.toString(m.getPairId()));
        } else {
            b.setText("?");
        }
    }

    public JFrame getFrame() { return frame; }
}
