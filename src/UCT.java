import java.util.Collections;
import java.util.Comparator;

// TODO: Auto-generated Javadoc
/**
 * The Class UCT.
 */
public class UCT {
	
	/**
	 * Uct value.
	 *
	 * @param parentVisit the parent visit
	 * @param nodeWinScore the node win score
	 * @param nodeVisit the node visit
	 * @return the double
	 */
	public static double uctValue(int parentVisit, double nodeWinScore, int nodeVisit) {
		if(nodeVisit == 0) { //Both terms will have 0 in the denominator so will tend to infinity (actually non-real)
			return Integer.MAX_VALUE;
		}
		return ((double)nodeWinScore/(double)nodeVisit) + 1.41*Math.sqrt(Math.log(parentVisit)/(double) nodeVisit);
	}
	
	/**
	 * Max UCT.
	 *
	 * @param currentState the current state
	 * @return the monte carlo tree search. state
	 */
	public static MonteCarloTreeSearch.State maxUCT(MonteCarloTreeSearch.State currentState) {
		int parentVisit = currentState.getVisits();
		return Collections.max(currentState.getChildren(), Comparator.comparing(c -> uctValue(parentVisit, c.getWinScore(), c.getVisits())));
	}
}
