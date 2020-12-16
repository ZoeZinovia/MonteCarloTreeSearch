import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class TicTacToe.
 */
public class TicTacToe implements Ilayout{
	
	/** The board. */
	private int[][] board; //initializes to 0 as default
	
	/** The status. */
	private int status = -1; //-1 means in progress, 0 means draw, 1 means player 1 won, 2 means player 2 won 
	
	/** The dimension. */
	private final int dimension = 3;
	
	/**
	 * Instantiates a new tic tac toe.
	 */
	public TicTacToe() {
		setBoard(new int[dimension][dimension]);
	}
	
	/**
	 * Instantiates a new tic tac toe.
	 *
	 * @param exampleBoard the example board
	 */
	public TicTacToe(int[][] exampleBoard) {
		setBoard(exampleBoard);
	}

	/**
	 * Check status.
	 *
	 * @return the int
	 */
	public int checkStatus() {
		int diagCheck = checkDiagonal();
		if(diagCheck != -1) 
			return diagCheck;
		int rowCheck = checkRows();
		if(rowCheck != -1) 
			return rowCheck;
		int colCheck = checkCols();
		if(colCheck != -1) 
			return colCheck;
		if(this.getEmptyPositions().size() == 0)//still have result = -1 and want to check if game is over and draw was reached
			return 0;
		return -1;
	}
	
	/**
	 * Check diagonal.
	 *
	 * @return the int
	 */
	public int checkDiagonal() {
		//check diagonals
		int[] diagonal1 = new int[dimension];
		int[] diagonal2 = new int[dimension];
		
		for(int i = 0; i < dimension; i++) {
			diagonal1[i] = board[i][i];
			diagonal2[i] = board[i][dimension-1-i];
		}
		if(Arrays.stream(diagonal1).distinct().count() == 1 && diagonal1[0] != 0) 
			return diagonal1[0];
		else if(Arrays.stream(diagonal2).distinct().count() == 1 && diagonal2[0] != 0) 
			return diagonal2[0];
		else 
			return -1;
	}
	
	/**
	 * Check rows.
	 *
	 * @return the int
	 */
	public int checkRows() {
		for(int i = 0; i < dimension; i++) {
			int[] tempRow = board[i];
			if(Arrays.stream(tempRow).distinct().count() == 1 && tempRow[0] != 0) 
				return tempRow[0];
		}
		return -1;
	}
	
	/**
	 * Check cols.
	 *
	 * @return the int
	 */
	public int checkCols() {
		for(int i = 0; i < dimension; i++) {
			int[] tempCol = new int[dimension];
			for(int j = 0; j < dimension; j++) {
				tempCol[j] = board[j][i];
			}
			if(Arrays.stream(tempCol).distinct().count() == 1 && tempCol[0] != 0) 
				return tempCol[0];
		}
		return -1;
	}

	/**
	 * Gets the empty positions.
	 *
	 * @return the empty positions
	 */
	@Override
	public List<Position> getEmptyPositions() {
		List<Position> emptyPositions = new ArrayList<>();
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				if(board[i][j] == 0) {
					emptyPositions.add(new Position(i, j));
				}
			}
		}
		return emptyPositions;
	}
	
	/**
	 * Rotate board.
	 *
	 * @return the ilayout
	 */
	@Override
	public Ilayout rotateBoard() {
		TicTacToe rotated = new TicTacToe();
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				rotated.board[i][j] = this.board[dimension-j-1][i];
			}
		}
		Ilayout result = rotated;
		return result;
	}

	/**
	 * Execute move.
	 *
	 * @param playerNo the player no
	 * @param p the p
	 */
	@Override
	public void executeMove(int playerNo, Position p) {
		board[p.getRow()][p.getCol()] = playerNo;
		setStatus(this.checkStatus());
	}

	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public int[][] getBoard() {
		return board;
	}


	/**
	 * Sets the board.
	 *
	 * @param board the new board
	 */
	public void setBoard(int[][] board) {
		this.board = board;
	}


	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}


	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Gets the dimension.
	 *
	 * @return the dimension
	 */
	public int getDimension() {
		return dimension;
	}

	/**
	 * Copy of.
	 *
	 * @return the ilayout
	 */
	@Override
	public Ilayout copyOf() {
		TicTacToe copy = new TicTacToe();
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				copy.board[i][j] = this.board[i][j];
			}
		}
		Ilayout result = copy;
		return result;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		String result = "";
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				result += this.board[i][j];
			}
			result += "\n";
		}
		return result;
	}
	
	/**
	 * To string solution.
	 *
	 * @return the string
	 */
	@Override
	public String toStringSolution() {
		String result = "";
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				result += this.board[i][j] == 1? 'X': this.board[i][j] == 2? 'O': '-';
			}
			result += "\n";
		}
		return result;
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
		TicTacToe that = (TicTacToe) o;
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				if(that.board[i][j] != this.board[i][j]) return false;
			}
		}
		return true;
	}

}
