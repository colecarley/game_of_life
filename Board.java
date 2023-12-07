
public class Board {
    public boolean[][] board;
    public int height;
    public int width;

    Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.board = new boolean[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.board[i][j] = false;
            }
        }
    }

    public void randomizeBoard() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.board[i][j] = Math.random() < 0.5;
            }
        }
    }

    public void updateBoard(boolean[][] XOR) {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.board[i][j] ^= XOR[i][j];
            }
        }
    }

    private int getNeighbors(int i, int j) {
        boolean[] neighbors = {
                this.board[(i + 1 + this.height) % this.height][(j + 1 + this.width) % this.width],
                this.board[(i + 1 + this.height) % this.height][(j + this.width) % this.width],
                this.board[(i + this.height) % this.height][(j + 1) % this.width],
                this.board[(i - 1 + this.height) % this.height][(j + this.width) % this.width],
                this.board[(i - 1 + this.height) % this.height][(j - 1 + this.width) % this.width],
                this.board[(i + this.height) % this.height][(j - 1 + this.width) % this.width],
                this.board[(i + 1 + this.height) % this.height][(j - 1 + this.width) % this.width],
                this.board[(i - 1 + this.height) % this.height][(j + 1 + this.width) % this.width]
        };

        int count = 0;
        for (int k = 0; k < 8; k++) {
            if (neighbors[k]) {
                count++;
            }
        }

        return count;
    }

    private boolean[][] makeXOR() {
        boolean[][] XOR = new boolean[this.height][this.width];

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                XOR[i][j] = false;
            }
        }

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                int count = getNeighbors(i, j);

                boolean alive = this.board[i][j];
                if (alive) {
                    XOR[i][j] = count < 2 || count > 3;
                } else {
                    XOR[i][j] = count == 3;
                }
            }
        }
        return XOR;
    }

    public void nextGeneration() {
        boolean[][] XOR = makeXOR();
        updateBoard(XOR);
    }
}
