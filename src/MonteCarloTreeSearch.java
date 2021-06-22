import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * The Class MonteCarloTreeSearch.
 */
public class MonteCarloTreeSearch {
	
	/**
	 * The Class State.
	 */
	static class State {
		
		/** The layout. */
		private Ilayout layout;
		
		/** The player no. */
		private int playerNo; 
		
		/** The visits. */
		private int visits = 0;
		
		/** The win score. */
		private double winScore = 0.0;
		
		/** The parent. */
		private State parent;
		
		/** The children. */
		private List<State> children = new ArrayList<State>();
		
		/**
		 * Instantiates a new state.
		 *
		 * @param layout the layout
		 * @param parent the parent
		 * @param playerNo the player no
		 */
		public State(Ilayout layout, State parent, int playerNo) {
			setLayout(layout);
			setParent(parent);
			setPlayerNo(playerNo);
		}
		
		/**
		 * Instantiates a new state.
		 *
		 * @param layout the layout
		 * @param parent the parent
		 */
		public State(Ilayout layout, State parent) {
			setLayout(layout);
			setParent(parent);
			setPlayerNo(layout.getEmptyPositions().size()%2 == 0? 1: 2); //If there are an even number of X's and O's on the board, then player 1 will be next. Otherwise player 2 is next. 
		}
		
		/**
		 * Gets the opponent.
		 *
		 * @return the opponent
		 */
		public int getOpponent() {
			return 3-this.playerNo;
		}
		
		/**
		 * Increment visit.
		 */
		public void incrementVisit() {
			setVisits(++this.visits);
		}
		
		/**
		 * Adds the to score.
		 *
		 * @param x the x
		 */
		public void addToScore(double x) {
			double newScore = this.winScore + x;
			setWinScore(newScore);
		}
		
		/**
		 * Toggle player.
		 */
		public void togglePlayer() {
			setPlayerNo(3-playerNo);
		}
		
		/**
		 * Expand state.
		 */
		public void expandState() {
			List<State> children = successors(this);
			this.setChildren(children);
		}
		
		/**
		 * Random playout.
		 */
		public void randomPlayout() {
			List<Position> possibleMoves = this.layout.getEmptyPositions(); 
			int randomChoice = (int) (Math.random()*possibleMoves.size());
			this.layout.executeMove(this.playerNo, possibleMoves.get(randomChoice));
		}
		
		/**
		 * Child with max score.
		 *
		 * @return the state
		 */
		public State childWithMaxScore() {
			return Collections.max(this.getChildren(), Comparator.comparing(c -> c.getWinScore()));
		}
		
		/**
		 * Gets the layout.
		 *
		 * @return the layout
		 */
		public Ilayout getLayout() {
			return layout;
		}

		/**
		 * Sets the layout.
		 *
		 * @param layout the new layout
		 */
		public void setLayout(Ilayout layout) {
			this.layout = layout;
		}

		/**
		 * Gets the player no.
		 *
		 * @return the player no
		 */
		public int getPlayerNo() {
			return playerNo;
		}

		/**
		 * Sets the player no.
		 *
		 * @param playerNo the new player no
		 */
		public void setPlayerNo(int playerNo) {
			this.playerNo = playerNo;
		}

		/**
		 * Gets the visits.
		 *
		 * @return the visits
		 */
		public int getVisits() {
			return visits;
		}

		/**
		 * Sets the visits.
		 *
		 * @param visits the new visits
		 */
		public void setVisits(int visits) {
			this.visits = visits;
		}

		/**
		 * Gets the win score.
		 *
		 * @return the win score
		 */
		public double getWinScore() {
			return winScore;
		}

		/**
		 * Sets the win score.
		 *
		 * @param winScore the new win score
		 */
		public void setWinScore(double winScore) {
			this.winScore = winScore;
		}

		/**
		 * Gets the parent.
		 *
		 * @return the parent
		 */
		public State getParent() {
			return parent;
		}

		/**
		 * Sets the parent.
		 *
		 * @param parent the new parent
		 */
		public void setParent(State parent) {
			this.parent = parent;
		}
		
		/**
		 * Gets the children.
		 *
		 * @return the children
		 */
		public List<State> getChildren() {
			return children;
		}

		/**
		 * Sets the children.
		 *
		 * @param children the new children
		 */
		public void setChildren(List<State> children) {
			this.children = children;
		}

		/**
		 * Equals.
		 *
		 * @param o the o
		 * @return true, if successful
		 */
		@Override
		public boolean equals(Object o){
			if (getClass() != o.getClass()) return false;
			State that = (State) o;
			return this.layout.equals(that.layout);
		}
		
		/**
		 * To string.
		 *
		 * @return the string
		 */
		public String toString() {
			return "player: " + playerNo + ", board:\n" + this.layout.toString() + "\n parent board:\n" + this.parent.layout.toString();
		}
	}

	/** The max duration. */
	private int maxDuration;
	
	/** The win value. */
	private int winValue;
	
	private double C;
	
	/**
	 * Instantiates a new monte carlo tree search.
	 *
	 * @param maxDuration the max duration
	 * @param winValue the win value
	 */
	public MonteCarloTreeSearch(int maxDuration, int winValue, double C) {
		setMaxDuration(maxDuration);
		setWinValue(winValue);
		setC(C);
	}
	
	/**
	 * Successors.
	 *
	 * @param n the n
	 * @return the list
	 */
	public static List<State> successors(State n){
		List<State> children = new ArrayList<>();
		List<Position> possibleMove = n.layout.getEmptyPositions(); 
		for(Position p: possibleMove) 
		{
				State newState = new State(n.layout.copyOf(), n, n.getOpponent());
				newState.layout.executeMove(newState.getPlayerNo(), p);
				if(isUnique(children, newState))
					children.add(newState);
		}
		return children;
	}
	
	/**
	 * Checks if is unique.
	 *
	 * @param list the list
	 * @param newState the new state
	 * @return true, if is unique
	 */
	public static boolean isUnique(List<State> list, State newState) {
		Iterator<State> stateIt = list.iterator();
		Ilayout rotatedLayout = newState.getLayout();
		while(stateIt.hasNext()) {
			State nextState = stateIt.next();
			for(int i = 0; i < 4; i++) {
				if(i > 0) { 
					rotatedLayout = rotatedLayout.rotateBoard();
				};
				if(nextState.layout.equals(rotatedLayout)) return false;
			}
		}
		return true;
	}

	/**
	 * Solve.
	 *
	 * @param state the state
	 * @return the state
	 */
	public State solve(State state) {
		State currentState = state;
		currentState.setChildren(successors(currentState));//expand this State i.e. add the children
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start < maxDuration) {
        	//Step 1. Selection
        	State promisingState = selectPromisingState(currentState, this.C);
        	
        	//Step 2. Expansion
        	promisingState.expandState();
        	
        	//Successor loop
        	Iterator<State> stateIt = promisingState.getChildren().iterator();
        	while(stateIt.hasNext()) {
        		State nextPromising = stateIt.next();
        		
            	//Step 3. Simulation
        		int playoutResult = simulatePlayout(nextPromising);
        		
        		//Step 4. Back Propagation
        		backPropagation(nextPromising, playoutResult);
        	}
        }
        State winner = currentState.childWithMaxScore();
        return winner;
	}
	
	/**
	 * Select promising state.
	 *
	 * @return the state
	 */
	public static State selectPromisingState(State currentState, double C) {
		while(currentState.getChildren().size() != 0) {
			UCT uct = new UCT(currentState);
			currentState = uct.maxUCT(C);
		}
		return currentState;
	}
	
		/**
	 * Simulate playout.
	 *
	 * @param state the state
	 * @return the int
	 */
	public int simulatePlayout(State state) {
		int playoutStatus = state.layout.getStatus();
		if(playoutStatus != -1) // Means that the game is over
			return playoutStatus;
		while(playoutStatus == -1) { //while game is not over
			state.togglePlayer();
			state.randomPlayout();
			playoutStatus = state.layout.getStatus();
		}
		return playoutStatus;
	}
	
	/**
	 * Back propagation.
	 *
	 * @param state the state
	 * @param playoutResult the playout result
	 */
	public void backPropagation(State state, int playoutResult) {
		State currentState = state;
		while(currentState != null) { //propagate up to the root node
			currentState.incrementVisit();
			if(currentState.getPlayerNo() == playoutResult)
				currentState.addToScore(winValue);
			currentState = currentState.getParent();
		}
	}
	
	/**
	 * Gets the max duration.
	 *
	 * @return the max duration
	 */
	public int getMaxDuration() {
		return maxDuration;
	}

	/**
	 * Sets the max duration.
	 *
	 * @param maxDuration the new max duration
	 */
	public void setMaxDuration(int maxDuration) {
		this.maxDuration = maxDuration;
	}

	/**
	 * Gets the win value.
	 *
	 * @return the win value
	 */
	public int getWinValue() {
		return winValue;
	}

	/**
	 * Sets the win value.
	 *
	 * @param winValue the new win value
	 */
	public void setWinValue(int winValue) {
		this.winValue = winValue;
	}

	public double getC() {
		return C;
	}

	public void setC(double c) {
		C = c;
	}
	
}
