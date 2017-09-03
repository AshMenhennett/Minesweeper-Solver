package guru.ashley.minesweeperSolver;

import java.util.Scanner;

/**
 *
 * @author Ashley Menhennett <ashleymenhennett@gmail.com>
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // the minesweeper board
        MinesweeperBoard board;

        // System in Scanner
        Scanner in = new Scanner(System.in);

        // Request user input for row and column length of 2d array
        System.out.println("Enter two seperated integers (e.g. 3 5), representing the number of rows and columns in the Minesweeper board:");

        // read in one line of input
        String input = in.nextLine();

        // get numeric values of row and column size
        int rowsCount = Character.getNumericValue(input.charAt(0));
        int colsCount = Character.getNumericValue(input.charAt(2));

        // instantiate new MinesweeperBoard
        board = new MinesweeperBoard(rowsCount, colsCount);

        // request user input for row values of MinesweeperBoard
        System.out.println("Enter " + colsCount + " non-seperated values that represent the values for each row of the Minesweeper board where * denotes a cell with a mine and . denotes any other cell (e.g. **..*).\nPress Enter to go to the next row:");

        // used to store the values entered in for the Cell configuration of the MinesweeperBoard
        String [] rowValues = new String[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            // read in line from System in Scanner
            rowValues[i] = in.nextLine();
            for (int j = 0; j < colsCount; j++) {
                // populate the board for each row input entered
                board.addCell(i, j, rowValues[i].charAt(j));
            }
        }

        // invoke the solve method on MinesweeperBoard
        board.solve();
    }

}
