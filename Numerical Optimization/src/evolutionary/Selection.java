package evolutionary;

import java.util.Collections;
import java.util.List;

public class Selection {

	// Parent selection

	// Survivor selection

	// (μ,λ)
	public static List<Individual> commaStrategy(List<Individual> indvs, int mu) {
		Collections.sort(indvs, new IndividualComparator());
		return indvs.subList(0, mu - 1);

	}
}
