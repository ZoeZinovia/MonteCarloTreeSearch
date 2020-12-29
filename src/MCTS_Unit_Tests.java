import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class MCTS_Unit_Tests {
	
	//State tests

	@Test
	void testStateGetOpponent() {
		MonteCarloTreeSearch.State ex1 = new MonteCarloTreeSearch.State(null, null, 1); 
		assertEquals(2, ex1.getOpponent());
		
		MonteCarloTreeSearch.State ex2 = new MonteCarloTreeSearch.State(null, null, 2); 
		assertEquals(1, ex2.getOpponent());	
	}
	
	@Test
	void testStateIncrementVisit() {
		MonteCarloTreeSearch.State ex1 = new MonteCarloTreeSearch.State(null, null, 1); 
		ex1.incrementVisit();
		assertEquals(1, ex1.getVisits());
		ex1.incrementVisit();
		ex1.incrementVisit();
		assertEquals(3, ex1.getVisits());
		
	}
	
	@Test
	void testStateAddToScore() {
		MonteCarloTreeSearch.State ex1 = new MonteCarloTreeSearch.State(null, null, 1); 
		ex1.addToScore(10);
		assertEquals(10, ex1.getWinScore());
		ex1.addToScore(10);
		ex1.addToScore(5);
		assertEquals(25, ex1.getWinScore());
	}
	
	@Test
	void testGetChildWithMaxScore() {
		int[][] ex1 = new int[][]{
									{1, 1, 2},
									{2, 2, 1},
									{0, 1, 0}
						   		  };	

		int[][] ex2 = new int[][]{
									{1, 1, 2},
									{0, 2, 1},
									{2, 1, 0}
								  };

		MonteCarloTreeSearch.State s1 = new MonteCarloTreeSearch.State(new TicTacToe(ex1), null, 2);
		MonteCarloTreeSearch.State s2 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 2);
		MonteCarloTreeSearch.State s3 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 2);
		MonteCarloTreeSearch.State s4 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 2);

		s2.setWinScore(2);
		s3.setWinScore(5);
		s4.setWinScore(3);
		
		s2.setParent(s1);s3.setParent(s1);s4.setParent(s1);
		s1.getChildren().add(s2); s1.getChildren().add(s3); s1.getChildren().add(s4); 
		
		assertEquals(s3, s1.childWithMaxScore());
		
		s3.setWinScore(1);
		assertEquals(s4, s1.childWithMaxScore());
	}
	
	//TicTacToeTests
	
	@Test
	void testTicTacToeConstuctor() {
		TicTacToe ex1 = new TicTacToe();
		assertEquals(ex1.toString(), "000\n"+
									 "000\n"+	
									 "000\n");
		assertEquals(-1, ex1.getStatus());
	}
	
	@Test
	void testTicTactToeDiagonalCheck() {
		int[][] ex1 = new int[][]{
									{0, 0, 1},
									{0, 1, 2},
									{1, 0, 2}
								 };
								 
		TicTacToe ttt1 = new TicTacToe(ex1);						 
		assertEquals(1, ttt1.checkDiagonal());
		
		int[][] ex2 = new int[][]{
			{0, 0, 2},
			{0, 1, 2},
			{1, 0, 2}
		 };
		 
		TicTacToe ttt2 = new TicTacToe(ex2);						 
		assertEquals(-1, ttt2.checkDiagonal());
		
	}
	
	@Test
	void testTicTactToeRowCheck() {
		int[][] ex1 = new int[][]{
									{2, 2, 2},
									{0, 1, 1},
									{1, 0, 2}
								 };
								 
		TicTacToe ttt1 = new TicTacToe(ex1);						 
		assertEquals(2, ttt1.checkRows());
		
		int[][] ex2 = new int[][]{
			{0, 0, 2},
			{0, 1, 2},
			{1, 0, 2}
		 };
		 
		TicTacToe ttt2 = new TicTacToe(ex2);						 
		assertEquals(-1, ttt2.checkRows());
	}
	
	@Test
	void testTicTactToeColsCheck() {
		int[][] ex1 = new int[][]{
									{1, 1, 1},
									{0, 2, 2},
									{1, 0, 2}
								 };
								 
		TicTacToe ttt1 = new TicTacToe(ex1);						 
		assertEquals(-1, ttt1.checkCols());
		
		int[][] ex2 = new int[][]{
			{0, 0, 2},
			{0, 1, 2},
			{1, 0, 2}
		 };
		 
		TicTacToe ttt2 = new TicTacToe(ex2);						 
		assertEquals(2, ttt2.checkCols());
	}
	
	@Test
	void testGetEmptyPOsitions() {
		int[][] ex1 = new int[][]{
									{1, 1, 1},
									{0, 2, 2},
									{1, 0, 2}
								 };
								 
		TicTacToe ttt1 = new TicTacToe(ex1);
		List<Position> list1 = new ArrayList<>();
		list1.add(new Position(1, 0));
		list1.add(new Position(2, 1));
		assertEquals(list1, ttt1.getEmptyPositions());
		
		int[][] ex2 = new int[][]{
			{0, 0, 0},
			{1, 1, 2},
			{1, 1, 2}
		 };
		 
		TicTacToe ttt2 = new TicTacToe(ex2);						 
		List<Position> list2 = new ArrayList<>();
		list2.add(new Position(0, 0));
		list2.add(new Position(0, 1));
		list2.add(new Position(0, 2));
		assertEquals(list2, ttt2.getEmptyPositions());
	}
	
	@Test
	void testTicTacToeEquals() {
		int[][] ex1 = new int[][]{
			{1, 1, 1},
			{0, 2, 2},
			{1, 0, 2}
		 };
		 
		TicTacToe ttt1 = new TicTacToe(ex1);
		
		int[][] ex2 = new int[][]{
		{0, 0, 0},
		{1, 1, 2},
		{1, 1, 2}
		};
		
		TicTacToe ttt2 = new TicTacToe(ex2);	
		TicTacToe ttt3 = new TicTacToe(ex2);

		assertFalse(ttt1.equals(ttt2));
		assertTrue(ttt2.equals(ttt3));
		assertFalse(ttt1.equals(ttt3));
	}
	
	@Test
	void testTicTacToeRotateBoard() {
		int[][] ex1 = new int[][]{
									{1, 1, 1},
									{0, 2, 2},
									{1, 0, 2}
								 };
		 
		TicTacToe ttt1 = new TicTacToe(ex1);
		
		int[][] rotated1 = new int[][]{
									{1, 0, 1},
									{0, 2, 1},
									{2, 2, 1}
								 };
						
		TicTacToe tttr1 = new TicTacToe(rotated1);
		
		
		int[][] ex2 = new int[][]{
									{0, 0, 0},
									{1, 1, 2},
									{1, 1, 2}
								 };
		
		TicTacToe ttt2 = new TicTacToe(ex2);	

		int[][] rotated2 = new int[][]{
									{1, 1, 0},
									{1, 1, 0},
									{2, 2, 0}
								 };

		 TicTacToe tttr2 = new TicTacToe(rotated2);	

		 assertEquals(tttr1, ttt1.rotateBoard());
		 assertEquals(tttr2, ttt2.rotateBoard());
	}
	
	//MCTS Tests
	
	@Test
	void testMCTSisUnique() {
		int[][] ex1 = new int[][]{
									{1, 0, 0},
									{0, 0, 0},
									{1, 0, 0}
								 };
								 
		MonteCarloTreeSearch.State s1 = new MonteCarloTreeSearch.State(new TicTacToe(ex1), null, 1);
								 
		int[][] ex2 = new int[][]{
									{1, 1, 0},
									{0, 2, 0},
									{0, 0, 0}
								 };		
								 
		MonteCarloTreeSearch.State s2 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 1);
								
		int[][] ex3 = new int[][]{
									{1, 0, 2},
									{0, 1, 1},
									{1, 0, 0}
								 };	
		 
		MonteCarloTreeSearch.State s3 = new MonteCarloTreeSearch.State(new TicTacToe(ex3), null, 1);
								 
		int[][] ex4 = new int[][]{
									{1, 0, 1},
									{0, 0, 0},
									{0, 0, 0}
								 };		
		MonteCarloTreeSearch.State s4 = new MonteCarloTreeSearch.State(new TicTacToe(ex4), null, 1);
		
		int[][] ex5 = new int[][]{
									{0, 0, 1},
									{0, 1, 0},
									{1, 0, 0}
								 };		
		MonteCarloTreeSearch.State s5 = new MonteCarloTreeSearch.State(new TicTacToe(ex5), null, 1);
		
		List<MonteCarloTreeSearch.State> list1 = new ArrayList<>();
		list1.add(s2);
		list1.add(s3);
		list1.add(s4);
		
		
		List<MonteCarloTreeSearch.State> list2 = new ArrayList<>();
		list2.add(s3);
		list2.add(s4);
		list2.add(s5);
				
		assertFalse(MonteCarloTreeSearch.isUnique(list1, s1));
		assertTrue(MonteCarloTreeSearch.isUnique(list1, s5));
		assertFalse(MonteCarloTreeSearch.isUnique(list2, s5));
	}
	
	@Test
	void testGetChildren() {
		int[][] ex1 = new int[][]{
									{1, 1, 2},
									{0, 2, 1},
									{0, 1, 0}
								 };		
		 
		 MonteCarloTreeSearch.State s1 = new MonteCarloTreeSearch.State(new TicTacToe(ex1), null, 1);
		 
		int[][] ex11 = new int[][]{
										{1, 1, 2},
										{2, 2, 1},
										{0, 1, 0}
									 };	
								 
	    int[][] ex12 = new int[][]{
										{1, 1, 2},
										{0, 2, 1},
										{2, 1, 0}
									 };	
									 
		int[][] ex13 = new int[][]{
										{1, 1, 2},
										{0, 2, 1},
										{0, 1, 2}
									 };	
		 
		 MonteCarloTreeSearch.State child11 = new MonteCarloTreeSearch.State(new TicTacToe(ex11), null, 1);	
		 MonteCarloTreeSearch.State child12 = new MonteCarloTreeSearch.State(new TicTacToe(ex12), null, 1);
		 MonteCarloTreeSearch.State child13 = new MonteCarloTreeSearch.State(new TicTacToe(ex13), null, 1);
		 
		 List<MonteCarloTreeSearch.State> children1 = new ArrayList<>();
		 children1.add(child11);
		 children1.add(child12);
		 children1.add(child13);
	
		 assertEquals(children1, MonteCarloTreeSearch.successors(s1));
		
		 int[][] ex2 = new int[][]{
									{1, 0, 2},
									{0, 1, 1},
									{1, 0, 0}
								 };	
						
		MonteCarloTreeSearch.State s2 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 2);
		
		int[][] ex21 = new int[][]{
									{1, 1, 2},
									{0, 1, 1},
									{1, 0, 0}
								 };	
	 
		int[][] ex22 = new int[][]{
									{1, 0, 2},
									{1, 1, 1},
									{1, 0, 0}
								 };	
				 
		int[][] ex23 = new int[][]{
									{1, 0, 2},
									{0, 1, 1},
									{1, 1, 0}
								 };	
								 
		int[][] ex24 = new int[][]{
									{1, 0, 2},
									{0, 1, 1},
									{1, 0, 1}
								 };	
		
		MonteCarloTreeSearch.State child21 = new MonteCarloTreeSearch.State(new TicTacToe(ex21), null, 1);	
		MonteCarloTreeSearch.State child22 = new MonteCarloTreeSearch.State(new TicTacToe(ex22), null, 1);
		MonteCarloTreeSearch.State child23 = new MonteCarloTreeSearch.State(new TicTacToe(ex23), null, 1);
		MonteCarloTreeSearch.State child24 = new MonteCarloTreeSearch.State(new TicTacToe(ex24), null, 1);
		
		List<MonteCarloTreeSearch.State> children2 = new ArrayList<>();
		children2.add(child21);
		children2.add(child22);
		children2.add(child23);
		children2.add(child24);

		assertEquals(children2, MonteCarloTreeSearch.successors(s2));
	}
	
	@Test
	void testSelectPromisingState() {
		int[][] ex1 = new int[][]{
									{1, 1, 2},
									{2, 2, 1},
									{0, 1, 0}
						   		  };	

		int[][] ex2 = new int[][]{
									{1, 1, 2},
									{0, 2, 1},
									{2, 1, 0}
								  };

		//Test1
		MonteCarloTreeSearch.State s1 = new MonteCarloTreeSearch.State(new TicTacToe(ex1), null, 2);
		MonteCarloTreeSearch.State s2 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 2);
		MonteCarloTreeSearch.State s3 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 2);
		MonteCarloTreeSearch.State s4 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 2);
		
		s1.setVisits(7);
		
		s2.setWinScore(2); s2.setVisits(3);
		s3.setWinScore(2); s3.setVisits(5);
		s4.setWinScore(2); s4.setVisits(4);
		
		s2.setParent(s1);s3.setParent(s2);s4.setParent(s3);
		s1.getChildren().add(s2); s2.getChildren().add(s3); s3.getChildren().add(s4); 
		
		assertEquals(s4,MonteCarloTreeSearch.selectPromisingState(s1, 1.41));
		
		//Test2
		MonteCarloTreeSearch.State s5 = new MonteCarloTreeSearch.State(new TicTacToe(ex1), null, 2);
		MonteCarloTreeSearch.State s6 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 2);
		MonteCarloTreeSearch.State s7 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 2);
		MonteCarloTreeSearch.State s8 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 2);
		
		s5.setVisits(7);
		
		s6.setWinScore(2); s6.setVisits(3);
		s7.setWinScore(2); s7.setVisits(5);
		s8.setWinScore(2); s8.setVisits(4);
		
		s6.setParent(s5);s7.setParent(s5);s8.setParent(s7);
		s5.getChildren().add(s6); s5.getChildren().add(s7); s7.getChildren().add(s8); 
		
		assertEquals(s6, MonteCarloTreeSearch.selectPromisingState(s5, 1.41));
	}
	
	
	// UCT Tests
	
	@Test
	void testUctValue() {
		int[][] ex1 = new int[][]{
									{1, 1, 2},
									{2, 2, 1},
									{0, 1, 0}
						   		  };	
	 
		int[][] ex2 = new int[][]{
									{1, 1, 2},
									{0, 2, 1},
									{2, 1, 0}
								  };	
		
		MonteCarloTreeSearch.State s1 = new MonteCarloTreeSearch.State(new TicTacToe(ex1), null, 2);
		MonteCarloTreeSearch.State s2 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 2);
		
		s1.setVisits(3);//parent state
		s2.setParent(s1); 
		
		s2.setWinScore(1); s2.setVisits(2);
		assertEquals(1.545, UCT.uctValue(s2.getParent().getVisits(), s2.getWinScore(), s2.getVisits(), 1.41), 0.001);
		
		s1.setVisits(15);//parent state
		s2.setWinScore(3); s2.setVisits(10);
		assertEquals(1.034, UCT.uctValue(s2.getParent().getVisits(), s2.getWinScore(), s2.getVisits(), 1.41), 0.001);
		
		s1.setVisits(7);//parent state
		s2.setWinScore(2); s2.setVisits(5);
		assertEquals(1.280, UCT.uctValue(s2.getParent().getVisits(), s2.getWinScore(), s2.getVisits(), 1.41), 0.001);
	}
	
	@Test
	void testMaxUCT() {
		int[][] ex1 = new int[][]{
									{1, 1, 2},
									{2, 2, 1},
									{0, 1, 0}
						   		  };	
	 
		int[][] ex2 = new int[][]{
									{1, 1, 2},
									{0, 2, 1},
									{2, 1, 0}
								  };
		
		MonteCarloTreeSearch.State s1 = new MonteCarloTreeSearch.State(new TicTacToe(ex1), null, 2);
		MonteCarloTreeSearch.State s2 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 2);
		MonteCarloTreeSearch.State s3 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 2);
		MonteCarloTreeSearch.State s4 = new MonteCarloTreeSearch.State(new TicTacToe(ex2), null, 2);
		List<MonteCarloTreeSearch.State> children = new ArrayList<>();
		children.add(s2);children.add(s3); children.add(s4);
		
		s2.setParent(s1);s3.setParent(s1);s4.setParent(s1);
		s1.setChildren(children); 
		
		s1.setVisits(7);
		
		s2.setWinScore(2); s2.setVisits(3);
		s3.setWinScore(2); s3.setVisits(5);
		s4.setWinScore(2); s4.setVisits(4);
		UCT uct1 = new UCT(s1);
		assertEquals(s2, uct1.maxUCT(1.41));
		
		s2.setWinScore(1); s2.setVisits(3);
		s3.setWinScore(2); s3.setVisits(5);
		s4.setWinScore(3); s4.setVisits(4);
		assertEquals(s4, uct1.maxUCT(1.41));
		
		s2.setWinScore(1); s2.setVisits(3);
		s3.setWinScore(4); s3.setVisits(5);
		s4.setWinScore(3); s4.setVisits(4);
		assertEquals(s3, uct1.maxUCT(1.41));
	}
	
	@Test
	void testSolve() {
		int[][] ex1 = new int[][]{
									{1, 1, 2},
									{2, 1, 1},
									{0, 2, 0}
						   		  };	
  		int[][] sol1 = new int[][]{
									{1, 1, 2},
									{2, 1, 1},
									{0, 2, 1}
						   		  };		
						   		  
		int[][] sol2 = new int[][]{
									{1, 1, 2},
									{2, 1, 1},
									{2, 2, 0}
								  };	
						   		  
	    MonteCarloTreeSearch.State s1 = new MonteCarloTreeSearch.State(new TicTacToe(ex1), null, 2); //player 1 to play next
	    MonteCarloTreeSearch.State s2 = new MonteCarloTreeSearch.State(new TicTacToe(ex1), null, 1); //player 2 to play next
	    
	    MonteCarloTreeSearch.State solution1 = new MonteCarloTreeSearch.State(new TicTacToe(sol1), null, 1); //player 2 to play next
	    MonteCarloTreeSearch.State solution2 = new MonteCarloTreeSearch.State(new TicTacToe(sol2), null, 2); //player 1 to play next
	    
	    MonteCarloTreeSearch mcts = new MonteCarloTreeSearch(3000, 10, 1.41);

	    assertEquals(solution1, mcts.solve(s1));
	    assertEquals(solution2, mcts.solve(s2));
	}

}
