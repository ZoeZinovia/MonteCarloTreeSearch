import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface Ilayout.
 */
public interface Ilayout {
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public int getStatus();
	
	/**
	 * Gets the empty positions.
	 *
	 * @return the empty positions
	 */
	public List<Position> getEmptyPositions();
	
	/**
	 * Execute move.
	 *
	 * @param playerNo the player no
	 * @param p the p
	 */
	public void executeMove(int playerNo, Position p);
	
	/**
	 * Rotate board.
	 *
	 * @return the ilayout
	 */
	public Ilayout rotateBoard();
	
	/**
	 * Copy of.
	 *
	 * @return the ilayout
	 */
	public Ilayout copyOf();
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString();
	
	/**
	 * To string solution.
	 *
	 * @return the string
	 */
	public String toStringSolution();
}
