package evolutionary;

import java.util.Comparator;

public class IndividualComparator implements Comparator<Individual> {

	@Override
	public int compare(Individual indv1, Individual indv2) {

		if (indv1.getFitness() < indv2.getFitness()) {
			return 1;
		} else if (indv1.getFitness() > indv2.getFitness()) {
			return -1;
		} else {
			return 0;
		}

	}

}
