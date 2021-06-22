
/**
 * The Class Position.
 */
public class Position {
	
	/** The row. */
	private int row;
	
	/** The col. */
	private int col;
	
	/**
	 * Instantiates a new position.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public Position(int row, int col) {
		setRow(row);
		setCol(col);
	}

	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Sets the row.
	 *
	 * @param row the new row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Gets the col.
	 *
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Sets the col.
	 *
	 * @param col the new col
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	/**
	 * Equals.
	 *
	 * @param o the o
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object o) {
		if (getClass() != o.getClass()) return false;
		Position that = (Position) o;
		return ((this.row == that.row) && (this.col == that.col));
	}
}
