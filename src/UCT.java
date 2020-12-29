import java.util.Collections;
import java.util.Comparator;


/**
 * The Class UCT.
 */
public class UCT {

	private MonteCarloTreeSearch.State state;
	
	
	public UCT(MonteCarloTreeSearch.State state) {
		this.state = state;
	}
	
	/**
	 * Uct value.
	 *
	 * @param parentVisit the parent visit
	 * @param nodeWinScore the node win score
	 * @param nodeVisit the node visit
	 * @return the double
	 */
	public static double uctValue(int parentVisit, double nodeWinScore, int nodeVisit, double C) {
		if(nodeVisit == 0) { //Both terms will have 0 in the denominator so will tend to infinity (actually non-real)
			return Integer.MAX_VALUE;
		}
		return ((double)nodeWinScore/(double)nodeVisit) + C*Math.sqrt(Math.log(parentVisit)/(double) nodeVisit);
	}
	
	/**
	 * Max UCT.
	 *
	 * @return the monte carlo tree search. state
	 */
	public MonteCarloTreeSearch.State maxUCT(double C) {
		int parentVisit = state.getVisits();
		return Collections.max(state.getChildren(), Comparator.comparing(c -> uctValue(parentVisit, c.getWinScore(), c.getVisits(), C)));
	}
}
