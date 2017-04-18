package guru.ashley.minesweeperSolver;

/**
 *
 * @author Ashley Menhennett <ashleymenhennett@gmail.com>
 */
public class Cell {

    /**
     * Instance properties
     */

    // value denotes the type of cell this Cell is.
    // i.e. if value === '*', it is a mine Cell.
    private char value;

    /**
     * Cell Constructor.
     *
     * @param  char value - the value property of the Cell.
     */
    public Cell (char value) {
        this.value = value;
    }

    /**
     * Getter for value property.
     *
     * @return char
     */
    public char getValue() {
        return this.value;
    }

    /**
     * Setter for value property.
     *
     * @param char value - value for the Cell.
     */
    public void setValue(char value) {
        this.value = value;
    }

    /**
     * Does the Cell contain a mine? If so, return true.
     *
     * @return boolean
     */
    public boolean hasMine() {
        return this.value == '*';
    }

    /**
     * Does the Cell contain a numeric value? If so, return true.
     *
     * @return boolean
     */
    public boolean hasNumericValue() {
        return Character.isDigit(this.value);
    }

}
