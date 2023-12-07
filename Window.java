import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame {
    Board board;

    public Window(String title, Board board) {
        super(title);

        this.board = board;
        setVisible(true);
        setBounds(0, 0, 800, 800);
        addWindowListener(new WindowDestroyer());

        getContentPane().setLayout(null);
        paint(getGraphics());
    }

    public void paint(Graphics g) {
        super.paint(g);

        // Dimension size = getSize();
        Rectangle size = getBounds();
        int cellHeight = size.height / this.board.height;
        int cellWidth = size.width / this.board.width;

        setBounds(0, 0, cellWidth * this.board.width, cellHeight * this.board.height);

        g.setColor(Color.BLACK);
        for (int i = 0; i < this.board.height; i++) {
            for (int j = 0; j < this.board.width; j++) {
                if (this.board.board[i][j]) {
                    g.fillRect(cellWidth * j, cellHeight * i, cellWidth, cellHeight);
                }
            }
        }
    }
}

class WindowDestroyer extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
