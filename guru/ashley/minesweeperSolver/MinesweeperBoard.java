package guru.ashley.minesweeperSolver;

/**
 *
 * @author Ashley Menhennett <ashleymenhennett@gmail.com>
 */
public class MinesweeperBoard {

    /**
    * Instance Properties
    */
    private int rowsCount;
    private int colsCount;
    private Cell cells[][];

    /**
    * Constructor - Creates an instance of MinesweeperBoard.
    *
    * @param  int rowsCount - Used to keep track of the bounds of the 2d array.
    * @param  int colsCount - Used to keep track of the bounds of the 2d array.
    */
    public MinesweeperBoard(int rowsCount, int colsCount){
        this.rowsCount = rowsCount;
        this.colsCount = colsCount;
        this.cells = new Cell[rowsCount][colsCount];
    }

    /**
    * Adds a Cell instance to the cells data structure at a given position.
    *
    * @param  int row - the row index to add the Cell instance at.
    * @param  int col - the column index to add the Cell instance at.
    * @param  char value - the value property for the Cell instance.
    */
    public void addCell(int row, int col, char value) {
        this.cells[row][col] = new Cell(value);
    }

    /**
    * Sets the value property for a Cell at a given position.
    *
    * @param  int row - the row index for the Cell at which to set the value property.
    * @param  int col - the column index for the Cell at which to set the value property.
    * @param  char value - the value property to set.
    * @return boolean - whether there was a Cell instance at the given position for a value property to be set.
    */
    public boolean setCell(int row, int col, char value) {
        if (this.cells[row][col] instanceof Cell == false) {
            return false;
        }
        this.cells[row][col].setValue(value);
        return true;
    }

    /**
    * Helper Methods
    */

    /**
    * Checks if given indices are with in the bounds of the cells 2d array.
    *
    * @param  int row - the row index to check against the count of rows for bounds.
    * @param  int col - the column index to check the count of columns for bounds.
    * @return boolean
    */
    private boolean cell_in_bounds(int row, int col) {
        return row >= 0
            && row < rowsCount
            && col >= 0
            && col < colsCount;
    }

    /**
     * Retrieves a Cell at a given position in the cells 2d array.
     *
     * @param  int row - the row index for the Cell position.
     * @param  int col - the column index for the Cell position.
     * @return Cell | null
     */
    private Cell get_cell(int row, int col){
        if (! cell_in_bounds(row,col)) {
            return null;
        }
        return this.cells[row][col];
    }

    /**
     * Increments the 'numeric' value of a Cell.
     *
     * @param  int row - the row index for the Cell position.
     * @param  int col - the column index for the Cell position.
     * @return boolean
     */
    private boolean update_cell(int row, int col) {
        if (this.cells[row][col].hasNumericValue()) {
            int val = (int)this.cells[row][col].getValue()+1;
            this.cells[row][col].setValue((char)val);
            return true;
        }
        return false;
    }

    /**
     * Retrieves a Cell relative to the current Cell, called from solve().
     *
     * @param  int row - the row index for the Cell position.
     * @param  int col - the column index for the Cell position.
     * @return Cell | null
     */
    private Cell get_top_cell(int row, int col) {
        row--;
        return get_cell(row, col);
    }

    /**
     * Retrieves a Cell relative to the current Cell, called from solve().
     *
     * @param  int row - the row index for the Cell position.
     * @param  int col - the column index for the Cell position.
     * @return Cell | null
     */
    private Cell get_top_right_diagnoal_cell(int row, int col) {
        row--;
        col++;
        return get_cell(row, col);
    }

    /**
     * Retrieves a Cell relative to the current Cell, called from solve().
     *
     * @param  int row - the row index for the Cell position.
     * @param  int col - the column index for the Cell position.
     * @return Cell | null
     */
    private Cell get_right_cell(int row, int col) {
        col++;
        return get_cell(row, col);
    }

    /**
     * Retrieves a Cell relative to the current Cell, called from solve().
     *
     * @param  int row - the row index for the Cell position.
     * @param  int col - the column index for the Cell position.
     * @return Cell | null
     */
    private Cell get_bottom_right_diagnoal_cell(int row, int col) {
        row++;
        col++;
        return get_cell(row, col);
    }

    /**
     * Retrieves a Cell relative to the current Cell, called from solve().
     *
     * @param  int row - the row index for the Cell position.
     * @param  int col - the column index for the Cell position.
     * @return Cell | null
     */
    private Cell get_bottom_cell(int row, int col) {
        row++;
        return get_cell(row, col);
    }

    /**
     * Retrieves a Cell relative to the current Cell, called from solve().
     *
     * @param  int row - the row index for the Cell position.
     * @param  int col - the column index for the Cell position.
     * @return Cell | null
     */
    private Cell get_bottom_left_diagnoal_cell(int row, int col) {
        row++;
        col--;
        return get_cell(row, col);
    }

    /**
     * Retrieves a Cell relative to the current Cell, called from solve().
     *
     * @param  int row - the row index for the Cell position.
     * @param  int col - the column index for the Cell position.
     * @return Cell | null
     */
    private Cell get_left_cell(int row, int col) {
        col--;
        return get_cell(row, col);
    }

    /**
     * Retrieves a Cell relative to the current Cell, called from solve().
     *
     * @param  int row - the row index for the Cell position.
     * @param  int col - the column index for the Cell position.
     * @return Cell | null
     */
    private Cell get_top_left_diagnoal_cell(int row, int col) {
        row--;
        col--;
        return get_cell(row, col);
    }


    /**
     * Solves the minesweeper game.
     */
    public void solve() {
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < colsCount; j++) {

                // if the current Cell contains a mine, let's skip this iteration.
                if (this.cells[i][j].hasMine()) {
                    continue;
                }

                // for all non-mine cells, set the default value to 0
                cells[i][j].setValue('0');


                // let's get all Cells surrounding the current Cell,
                // checking if the other Cells have a mine.
                // if there is a mine Cell touching the current Cell,
                // proceed to update the value of the current Cell.
                Cell topCell = get_top_cell(i, j);
                if (topCell != null && topCell.hasMine() == true) {
                    update_cell(i, j);
                }

                Cell trdCell = get_top_right_diagnoal_cell(i, j);
                if (trdCell != null && trdCell.hasMine() == true) {
                    update_cell(i, j);
                }

                Cell rightCell = get_right_cell(i, j);
                if (rightCell != null && rightCell.hasMine() == true) {
                    update_cell(i, j);
                }

                Cell brdCell = get_bottom_right_diagnoal_cell(i, j);
                if (brdCell != null && brdCell.hasMine() == true) {
                    update_cell(i, j);
                }

                Cell bottomCell = get_bottom_cell(i, j);
                if (bottomCell != null && bottomCell.hasMine() == true) {
                    update_cell(i, j);
                }

                Cell bldCell = get_bottom_left_diagnoal_cell(i, j);
                if (bldCell != null && bldCell.hasMine() == true) {
                    update_cell(i, j);
                }

                Cell leftCell = get_left_cell(i, j);
                if (leftCell != null && leftCell.hasMine() == true) {
                    update_cell(i, j);
                }


                Cell tldCell = get_top_left_diagnoal_cell(i, j);
                if (tldCell != null && tldCell.hasMine() == true) {
                    update_cell(i, j);
                }
            }
        }

        // print the solution to System out
        print_solution();
    }

    /**
     * Prints the solution of the minesweeper game to System out.
     */
    private void print_solution() {
        System.out.print("\n");
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < colsCount; j++) {
              System.out.print(cells[i][j].getValue());
            }
            System.out.print("\n");
        }
    }

}
