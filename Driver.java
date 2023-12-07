
class Driver {
    public static void main(String[] args) {
        int height = 100;
        int width = 100;

        Board board = new Board(height, width);
        board.randomizeBoard();

        Window window = new Window("Game of Life", board);

        while (true) {
            window.repaint();
            board.nextGeneration();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Interrupted!");
            }
        }
    }
}