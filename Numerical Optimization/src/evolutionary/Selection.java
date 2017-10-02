package evolutionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import utils.IndividualComparator;

public class Selection {

	static ThreadLocalRandom rand = ThreadLocalRandom.current();
	static Population population;
	
	// Parent selection

	// selects parent uniformly randomly
	public static List<Integer> selectParentsRandom(int popSize, int numOfParents) {

		List<Integer> parentId = new ArrayList<Integer>();
		for (int i = 0; i < numOfParents; i++) {
			parentId.add(rand.nextInt(0, popSize));
		}

		return parentId;

	}

	// Survivor selection

	// (μ,λ)
	public static List<Individual> commaStrategy(List<Individual> indvs, int mu) {
		Collections.sort(indvs, new IndividualComparator());
		return indvs.subList(0, mu - 1);

	}
	
	public static List<Individual> plusStrategy(List<Individual> indvs, int mu) {
		Collections.sort(indvs, new IndividualComparator());
		return indvs.subList(0, mu);

	}
}
