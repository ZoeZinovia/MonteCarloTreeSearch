import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		//Read from console
		Scanner sc = new Scanner(System.in);
		String[] lines = new String[3];
		int k = 0;
		while(k < 3) {
			lines[k++] = sc.next();
		}
		sc.close();
		
		//Create board
		int[][] inputBoard = new int[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				try{
					inputBoard[i][j] = lines[i].charAt(j) == 'X'? 1: lines[i].charAt(j) == 'O'? 2: lines[i].charAt(j) == '-'? 0: null; //X is player 1 and O is player 2, 0 is empty
				} catch(NullPointerException e) {
					throw new IllegalArgumentException("An incompatible value was entered. Please check format.");
				}
			}
		}
		
		//Solve round
		MonteCarloTreeSearch MCTS = new MonteCarloTreeSearch(10000, 10, 1.41); //input is the maximum time that the algorithm can run (in milliseconds) and the points added for a win
	    MonteCarloTreeSearch.State initialState = new MonteCarloTreeSearch.State(new TicTacToe(inputBoard), null); //Can input the current player or program calculates it. player 2 to play next. Will need to be changed on who starts the game
	    MonteCarloTreeSearch.State solution = MCTS.solve(initialState);
	    
	    //Print solution
	    System.out.println(solution.getLayout().toStringSolution());
	    int result = solution.getLayout().getStatus();
	    if(result != -1) {
		   String outcome = result == 0? "The game has ended in a draw." : "Player " + result + " has won!";
		   System.out.println(outcome);
	    }
	}
}
